/*
* Copyright © 2018. TIBCO Software Inc.
* This file is subject to the license terms contained
* in the license file that is distributed with this file.
 */
package com.tibco.dovetail.core.runtime.engine;


import java.util.LinkedHashMap;
import java.util.Map;

public class Scope {
	public final static String SCOPE_FLOW = "$flow";
	public final static String SCOPE_ACTIVITY = "$activity";
	public final static String SCOPE_CURRENT = "$.";
	//public final static String SCOPE_ENV = "$env";
	public final static String SCOPE_PROPERTY = "$property";
	//public final static String SCOPE_LOCAL = "$.";
	public final static String SCOPE_ITERATION = "$iteration";
	
    Scope parent = null;
    private Map<String, Object> activity = new LinkedHashMap<String, Object>();
    private Map<String, Object> flow = new LinkedHashMap<String, Object>();
  //  private Map<String, Object> local = new LinkedHashMap<String, Object>();
    private Map<String, Object> current = new LinkedHashMap<String, Object>();
    private Map<String, Object> property = new LinkedHashMap<String, Object>();
    private Map<String, Object> iterate = new LinkedHashMap<String, Object>();
    
    private Map<String, Map<String, Object>> scopes;
    private boolean isRootScope;

    public Scope(){
        scopes = new LinkedHashMap<String, Map<String, Object>>() ;
        scopes.put(Scope.SCOPE_ACTIVITY, activity);
        scopes.put(Scope.SCOPE_FLOW, flow);
      //  scopes.put(Scope.SCOPE_LOCAL, local);
        scopes.put(Scope.SCOPE_CURRENT, current);
        scopes.put(Scope.SCOPE_PROPERTY, property);
        scopes.put(Scope.SCOPE_ITERATION, iterate);
        this.isRootScope = true;
    }

    public Scope(Scope parentScope, boolean isTopScope) {
    		this();
    		this.parent = parentScope;
    		this.isRootScope = isTopScope;
    }
    
    public boolean isRootScope() {
    		return this.isRootScope;
    }
    public Object getVariable(String scope, String var){
        Map<String, Object> scopeObj = scopes.get(scope);
        Object val = scopeObj.get(var);
        if(val == null && scope != Scope.SCOPE_CURRENT && parent != null) {
        		val =parent.getVariable(scope, var);
        }
        
        return val;
    }
    
    public Object getVariable(String var){
    		Object val = null;
    		if(var.startsWith(Scope.SCOPE_FLOW)) {
    			val = getVariable(Scope.SCOPE_FLOW, var.substring(6));
    		} else if(var.startsWith(Scope.SCOPE_ACTIVITY)) {
    			val = getVariable(Scope.SCOPE_ACTIVITY, var.substring(10));
    		} else if(var.startsWith(Scope.SCOPE_ITERATION)) {
    			if(var.equals("$iteration[key]")) {
    				val = getVariable(Scope.SCOPE_ITERATION, "key");
    			} else {
    				val = getVariable(Scope.SCOPE_ITERATION, "value");
    			}
    		} else if(var.startsWith(SCOPE_PROPERTY)){
    			val = getVariable(SCOPE_PROPERTY, var.substring(10, var.length()-1));
    		} else if(var.startsWith(SCOPE_CURRENT)){
    			val = getVariable(Scope.SCOPE_CURRENT, var.substring(2)); //for txn input mapping
    		} 
    		
    		return val;	
    }

    public void addVariable(String scope, String var, Object value){
        scopes.get(scope).put(var, value);
    }
    
    public static boolean isScopeVariable(String mapexpr) {
	    	if(mapexpr.contains(SCOPE_FLOW) || mapexpr.contains(SCOPE_ACTIVITY) || mapexpr.contains(SCOPE_CURRENT) ||
					mapexpr.contains(SCOPE_ITERATION) || mapexpr.contains(SCOPE_PROPERTY)) {
	    		return true;
	    	} else {
	    		return false;
	    	}
    }
}
