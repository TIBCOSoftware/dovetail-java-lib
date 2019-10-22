package com.tibco.dovetail.core.runtime.engine;

import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;

import org.antlr.v4.runtime.tree.ParseTree;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.tibco.dovetail.core.runtime.expression.MapExprResolver;
import com.tibco.dovetail.core.runtime.flow.AttributeMapping;
import com.tibco.dovetail.core.runtime.flow.ValueMappingType;
import com.tibco.dovetail.core.runtime.util.JsonUtil;

import net.minidev.json.JSONArray;

public class InputResolver {
	public static void resolveInput(ContextImpl context, Scope scope, String key, AttributeMapping v) {
		 switch (v.getMappingType()){
	         case literal:
	             context.addInput(key, v.getMappingValue());
	             break;
	         case assign:
	         		context.addInput(key, scope.getVariable(v.getMappingValue().toString()));
	         		break;
	         case expression:
	         	   Object mapObj = v.getMappingValue();
	         	   
	         	   ParseTree mapping = (ParseTree)mapObj;
	         	   Object ov = readValue(mapping, scope);
	         	   DocumentContext edoc;
	         	   if (ov instanceof DocumentContext) {
	         		   edoc = (DocumentContext) ov;
	         		   context.addInput(key, edoc);
	         	   } else if (ov instanceof List) {
	         		   edoc = JsonUtil.getJsonParser().parse("[]");
	         		   List<Object> values = edoc.json();
	         		   values.addAll((Collection<? extends Object>) ov);
	         		  context.addInput(key, edoc);
	         	   } else if (ov instanceof Object[]) {
	         		   edoc = JsonUtil.getJsonParser().parse("[]");
	         		   List<Object> values = edoc.json();
	         		   for (Object o : (Object[])ov)
	 						values.add(o);
	         		  context.addInput(key, edoc);
	         	   } else if (isPrimitive(ov)){
	         		  context.addInput(key, ov);
	         	   } else {
	         		  context.addInput(key, JsonUtil.getJsonParser().parse(ov));
	         	   }
	             break;
	         case object:
	             List<AttributeMapping> map = (List<AttributeMapping>) v.getMappingValue();
	             DocumentContext doc = JsonUtil.getJsonParser().parse("{}");
	             doc = createObject(doc, scope, "$", map);
	             context.addInput(key, doc);
	             break;
	         case array:
	         		List<AttributeMapping> arrmap = (List<AttributeMapping>) v.getMappingValue();
	              DocumentContext arrdoc = JsonUtil.getJsonParser().parse("[]");
	              
	              List<LinkedHashMap<String, Object>> value = createArrayObject(scope, v);
	              value.forEach(mv -> arrdoc.add(JsonPath.compile("$"), mv));
	             
	              context.addInput(key, arrdoc);
	             break;
	          default:
	             	throw new IllegalArgumentException("Unsupported mapping type " + v.getMappingType());
	     }
	}
	
	private static boolean isPrimitive(Object v) {
		if((v instanceof Integer) || (v instanceof Long) || (v instanceof String) || 
			(v instanceof Boolean) || (v instanceof Double) || (v instanceof Float) ||
			v instanceof Instant || v instanceof LocalDate)
			return true;
		else
			return false;
	}
	
	public static Object readValue(ParseTree mapping, Scope lclscope){

        MapExprResolver visitor = new MapExprResolver(lclscope);
        return visitor.visit(mapping);
    }
	
	@SuppressWarnings("unchecked")
	private static List<LinkedHashMap<String, Object>> createArrayObject(Scope parent, AttributeMapping mapping) {
	    		Scope lclscope = new Scope(parent, false);
	    		List<LinkedHashMap<String, Object>> entries = new ArrayList<LinkedHashMap<String, Object>>();
	    		
	    		int loop = -1;
	    		Object value = readValue(mapping.getFrom(), parent);
	    		List<Object> from = new ArrayList<Object>();
	    		if(value != null) {
		    		if(value instanceof DocumentContext) {
		    			from = ((DocumentContext)value).json();
		    		} else {
		    			from = (List<Object>) value;
		    		}
		        
		    		loop = from.size();
	    		}
	    		
	        for(int i=0; i<loop; i++) {
	        	    lclscope.addVariable(Scope.SCOPE_CURRENT, "from", from.get(i));
	        	    DocumentContext doc = JsonUtil.getJsonParser().parse("{}");
	            doc = createObject(doc, lclscope, "$", (List<AttributeMapping>)mapping.getMappingValue());
	            entries.add(doc.json());
	        }
	        
			return entries;
	    }
	    
	    private static DocumentContext createObject(DocumentContext doc, Scope lclscope, String parentPath, List<AttributeMapping> mapping){

	        for(AttributeMapping k: mapping){
	            Object objmap = k.getMappingValue();
	            ValueMappingType mapType = k.getMappingType();
	            
	            switch(mapType) { 
	            case literal:
	            		doc = setAttrValue(doc, parentPath, k.getName(), objmap);
	            		break;
	            case assign:
	            		doc = setAttrValue(doc, parentPath, k.getName(), lclscope.getVariable(objmap.toString()));
	            		break;
	            case expression:
	            		Object value = readValue((ParseTree)objmap, lclscope);
		            //check/create nested objects
		            if (value instanceof DocumentContext)
		            		doc = setAttrValue(doc, parentPath, k.getName(), ((DocumentContext)value).json());    
		            else 
		            		doc = setAttrValue(doc, parentPath, k.getName(), value);
		            break;
	            case object:
	            		doc = createObject(doc, lclscope, parentPath+"."+k.getName(), (List<AttributeMapping>)k.getMappingValue());
	            		break;
	            case array:
	            		List<LinkedHashMap<String, Object>> arrvalue = createArrayObject(lclscope, k);
	            		doc = setAttrValue(doc, parentPath, k.getName(), arrvalue);
	            		break;
	            default:
	            		throw new RuntimeException("mapping type " + mapType + " is not supported");
	            }
	        }

	        return doc;
	    }
	    
	    private static DocumentContext setAttrValue(DocumentContext doc, String rootPath, String mapto, Object value) {
	    	String[] objs= rootPath.split("\\.");
    		String path = objs[0];
        for(int i=1; i<objs.length; i++){
            Object o = doc.read(path + "." + objs[i]);
            if(o == null) {
            		doc.put(path, objs[i], new LinkedHashMap<>());
            } else {
            	  if(o instanceof List && ((List)o).get(0) == null) {
            		  doc.put(path, objs[i], new LinkedHashMap<>());
            	  } else if (o instanceof JSONArray && ((JSONArray)o).get(0) == null) {
            		  doc.put(path, objs[i], new LinkedHashMap<>());
            	  }
            }
           
            path = path + "." + objs[i];
        }
	   
	   
        //doc.put(JsonPath.compile(path), objs[objs.length-1], value);
	    	doc.put(path, mapto, value);
        return doc;
    }

}
