/*
* Copyright © 2018. TIBCO Software Inc.
* This file is subject to the license terms contained
* in the license file that is distributed with this file.
 */
package com.tibco.dovetail.core.model.flow;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Resources {
   private String id;
   private FlowConfig data;

    public String getId() {
        return id;
    }

    public void setId(String id) {

        this.id = id;
    }

    public FlowConfig getData() {
        return data;
    }

    public void setData(FlowConfig data) {
        this.data = data;
    }
}
