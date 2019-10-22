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
	
	public static BigDecimal multiply(Object op1, Object op2) {
		BigDecimal b1 = new BigDecimal(op1.toString());
		BigDecimal b2 = new BigDecimal(op2.toString());
		return b1.multiply(b2);
	}
	
	public static BigDecimal divide(Object op1, Object op2 ) {
		BigDecimal b1 = new BigDecimal(op1.toString());
		BigDecimal b2 = new BigDecimal(op2.toString());
		return b1.divide(b2);
	}
	
	public static BigDecimal negate(Object op1) {
		BigDecimal b1 = new BigDecimal(op1.toString());
		return b1.negate();
	}
	
	public static BigDecimal multiplyDecimal(BigDecimal x, BigDecimal y, int scale, RoundingMode rounding) {
		return x.multiply(y).setScale(scale, rounding);
		
	}
	public static Long multiplyLong(Long x, Long y) {
		return java.lang.Math.multiplyExact(x, y);
	}
	
	public static BigDecimal divideDecimal(String roundingMode,int scale,Object op1, Object op2 ) {
		BigDecimal b1 = new BigDecimal(op1.toString());
		BigDecimal b2 = new BigDecimal(op2.toString());
		return b1.divide(b2).setScale(scale, RoundingMode.valueOf(roundingMode));
	}

	public static BigDecimal sum(Object ...params) {
		BigDecimal seed = new BigDecimal(0.0);
		return Arrays.asList(params).stream().map(p -> new BigDecimal(p.toString())).reduce(seed, (sum, v) -> sum.add(v));
	}
	
	public static BigDecimal subtract(Object ...params){
   		BigDecimal result = new BigDecimal(params[0].toString());
   		for(int i=1; i<params.length; i++) {
   			BigDecimal op = new BigDecimal(params[i].toString());
   			result = result.subtract(op);
   		}
        return result;
    }
	
	public static Object rounding(Object number, String roundingMode, int scale) {
		if(number instanceof BigDecimal) {
			BigDecimal b = (BigDecimal)number;
			return b.setScale(scale, RoundingMode.valueOf(roundingMode));
		} else if (number instanceof Double) {
			BigDecimal b = BigDecimal.valueOf((Double)number);
			return b.setScale(scale, RoundingMode.valueOf(roundingMode)); 
		} else {
			return number;
		}
	}
	
	public static Long negateLong(Object op1) {
		return java.lang.Math.negateExact(Long.valueOf(op1.toString()));
	}
	
    public static Long sumLong(Object ...params){
    		Long result = Long.valueOf(params[0].toString());
   		for(int i=1; i<params.length; i++) {
   			result = java.lang.Math.addExact(result, Long.valueOf(params[i].toString()));
   		}
        return result;
    }

    public static Long subtractLong(Object ...params){
   		Long result = Long.valueOf(params[0].toString());
   		for(int i=1; i<params.length; i++) {
   			result = java.lang.Math.subtractExact(result, Long.valueOf(params[i].toString()));
   		}
        return result;
    }
    
    public static BigDecimal sumBigDecimal(RoundingMode rounding, int scale,  Object ...params){
    		BigDecimal seed = new BigDecimal(0.0);
    		BigDecimal ttl = Arrays.asList(params).stream().map(p -> new BigDecimal(p.toString())).reduce(seed, (sum, v) -> sum.add(v));
    		BigDecimal result = ttl.setScale(scale, rounding);
        return result;
    }
    
/*
    public static Integer sumInt(Object ...params){
        return (Integer)Arrays.asList(params).stream().collect(Collectors.summingInt(it -> ((Integer)it).intValue()));
    }
   public static Object min(RoundingMode rounding, int scale, Object ...params) {
		if(params[0] instanceof Integer)
			return minInt(params);
		else if(params[0] instanceof Long)
			return minLong(params);
		else
			return minDouble(rounding, scale, params);
	}
    public static Object max(RoundingMode rounding, int scale, Object ...params) {
		if(params[0] instanceof Integer)
			return maxInt(params);
		else if(params[0] instanceof Long)
			return maxLong(params);
		else
			return maxDouble(rounding, scale, params);
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
	public static String maxDouble (RoundingMode rounding, int scale, Object ...params) {
		BigDecimal result = (BigDecimal)Arrays.asList(params).stream()
									.map(p -> {
											BigDecimal bd = new BigDecimal(p.toString());
											bd.setScale(scale, rounding);
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
   	public static String minDouble (RoundingMode rounding, int scale, Object ...params) {
   		BigDecimal result = (BigDecimal)Arrays.asList(params).stream()
   									.map(p -> {
   											BigDecimal bd = new BigDecimal(p.toString());
   											bd.setScale(scale, rounding);
   											return bd;
   										})
   									.collect(Collectors.maxBy(new CompareUtil()));
   		
   		return result.toPlainString();
   }
   
   	public static BigDecimal avg (String rounding, int scale, Object ...params) {
    		if(params.length == 0)
    			return BigDecimal.ZERO;

       	BigDecimal result = sum(rounding, scale, params);
   		
   		return divideDecimal(rounding, scale, result, BigDecimal.valueOf(params.length));
   }
   	
   	public static BigDecimal subtractDecimal(String rounding, int scale,Object ...params){
   		BigDecimal result = new BigDecimal(params[0].toString());
   		for(int i=1; i<params.length; i++) {
   			BigDecimal op = new BigDecimal(params[i].toString());
   			result = result.subtract(op);
   		}
        return result.setScale(scale, RoundingMode.valueOf(rounding));
    }

    public static Integer subtractInt(Object ...params){
    		Integer result = (Integer)params[0];
   		for(int i=0; i<params.length; i++) {
   			result = result - (Integer)params[i];
   		}
        return result;
    }
    */
}
