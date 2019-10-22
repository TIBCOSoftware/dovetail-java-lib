/*
* Copyright © 2018. TIBCO Software Inc.
* This file is subject to the license terms contained
* in the license file that is distributed with this file.
 */
package com.tibco.dovetail.core.runtime.flow;

import java.util.Map;

public class ReplyHandler {
	ReplyData reply = null;
    boolean hasReplied = false;
	public void setReply(Map<String, Object> data) {
		if(!hasReplied) {
			reply = new ReplyData(data);
			hasReplied = true;
		} else {
			throw new RuntimeException("Flow has been previously replied with " + this.reply.getData() + ", new reply is " + data);
		}
	}
	
	
	
	public boolean hasReplied() {
		return this.hasReplied;
	}
	
	public ReplyData getReplyData() {
		return this.reply;
	}
}
