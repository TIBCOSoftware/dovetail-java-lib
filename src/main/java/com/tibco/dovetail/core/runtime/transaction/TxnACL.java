package com.tibco.dovetail.core.runtime.transaction;

import java.util.List;
import java.util.Map;

public class TxnACL {
	private List<String> authorizedParties = null;
	private Map<String, String> conditions = null;
	
	public TxnACL(List<String> parties, Map<String, String> conditions) {
		this.authorizedParties = parties;
		this.conditions = conditions;
	}
	public List<String> getAuthorizedParties() {
		return authorizedParties;
	}
	
	public Map<String, String> getConditions() {
		return conditions;
	}
}
