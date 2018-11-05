package com.tibco.dovetail.core.runtime.trigger;

public class TriggerFactory {
	public static ITrigger createTrigger(String triggerName) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		return (ITrigger) Class.forName(triggerName).newInstance();
	}
}
