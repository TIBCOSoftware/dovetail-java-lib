/*
* Copyright © 2018. TIBCO Software Inc.
* This file is subject to the license terms contained
* in the license file that is distributed with this file.
 */
package com.tibco.dovetail.core.runtime.util;

public class ModelUtil {
	public static String getRefClassName(String ref) {
		ref = ref.replace('-', '_');
		String[] path = ref.split("/");
        return path[path.length-3].toLowerCase() + "." + path[path.length-2].toLowerCase() + "." + path[path.length-1].toLowerCase() + "." + path[path.length-1];
	}
	
	public static String getNameSpace(String asset) {
		return asset.substring(0, asset.lastIndexOf("."));
	}
}
