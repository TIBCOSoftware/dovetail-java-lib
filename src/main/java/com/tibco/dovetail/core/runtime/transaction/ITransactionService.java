package com.tibco.dovetail.core.runtime.transaction;

import java.util.List;
import java.util.Map;

public interface ITransactionService {
	//the type of Object should be primitive or DocumentContext
	public Map<String, Object> resolveTransactionInput(List<TxnInputAttribute> txnInputs);
	public boolean isTransactionSecuritySupported();
	public String getTransactionName();
	public String getTransactionInitiator();
	public String getInitiatorCertAttribute(String attr);
}
