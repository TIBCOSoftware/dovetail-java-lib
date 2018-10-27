package com.tibco.dovetail.core.runtime.flow;


import java.util.HashMap;
import java.util.Map;

public class TransactionFlows {
    private Map<String, TransactionFlow> flows = new HashMap<String, TransactionFlow>();


    public void addTransactionFlow(String txnName, TransactionFlow flow){
        flows.put(txnName, flow);
    }
    public TransactionFlow getTransactionFlow(String txnName){
        return flows.get(txnName);
    }
}
