/*
* Copyright © 2018. TIBCO Software Inc.
* This file is subject to the license terms contained
* in the license file that is distributed with this file.
 */
package com.tibco.dovetail.function;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import com.jayway.jsonpath.DocumentContext;

public class string {
    public static String concat(Object ...arg){
        return Arrays.asList(arg).stream().map(o -> o.toString()).collect(Collectors.joining());
    }
   
    public static String tostring(Object arg){
    		if (arg == null)
    			return null;
    		
    		if(arg instanceof DocumentContext)
    			return ((DocumentContext)arg).jsonString();
    		
        return arg.toString();
    }
    
    public static String lowerCase(String arg){
        return arg.toLowerCase();
    }
    
    public static String upperCase(String arg){
        return arg.toUpperCase();
    }
    
    public static String concatWithDelimiter(String delimiter, Collection<Object> arg2){
        return arg2.stream().map(it -> it.toString()).collect(Collectors.joining(delimiter));
    }
    
    public static String[] split(String str, String delimiter){
        String[] tokens = str.split(delimiter);
        for(int i=0; i<tokens.length; i++) {
        		tokens[i] = tokens[i].trim();
        }
        
        return tokens;
    }
    
    public static boolean contains(String arg, String substr){
        return arg.contains( substr);
    }
    
    public static boolean endsWith(String arg, String substr){
        return arg.endsWith( substr);
    }
    
    public static boolean startsWith(String arg, String substr){
        return arg.startsWith( substr);
    }
    
    public static boolean equals(String arg, String str){
        return arg.equals(str);
    }
    
    public static boolean equalsIgnoreCase(String arg, String str){
        return arg.equalsIgnoreCase(str);
    }
    
    public static int index(String arg, String str){
        return arg.indexOf(str);
    }
    
    public static int lastIndex(String arg, String str){
        return arg.lastIndexOf(str);
    }
    
    public static int len(String arg){
        return arg.length();
    }
    
    public static String substring(String arg, Object start, Object end){
    		int a = Integer.valueOf(start.toString());
    		int b = Integer.valueOf(end.toString());
        return arg.substring(a, b);
    }
    
    public static String substringBefore(String arg, String str){
    		int idx = arg.indexOf(str);
    		if(idx == -1)
    			return "";
    		
        return arg.substring(0, idx);
    }
    
    public static String substringAfter(String arg, String str){
    		int idx = arg.indexOf(str);
		if(idx == -1)
			return "";
		
		return arg.substring(idx + str.length());
    }
    
    public static String trim(String arg){
        return arg.trim();
    }
}
