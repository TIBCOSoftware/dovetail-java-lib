package com.tibco.dovetail.core.model.flow;

import java.util.Map;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class HandlerConfig {
	private Map<String, String> settings;
	private Output outputs;  
	private Action action;
	private Resources flowResource;
	private String flowId;
	
	 public String getFlowURI() {
		 if(this.action != null && this.action.data != null) {
			 return this.getAction().getData().get("flowURI");
		 } else {
			 return null;
		 }
		
	 }
	 
	 public String getTransactionInputSchema() {
		 if(this.outputs != null && this.outputs.transactionInput != null) {
			 return this.outputs.transactionInput.metadata;
		 } else {
			 return null;
		 }
		
	 }
	 
	 public String getSetting(String name) {
		 if(this.settings != null ) {
			 return this.settings.get(name);
		 } else {
			 return null;
		 }
		
	 }
	 
	 public void setFlow(String flowId, Resources r) {
		 this.flowId = flowId;
		 this.flowResource = r;
	 }
	 
	 public Resources getFlow() {
		 return this.flowResource;
	 }
	 
	 public String getFlowId() {
		 return this.flowId;
	 }
	 
	 public Map<String, String> getSettings() {
		return settings;
	}

	public void setSettings(Map<String, String> settings) {
		this.settings = settings;
	}

	public Output getOutputs() {
		return outputs;
	}

	public void setOutputs(Output outputs) {
		this.outputs = outputs;
	}

	public Action getAction() {
		return action;
	}

	public void setAction(Action action) {
		this.action = action;
	}
	 
	 @JsonIgnoreProperties(ignoreUnknown = true)
	 public static class Output {
		 public TransactionInput getTransactionInput() {
			return transactionInput;
		}

		public void setTransactionInput(TransactionInput transactionInput) {
			this.transactionInput = transactionInput;
		}

		private TransactionInput transactionInput;
	 }
	 
	 @JsonIgnoreProperties(ignoreUnknown = true)
	 public static class TransactionInput {
		 public String getMetadata() {
			return metadata;
		}

		public void setMetadata(String metadata) {
			this.metadata = metadata;
		}

		private String metadata;
	 }
	 
	 @JsonIgnoreProperties(ignoreUnknown = true)
	 public static class Action {
		 public String getRef() {
			return ref;
		}
		public void setRef(String ref) {
			this.ref = ref;
		}
		public Map<String, String> getData() {
			return data;
		}
		public void setData(Map<String, String> data) {
			this.data = data;
		}
		private String ref;
		 private Map<String, String> data;
	 }
	 
}
