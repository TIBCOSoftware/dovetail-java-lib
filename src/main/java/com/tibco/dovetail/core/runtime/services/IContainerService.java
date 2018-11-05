package com.tibco.dovetail.core.runtime.services;

public interface IContainerService {
    public IDataService getDataService();
    public IEventService getEventService();
    public ILogService getLogService();
}
