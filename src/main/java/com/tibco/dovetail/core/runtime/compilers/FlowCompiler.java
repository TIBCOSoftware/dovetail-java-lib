/*
* Copyright © 2018. TIBCO Software Inc.
* This file is subject to the license terms contained
* in the license file that is distributed with this file.
 */
package com.tibco.dovetail.core.runtime.compilers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tibco.dovetail.core.model.activity.ActivityModel;
import com.tibco.dovetail.core.model.common.SimpleAttribute;
import com.tibco.dovetail.core.model.flow.*;
import com.tibco.dovetail.core.model.flow.ActivityConfig.Schemas;
import com.tibco.dovetail.core.model.metadata.MetadataParser;
import com.tibco.dovetail.core.runtime.flow.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class FlowCompiler {
//	final static Pattern MAP_TO_ATTR_PATTERN = Pattern.compile("(\\$INPUT.\\$.\\$)?(\\['(?<name>\\w+)'\\])+?");
//	final static Pattern FUNCTION = Pattern.compile("[a-zA-Z]+\\.[a-zA-Z].*?\\(.*?\\)");
	
    public static TransactionFlow compileTriggeFlowMapping( TransactionFlow flow, HandlerConfig cfg) throws Exception {
    		
    		compileTriggerToFlowMapping(flow, cfg.getInput(), cfg.getFlow().getData().getMetadata().getInput());
    		compileFlowToTriggerMapping(flow, cfg.getOutput(), cfg.getFlow().getData().getMetadata().getOutput());
    		
    		
    		if(cfg.getFlow().getData().getMetadata() != null) {
               flow.setFlowOutputs(cfg.getFlow().getData().getMetadata().getOutput());
               flow.setFlowInputs(cfg.getFlow().getData().getMetadata().getInput());
        }
        
        return flow;
    }
    
    public static BasicTransactionFlow compileFlow(ObjectMapper mapper, FlowConfig data, BasicTransactionFlow flow) throws Exception{
        //tasks
        for(TasksConfig task : data.getTasks()){
        		if(task.getActivity().getRef().endsWith("actreturn")) {
        			flow.addTask(task.getId(), compileReturn(mapper, task, data.getMetadata()));
        		} else if(task.getActivity().getRef().endsWith("subflow")){
        			String subflow = ((String) task.getActivity().getSettings().get("flowURI")).substring(11);
        			
        			flow.addTask(task.getId(), compileSubflow(mapper, task, subflow, flow));
        		} else {
        			flow.addTask(task.getId(), compileTask(mapper, task));
        		}
        }

        //links
        Node root = compileLinks(data.getLinks());
    
        if(root == null && data.getTasks().length > 0) {
        		root = new Node(data.getTasks()[0].getId());
        }
        
        flow.setRoot(root);
        if(data.getMetadata() != null) {
            flow.setFlowOutputs(data.getMetadata().getOutput());
            flow.setFlowInputs(data.getMetadata().getInput());
        }
        return flow;
    }

    private static ActivityTask compileSubflow(ObjectMapper mapper, TasksConfig config, String subflow, BasicTransactionFlow flow) throws Exception {
    		ActivityTask activityTask = new ActivityTask(config.getId());
    		activityTask.setActivityRef(config.getActivity().getRef());
    		ActivityModel.registerActivity(activityTask.getActivityRef());
		activityTask.setSubflow(true);
		
		Map<String, Object> input = config.getActivity().getInput();
        if(input != null) {
        		for(SimpleAttribute a : flow.getFlowInputs()) {
	           
                AttributeMapping inputMapping = null;
                
                Object mappingValue = input.get(a.getName());
                if(mappingValue != null) {  
                		inputMapping = mapInputs(a, mappingValue);
                		
                		activityTask.addInput(a.getName(), inputMapping);
                }
        		}
        }
		activityTask.addSetting("flowURI", subflow);
		return activityTask;
 
    }
    private static ActivityTask compileReturn(ObjectMapper mapper, TasksConfig task, Metadata metadata) throws Exception {
    		ActivityTask activityTask = new ActivityTask(task.getId());
    		activityTask.setActivityRef(task.getActivity().getRef());
    		ActivityModel.registerActivity(activityTask.getActivityRef());
    		
    		Map<String, Object> settings = task.getActivity().getSettings();
    		
    		if(settings != null) {
    			List<SimpleAttribute> inputattrs = metadata.getOutput();
    			Map<String, Object> input = (Map<String, Object>) settings.get("mappings");
    			
    			if(input != null) {
    	            for (SimpleAttribute attr : inputattrs){
    	                AttributeMapping inputMapping = null;
    	                
    	                Object mappingValue = input.get(attr.getName());
    	                if(mappingValue != null) {  
    	                		inputMapping = mapInputs(attr, mappingValue);
    	                		
    	                		activityTask.addInput(attr.getName(), inputMapping);
    	                }
    	            }
    	        }
    		}
    		
    		return activityTask;
    }
    
    private static ActivityTask compileTask(ObjectMapper mapper, TasksConfig task) throws Exception{
        ActivityTask activityTask = new ActivityTask(task.getId());
       
        if(task.getType() != null && task.getType().equals("iterator")) {
        		activityTask.setIteratorTask(true);
        		activityTask.setIterateField(CompilerHelper.parseExpression(task.getSetting("iterate")));
        }
        
        activityTask.setActivityRef(task.getActivity().getRef());
        ActivityModel activityModel = ActivityModel.loadModel(mapper, activityTask.getActivityRef());

        //map input values
        Map<String, Object> input = task.getActivity().getInput();
        if(input != null) {
            for (String attr : input.keySet()){
                SimpleAttribute a = getActivityInputAttribute(activityModel, attr);
                AttributeMapping inputMapping = null;
                
                Object mappingValue = input.get(a.getName());
                if(mappingValue != null) {  
                		inputMapping = mapInputs(a, mappingValue);
                		Schemas schemas = task.getActivity().getSchemas();
                		if(schemas != null) {
                			String meta = schemas.getInputSchema(a.getName());
                    		if(meta != null && !meta.trim().isEmpty()) {
                    			a.setMetaSchema(meta);
                    			activityTask.addSetting(a.getName() + "_metadata", MetadataParser.parseSingleSchema(meta.toString()));
                    		}
                		}
                		
                		activityTask.addInput(a.getName(), inputMapping);
                }
            }
        }
        
        return activityTask;
    }
    
    private static void compileTriggerToFlowMapping(TransactionFlow flow, Map<String, Object> maps, List<SimpleAttribute> flowattrs) {
		for(SimpleAttribute attr : flowattrs) {
			Object value = maps.get(attr.getName());
			if(value != null) {
				flow.addTxnToFlowMapping(mapInputs(attr, value));
			}
		}
}

	private static void compileFlowToTriggerMapping(TransactionFlow flow, Map<String, Object> maps, List<SimpleAttribute> flowattrs) {
		for(SimpleAttribute attr : flowattrs) {
			Object value = maps.get(attr.getName());
			if(value != null) {
				flow.addFlowToTxnMapping(mapInputs(attr, value));
			}
		}
	}
	
    private static AttributeMapping mapInputs(SimpleAttribute a, Object mappingValue) {
    	 	AttributeMapping inputMapping = null;
	    	switch(a.getType()) {
			case "complex_object":
			case "object":
		    		if(mappingValue instanceof Map) {
		    			Map<String, Object> values = (Map<String, Object>)mappingValue;
		    			inputMapping = CompilerHelper.parseObjectAttrs(a.getName(), values);
		    		} else {
		    			inputMapping = CompilerHelper.setDirectMapping(a.getName(), mappingValue);
		    		}
				break;
			case "any":
				inputMapping = new AttributeMapping(a.getName());
				inputMapping.setMappingType(ValueMappingType.assign);
				if(CompilerHelper.isExpression(mappingValue.toString()))
					inputMapping.setMappingValue(mappingValue.toString().substring(1));
				else
					inputMapping.setMappingValue(mappingValue);
				break;
			default:
				inputMapping = CompilerHelper.setDirectMapping(a.getName(), mappingValue);
		}
	    	return inputMapping;
    }
    
    private static Node compileLinks(LinksConfig[] links){
        Node root = null;
        Map<String, Node> nodes = new LinkedHashMap<String, Node>();
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
    
}
