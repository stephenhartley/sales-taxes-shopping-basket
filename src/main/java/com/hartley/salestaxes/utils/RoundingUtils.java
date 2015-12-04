package com.hartley.salestaxes.utils;

import static com.hartley.salestaxes.model.Constants.TAX_ROUNDING_UNIT;

import java.math.BigDecimal;
import java.math.RoundingMode;

public final class RoundingUtils {

	public static BigDecimal roundTaxAmount(BigDecimal rawAmount) {
		BigDecimal divided = rawAmount.divide(TAX_ROUNDING_UNIT, 0,
				RoundingMode.UP);
		return divided.multiply(TAX_ROUNDING_UNIT);
	}

}
