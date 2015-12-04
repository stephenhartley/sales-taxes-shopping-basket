package com.hartley.salestaxes.model;

import java.math.BigDecimal;

public final class Constants {

	static final BigDecimal BASIC_SALES_TAX_PERCENTAGE = new BigDecimal("0.10");
	static final BigDecimal IMPORT_TAX_RATE_PERCENTAGE = new BigDecimal("0.05");

	public static final BigDecimal TAX_ROUNDING_UNIT = new BigDecimal("0.05");
	
	private Constants() {
	}

}