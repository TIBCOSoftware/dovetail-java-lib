/*
* Copyright © 2018. TIBCO Software Inc.
* This file is subject to the license terms contained
* in the license file that is distributed with this file.
 */
package com.tibco.dovetail.core.model.composer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class HLCMetadata {
    private String name;
    private ResourceType type;
    private Map<String, HLCDecorator> decorators = new LinkedHashMap<String, HLCDecorator>();
    private List<String> actors = new ArrayList<String>();
    private String asset = null;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public ResourceType getType() {
        return type;
    }
    public void setType(String type) {
        this.type = ResourceType.valueOf(type);
    }

    public void setDecorators(List<HLCDecorator> inputs) {
    	inputs.forEach(in -> this.decorators.put(in.getName(), in));
    }
    
    public List<String> getActors() {
		return actors;
	}
	public void setActors(List<String> actors) {
		this.actors = actors;
	}
	public String getAsset() {
		return asset;
	}
	public void setAsset(String asset) {
		this.asset = asset;
	}
	public Collection<HLCDecorator> getDecorators() {
       return decorators.values();
    }

    public HLCDecorator getDecorator(String name) {
    		return this.decorators.get(name);
    }
    
	public static enum ResourceType {
        Asset, Participant, Transaction, Event, Concept
    }
}
