/*
* Copyright © 2018. TIBCO Software Inc.
* This file is subject to the license terms contained
* in the license file that is distributed with this file.
 */
package com.tibco.dovetail.core.runtime.flow;

import java.util.Map;

public class ReplyData {
	Map<String, Object> data;
	
	public ReplyData(Map<String, Object> data) {
		this.data =data;
	}

	public Map<String, Object> getData() {
		return data;
	}
}
