/*
* Copyright © 2018. TIBCO Software Inc.
* This file is subject to the license terms contained
* in the license file that is distributed with this file.
 */
package com.tibco.dovetail.core.runtime.flow;

import java.util.Map;

import com.tibco.dovetail.core.runtime.engine.ContextImpl;
import com.tibco.dovetail.core.runtime.engine.FlowEngine;
import com.tibco.dovetail.core.runtime.services.IContainerService;
import com.tibco.dovetail.core.runtime.transaction.TxnACL;

public class TransactionFlow extends BasicTransactionFlow{

    private BasicTransactionFlow errorHandler;
    private TxnACL acl = null;
    private Map<String, Object> properties = null;
    
	public BasicTransactionFlow getErrorHandler() {
        return errorHandler;
    }
    public void setErrorHandler(BasicTransactionFlow handler) {
        this.errorHandler = handler;
    }

    public ReplyData handle(IContainerService stub, Map<String, Object> triggerData) {
    		FlowEngine engine = new FlowEngine(this);
    		ContextImpl context = new ContextImpl();
    		triggerData.forEach((k, v) -> context.addInput(k, v));
    		context.setContainerService(stub);
    		
    		return engine.execute(context);
    }
	public TxnACL getAcl() {
		return acl;
	}
	public void setAcl(TxnACL acl) {
		this.acl = acl;
	}
	
	public Map<String, Object> getProperties() {
		return properties;
	}
	public void setProperties(Map<String, Object> properties2) {
		this.properties = properties2;
	}
	
}
