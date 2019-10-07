/*
* Copyright © 2018. TIBCO Software Inc.
* This file is subject to the license terms contained
* in the license file that is distributed with this file.
 */
package com.tibco.dovetail.core.model.metadata;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Metadata {
    private String name;
    private ResourceType type;
    private String asset = null;
    private Map<String, String> authorizedUserAndCerts = new LinkedHashMap<String, String>();
    private TimeWindow timewindow;
    
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
    
    public Map<String, String> getAuthorizedUserAndCerts() {
		return authorizedUserAndCerts;
	}
	public void setActors(List<String> actors) {
		if(actors != null) {
			actors.forEach(a -> {
				String[] callers = a.split("\\|");
				if(callers.length == 1)
					authorizedUserAndCerts.put(callers[0], null);
				else
					authorizedUserAndCerts.put(callers[0], callers[1]);
			});
		}
	}
	public String getAsset() {
		return asset;
	}
	public void setAsset(String asset) {
		this.asset = asset;
	}
    
	public TimeWindow getTimewindow() {
		return timewindow;
	}
	public void setTimewindow(TimeWindow timewindow) {
		this.timewindow = timewindow;
	}

	public static enum ResourceType {
        Asset, Participant, Transaction, Event, Concept, ScheduledEvent
    }
}
