/*
* Copyright © 2018. TIBCO Software Inc.
* This file is subject to the license terms contained
* in the license file that is distributed with this file.
 */
package com.tibco.dovetail.function;

import java.time.Instant;

public class datetime {
	public static Instant now() {
		return Instant.now();
	}
	
	public static String addTime(String instant, long hours, long minutes, long seconds) {
		Instant dt = Instant.parse(instant);
		return dt.plusSeconds(hours * 3600 + minutes * 60 + seconds).toString();
	}
	
	public static String minusTime(String instant, long hours, long minutes, long seconds) {
		Instant dt = Instant.parse(instant);
		return dt.minusSeconds(hours * 3600 + minutes * 60 + seconds).toString();
	}
	
	public static String addDays(String instant, long days) {
		Instant dt = Instant.parse(instant);
		return dt.plusSeconds(days * 24 * 60 *60).toString();
	}
	
	public static String minusDays(String instant, long days) {
		Instant dt = Instant.parse(instant);
		return dt.minusSeconds(days * 24 * 60 *60).toString();
	}
	
	public static boolean isBefore(String instant, String compareTo) {
		Instant dt1 = Instant.parse(instant);
		Instant dt2 = Instant.parse(compareTo);
		return dt1.isBefore(dt2);
	}
	
	public static boolean isAfter(String instant, String compareTo) {
		Instant dt1 = Instant.parse(instant);
		Instant dt2 = Instant.parse(compareTo);
		return dt1.isAfter(dt2);
	}
}
