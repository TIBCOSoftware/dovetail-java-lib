/*
* Copyright © 2018. TIBCO Software Inc.
* This file is subject to the license terms contained
* in the license file that is distributed with this file.
 */
package com.tibco.dovetail.core.runtime.transaction;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class TxnACL {
	
	private Map<String, Map<String, String>> authorization = new LinkedHashMap<String, Map<String, String>>();
	
	public TxnACL() {
		
	}
	public Collection<String> getAuthorizedParties() {
		return authorization.keySet();
	}
	
	public Map<String, String> getConditions(String party) {
		return authorization.get(party);
	}
	
	public void addAthorizedParty(String party, Map<String, String> conditions) {
		this.authorization.put(party, conditions);
	}
}
