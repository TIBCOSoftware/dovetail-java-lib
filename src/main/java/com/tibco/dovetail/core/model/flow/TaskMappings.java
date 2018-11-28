/*
* Copyright © 2018. TIBCO Software Inc.
* This file is subject to the license terms contained
* in the license file that is distributed with this file.
 */
package com.tibco.dovetail.core.model.flow;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TaskMappings {
    private List<TaskMapping> input;
    private List<TaskMapping> output;

    public List<TaskMapping> getInput() {
        return input;
    }

    public void setInput(List<TaskMapping> input) {
        this.input = input;
    }

    public List<TaskMapping> getOutput() {
        return output;
    }

    public void setOutput(List<TaskMapping> output) {
        this.output = output;
    }

}
