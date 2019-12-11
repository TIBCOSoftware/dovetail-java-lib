/*
* Copyright © 2018. TIBCO Software Inc.
* This file is subject to the license terms contained
* in the license file that is distributed with this file.
 */
package com.tibco.dovetail.core.model.activity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tibco.dovetail.core.model.common.SimpleAttribute;
import com.tibco.dovetail.core.runtime.flow.ActivityRegistry;
import com.tibco.dovetail.core.runtime.util.ModelUtil;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ActivityModel {
    private String ref;
    private Map<String, SimpleAttribute> inputs = new LinkedHashMap<String, SimpleAttribute>();
    private Map<String, SimpleAttribute> outputs = new LinkedHashMap<String, SimpleAttribute>();

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }
    
    public SimpleAttribute getInputs(String name) {
        return inputs.get(name);
    }

    public void setInputs(List<SimpleAttribute> inputs) {
        inputs.forEach(in -> this.inputs.put(in.getName(), in));
    }

    public SimpleAttribute getOutputs(String name) {
        return outputs.get(name);
    }

    public void setOutputs(List<SimpleAttribute> outputs) {
        outputs.forEach(out -> this.outputs.put(out.getName(), out));
    }


	public static ActivityModel loadModel(ObjectMapper mapper, String ref) throws Exception{
        String clazz = ModelUtil.getRefClassName(ref);
        InputStream isJson = Class.forName(clazz).getResourceAsStream("activity.json");
        if (isJson == null)
            throw new FileNotFoundException("activity.json file is not found for " + ref);

        ActivityModel model = mapper.readValue(isJson, ActivityModel.class);
        ActivityRegistry.registerActivity(ref, clazz);
        isJson.close();
        return model;
    }
	
	public static void registerActivity(String ref) throws Exception {
		 String clazz = ModelUtil.getRefClassName(ref);
		 ActivityRegistry.registerActivity(ref, clazz);
	}
}
