/*
* Copyright © 2018. TIBCO Software Inc.
* This file is subject to the license terms contained
* in the license file that is distributed with this file.
 */
package com.tibco.dovetail.core.model.flow;

import java.util.Map;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class HandlerConfig {
	private Map<String, Object> settings; 
	private Action action;
	private Map<String, Object> schemas;
	private Resources flowResource;
	private String flowId;
	private String flowName;
	
	 public String getFlowURI() {
		 if(this.action != null && this.action.getSettings() != null) {
			 return this.getAction().getSettings().get("flowURI");
		 } else {
			 return null;
		 }
		
	 }
	 
	 public Map<String, Object> getInput() {
		return this.action.getInput();
	}

	public Map<String, Object> getOutput() {
		return this.action.getOutput();
	}

	public Object getSetting(String name) {
		 if(this.settings != null ) {
			 return this.settings.get(name);
		 } else {
			 return null;
		 }
		
	 }
	 
	 public void setFlow(String flowId, Resources r) {
		 this.flowId = flowId;
		 this.flowResource = r;
		 this.flowName = flowId.substring(5);
	 }
	 
	 public Resources getFlow() {
		 return this.flowResource;
	 }
	 
	 public String getFlowId() {
		 return this.flowId;
	 }
	 
	 public String getFlowName() {
		 return this.flowName;
	 }
	 
	 public Map<String, Object> getSettings() {
		return settings;
	}

	public void setSettings(Map<String, Object> settings) {
		this.settings = settings;
	}

	public Action getAction() {
		return action;
	}

	public void setAction(Action action) {
		this.action = action;
	}
	
	public Map<String, Object> getSchemas() {
		return schemas;
	}

	public void setSchemas(Map<String, Object> schemas) {
		this.schemas = schemas;
	}
	
	public String getTransactionInputMetadata() {
		Map<String, Object> txnout = (Map<String, Object>) this.schemas.get("output");
		Map<String, Object> txnIn = (Map<String, Object>) txnout.get("transactionInput");
		return txnIn.get("value").toString();
	}
	 
	 @JsonIgnoreProperties(ignoreUnknown = true)
	 public static class Action {
		 public String getRef() {
			return ref;
		}
		public void setRef(String ref) {
			this.ref = ref;
		}
		public Map<String, String> getSettings() {
			return settings;
		}
		public void setSettings(Map<String, String> data) {
			this.settings = data;
		}
		public Map<String, Object> getInput() {
			return input;
		}
		public void setInput(Map<String, Object> input) {
			this.input = input;
		}
		public Map<String, Object> getOutput() {
			return output;
		}
		public void setOutput(Map<String, Object> putput) {
			this.output = putput;
		}
		
		private Map<String, Object> output;
		private String ref;
		private Map<String, String> settings;
		private Map<String, Object> input;
	 }
	 
}
