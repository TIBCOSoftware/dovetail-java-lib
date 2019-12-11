/*
* Copyright © 2018. TIBCO Software Inc.
* This file is subject to the license terms contained
* in the license file that is distributed with this file.
 */
package com.tibco.dovetail.core.runtime.engine;

import java.util.List;
import java.util.Map;

import com.jayway.jsonpath.DocumentContext;
import com.tibco.dovetail.core.runtime.activity.IActivity;
import com.tibco.dovetail.core.runtime.expression.MapExprResolver;
import com.tibco.dovetail.core.runtime.flow.ActivityRegistry;
import com.tibco.dovetail.core.runtime.flow.ActivityTask;
import com.tibco.dovetail.core.runtime.flow.BasicTransactionFlow;
import com.tibco.dovetail.core.runtime.flow.Link;
import com.tibco.dovetail.core.runtime.flow.Node;
import com.tibco.dovetail.core.runtime.flow.ReplyData;
import com.tibco.dovetail.core.runtime.flow.ReplyHandler;
import com.tibco.dovetail.core.runtime.services.IContainerService;
import com.tibco.dovetail.core.runtime.services.ILogService;
import com.tibco.dovetail.core.runtime.util.JsonUtil;

import co.paralleluniverse.fibers.Suspendable;

public class FlowEngine {
	private BasicTransactionFlow flow;
    private IContainerService container;
    private ILogService logger;
    private Map<String, Object> flowInputs;
    private Scope scope;
    private ReplyHandler replyHandler;
	
    public FlowEngine (BasicTransactionFlow flow){
        this.flow = flow;
        replyHandler = new ReplyHandler();
    }
    
    @Suspendable
	public ReplyData execute(Context context, Scope parent) {
        try {
            this.container = context.getContainerService();
            logger = this.container.getLogService();
            scope = new Scope(parent, true);
            
            flowInputs = context.getInputs();
            flowInputs.forEach((k, v) -> scope.addVariable("$flow", k, v));

            Node root = flow.getRoot();
            if(root == null)
            		return null;
            
            runNode(root);
        }catch (Exception e){
            	throw e;
        }

        return this.replyHandler.getReplyData();
    }

    @Suspendable
    private void runNode(Node n){
        //invoke model
    		try {
    			invokeTask(n.getTaskId());
    		}catch(Exception e) {
    			//find error link if exist
    			boolean errHandled = false;
    			for(Link l : n.getToLinks()) {
    				if(l.getType() == Link.LinkMapType.error) {
    					runNode(l.getNextNode());
    					errHandled = true;
    					break;
    				}
    			}
    			
    			if(!errHandled)
    				throw e;
    		}

        for(Link l : n.getToLinks()){
        		if(l.getType() != Link.LinkMapType.error) {
	            if(eval(l)){
	                runNode(l.getNextNode());
	            }
        		}
        };
    }

    @Suspendable
    private void invokeTask(String id){
    		logger.debug("Invoke activity " + id);
        if(!checkActivityExist(id))
        		return;
        
        Context context = null;
        if(isIterateTask(id)) {
        		//get iterate object
        		Object iterate = getIterateValue(id);
        		if(iterate != null) {
        			List<Object> vals = ((DocumentContext)iterate).json();
        			DocumentContext output = JsonUtil.getJsonParser().parse("[]");
        			for (int i=0; i<vals.size(); i++) {
                		//set current scope variables
        				scope.addVariable("$iteration", "key", i);
        				scope.addVariable("$iteration", "value", vals.get(i));
        				
        				context = resolveInputs(id);	
        				context = evalActivity(getActivityRef(id), context);
        				
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
        		context = resolveInputs(id);
        		context = evalActivity(getActivityRef(id), context);
        		context.getOutputs().forEach((k, v) -> scope.addVariable("$activity", id + "." + k, v));
        		
        }

    }
    
    private boolean checkActivityExist(String id) {
    		ActivityTask activity = flow.getTask(id);
        if(activity == null)
             return false;
        else
        		return true;
    }

    private String getActivityRef(String id) {
    		return flow.getTask(id).getActivityRef();
    }
    
    private boolean isIterateTask(String id) {
    		return flow.getTask(id).isIteratorTask();
    }
    
    private Object getIterateValue(String id) {
    		ActivityTask activity = flow.getTask(id);
    		return InputResolver.readValue(activity.getIterateField(), scope);
    }
    private ContextImpl resolveInputs(String id){
    		ActivityTask activity = flow.getTask(id);

        ContextImpl context = new ContextImpl();
        context.setContainerService(this.container);
        context.setReplyHandler(replyHandler);

        activity.getInputs().forEach((k, v) -> {
            if(v.getMappingType() == null){
                context.addInput(k, v.getMappingValue());
                return;
            }

            InputResolver.resolveInput(context, scope, k, v);
            
        });
        
        context.addSettings(activity.getSettings());

        return context;
    }

   
    private boolean eval(Link condition){
    		logger.debug("eval link " + condition.getCondition());
        if(condition.getCondition() == null || condition.getType() == Link.LinkMapType.normal )
            return true;

        MapExprResolver visitor = new MapExprResolver(scope);
        return (Boolean)visitor.visit(condition.getConditionObject());
    }

    
    @Suspendable
    private Context evalActivity(String activityRef, Context context) {
        IActivity activity = ActivityRegistry.getActivityInstance(activityRef);
        activity.eval(context);

        return context;
    }
}
