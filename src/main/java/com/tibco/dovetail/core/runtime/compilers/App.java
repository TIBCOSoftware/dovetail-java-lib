package com.tibco.dovetail.core.runtime.compilers;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import com.tibco.dovetail.core.model.flow.AppProperty;
import com.tibco.dovetail.core.runtime.flow.BasicTransactionFlow;
import com.tibco.dovetail.core.runtime.trigger.ITrigger;

public class App {
	LinkedHashMap<String, ITrigger> triggers = new LinkedHashMap<String, ITrigger>();
	LinkedHashMap<String, String> refs = new LinkedHashMap<String, String>();
	LinkedHashMap<String, BasicTransactionFlow> flows = new LinkedHashMap<String, BasicTransactionFlow>();
	List<AppProperty> properties = new ArrayList<AppProperty>();
	
	void addImports(String[] imports) {
		for(String in : imports) {
			String[] parts = in.split("//");
			refs.put("#" + parts[parts.length-1], in);
		}
	}
	
	public String getRef(String key) {
		return this.refs.get(key);
	}
	
	void addTriggers(LinkedHashMap<String, ITrigger> in) {
		this.triggers.putAll(in);
	}
	
	public LinkedHashMap<String, ITrigger> getTriggers(){
		return this.triggers;
	}
	
	void addProperties(AppProperty[] ps) {
		for(AppProperty p : ps) {
			properties.add(p);
		}
	}
	
	public List<AppProperty> getProperties(){
		return this.properties;
	}

	public BasicTransactionFlow getFlow(String name) {
		return flows.get(name);
	}

	void addFlows(LinkedHashMap<String, BasicTransactionFlow> flows) {
		this.flows.putAll(flows);
	}
	
	
}

