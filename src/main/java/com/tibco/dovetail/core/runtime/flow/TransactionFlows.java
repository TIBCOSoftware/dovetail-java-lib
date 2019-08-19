/*
* Copyright © 2018. TIBCO Software Inc.
* This file is subject to the license terms contained
* in the license file that is distributed with this file.
 */
package com.tibco.dovetail.core.runtime.flow;


import java.util.LinkedHashMap;
import java.util.Map;

public class TransactionFlows {
    private Map<String, TransactionFlow> flows = new LinkedHashMap<String, TransactionFlow>();


    public void addTransactionFlow(String txnName, TransactionFlow flow){
        flows.put(txnName, flow);
    }
    public TransactionFlow getTransactionFlow(String txnName){
        return flows.get(txnName);
    }
}
