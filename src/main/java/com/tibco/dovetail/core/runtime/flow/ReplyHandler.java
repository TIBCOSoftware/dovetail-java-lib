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
	
	public boolean hasReplied() {
		return this.hasReplied;
	}
	
	public ReplyData getReplyData() {
		return this.data;
	}
}
