/*
* Copyright © 2018. TIBCO Software Inc.
* This file is subject to the license terms contained
* in the license file that is distributed with this file.
 */
package com.tibco.dovetail.core.runtime.trigger;

public class TriggerFactory {
	public static ITrigger createTrigger(String triggerName) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		return (ITrigger) Class.forName(triggerName).newInstance();
	}
}
