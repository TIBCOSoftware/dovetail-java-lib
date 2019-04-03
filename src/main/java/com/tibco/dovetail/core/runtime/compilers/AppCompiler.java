package com.tibco.dovetail.core.runtime.compilers;

import java.io.InputStream;
import java.util.LinkedHashMap;

import com.tibco.dovetail.core.model.flow.FlowAppConfig;
import com.tibco.dovetail.core.model.flow.TriggerConfig;
import com.tibco.dovetail.core.runtime.trigger.ITrigger;
import com.tibco.dovetail.core.runtime.trigger.TriggerFactory;
import com.tibco.dovetail.core.runtime.util.ModelUtil;

public class AppCompiler {

	 public static LinkedHashMap<String, ITrigger> compileApp(InputStream txJson)  {
		 LinkedHashMap<String, ITrigger> triggers = new LinkedHashMap<String, ITrigger>();
		try {
			FlowAppConfig app = FlowAppConfig.parseModel(txJson);
		    TriggerConfig[] triggerConfigs = app.getTriggers();
		    if(triggerConfigs == null || triggerConfigs.length == 0)
		    		throw new RuntimeException("There is no trigger defined in the application");
		   
		    for (int i=0; i<triggerConfigs.length; i++) {
				String ref = triggerConfigs[i].getRef();
		        	String clazz = ModelUtil.getRefClassName(ref);
		        ITrigger	trigger = TriggerFactory.createTrigger(clazz); 
		        	triggers.putAll(trigger.Initialize(triggerConfigs[i]));
		    }
	        	
		    return triggers;
		}catch(Exception e) {
            throw new RuntimeException(e);
        }
    	}
}
