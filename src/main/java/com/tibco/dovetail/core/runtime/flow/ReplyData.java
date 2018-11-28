/*
* Copyright © 2018. TIBCO Software Inc.
* This file is subject to the license terms contained
* in the license file that is distributed with this file.
 */
package com.tibco.dovetail.core.runtime.flow;

public class ReplyData {
	String status;
	String message;
	String paylad;
	
	public ReplyData(String status, String message, String payload) {
		this.status = status;
		this.message = message;
		this.paylad = payload;
	}
	public String getStatus() {
		return status;
	}

	public String getPayload() {
		return paylad;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
