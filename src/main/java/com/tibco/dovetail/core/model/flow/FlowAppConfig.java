package com.tibco.dovetail.core.model.flow;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FlowAppConfig {
    private String name;
    private String type;
    private String description;
    private Map<String, Resources> resources = new HashMap<String, Resources>();
    private TriggerConfig[] triggers;

    public static FlowAppConfig parseModel(InputStream jsonModel) throws JsonParseException, JsonMappingException, IOException {
    	 	ObjectMapper mapper = new ObjectMapper();
        FlowAppConfig app = mapper.readValue(jsonModel, FlowAppConfig.class);
        
        //for each handler of a trigger, find the flow resource in the app and store inside the handler
        TriggerConfig[] triggers = app.getTriggers();
        for(int i=0; i<triggers.length; i++) {
    			HandlerConfig[] handlers = triggers[i].getHandlers();
    			if(handlers == null || handlers.length == 0)
    				throw new RuntimeException("No handler for trigger " + triggers[i].getName());
    			
    			for(int j=0; j<handlers.length; j++) {
    				String flowId = handlers[j].getFlowURI().split("//")[1];
    				Resources r = app.getResource(flowId);
    				if (r == null)
    					throw new RuntimeException("Resource " + flowId + " does not exist");
    				
    				handlers[j].setFlow(flowId, r);
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
    
    public Collection<Resources> getResources() {
        return resources.values();
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
}
