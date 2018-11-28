/*
* Copyright © 2018. TIBCO Software Inc.
* This file is subject to the license terms contained
* in the license file that is distributed with this file.
 */
package com.tibco.dovetail.core.runtime.services;

public interface ILogService {
    public void debug(String msg);
    public void info(String msg);
    public void warning(String msg);
    public void error(String errCode, String msg, Throwable err);
}
