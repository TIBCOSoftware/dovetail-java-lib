/*
* Copyright © 2018. TIBCO Software Inc.
* This file is subject to the license terms contained
* in the license file that is distributed with this file.
 */
package com.tibco.dovetail.core.runtime.compilers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tibco.dovetail.core.model.activity.ActivityModel;
import com.tibco.dovetail.core.model.common.SimpleAttribute;
import com.tibco.dovetail.core.model.flow.*;
import com.tibco.dovetail.core.runtime.engine.Scope;
import com.tibco.dovetail.core.runtime.expression.MapExprGrammarLexer;
import com.tibco.dovetail.core.runtime.expression.MapExprGrammarParser;
import com.tibco.dovetail.core.runtime.flow.*;
import com.tibco.dovetail.core.runtime.flow.Mapping.ValueMappingType;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

public class FlowCompiler {
	final static Pattern MAP_TO_ATTR_PATTERN = Pattern.compile("(\\$INPUT.\\$.\\$)?(\\['(?<name>\\w+)'\\])+?");
	final static Pattern FUNCTION = Pattern.compile("[a-zA-Z]+\\.[a-zA-Z].*?\\(.*?\\)");
	
    public static TransactionFlow compile( Resources r) throws Exception {
    		ObjectMapper mapper = new ObjectMapper();
    		TransactionFlow flow = new TransactionFlow();
    		compileFlow(mapper, flow, r.getData());
    		
		 //error handler
        //TODO: error handler input (activity, data, message)
          /*
          if(r.getData().getErrorHandler() != null){
              BasicTransactionFlow errflow = new BasicTransactionFlow();
              compileFlow(mapper, errflow, r.getData().getErrorHandler());
              flow.setErrorHandler(errflow);
          }*/
        
        return flow;
    }
    private static void compileFlow(ObjectMapper mapper, BasicTransactionFlow flow, BasicFlow data) throws Exception{
        //tasks
        for(TasksConfig task : data.getTasks()){
            flow.addTask(task.getId(), compileTask(mapper, task));
        }

        //links
        Node root = compileLinks(data.getLinks());
        if(root == null && data.getTasks().length > 0) {
        		root = new Node(data.getTasks()[0].getId());
        }
        else {
        		throw new RuntimeException("There is no activity in the flow");
        }
        flow.setRoot(root);
    }

    private static ActivityTask compileTask(ObjectMapper mapper, TasksConfig task) throws Exception{
        ActivityTask activityTask = new ActivityTask(task.getId());
        activityTask.setActivityRef(task.getActivity().getRef());
        ActivityModel activityModel = ActivityModel.loadModel(mapper, activityTask.getActivityRef());

        //map input values
        Map<String, Object> input = task.getActivity().getInput();
        if(input != null) {
            for (String attr : input.keySet()){
                SimpleAttribute a = getActivityInputAttribute(activityModel, attr);
                InputMapping inputMapping = new InputMapping();
                inputMapping.setMappingType(Mapping.ValueMappingType.literal);
                inputMapping.setMappingValue(task.getActivity().getInput().get(attr));
                activityTask.addInput(a.getName(), inputMapping);
            }
        }

        //map input mappings, it could overrides map input values
        TaskMappings mappings = task.getActivity().getMappings();
        if(mappings != null && mappings.getInput() != null) {
            for (TaskMapping mapping : mappings.getInput()){
            		ArrayList<String> names = getInputAttrName(MAP_TO_ATTR_PATTERN, mapping.getMapTo());
                 Object mappingValue = mapping.getValue();
                 ValueMappingType mappingType = AttributeMapping.ValueMappingType.valueOf(mapping.getType());
                 
            		//find top level attribute object mapping
                Mapping root = activityTask.getInput(names.get(0));
                if (root == null) {
                	   SimpleAttribute a = getActivityInputAttribute(activityModel, names.get(0));
                    root = new InputMapping();
                    
                    activityTask.addInput(names.get(0), root);
                }

                //first time
                if(root.getMappingType() != Mapping.ValueMappingType.object && names.size() > 1) {
            			root.setMappingType(Mapping.ValueMappingType.object);
            			root.setMappingValue(new HashMap<String, AttributeMapping>());
                }
                
                Mapping map = root;
                if(names.size() > 1) {
                		String attr = names.subList(1,names.size()).stream().collect(java.util.stream.Collectors.joining("."));
                		map = ((Map<String, AttributeMapping>)root.getMappingValue()).get(attr);
                		if (map == null) {
                			map = new AttributeMapping(attr);
                			((Map<String, AttributeMapping>)root.getMappingValue()).put(attr, (AttributeMapping) map);
                		}
                }
                
                switch(mappingType) {
		    	        case array:
		    	            Map<String, Object> arraymap = mapper.readValue(mappingValue.toString(), new TypeReference<Map<String, Object>>(){} ); 
		    	        		map.setMappingType(AttributeMapping.ValueMappingType.array);
		    	        		map.setMappingValue(parseArrayMapping(arraymap));
		    	            break;
		    	        case expression:
		    		        	if(mappingValue.equals("$flow.containerServiceStub")) {
		    		        		map.setMappingType(AttributeMapping.ValueMappingType.assign);
		    		        		map.setMappingValue(mappingValue);
		    		        	}
		    		        else {
		    		        		String mapexpr = mappingValue.toString();
		    		        		if(Scope.isScopeVariable(mapexpr) || isFunctionMapping(mapexpr)) {
		    		        			map.setMappingType(mappingType);
		    		        			map.setMappingValue(parseExpression(mappingValue.toString()));
		    		        		} else {
		    		        			map.setMappingType(AttributeMapping.ValueMappingType.literal);
		    		        			map.setMappingValue(mappingValue);
		    		        		}
		    		        }
		    		        	break;
		    		    default:
		    		    		throw new IllegalArgumentException("Unsupported mapping type " + mappingType);
		        }
               
            }
        }

        return activityTask;
    }
    private static Node compileLinks(LinksConfig[] links){
        Node root = null;
        Map<String, Node> nodes = new HashMap<String, Node>();
        for(LinksConfig l : links){
            String from = l.getFrom();
            String to = l.getTo();

            Node fromNode = nodes.get(from);
            if(fromNode == null){
                fromNode = new Node(from);
                nodes.put(from, fromNode);
            }

            Node toNode = nodes.get(to);
            if(toNode == null){
                toNode = new Node(to);
                nodes.put(to, toNode);
            }

            String linktype = l.getType();
            if(linktype.equalsIgnoreCase("default"))
            		linktype = "normal";
            
            Link link = new Link(Link.LinkMapType.valueOf(linktype), l.getValue());
            link.setNextNode(toNode);
            fromNode.addToLink(link);
            toNode.addFromLink(link);
        }

        for(Node node : nodes.values()){
            if(node.getFromLinks().isEmpty()){
                root = node;
                break;
            }
        }
        return root;
    }

    private static SimpleAttribute getActivityInputAttribute(ActivityModel activityModel, String attr) throws NoSuchFieldException{
        SimpleAttribute a = activityModel.getInputs(attr);
        if (a == null)
            throw new NoSuchFieldException("attribute " + attr + " is not found for activity " + activityModel.getRef());

        return a;
    }
    
    private static ParseTree parseExpression(String mapping) {
		InputStream stream = null;

	    try {
	        stream = new ByteArrayInputStream(mapping.getBytes(StandardCharsets.UTF_8));
	        MapExprGrammarLexer lexer = new MapExprGrammarLexer(CharStreams.fromStream(stream, StandardCharsets.UTF_8));
	        CommonTokenStream tokens = new CommonTokenStream(lexer);
	        MapExprGrammarParser parser = new MapExprGrammarParser(tokens);
	        ParseTree tree = parser.expression();
	        return tree;
	    }catch (Exception e){
	        throw new RuntimeException("parseExpression error: " + mapping, e);
	    } finally {
	    		if(stream != null) {
				try {
					stream.close();
				} catch (IOException e) {}
	    		}
	    }
	}
    
    @SuppressWarnings("unchecked")
	private static Map<String, Object> parseArrayMapping(Map<String, Object> map) throws Exception {
    		String from = map.get("from").toString();
    		if(from.equals("NEWARRAY") == false && (Scope.isScopeVariable(from) || isFunctionMapping(from))) {
    			map.put("from", parseExpression(from));
    		} 
    		
    		ArrayList<String> names = getInputAttrName(MAP_TO_ATTR_PATTERN, map.get("to").toString());
    		map.put("to", names.stream().collect(java.util.stream.Collectors.joining(".")));
		Object fields = map.get("fields");
		if(fields != null && fields instanceof List) {
			List<Object> lstfield = (List<Object>)fields;
			for(Object v: lstfield){
				Map<String, Object> fieldmap = (Map<String, Object>)v;
				if(fieldmap.get("type").toString().equals("primitive")) {
					//primitive
					ArrayList<String> pnames = getInputAttrName(MAP_TO_ATTR_PATTERN, fieldmap.get("to").toString());
		    			fieldmap.put("to", pnames.stream().collect(java.util.stream.Collectors.joining(".")));
		    			String fromexp = fieldmap.get("from").toString();
		    			if(Scope.isScopeVariable(fromexp) || isFunctionMapping(fromexp))
		    				fieldmap.put("from", parseExpression(fromexp));
		    			else
		    				fieldmap.put("from", fieldmap.get("from"));
				} else {
					try {
						parseArrayMapping(fieldmap);
					} catch (Exception e) {
						throw e;
					}
				}
			}	
		}
    		
    		return map;
    } 
    
    
    private static ArrayList<String> getInputAttrName(Pattern pattern, String mapTo) throws Exception {
    		ArrayList<String> names = new ArrayList<String>();
    		Matcher matcher = pattern.matcher(mapTo);
		while(matcher.find()) {
			names.add(matcher.group("name"));
		}
		
		if(names.isEmpty())
			throw new Exception("Invalid $INPUT format:" + mapTo);
		
		return names;
    }
    
    private static boolean isFunctionMapping(String mapping) {
    		Matcher matcher = FUNCTION.matcher(mapping);
    		return matcher.matches();
    }

}
