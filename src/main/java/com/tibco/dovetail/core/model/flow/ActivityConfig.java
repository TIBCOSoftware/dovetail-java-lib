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
    
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Schemas {
    		private Map<String, Map> input;

		public Map<String, Map> getInput() {
			return input;
		}

		public void setInput(Map<String, Map> input) {
			this.input = input;
		}
		
		public String getInputSchema(String name) {
			if(input == null)
				return null;
			
			return input.get(name) == null? null:input.get(name).get("value").toString();
		}
    }
}