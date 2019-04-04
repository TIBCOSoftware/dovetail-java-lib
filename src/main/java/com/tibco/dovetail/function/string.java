/*
* Copyright © 2018. TIBCO Software Inc.
* This file is subject to the license terms contained
* in the license file that is distributed with this file.
 */
package com.tibco.dovetail.function;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

public class string {
    public static String concat(Object ...arg){
        return Arrays.asList(arg).stream().map(o -> o.toString()).collect(Collectors.joining());
    }
   
    public static String tostring(Object arg){
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
}
