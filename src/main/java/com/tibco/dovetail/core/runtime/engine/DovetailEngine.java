package com.tibco.dovetail.core.runtime.engine;

import com.tibco.dovetail.core.model.flow.FlowAppConfig;
import com.tibco.dovetail.core.model.flow.TriggerConfig;
import com.tibco.dovetail.core.runtime.trigger.ITrigger;
import com.tibco.dovetail.core.runtime.trigger.TriggerFactory;
import com.tibco.dovetail.core.runtime.util.ModelUtil;



public class DovetailEngine {
    
    private ITrigger trigger = null;

    public DovetailEngine(FlowAppConfig app) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
    	 // triggers
	    TriggerConfig[] triggerConfigs = app.getTriggers();
	    if(triggerConfigs == null || triggerConfigs.length == 0)
	    		throw new RuntimeException("There is trigger defined in the application");
	    
	    if(triggerConfigs.length >1)
				throw new RuntimeException("Each application can have only one type of trigger");
	    
	
		String ref = triggerConfigs[0].getRef();
        	String clazz = ModelUtil.getRefClassName(ref);
        	trigger = TriggerFactory.createTrigger(clazz); 
        	trigger.Initialize(triggerConfigs[0]);
    	}
    
   public ITrigger getTrigger() {
	   return this.trigger;
   }
}
