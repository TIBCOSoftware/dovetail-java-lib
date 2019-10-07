package com.tibco.dovetail.core.runtime.trigger;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.tibco.dovetail.core.model.flow.AppProperty;
import com.tibco.dovetail.core.model.flow.HandlerConfig;
import com.tibco.dovetail.core.model.flow.TriggerConfig;
import com.tibco.dovetail.core.runtime.compilers.App;
import com.tibco.dovetail.core.runtime.compilers.FlowCompiler;
import com.tibco.dovetail.core.runtime.flow.ReplyData;
import com.tibco.dovetail.core.runtime.flow.TransactionFlow;
import com.tibco.dovetail.core.runtime.services.IContainerService;
import com.tibco.dovetail.core.runtime.transaction.ITransactionService;

public abstract class DefaultTriggerImpl implements ITrigger{
	protected Map<String, TransactionFlow> handlers = new LinkedHashMap<String, TransactionFlow>();
	protected List<AppProperty> properties;
	
	@Override
	public ReplyData invoke(IContainerService stub, ITransactionService txn) {
		TransactionFlow handler = handlers.get(txn.getTransactionName());
		if(handler == null)
			throw new RuntimeException("Transaction flow " + txn.getTransactionName() + " is not found");
		
		Map<String, Object> triggerData = txn.resolveTransactionInput(handler.getTxnInputs());
		
		return handler.handle(stub, triggerData, this.properties);
	}

	@Override
	public TransactionFlow getHandler(String name) {
		return handlers.get(name);
	}
	
	@Override
	public Map<String, ITrigger> Initialize(TriggerConfig triggerConfig, App app) {
		try {
			this.properties = app.getProperties();
			HandlerConfig[] handlerConfigs = triggerConfig.getHandlers();
			if(handlerConfigs == null || handlerConfigs.length == 0)
				throw new RuntimeException("No handlers defined for trigger " + triggerConfig.getName());
			
			Map<String, ITrigger> lookup = new LinkedHashMap<String, ITrigger>();
			
			for(int j=0; j<handlerConfigs.length; j++) {
				TransactionFlow flow = new TransactionFlow(app.getFlow(handlerConfigs[j].getFlowId()));
				flow = FlowCompiler.compileTriggeFlowMapping(flow, handlerConfigs[j]);
				processTxnInput(flow, handlerConfigs[j]);
	  
	            handlers.put(handlerConfigs[j].getFlowId(), flow);
	            handlers.put(flow.getTransactionName(), flow);
	            lookup.put(handlerConfigs[j].getFlowId(), this);
	            lookup.put(flow.getTransactionName(), this);
			}
			
			 return lookup;
		}catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	abstract protected void processTxnInput(TransactionFlow flow, HandlerConfig cfg)  throws Exception ;
}
