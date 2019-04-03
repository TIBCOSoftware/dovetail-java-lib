/*
* Copyright © 2018. TIBCO Software Inc.
* This file is subject to the license terms contained
* in the license file that is distributed with this file.
 */
package com.tibco.dovetail.core.runtime.engine;

import com.tibco.dovetail.core.runtime.flow.ReplyHandler;
import com.tibco.dovetail.core.runtime.services.IContainerService;

import java.util.LinkedHashMap;
import java.util.Map;

abstract public class Context {
    protected Map<String, Object> inputs = new LinkedHashMap<>();
    private Map<String, Object> outputs = new LinkedHashMap<>();
    protected IContainerService containerService;
    protected ReplyHandler reply;

    public Object getInput(String param) {
        return inputs.get(param);
    }

    public Map<String, Object> getInputs() {
        return this.inputs;
    }

    public Object getOutput(String param) {
        return outputs.get(param);
    }

    public Map<String, Object> getOutputs() {
        return this.outputs;
    }

    public void setOutput(String param, Object value) {
        outputs.put(param, value);
    }

    public IContainerService getContainerService() {
        return containerService;
    }
    
    public ReplyHandler getReplyHandler() {
    		return this.reply;
    }
}
