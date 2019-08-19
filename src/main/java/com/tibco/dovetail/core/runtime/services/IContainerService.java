/*
* Copyright © 2018. TIBCO Software Inc.
* This file is subject to the license terms contained
* in the license file that is distributed with this file.
 */
package com.tibco.dovetail.core.runtime.services;

public interface IContainerService {
    public IDataService getDataService();
    public IEventService getEventService();
    public ILogService getLogService();
    public void addContainerAsyncTask(String name, Object v);
    public Object getContainerProperty(String name);
}
