/*
* Copyright © 2018. TIBCO Software Inc.
* This file is subject to the license terms contained
* in the license file that is distributed with this file.
 */
package com.tibco.dovetail.core.model.composer;

public class HLCDecorator {
	private String name;
	private String[] args;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String[] getArgs() {
		return args;
	}
	public void setArgs(String[] args) {
		this.args = args;
	}
}
