package com.tibco.dovetail.core.model.flow;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BasicFlow {
    private LinksConfig[] links;

    private TasksConfig[] tasks;

    public LinksConfig[] getLinks ()
    {
        return links;
    }

    public void setLinks (LinksConfig[] links)
    {
        this.links = links;
    }

    public TasksConfig[] getTasks ()
    {
        return tasks;
    }

    public void setTasks (TasksConfig[] tasks)
    {
        this.tasks = tasks;
    }
}
