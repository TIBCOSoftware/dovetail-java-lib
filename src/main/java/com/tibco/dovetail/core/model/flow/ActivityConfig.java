package com.tibco.dovetail.core.model.flow;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ActivityConfig
{
    private String ref;
    private Map<String, Object> input;
    private TaskMappings mappings;

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

    public TaskMappings getMappings ()
    {
        return mappings;
    }

    public void setMappings (TaskMappings mappings)
    {
        this.mappings = mappings;
    }
}