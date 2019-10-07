/*
* Copyright © 2018. TIBCO Software Inc.
* This file is subject to the license terms contained
* in the license file that is distributed with this file.
 */
package com.tibco.dovetail.core.model.flow;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tibco.dovetail.core.model.common.SimpleAttribute;

import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Metadata {
    private List<SimpleAttribute> input;
    private List<SimpleAttribute> output;
    private Map<String, String> fe_metadata;

    public List<SimpleAttribute> getInput() {
        return input;
    }

    public void setInput(List<SimpleAttribute> input) {
        this.input = input;
    }

    public List<SimpleAttribute> getOutput() {
        return output;
    }

    public void setOutput(List<SimpleAttribute> output) {
        this.output = output;
    }

	public Map<String, String> getFe_metadata() {
		return fe_metadata;
	}

	public void setFe_metadata(Map<String, String> fe_metadata) {
		this.fe_metadata = fe_metadata;
	}
}
