package com.hcl.stockex.util;

public class StockTypeUtil {
	
	private StockTypeUtil() {
	    throw new IllegalStateException("Utility class");
	  }

	public static final Integer EQUITY = 0;
	public static final Integer ETF = 1;
	public static final Integer MUTUALFUND = 2;
	
}
