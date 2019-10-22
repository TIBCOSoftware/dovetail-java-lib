/*
* Copyright © 2018. TIBCO Software Inc.
* This file is subject to the license terms contained
* in the license file that is distributed with this file.
 */
package com.tibco.dovetail.core.model.flow;

import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FlowAppConfig {
	private String[] imports;
    private String name;
    private String type;
    private String description;
    private Map<String, Resources> resources = new LinkedHashMap<String, Resources>();
    private TriggerConfig[] triggers;
    private AppProperty[] properties;
    
    private static LinkedHashMap<String, String> refs = new LinkedHashMap<String, String>();

	public static FlowAppConfig parseModel(ObjectMapper mapper, InputStream jsonModel) throws Exception {
        FlowAppConfig app = mapper.readValue(jsonModel, FlowAppConfig.class);
        
        for(String k : app.getResources().keySet()){
	    	    Resources r = app.getResource(k);
	    	    r.setId(r.getId().substring(5));
	    	    for(TasksConfig t : r.getData().getTasks()) {
					//set full reference url
				t.getActivity().setRef(refs.get(t.getActivity().getRef()));
			}
        }
        
        //for each handler of a trigger, find the flow resource in the app and store inside the handler
        TriggerConfig[] triggers = app.getTriggers();
        for(int i=0; i<triggers.length; i++) {
        		//set full reference url
        		triggers[i].setRef(refs.get(triggers[i].getRef()));
        		
    			HandlerConfig[] handlers = triggers[i].getHandlers();
    			if(handlers == null || handlers.length == 0)
    				throw new RuntimeException("No handler for trigger " + triggers[i].getName());
    			
    			for(int j=0; j<handlers.length; j++) {
    				//format: res://flow:flowname
    				String flowId = handlers[j].getFlowURI().substring(6);
    				Resources r = app.getResource(flowId);	
    				handlers[j].setFlow(flowId.substring(5), r);
    			}
        }
        
       
        return app;
}
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Resources getResource(String resourceId) {
        return this.resources.get(resourceId);
    }
    
    public Map<String, Resources> getResources() {
        return resources;
    }
    
    public Resources removeResource(String resourceId) {
        return resources.remove(resourceId);
    }

    public void setResources(Resources[] resources) {
    		for(int i=0; i<resources.length; i++)
    			this.resources.put(resources[i].getId(), resources[i]);
    }

	public TriggerConfig[] getTriggers() {
		return triggers;
	}

	public void setTriggers(TriggerConfig[] triggers) {
		this.triggers = triggers;
	}
	
	public String[] getImports() {
		return imports;
	}

	public void setImports(String[] imports) {
		this.imports = imports;
		
		for(String in : imports) {
			String[] parts = in.split("/");
			refs.put("#" + parts[parts.length-1], in);
		}
	}

	public AppProperty[] getProperties() {
		return properties;
	}

	public void setProperties(AppProperty[] properties) {
		this.properties = properties;
	}
}
