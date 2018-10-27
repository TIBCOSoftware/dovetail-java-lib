package com.tibco.dovetail.core.runtime.flow;

import java.util.ArrayList;

public class Node {

    private String taskId;
    private ArrayList<Link> toLinks = new ArrayList<Link>();
    private ArrayList<Link> fromLinks = new ArrayList<Link>();

    public Node (String id){
        this.taskId = id;
    }

    public Node addToLink(Link l){
        this.toLinks.add(l);
        return this;
    }

    public Node addFromLink(Link l){
        this.fromLinks.add(l);
        return this;
    }

    public String getTaskId() {
        return taskId;
    }

    public ArrayList<Link> getToLinks() {
        return toLinks;
    }
    public ArrayList<Link> getFromLinks() { return fromLinks; }
}
