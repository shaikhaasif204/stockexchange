package com.hcl.stockex.util;

public class RequestStatusUtil {
	
	private RequestStatusUtil() {
	    throw new IllegalStateException("Utility class");
	  }

	public static final Integer INITIATED = 0;
	public static final Integer REVIEWED = 1;
	public static final Integer EXECUTED = 2;
	public static final Integer DECLINED = 3;
	
}