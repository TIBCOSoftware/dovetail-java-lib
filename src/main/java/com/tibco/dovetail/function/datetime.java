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
	
	public static Instant addTime(Instant instant, int hours, int minutes, int seconds) {
		return instant.plusSeconds(hours * 3600 + minutes * 60 + seconds);
	}
	
}
