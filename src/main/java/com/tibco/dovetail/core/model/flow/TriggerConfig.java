/*
* Copyright © 2018. TIBCO Software Inc.
* This file is subject to the license terms contained
* in the license file that is distributed with this file.
 */
package com.tibco.dovetail.core.model.flow;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TriggerConfig {
	private String ref;
	private String name;
	private HandlerConfig[] handlers;
	private Map<String, String> settings;
	
	public String getRef() {
		return ref;
	}
	public void setRef(String ref) {
		this.ref = ref;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public HandlerConfig[]  getHandlers() {
		return handlers;
	}
	public void setHandlers(HandlerConfig[] handlers) {
		this.handlers = handlers;
	}
	
	 public String getSetting(String name) {
		 if(this.settings != null ) {
			 return this.settings.get(name);
		 } else {
			 return null;
		 }
		
	 }
	 public Map<String, String> getSettings() {
			return settings;
	}

	public void setSettings(Map<String, String> settings) {
		this.settings = settings;
	}
}
