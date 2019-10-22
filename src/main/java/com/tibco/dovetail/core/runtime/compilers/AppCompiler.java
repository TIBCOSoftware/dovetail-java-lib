package com.tibco.dovetail.core.runtime.compilers;

import java.io.InputStream;
import java.util.LinkedHashMap;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tibco.dovetail.core.model.flow.FlowAppConfig;
import com.tibco.dovetail.core.model.flow.Resources;
import com.tibco.dovetail.core.model.flow.TriggerConfig;
import com.tibco.dovetail.core.runtime.flow.ActivityTask;
import com.tibco.dovetail.core.runtime.flow.BasicTransactionFlow;
import com.tibco.dovetail.core.runtime.trigger.ITrigger;
import com.tibco.dovetail.core.runtime.trigger.TriggerFactory;
import com.tibco.dovetail.core.runtime.util.ModelUtil;

public class AppCompiler {

	 public static App compileApp(InputStream txJson)  {
		 App app = new App();
		 LinkedHashMap<String, ITrigger> triggers = new LinkedHashMap<String, ITrigger>();
		 
		try {
			ObjectMapper mapper = new ObjectMapper();
			FlowAppConfig appconfig = FlowAppConfig.parseModel(mapper, txJson);
			app.addImports(appconfig.getImports());
			app.addProperties(appconfig.getProperties());
			
			LinkedHashMap<String, BasicTransactionFlow> flows = new LinkedHashMap<String, BasicTransactionFlow>();
	        //process flows
	        for(String k : appconfig.getResources().keySet()){
		    	    Resources r = appconfig.getResource(k);
		 
		    		BasicTransactionFlow flow = new BasicTransactionFlow();
		    		flow.setTransactionName(r.getId());
		    		if(r.getData().getMetadata() != null) {
		    		    flow.setFlowOutputs(r.getData().getMetadata().getOutput());
		             flow.setFlowInputs(r.getData().getMetadata().getInput());
		         }
		    		
		    		FlowCompiler.compileFlow(mapper, r.getData(), flow);
		    		flows.put(r.getId(), flow);
	        }
	        
	        for(BasicTransactionFlow flow : flows.values()) {
	        		for(ActivityTask t : flow.getTasks().values()) {
	        			if(t.isSubflow()) {
	        				t.addSetting("subflow", flows.get(t.getSetting("flowURI")));
	        			}
	        		}
	        }
	        app.addFlows(flows);
	        
		    TriggerConfig[] triggerConfigs = appconfig.getTriggers();
		    if(triggerConfigs == null || triggerConfigs.length == 0)
		    		throw new RuntimeException("There is no trigger defined in the application");
		   
		    for (int i=0; i<triggerConfigs.length; i++) {
				String ref = triggerConfigs[i].getRef();
		        	String clazz = ModelUtil.getRefClassName(ref);
		        ITrigger	trigger = TriggerFactory.createTrigger(clazz);
		        	triggers.putAll(trigger.Initialize(triggerConfigs[i], app));
		    }
	        	
		    app.addTriggers(triggers);
		   
		    
		    return app;
		}catch(Exception e) {
            throw new RuntimeException(e);
        }
    	}
	
	
}
