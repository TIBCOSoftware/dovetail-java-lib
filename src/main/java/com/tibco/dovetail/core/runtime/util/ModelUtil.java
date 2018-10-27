package com.tibco.dovetail.core.runtime.util;

public class ModelUtil {
	public static String getRefClassName(String ref) {
		String[] path = ref.split("/");
        return path[path.length-3].toLowerCase() + "." + path[path.length-2].toLowerCase() + "." + path[path.length-1].toLowerCase() + "." + path[path.length-1];
	}
}
