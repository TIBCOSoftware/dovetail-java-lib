/*
* Copyright © 2018. TIBCO Software Inc.
* This file is subject to the license terms contained
* in the license file that is distributed with this file.
 */
package com.tibco.dovetail.core.runtime.flow;

import java.util.LinkedHashMap;

import org.antlr.v4.runtime.tree.ParseTree;

public class ActivityTask {
    String taskId;
	String activityRef;
    boolean isIteratorTask = false;
    boolean isSubflow = false;
    ParseTree iterateField = null;
	LinkedHashMap<String, AttributeMapping> inputs = new LinkedHashMap<String, AttributeMapping>();
    LinkedHashMap<String, AttributeMapping> outputs = new LinkedHashMap<String, AttributeMapping>();
    LinkedHashMap<String, Object> settings = new LinkedHashMap<String, Object>();

    public ActivityTask(String taskId) {
        this.taskId = taskId;
    }

    public String getTaskId() {
        return taskId;
    }

    public boolean isSubflow() {
		return isSubflow;
	}

	public void setSubflow(boolean isSubflow) {
		this.isSubflow = isSubflow;
	}

    public String getActivityRef() {
        return activityRef;
    }

    public void setActivityRef(String activityRef) {
        this.activityRef = activityRef;
    }

    public AttributeMapping getInput(String name){
        return inputs.get(name);
    }

    public AttributeMapping getOutput(String name){
        return outputs.get(name);
    }

    public void addInput(String name, AttributeMapping a){
        this.inputs.put(name, a);
    }

    public void addOutput(String name, AttributeMapping a){
        this.outputs.put(name, a);
    }

    public LinkedHashMap<String, AttributeMapping> getInputs() {
        return inputs;
    }

    public LinkedHashMap<String, AttributeMapping> getOutputs() {
        return outputs;
    }
    
    public Object getSetting(String name){
        return settings.get(name);
    }
    
    public LinkedHashMap<String, Object> getSettings(){
        return settings;
    }

    public void addSetting(String name, Object a){
        this.settings.put(name, a);
    }

    public boolean isIteratorTask() {
		return isIteratorTask;
	}

	public void setIteratorTask(boolean isIteratorTask) {
		this.isIteratorTask = isIteratorTask;
	}

	public ParseTree getIterateField() {
		return iterateField;
	}

	public void setIterateField(ParseTree iterateField) {
		this.iterateField = iterateField;
	}
}
