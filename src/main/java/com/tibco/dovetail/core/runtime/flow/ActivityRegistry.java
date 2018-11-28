/*
* Copyright © 2018. TIBCO Software Inc.
* This file is subject to the license terms contained
* in the license file that is distributed with this file.
 */
package com.tibco.dovetail.core.runtime.flow;

import com.tibco.dovetail.core.runtime.activity.IActivity;

import java.util.HashMap;
import java.util.Map;

public class ActivityRegistry {
    private static Map<String, IActivity> registry = new HashMap<String, IActivity>();

    public static synchronized void registerActivity(String ref, String clazz ) throws Exception{
        if(registry.get(ref) == null)
            registry.put(ref, (IActivity)Class.forName(clazz).newInstance());
    }

    public static IActivity getActivityInstance(String ref){

        return registry.get(ref);
    }
}
