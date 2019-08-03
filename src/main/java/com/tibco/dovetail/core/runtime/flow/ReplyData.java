/*
* Copyright © 2018. TIBCO Software Inc.
* This file is subject to the license terms contained
* in the license file that is distributed with this file.
 */
package com.tibco.dovetail.core.runtime.flow;

public class ReplyData {
	String data;
	
	public ReplyData(String data) {
		this.data =data;
	}

	public String getData() {
		return data;
	}
}
