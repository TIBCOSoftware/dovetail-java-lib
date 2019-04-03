/*
* Copyright © 2018. TIBCO Software Inc.
* This file is subject to the license terms contained
* in the license file that is distributed with this file.
 */
package com.tibco.dovetail.core.runtime.trigger;

import java.util.Map;

import com.tibco.dovetail.core.model.flow.TriggerConfig;
import com.tibco.dovetail.core.runtime.flow.ReplyData;
import com.tibco.dovetail.core.runtime.flow.TransactionFlow;
import com.tibco.dovetail.core.runtime.services.IContainerService;
import com.tibco.dovetail.core.runtime.transaction.ITransactionService;


public interface ITrigger {
	//return a map to look up table for the trigger
	public Map<String, ITrigger> Initialize(TriggerConfig triggerConfig);

	public ReplyData invoke(IContainerService stub, ITransactionService txn);
	TransactionFlow getHandler(String name);
}
