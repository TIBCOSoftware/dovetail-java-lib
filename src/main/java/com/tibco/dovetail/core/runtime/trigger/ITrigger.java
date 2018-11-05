package com.tibco.dovetail.core.runtime.trigger;

import com.tibco.dovetail.core.model.flow.TriggerConfig;
import com.tibco.dovetail.core.runtime.flow.ReplyData;
import com.tibco.dovetail.core.runtime.services.IContainerService;
import com.tibco.dovetail.core.runtime.transaction.ITransactionService;

public interface ITrigger {
	public void Initialize(TriggerConfig triggerConfig);
	public ReplyData invoke(IContainerService stub, ITransactionService txn);
}
