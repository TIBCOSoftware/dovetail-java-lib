package com.tibco.dovetail.core.runtime.services;

public interface ILogService {
    public void debug(String msg);
    public void info(String msg);
    public void warning(String msg);
    public void error(String errCode, String msg, Throwable err);
}
