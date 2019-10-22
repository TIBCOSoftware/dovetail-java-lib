/*
* Copyright © 2018. TIBCO Software Inc.
* This file is subject to the license terms contained
* in the license file that is distributed with this file.
 */
package com.tibco.dovetail.core.model.metadata;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResourceDescriptor {
    private Metadata metadata;
    private Map<String, Attribute> attributes = new LinkedHashMap<String, Attribute>();
    private List<Attribute> attributeList;

    public Metadata getMetadata() {
        return metadata;
    }

    public void setMetadata(Metadata metadata) {
        this.metadata = metadata;
    }

    public Attribute getAttribute(String attr) {
        return attributes.get(attr);
    }

    public void setAttributes(List<Attribute> attributes) {
    		attributeList = attributes;
        attributes.forEach(attr -> this.attributes.put(attr.getName(), attr));
    }
    public List<Attribute> getAttributes() {
       return attributeList;
    }
}
