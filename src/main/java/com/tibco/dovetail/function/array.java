/*
* Copyright © 2018. TIBCO Software Inc.
* This file is subject to the license terms contained
* in the license file that is distributed with this file.
 */
package com.tibco.dovetail.function;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class array {
	public static boolean contains(List<Object> args, Object item) {
		return args.stream().filter( v -> v.toString().equals(item.toString())).count() > 0 ? true : false;
	}
	
	public static List<Object> create(Object... args) {
		return Arrays.asList(args);
	}
	
	public static int count(List<Object> col) {
		if(col == null)
			return 0;
		else
			return col.size();
	}
	
	public static List<Object> append(List<Object> col, Object item) {
		col.add(item);
		return col;
	}
	
	public static List<Object> delete(List<Object> col, long idx) {
		col.remove(Integer.valueOf(Long.toString(idx)));
		return col;
	}
	
	public static Object get(List<Object> col, long idx) {
		return col.get(Integer.valueOf(Long.toString(idx)));
	}
	
	public static Set<String> distinct(Collection<Object> col) {
		HashSet<String> set = new HashSet<String>();
		col.forEach(c -> set.add(c.toString()));
		return set;
	}
}
