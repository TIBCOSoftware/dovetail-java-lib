/*
* Copyright © 2018. TIBCO Software Inc.
* This file is subject to the license terms contained
* in the license file that is distributed with this file.
 */
package com.tibco.dovetail.core.runtime.flow;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.tibco.dovetail.core.model.common.SimpleAttribute;
import com.tibco.dovetail.core.model.flow.AppProperty;
import com.tibco.dovetail.core.model.metadata.TimeWindow;
import com.tibco.dovetail.core.runtime.engine.ContextImpl;
import com.tibco.dovetail.core.runtime.engine.FlowEngine;
import com.tibco.dovetail.core.runtime.engine.InputResolver;
import com.tibco.dovetail.core.runtime.engine.Scope;
import com.tibco.dovetail.core.runtime.services.IContainerService;
import com.tibco.dovetail.core.runtime.transaction.TxnACL;
import com.tibco.dovetail.core.runtime.transaction.TxnInputAttribute;

import co.paralleluniverse.fibers.Suspendable;

public class TransactionFlow extends BasicTransactionFlow{

    private BasicTransactionFlow errorHandler;
    private TxnACL acl = null;
    private Map<String, Object> properties = null;
    private List<TxnInputAttribute> txnInputs = new ArrayList<TxnInputAttribute>();
    private List<AttributeMapping> txnToFlowMapping = new ArrayList<AttributeMapping>();
    private List<AttributeMapping> flowToTxnMapping = new ArrayList<AttributeMapping>();
    
    private TimeWindow timewindowl;
    
    public TransactionFlow(BasicTransactionFlow flow) {
    		this.setFlowInputs(flow.getFlowInputs());
    		this.setFlowOutputs(flow.getFlowOutputs());
    		this.setTasks(flow.getTasks());
    		this.setRoot(flow.getRoot());
    		this.setTransactionName(flow.getTransactionName());
    }
	public List<TxnInputAttribute> getTxnInputs() {
		return txnInputs;
	}
	public void addTxnInput(TxnInputAttribute txnInput) {
		this.txnInputs.add(txnInput);
	}
	
//	public List<AttributeMapping> getTxnToFlowMapping() {
//		return txnToFlowMapping;
//	}
	public void addTxnToFlowMapping(AttributeMapping mapping) {
		this.txnToFlowMapping.add(mapping);
	}
	
//	public List<AttributeMapping> getFlowToTxnMapping() {
//		return flowToTxnMapping;
//	}
	public void addFlowToTxnMapping(AttributeMapping mapping) {
		this.flowToTxnMapping.add(mapping);
	}
	
	public BasicTransactionFlow getErrorHandler() {
        return errorHandler;
    }
    public void setErrorHandler(BasicTransactionFlow handler) {
        this.errorHandler = handler;
    }

    @Suspendable
    public ReplyData handle(IContainerService stub, Map<String, Object> triggerData, List<AppProperty> properties) {
    		FlowEngine engine = new FlowEngine(this);
    		
    		ContextImpl context = new ContextImpl();
    		Scope pscope = new Scope();
    		Scope lscope = new Scope(pscope, true);
    	
    		triggerData.forEach((k, v) -> lscope.addVariable(Scope.SCOPE_CURRENT, k,v));
    		properties.forEach(p -> pscope.addVariable(Scope.SCOPE_PROPERTY, p.getName(), p.getValue()));
    		
    		for(AttributeMapping m : txnToFlowMapping) {
    			InputResolver.resolveInput(context, lscope, m.getName(), m);
    		}
    		
    		context.setContainerService(stub);
    		
    		return engine.execute(context, pscope);
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
	public TimeWindow getTimewindowl() {
		return timewindowl;
	}
	public void setTimewindowl(TimeWindow timewindowl) {
		this.timewindowl = timewindowl;
	}
	
}
