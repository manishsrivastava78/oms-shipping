package com.tcs.eas.event.utility;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.RandomStringUtils;

/**
 * 
 * @author 44745
 *
 */
public class Utility {
	/**
	 * 
	 * @param stringLength
	 * @param letters
	 * @param numbers
	 * @return
	 */
	public static String getTrackingNumber(int stringLength,boolean letters, boolean numbers) {
	    return RandomStringUtils.random(stringLength, letters, numbers);
	}
	
	/**
	 * 
	 * @return
	 */
	public static String getTrackingNumber() {
	    return RandomStringUtils.random(12, true, true);
	}
	
	/**
	 * 
	 * @param request
	 * @return
	 */
	public static String getClientIp(HttpServletRequest request) {
		String remoteAddr = "";
		if (request != null) {
			remoteAddr = request.getHeader("X-FORWARDED-FOR");
			if (remoteAddr == null || "".equals(remoteAddr)) {
				remoteAddr = request.getRemoteAddr();
			}
		}
		return remoteAddr;
	}
	
	/**
	 * 
	 * @param request
	 * @return
	 */
	public static Map<String, String> getRequestHeaderValues(HttpServletRequest request) {
		Map<String, String> map = new HashMap<>();
		Enumeration<String> headerNames = request.getHeaderNames();
		while (headerNames.hasMoreElements()) {
			String key = (String) headerNames.nextElement();
			String value = request.getHeader(key);
			map.put(key, value);
		}
		return map;
	}

}
