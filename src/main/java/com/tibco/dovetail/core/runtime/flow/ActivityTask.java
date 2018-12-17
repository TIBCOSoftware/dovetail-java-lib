/*
* Copyright © 2018. TIBCO Software Inc.
* This file is subject to the license terms contained
* in the license file that is distributed with this file.
 */
package com.tibco.dovetail.core.runtime.flow;

import java.util.HashMap;

import org.antlr.v4.runtime.tree.ParseTree;

public class ActivityTask {
    String taskId;
    String activityRef;
    boolean isIteratorTask = false;
    ParseTree iterateField = null;
	HashMap<String, Mapping> inputs = new HashMap<String, Mapping>();
    HashMap<String, Mapping> outputs = new HashMap<String, Mapping>();

    public ActivityTask(String taskId) {
        this.taskId = taskId;
    }

    public String getTaskId() {
        return taskId;
    }

    public String getActivityRef() {
        return activityRef;
    }

    public void setActivityRef(String activityRef) {
        this.activityRef = activityRef;
    }

    public Mapping getInput(String name){
        return inputs.get(name);
    }

    public Mapping getOutput(String name){
        return outputs.get(name);
    }

    public void addInput(String name, Mapping a){
        this.inputs.put(name, a);
    }

    public void addOutput(String name, Mapping a){
        this.outputs.put(name, a);
    }

    public HashMap<String, Mapping> getInputs() {
        return inputs;
    }

    public HashMap<String, Mapping> getOutputs() {
        return outputs;
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
