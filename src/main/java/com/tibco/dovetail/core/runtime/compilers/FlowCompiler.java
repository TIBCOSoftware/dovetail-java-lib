/*
* Copyright © 2018. TIBCO Software Inc.
* This file is subject to the license terms contained
* in the license file that is distributed with this file.
 */
package com.tibco.dovetail.core.runtime.compilers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tibco.dovetail.core.model.activity.ActivityModel;
import com.tibco.dovetail.core.model.common.SimpleAttribute;
import com.tibco.dovetail.core.model.composer.MetadataParser;
import com.tibco.dovetail.core.model.flow.*;
import com.tibco.dovetail.core.model.flow.ActivityConfig.Schemas;
import com.tibco.dovetail.core.runtime.flow.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class FlowCompiler {
//	final static Pattern MAP_TO_ATTR_PATTERN = Pattern.compile("(\\$INPUT.\\$.\\$)?(\\['(?<name>\\w+)'\\])+?");
//	final static Pattern FUNCTION = Pattern.compile("[a-zA-Z]+\\.[a-zA-Z].*?\\(.*?\\)");
	
    public static TransactionFlow compile( HandlerConfig cfg) throws Exception {
    		Resources r = cfg.getFlow();
    		ObjectMapper mapper = new ObjectMapper();
    		TransactionFlow flow = new TransactionFlow();
    		compileFlow(mapper, flow, r.getData());
    		compileTriggerToFlowMapping(flow, cfg.getInput(), r.getData().getMetadata().getInput());
    		compileFlowToTriggerMapping(flow, cfg.getOutput(), r.getData().getMetadata().getOutput());
    		
    		
    		if(r.getData().getMetadata() != null) {
               flow.setFlowOutputs(r.getData().getMetadata().getOutput());
               flow.setFlowInputs(r.getData().getMetadata().getInput());
        }
    		
    		
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
        
        flow.setRoot(root);
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
                    			inputMapping.setComplextObjectMetadata(MetadataParser.parseSingleSchema(meta.toString()));
                    		}
                		}
                		
                		activityTask.addInput(a.getName(), inputMapping);
                }
            }
        }

        return activityTask;
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
    
   /* private static ArrayList<String> getInputAttrName(Pattern pattern, String mapTo) throws Exception {
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
    */
    
}
