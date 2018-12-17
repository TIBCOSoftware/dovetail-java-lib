/*
* Copyright © 2018. TIBCO Software Inc.
* This file is subject to the license terms contained
* in the license file that is distributed with this file.
 */
package com.tibco.dovetail.core.runtime.engine;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.antlr.v4.runtime.tree.ParseTree;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.tibco.dovetail.core.runtime.activity.IActivity;
import com.tibco.dovetail.core.runtime.expression.MapExprResolver;
import com.tibco.dovetail.core.runtime.flow.ActivityRegistry;
import com.tibco.dovetail.core.runtime.flow.ActivityTask;
import com.tibco.dovetail.core.runtime.flow.AttributeMapping;
import com.tibco.dovetail.core.runtime.flow.Link;
import com.tibco.dovetail.core.runtime.flow.Node;
import com.tibco.dovetail.core.runtime.flow.ReplyData;
import com.tibco.dovetail.core.runtime.flow.ReplyHandler;
import com.tibco.dovetail.core.runtime.flow.TransactionFlow;
import com.tibco.dovetail.core.runtime.flow.Mapping.ValueMappingType;
import com.tibco.dovetail.core.runtime.services.IContainerService;
import com.tibco.dovetail.core.runtime.services.ILogService;
import com.tibco.dovetail.core.runtime.util.JsonUtil;

import net.minidev.json.JSONArray;

public class FlowEngine {
	private TransactionFlow flow;
    private IContainerService container;
    private ILogService logger;
    private Map<String, Object> flowInputs;
    private Scope scope;
    private ReplyHandler replyHandler;
	
    public FlowEngine (TransactionFlow flow){
        this.flow = flow;
        scope = new Scope();
        replyHandler = new ReplyHandler();
    }
    
	public ReplyData execute(Context context) {
        try {
            this.container = context.getContainerService();
            logger = this.container.getLogService();
            
            flowInputs = context.getInputs();
            flowInputs.forEach((k, v) -> scope.addVariable("$flow", k, v));

            Node root = flow.getRoot();
            runNode(root);
        }catch (Exception e){
            if(flow.getErrorHandler() != null){
                //TODO:
                throw e;
            } else {
            		throw e;
            }
        }

        return this.replyHandler.getReplyData();
    }

    private void runNode(Node n){
        //invoke model
        invokeTask(n.getTaskId());

        n.getToLinks().forEach(l -> {
            if(eval(l)){
                runNode(l.getNextNode());
            }
        });
    }

    private void invokeTask(String id){
    		logger.debug("Invoke activity " + id);
        ActivityTask activity = flow.getTask(id);
        if(activity == null)
            return;
        
        Context context = null;
        if(activity.isIteratorTask()) {
        		//get iterate object
        		Object iterate = readValue(activity.getIterateField(), scope);
        		if(iterate != null) {
        			JSONArray vals = ((DocumentContext)iterate).json();
        			DocumentContext output = JsonUtil.getJsonParser().parse("[]");
        			for (int i=0; i<vals.size(); i++) {
                		//set current scope variables
        				scope.addVariable("$current", "key", i);
        				scope.addVariable("$current", "value", vals.get(i));
        				
        				context = resolveInputs(activity);
        				context = evalActivity(activity.getActivityRef(), context);
        				
        				//TODO: should check accumulation flag from UI
        				if(context.getOutputs().size() > 0) {
        					String rootV = "output";
        					if(context.getOutputs().size() == 1) {
        						Object[] keys = context.getOutputs().keySet().toArray();
        						rootV = keys[0].toString();
        						Object v = context.getOutput(rootV);
        						if(v instanceof DocumentContext) {
        							DocumentContext objv = (DocumentContext)v;
        							output.add("$", objv.json());
        						} else {
        							output.add("$", v);
        						}
        					
        					} else {
        						//TODO: what is the root output name? default to output
        						context.getOutputs().forEach((k, v) -> {
	        						if(v instanceof DocumentContext) {
	        							DocumentContext objv = (DocumentContext)v;
	        							output.put("$", k, objv.json());
	        						} else {
	        							output.put("$", k, v);
	        						}
        						});
        					}
        					
        					scope.addVariable("$activity", id + "." + rootV, output);
        				}
        			}
        		}
        		
        }else {
        		context = resolveInputs(activity);
        		context = evalActivity(activity.getActivityRef(), context);
        		context.getOutputs().forEach((k, v) -> scope.addVariable("$activity", id + "." + k, v));
        }

    }

    private ContextImpl resolveInputs(ActivityTask activity){

        ContextImpl context = new ContextImpl();
        context.setContainerService(this.container);
        context.setReplyHandler(replyHandler);

        activity.getInputs().forEach((k, v) -> {
            if(v.getMappingType() == null){
                context.addInput(k, v.getMappingValue());
                return;
            }

            switch (v.getMappingType()){
                case literal:
                    context.addInput(k, v.getMappingValue());
                    break;
                case assign:
                		context.addInput(k, scope.getVariable(v.getMappingValue().toString()));
                		break;
                case expression:
                	   Object mapObj = v.getMappingValue();
                	   
                	   ParseTree mapping = (ParseTree)mapObj;
                	   Object ov = readValue(mapping, scope);
                	   DocumentContext edoc;
                	   if (ov instanceof DocumentContext) {
                		   edoc = (DocumentContext) ov;
                	   } else if (ov instanceof List) {
                		   edoc = JsonUtil.getJsonParser().parse("[]");
                		   JSONArray values = edoc.json();
                		   values.addAll((Collection<? extends Object>) ov);
                	   } else if (ov instanceof Object[]) {
                		   edoc = JsonUtil.getJsonParser().parse("[]");
                		   JSONArray values = edoc.json();
                		   for (Object o : (Object[])ov)
        						values.add(o);
                	   } else {
                		   edoc = JsonUtil.getJsonParser().parse(ov);
                	   }
                	   context.addInput(k, edoc);
                    break;
                case object:
                    Map<String, AttributeMapping> map = (Map<String, AttributeMapping>) v.getMappingValue();
                    DocumentContext doc = JsonUtil.getJsonParser().parse("{}");
                    doc = createObject(doc, scope, "$", map);
                    context.addInput(k, doc);
                    break;
                case array:
                	 	Map<String, Object> arrmap = (Map<String, Object>) v.getMappingValue();
                     DocumentContext arrdoc = JsonUtil.getJsonParser().parse("[]");
                     
                     List<LinkedHashMap<String, Object>> value = createArrayObject(scope, arrmap);
                     value.forEach(mv -> arrdoc.add(JsonPath.compile("$"), mv));
                    
                     context.addInput(k, arrdoc);
                    break;
                 default:
                    	throw new IllegalArgumentException("Unsupported mapping type " + v.getMappingType());
            }
        });

        return context;
    }

    @SuppressWarnings("unchecked")
	private List<LinkedHashMap<String, Object>> createArrayObject(Scope parent, Map<String, Object> mapping) {
    		Scope lclscope = new Scope(parent);
    		List<LinkedHashMap<String, Object>> entries = new ArrayList<LinkedHashMap<String, Object>>();
    		
    		String arrayType = mapping.get("type").toString();
    		List<Object> from = new ArrayList<Object>();
    		int loop = -1;
    		boolean isNewArray = false;
        if(arrayType.equals("foreach")) {
        		Object om = mapping.get("from");
        		if (om instanceof ParseTree) {
        			Object value = readValue((ParseTree)om, parent);
	        		if(value instanceof DocumentContext) {
	        			from = ((DocumentContext)value).json();
	        			loop = from.size();
	        		}
        		} else {
        			//NEWARRAY
            		loop = 1;
            		isNewArray = true;
        		}
        } else {
        		//NEWARRAY
        		loop = 1;
        		isNewArray = true;
        }
        
        for(int i=0; i<loop; i++) {
        	    if(!isNewArray)
        	    		lclscope.addVariable(Scope.SCOPE_LOCAL, "from", from.get(i));
    		
        		Object fields = mapping.get("fields");
        		
	    		if(fields != null && fields instanceof List) {
	    			LinkedHashMap<String, Object> entry = new LinkedHashMap<String, Object>();
	    			entries.add( entry);
	    			
	    			List<Object> lstfield = (List<Object>)fields;
	    			for(Object v: lstfield){
	    				Map<String, Object> fieldmap = (Map<String, Object>)v;
	    				String to = fieldmap.get("to").toString();
	    				String[] tokens = to.split("\\.");
	    				LinkedHashMap<String, Object> top = entry;
	    				for(int t=0; t<tokens.length-1; t++) {
	    					Object chld = top.get(tokens[t]);
	    					if(chld == null) {
	    						chld = new LinkedHashMap<String, Object>();
	    						top.put(tokens[t], chld);
	    					}
	    					top = (LinkedHashMap<String, Object>) chld;
	    				}
	    				
	    				if(fieldmap.get("type").toString().equals("primitive")) {
	    					Object mapObj = fieldmap.get("from");
	    					if(mapObj instanceof ParseTree) {
	    						top.put(tokens[tokens.length-1], readValue((ParseTree)fieldmap.get("from"), lclscope));
	    					} else {
	    						//literal mapping
	    						top.put(tokens[tokens.length-1], mapObj);
	    					}
	    				} else {
	    					//foreach and NEWARRAY
	    					List<LinkedHashMap<String, Object>> value = createArrayObject(lclscope, fieldmap);
	    					top.put(tokens[tokens.length-1], value);
	    				} 
	    			}	
	    		}
        }
        
		
		return entries;
    }
    
    private DocumentContext createObject(DocumentContext doc, Scope lclscope, String parentPath, Map<String, AttributeMapping> mapping){

        for(String k: mapping.keySet()){
            Object objmap = mapping.get(k).getMappingValue();
            ValueMappingType mapType = mapping.get(k).getMappingType();
            
            switch(mapType) { 
            case literal:
            		doc = setAttrValue(doc, parentPath, k, objmap);
            		break;
            case assign:
            		doc = setAttrValue(doc, parentPath, k, scope.getVariable(objmap.toString()));
            		break;
            case expression:
            		Object value = readValue((ParseTree)objmap, lclscope);
	            //check/create nested objects
	            if (value instanceof DocumentContext)
	            		doc = setAttrValue(doc, parentPath, k, ((DocumentContext)value).json());    
	            else 
	            		doc = setAttrValue(doc, parentPath, k, value);
	            break;
            case array:
            		List<LinkedHashMap<String, Object>> arrvalue = createArrayObject(scope, (Map<String, Object>)objmap);
            		doc = setAttrValue(doc, parentPath, k, arrvalue);
            default:
            		throw new RuntimeException("mapping type " + mapType + " is not supported");
            }
            /*
            if(objmap instanceof ParseTree) {
	            Object value = readValue((ParseTree)mapping.get(k), lclscope);
	            //check/create nested objects
	            if (value instanceof DocumentContext)
	            		doc = setAttrValue(doc, parentPath, k, ((DocumentContext)value).json());    
	            else 
	            		doc = setAttrValue(doc, parentPath, k, value);
            } else if (objmap instanceof Map) {
            		List<LinkedHashMap<String, Object>> value = createArrayObject(scope, (Map<String, Object>)objmap);
            		doc = setAttrValue(doc, parentPath, k, value);
            } else {
            	//literal mapping
            		doc = setAttrValue(doc, parentPath, k, objmap);
            }*/
        }

        return doc;
    }

    private DocumentContext setAttrValue(DocumentContext doc, String rootPath, String mapto, Object value) {
    		String[] objs= mapto.split("\\.");
    		String path = rootPath;
        for(int i=0; i<objs.length-1; i++){
            JSONArray o = doc.read(path + "." + objs[i]);
            if(o.get(0) == null) {
                doc.put(path, objs[i], new LinkedHashMap<>());
            }
            path = path + "." + objs[i];
        }
        
        doc.put(JsonPath.compile(path), objs[objs.length-1], value);
        return doc;
    }
    private boolean eval(Link condition){
    		logger.debug("eval link " + condition.getCondition());
        if(condition.getCondition() == null || condition.getType() == Link.LinkMapType.normal )
            return true;

        MapExprResolver visitor = new MapExprResolver(scope);
        return (Boolean)visitor.visit(condition.getConditionObject());
    }

    private Object readValue(ParseTree mapping, Scope lclscope){

        MapExprResolver visitor = new MapExprResolver(lclscope);
        return visitor.visit(mapping);
    }

    private Context evalActivity(String activityRef, Context context) {
        IActivity activity = ActivityRegistry.getActivityInstance(activityRef);
        activity.eval(context);

        return context;
    }
}
