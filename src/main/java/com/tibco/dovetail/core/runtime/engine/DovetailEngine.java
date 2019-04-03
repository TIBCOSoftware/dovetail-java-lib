/*
* Copyright © 2018. TIBCO Software Inc.
* This file is subject to the license terms contained
* in the license file that is distributed with this file.
 */
package com.tibco.dovetail.core.runtime.engine;

import java.util.LinkedHashMap;

import com.tibco.dovetail.core.model.flow.FlowAppConfig;
import com.tibco.dovetail.core.model.flow.TriggerConfig;
import com.tibco.dovetail.core.runtime.trigger.ITrigger;
import com.tibco.dovetail.core.runtime.trigger.TriggerFactory;
import com.tibco.dovetail.core.runtime.util.ModelUtil;



public class DovetailEngine {
    
    private LinkedHashMap<String, ITrigger> triggers = new LinkedHashMap<String, ITrigger>();

    public DovetailEngine(FlowAppConfig app) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
    	 // triggers
	    TriggerConfig[] triggerConfigs = app.getTriggers();
	    if(triggerConfigs == null || triggerConfigs.length == 0)
	    		throw new RuntimeException("There is no trigger defined in the application");
	    
	    
	   /*  comment out to support multiple triggers
	    if(triggerConfigs.length >1)
				throw new RuntimeException("Each application can have only one type of trigger");
	    
	*/
	    for (int i=0; i<triggerConfigs.length; i++) {
			String ref = triggerConfigs[i].getRef();
	        	String clazz = ModelUtil.getRefClassName(ref);
	        ITrigger	trigger = TriggerFactory.createTrigger(clazz); 
	        	triggers.putAll(trigger.Initialize(triggerConfigs[i]));
	    }
        	
    	}
    
    //by txn or flow name
   public ITrigger getTrigger(String lookup) {
	   return this.triggers.get(lookup);
   }
   
   public LinkedHashMap<String, ITrigger> getTriggers(){
	   return this.triggers;
   }
}
