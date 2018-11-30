/*
* Copyright © 2018. TIBCO Software Inc.
* This file is subject to the license terms contained
* in the license file that is distributed with this file.
 */
package com.tibco.cp;
import static org.junit.Assert.*;

import java.io.IOException;
import java.io.InputStream;

import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tibco.dovetail.core.model.flow.FlowAppConfig;
import com.tibco.dovetail.core.model.flow.HandlerConfig;
import com.tibco.dovetail.core.model.flow.Resources;
import com.tibco.dovetail.core.runtime.compilers.FlowCompiler;
import com.tibco.dovetail.core.runtime.flow.TransactionFlow;

import junit.framework.Assert;

public class TestCompiler {

	@Test
	public void testShcemaCompiler() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		InputStream in = this.getClass().getResourceAsStream("transactions.json");
		
			FlowAppConfig app = FlowAppConfig.parseModel(in);
			System.out.println(mapper.writeValueAsString(app));
			assertTrue(app.getTriggers().length == 1);
			
			HandlerConfig[] handlerConfigs = app.getTriggers()[0].getHandlers();
			for(int j=0; j<handlerConfigs.length; j++) {
				String txnName = handlerConfigs[j].getSetting("transaction");
				Resources r = handlerConfigs[j].getFlow();
	
	             TransactionFlow flow = FlowCompiler.compile(r);
			}
		
		
	}
}
