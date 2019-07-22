/*
* Copyright © 2018. TIBCO Software Inc.
* This file is subject to the license terms contained
* in the license file that is distributed with this file.
 */
package com.tibco.dovetail.core.runtime.flow;

public class ReplyHandler {
	ReplyData data = null;
    boolean hasReplied = false;
	public void setReply(String status, String message, String payload) {
		if(!hasReplied) {
			data = new ReplyData(status, message, payload);
			hasReplied = true;
		}
	}
	
	public void setReply(String status, Object o) {
		if(!hasReplied) {
			data = new ReplyData(status, o);
			hasReplied = true;
		} 
	}
	
	public boolean hasReplied() {
		return this.hasReplied;
	}
	
	public ReplyData getReplyData() {
		return this.data;
	}
}
