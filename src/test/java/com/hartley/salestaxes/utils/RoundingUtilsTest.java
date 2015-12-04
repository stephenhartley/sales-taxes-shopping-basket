package com.hartley.salestaxes.utils;

import static org.assertj.core.api.Assertions.assertThat;
import static com.hartley.salestaxes.utils.RoundingUtils.roundTaxAmount;

import java.math.BigDecimal;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@Test
public final class RoundingUtilsTest {
	
	// test push

	@DataProvider(name = "amounts")
	private Object[][] createAmounts() {
		return new Object[][] {
				{ new BigDecimal("0.01"), new BigDecimal("0.05") },
				{ new BigDecimal("0.02"), new BigDecimal("0.05") },
				{ new BigDecimal("0.03"), new BigDecimal("0.05") },
				{ new BigDecimal("0.04"), new BigDecimal("0.05") },
				{ new BigDecimal("0.05"), new BigDecimal("0.05") },
				{ new BigDecimal("0.06"), new BigDecimal("0.10") },
				{ new BigDecimal("0.07"), new BigDecimal("0.10") },
				{ new BigDecimal("0.08"), new BigDecimal("0.10") },
				{ new BigDecimal("0.09"), new BigDecimal("0.10") },
				{ new BigDecimal("0.10"), new BigDecimal("0.10") },
				{ new BigDecimal("0.50"), new BigDecimal("0.50") },
				{ new BigDecimal("0.00"), new BigDecimal("0.00") }, };
	}

	@Test(dataProvider = "amounts")
	public void amountsAreCorrectlyRounded(BigDecimal inputAmount,
			BigDecimal expectedResult) {
		assertThat(roundTaxAmount(inputAmount)).isEqualTo(expectedResult);
	}

}