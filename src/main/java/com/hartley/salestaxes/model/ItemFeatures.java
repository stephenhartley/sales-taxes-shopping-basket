package com.hartley.salestaxes.model;

import java.math.BigDecimal;

interface ItemFeatures {

	BigDecimal calculateBasicSalesTax();

	BigDecimal calculateImportTax();

	BigDecimal getTax();

}
