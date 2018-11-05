package com.tibco.dovetail.core.runtime.services;

public interface IEventService {
    public void publish(String evtName, String metadata, String evtPayload);
}
