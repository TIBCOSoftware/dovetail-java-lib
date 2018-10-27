package com.tibco.dovetail.core.runtime.engine;

import com.tibco.dovetail.core.runtime.flow.ReplyHandler;
import com.tibco.dovetail.core.runtime.services.IContainerService;

public class ContextImpl extends Context {

    public void addInput(String param, Object value){
        inputs.put(param, value);
    }
    public void setContainerService(IContainerService service) {
        containerService = service;
    }
    public void setReplyHandler(ReplyHandler reply) {
    		this.reply = reply;
    }
}
