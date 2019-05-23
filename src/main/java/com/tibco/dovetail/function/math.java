/*
* Copyright © 2018. TIBCO Software Inc.
* This file is subject to the license terms contained
* in the license file that is distributed with this file.
 */
package com.tibco.dovetail.function;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.stream.Collectors;

import com.tibco.dovetail.core.runtime.util.CompareUtil;

public class math {
	public static int toInt(Object val) {
		return Integer.parseInt(val.toString());
	}
	
	public static long toLong(Object val) {
		return Long.parseLong(val.toString());
	}
	
	public static Object min(RoundingMode rounding, int precision, int scale, Object ...params) {
		if(params[0] instanceof Integer)
			return minInt(params);
		else if(params[0] instanceof Long)
			return minLong(params);
		else
			return minDouble(rounding, precision, scale, params);
	}
	
	public static Object max(RoundingMode rounding, int precision, int scale, Object ...params) {
		if(params[0] instanceof Integer)
			return maxInt(params);
		else if(params[0] instanceof Long)
			return maxLong(params);
		else
			return maxDouble(rounding, precision, scale, params);
	}
	
	public static Object sum(RoundingMode rounding, int precision, int scale, Object ...params) {
		if(params[0] instanceof Integer)
			return sumInt(params);
		else if(params[0] instanceof Long)
			return sumLong(params);
		else
			return sumDouble(rounding, precision, scale, params);
	}
	
    public static Long sumLong(Object ...params){
    		Long result = Long.valueOf(params[0].toString());
   		for(int i=1; i<params.length; i++) {
   			result = java.lang.Math.addExact(result, Long.valueOf(params[i].toString()));
   		}
        return result;
    }

    public static Integer sumInt(Object ...params){
        return (Integer)Arrays.asList(params).stream().collect(Collectors.summingInt(it -> ((Integer)it).intValue()));
    }

    public static String sumDouble(RoundingMode rounding, int precision, int scale, Object ...params){
    		MathContext context = new MathContext(precision, rounding);
    		BigDecimal seed = new BigDecimal(0.0);
    		BigDecimal ttl = Arrays.asList(params).stream().map(p -> new BigDecimal(p.toString())).reduce(seed, (sum, v) -> sum.add(v));
    		BigDecimal result = new BigDecimal(ttl.toString(), context);
    		result.setScale(scale);
        return result.toPlainString();
    }
    
    @SuppressWarnings("unchecked")
	public static Integer maxInt (Object ...params) {
    		return (Integer)Arrays.asList(params).stream().map(v -> Integer.valueOf(v.toString())).collect(Collectors.maxBy(new CompareUtil()));
    }
    
    @SuppressWarnings("unchecked")
	public static Long maxLong (Object ...params) {
		return (Long)Arrays.asList(params).stream().map(v -> Long.valueOf(v.toString())).collect(Collectors.maxBy(new CompareUtil()));
    }
    
    @SuppressWarnings("unchecked")
	public static String maxDouble (RoundingMode rounding, int precision, int scale, Object ...params) {
    		MathContext context = new MathContext(precision, rounding);
		BigDecimal result = (BigDecimal)Arrays.asList(params).stream()
									.map(p -> {
											BigDecimal bd = new BigDecimal(p.toString(), context);
											bd.setScale(scale);
											return bd;
										})
									.collect(Collectors.maxBy(new CompareUtil()));
		
		return result.toPlainString();
    }
    
    @SuppressWarnings("unchecked")
   	public static Integer minInt (Object ...params) {
       	return (Integer)Arrays.asList(params).stream().map(v -> Integer.valueOf(v.toString())).collect(Collectors.maxBy(new CompareUtil()));
    }
       
    @SuppressWarnings("unchecked")
   	public static Long minLong (Object ...params) {
   		return (Long)Arrays.asList(params).stream().map(v -> Long.valueOf(v.toString())).collect(Collectors.maxBy(new CompareUtil()));
    }
       
    @SuppressWarnings("unchecked")
   	public static String minDouble (RoundingMode rounding, int precision, int scale, Object ...params) {
       	MathContext context = new MathContext(precision, rounding);
   		BigDecimal result = (BigDecimal)Arrays.asList(params).stream()
   									.map(p -> {
   											BigDecimal bd = new BigDecimal(p.toString(), context);
   											bd.setScale(scale);
   											return bd;
   										})
   									.collect(Collectors.maxBy(new CompareUtil()));
   		
   		return result.toPlainString();
   }
   
   	public static String avg (RoundingMode rounding, int precision, int scale, Object ...params) {
    		if(params.length == 0)
    			return "0";
    		
       	MathContext context = new MathContext(precision, rounding);
       	String result = sum(rounding, precision, scale, params).toString();
   		
   		BigDecimal bd = new BigDecimal(result);
   		bd.setScale(scale);
   		
   		return bd.divide(new BigDecimal(params.length), context).toPlainString();
   }
   	
   	public static Long subtractLong(Object ...params){
   		Long result = Long.valueOf(params[0].toString());
   		for(int i=1; i<params.length; i++) {
   			result = java.lang.Math.subtractExact(result, Long.valueOf(params[i].toString()));
   		}
        return result;
    }

    public static Integer subtractInt(Object ...params){
    		Integer result = (Integer)params[0];
   		for(int i=0; i<params.length; i++) {
   			result = result - (Integer)params[i];
   		}
        return result;
    }
}
