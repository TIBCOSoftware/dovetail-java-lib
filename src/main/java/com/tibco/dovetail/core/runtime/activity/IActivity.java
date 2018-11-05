package com.tibco.dovetail.core.runtime.activity;

import com.tibco.dovetail.core.runtime.engine.Context;

public interface IActivity {
    public void eval(Context context) throws IllegalArgumentException;
}
