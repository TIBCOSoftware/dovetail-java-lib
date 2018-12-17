/*
* Copyright © 2018. TIBCO Software Inc.
* This file is subject to the license terms contained
* in the license file that is distributed with this file.
 */
package com.tibco.dovetail.core.runtime.engine;


import java.util.HashMap;
import java.util.Map;

public class Scope {
	public final static String SCOPE_FLOW = "$flow";
	public final static String SCOPE_ACTIVITY = "$activity";
	public final static String SCOPE_CURRENT = "$current";
	public final static String SCOPE_ENV = "$env";
	public final static String SCOPE_PROPERTY = "$property";
	public final static String SCOPE_LOCAL = "$.";
	
    Scope parent = null;
    private Map<String, Object> activity = new HashMap<String, Object>();
    private Map<String, Object> flow = new HashMap<String, Object>();
    private Map<String, Object> local = new HashMap<String, Object>();
    private Map<String, Object> current = new HashMap<String, Object>();
    
    private Map<String, Map<String, Object>> scopes;

    public Scope(){
        scopes = new HashMap<String, Map<String, Object>>() ;
        scopes.put(Scope.SCOPE_ACTIVITY, activity);
        scopes.put(Scope.SCOPE_FLOW, flow);
        scopes.put(Scope.SCOPE_LOCAL, local);
        scopes.put(Scope.SCOPE_CURRENT, current);
    }

    public Scope(Scope parentScope) {
    		this();
    		this.parent = parentScope;
    }
    public Object getVariable(String scope, String var){
        Map<String, Object> scopeObj = scopes.get(scope);
        Object val = scopeObj.get(var);
        if(val == null && parent != null)
			return parent.getVariable(scope, var);
		else
			return val;
    }
    
    public Object getVariable(String var){
    		Object val = null;
    		if(var.startsWith(Scope.SCOPE_FLOW)) {
    			val = getVariable(Scope.SCOPE_FLOW, var.substring(6));
    		} else if(var.startsWith(Scope.SCOPE_ACTIVITY)) {
    			val = getVariable(Scope.SCOPE_ACTIVITY, var.substring(10));
    		} else if(var.startsWith(Scope.SCOPE_CURRENT)) {
    			if(var.equals("$current.iteration.key")) {
    				val = getVariable(Scope.SCOPE_CURRENT, "key");
    			} else {
    				val = getVariable(Scope.SCOPE_CURRENT, "value");
    			}
    		} else {
    			val = getVariable(Scope.SCOPE_LOCAL, "from");
    		}
    		
    		return val;	
    }

    public void addVariable(String scope, String var, Object value){
        scopes.get(scope).put(var, value);
    }
    
    public static boolean isScopeVariable(String mapexpr) {
	    	if(mapexpr.contains(SCOPE_FLOW) || mapexpr.contains(SCOPE_ACTIVITY) || mapexpr.contains(SCOPE_CURRENT) ||
					mapexpr.contains(SCOPE_ENV) || mapexpr.contains(SCOPE_PROPERTY) || mapexpr.contains(SCOPE_LOCAL)) {
	    		return true;
	    	} else {
	    		return false;
	    	}
    }
}
