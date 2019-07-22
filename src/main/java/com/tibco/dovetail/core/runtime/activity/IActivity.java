/*
* Copyright © 2018. TIBCO Software Inc.
* This file is subject to the license terms contained
* in the license file that is distributed with this file.
 */
package com.tibco.dovetail.core.runtime.activity;

import com.tibco.dovetail.core.runtime.engine.Context;

import co.paralleluniverse.fibers.Suspendable;

public interface IActivity {
	@Suspendable
    public void eval(Context context) throws IllegalArgumentException;
}
