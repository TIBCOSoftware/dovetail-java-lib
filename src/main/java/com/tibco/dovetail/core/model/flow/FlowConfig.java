package com.tibco.dovetail.core.model.flow;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FlowConfig extends BasicFlow{
    private String name;
    private Metadata metadata = null;
    private BasicFlow errorHandler = null;

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public Metadata getMetadata() {
        return metadata;
    }

    public void setMetadata(Metadata metadata) {
        this.metadata = metadata;
    }

    public BasicFlow getErrorHandler() {
        return errorHandler;
    }

    public void setErrorHandler(BasicFlow errorHandler) {
        this.errorHandler = errorHandler;
    }
}

