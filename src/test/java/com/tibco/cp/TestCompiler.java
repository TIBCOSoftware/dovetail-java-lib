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

import junit.framework.Assert;

public class TestCompiler {

	@Test
	public void testShcemaCompiler() {
		ObjectMapper mapper = new ObjectMapper();
		InputStream in = this.getClass().getResourceAsStream("transactions.json");
		try {
			FlowAppConfig app = FlowAppConfig.parseModel(in);
			System.out.println(mapper.writeValueAsString(app));
			assertTrue(app.getTriggers().length == 1);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
