/*
* Copyright © 2018. TIBCO Software Inc.
* This file is subject to the license terms contained
* in the license file that is distributed with this file.
 */
package com.tibco.dovetail.core.runtime.util;

import java.math.BigDecimal;
import java.util.Comparator;

@SuppressWarnings("rawtypes")
public class CompareUtil implements Comparator{
	
	@Override
	public int compare(Object o1, Object o2) {
		if(compare(o1, o2, "=="))
			return 0;
		else if(compare(o1, o2, ">"))
			return 1;
		else
			return -1;
	}

    public static boolean compare(Object v1, Object v2, String op) {
    	if (op.equalsIgnoreCase("in")) {
			boolean same = false;
			Object[] values = (Object[])v2;
			for(int i=0; i<values.length; i++) {
				if(compare(v1, values[i], "==")){
					return true;
				}
			}
			return false;
		} else {
	        if(v1 instanceof String || v2 instanceof String)
	            return stringCompare(v1, v2, op);
	        else if(v1 instanceof BigDecimal || v2 instanceof BigDecimal)
	            return numberCompare((BigDecimal)v1, (BigDecimal)v2, op);
	        else
	            return numberCompare(((Number)v1).longValue(), ((Number)v2).longValue(), op);
		}
    }

    private static boolean stringCompare(Object v1, Object v2, String op){
		int r = v1.toString().trim().compareTo(v2.toString().trim());
		return getCompareResult(r, op);
    }


    private static boolean getCompareResult(int r, String op) {
	    	if (op.equals("<")) {
	            return r < 0 ? true : false;
	        } else if(op.equals("==")){
	            return  r == 0?true:false;
	        } else if(op.equals(">")) {
	            return  r > 0 ? true : false;
	        } else if(op.equals("<=")) {
	            return r <= 0 ? true : false;
	        } else if(op.equals(">=")){
	            return r >= 0 ? true : false;
	        } else if(op.equals("!=") || op.equals("<>")) {
	            return r == 0 ? false : true;
	        } else
	            return false;
    }
    private static boolean numberCompare(long v1, long v2, String op){
    		int r = Long.compare(v1, v2);
		return getCompareResult(r, op);
    }

    private static boolean numberCompare(BigDecimal v1, BigDecimal v2, String op){

    		int r = v1.compareTo(v2);
    		return getCompareResult(r, op);
    }
}
