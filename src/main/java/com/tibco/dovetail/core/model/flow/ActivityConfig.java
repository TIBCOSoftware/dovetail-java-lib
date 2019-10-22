/*
* Copyright © 2018. TIBCO Software Inc.
* This file is subject to the license terms contained
* in the license file that is distributed with this file.
 */
package com.tibco.dovetail.core.model.flow;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ActivityConfig
{
    private String ref;
    private Map<String, Object> input;
    private Map<String, Object> settings;
    private Schemas schemas;

    public Schemas getSchemas() {
		return schemas;
	}

	public void setSchemas(Schemas schemas) {
		this.schemas = schemas;
	}

	public String getRef ()
    {
        return ref;
    }

    public void setRef (String ref)
    {
        this.ref = ref;
    }

    public Map<String, Object> getInput ()
    {
        return input;
    }

    public void setInput (Map<String, Object> input)
    {
        this.input = input;
    }
    
    public Map<String, Object> getSettings() {
		return settings;
	}

	public void setSettings(Map<String, Object> settings) {
		this.settings = settings;
	}

	@JsonIgnoreProperties(ignoreUnknown = true)
    public static class Schemas {
    		private Map<String, Map> input;
    		private Map<String, Map> output;

		public void setInput(Map<String, Map> input) {
			this.input = input;
		}
		
		public void setOutput(Map<String, Map> output) {
			this.output = output;
		}
		
		public String getInputSchema(String name) {
			if(input == null && output == null)
				return null;
			
			String result = null;
			if(input != null && input.get(name) != null)
				result = input.get(name).get("value").toString();
			
			if(result == null) {
				if(output != null && output.get(name) != null)
					result = output.get(name).get("value").toString();
			}
				
			return result;
		}
    }
}