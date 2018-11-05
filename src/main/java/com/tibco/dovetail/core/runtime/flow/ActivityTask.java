package com.tibco.dovetail.core.runtime.flow;

import java.util.HashMap;

public class ActivityTask {
    String taskId;
    String activityRef;
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

}
