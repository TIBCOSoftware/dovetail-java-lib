/*
* Copyright © 2018. TIBCO Software Inc.
* This file is subject to the license terms contained
* in the license file that is distributed with this file.
 */
package com.tibco.dovetail.core.runtime.flow;

import com.tibco.dovetail.core.model.common.SimpleAttribute;
import com.tibco.dovetail.core.runtime.transaction.TxnInputAttribute;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BasicTransactionFlow {
	
    private Node root;
    private Map<String, ActivityTask> tasks = new HashMap<String, ActivityTask>();
    private List<TxnInputAttribute> flowInputs = new ArrayList<TxnInputAttribute>();
    private List<SimpleAttribute> flowOutputs = new ArrayList<SimpleAttribute>();

    public List<TxnInputAttribute> getFlowInputs(){return this.flowInputs;}
    public List<SimpleAttribute> getFlowOutputs(){return this.flowOutputs;}
    
    public void setFlowInputs(List<TxnInputAttribute> input){
       this.flowInputs = input;
    }
    
    public void addFlowInput(TxnInputAttribute input){
         this.flowInputs.add(input);
    }
    public void setFlowOutputs(List<SimpleAttribute> output){
        this.flowOutputs = output;
    }

    public Node getRoot() {
        return root;
    }

    public ActivityTask getTask(String id) {
        return this.tasks.get(id);
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public void setTasks(Map<String, ActivityTask> tasks) {
        this.tasks = tasks;
    }
    public void addTask(String id, ActivityTask task) {
        this.tasks.put(id, task);
    }
    public Map<String, ActivityTask> getTasks() {
        return tasks;
    }
}
