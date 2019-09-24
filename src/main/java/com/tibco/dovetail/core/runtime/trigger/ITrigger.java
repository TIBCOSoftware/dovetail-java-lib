/*
* Copyright © 2018. TIBCO Software Inc.
* This file is subject to the license terms contained
* in the license file that is distributed with this file.
 */
package com.tibco.dovetail.core.runtime.trigger;

import java.util.List;
import java.util.Map;

import com.tibco.dovetail.core.model.flow.TriggerConfig;
import com.tibco.dovetail.core.runtime.flow.ReplyData;
import com.tibco.dovetail.core.runtime.flow.TransactionFlow;
import com.tibco.dovetail.core.runtime.services.IContainerService;
import com.tibco.dovetail.core.runtime.transaction.ITransactionService;

import co.paralleluniverse.fibers.Suspendable;
import com.tibco.dovetail.core.model.flow.AppProperty;


public interface ITrigger {
	
	@Suspendable
	public ReplyData invoke(IContainerService stub, ITransactionService txn);
	public TransactionFlow getHandler(String name);

	//return a map to look up table for the trigger
	public Map<String, ITrigger> Initialize(TriggerConfig triggerConfig, List<AppProperty> properties);
}
