package com.tibco.dovetail.core.model.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SimpleAttribute {
    protected String name;
    protected String type;
    protected Object value = null;

    public void setName(String name) {
        this.name = name;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getName() {
        return name;
    }
    public Object getValue() {
        return value;
    }
    public void setValue(Object v) {
        this.value = v;
    }
    public String getType() {
        return type;
    }
}
