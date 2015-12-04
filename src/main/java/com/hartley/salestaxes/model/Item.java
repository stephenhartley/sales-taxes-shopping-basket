package com.hartley.salestaxes.model;

import java.math.BigDecimal;
import java.util.Objects;

import org.apache.commons.lang.StringUtils;

import static com.hartley.salestaxes.model.Constants.BASIC_SALES_TAX_PERCENTAGE;
import static com.hartley.salestaxes.model.Constants.IMPORT_TAX_RATE_PERCENTAGE;
import static com.hartley.salestaxes.model.ImportStatus.IMPORTED;
import static com.hartley.salestaxes.model.Category.OTHER;
import static com.hartley.salestaxes.utils.RoundingUtils.roundTaxAmount;

public final class Item implements ItemFeatures {

	private String name;

	private final BigDecimal price;
	private final BigDecimal basicSalesTax;
	private final BigDecimal importTax;

	private final Category category;
	private final ImportStatus importStatus;

	@Override
	public int hashCode() {
		return Objects.hash(this.name, this.price, this.category,
				this.importStatus);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final Item other = (Item) obj;
		return Objects.equals(this.name, other.name)
				&& Objects.equals(this.price, other.price)
				&& Objects.equals(this.category, other.category)
				&& Objects.equals(this.importStatus, other.importStatus);
	}

	private Item(String name, BigDecimal price, Category category,
			ImportStatus importStatus) {
		super();
		this.name = name;
		this.price = price;
		this.category = category;
		this.importStatus = importStatus;
		this.basicSalesTax = calculateBasicSalesTax();
		this.importTax = calculateImportTax();
	}

	public static Item of(String name, BigDecimal price, Category category,
			ImportStatus importStatus) {
		if (StringUtils.isBlank(name)) {
			throw new IllegalArgumentException("empty name passed");
		}
		if (price == null) {
			throw new IllegalArgumentException("null passed for price");
		}
		if (category == null) {
			throw new IllegalArgumentException("null passed for category");
		}
		if (importStatus == null) {
			throw new IllegalArgumentException("null passed for import status");
		}
		return new Item(name, price, category, importStatus);
	}

	@Override
	public BigDecimal calculateBasicSalesTax() {
		BigDecimal result = BigDecimal.ZERO;
		if (this.category == OTHER) {
			result = getTaxAmount(BASIC_SALES_TAX_PERCENTAGE);
		}
		return result;
	}

	@Override
	public BigDecimal calculateImportTax() {
		BigDecimal result = BigDecimal.ZERO;
		if (this.importStatus == IMPORTED) {
			result = getTaxAmount(IMPORT_TAX_RATE_PERCENTAGE);
		}
		return result;
	}

	private BigDecimal getTaxAmount(BigDecimal taxPercentage) {
		return roundTaxAmount(price.multiply(taxPercentage));
	}

	public String getName() {
		return name;
	}

	public BigDecimal getPriceIncludingTax() {
		return price.add(getTax());
	}

	@Override
	public BigDecimal getTax() {
		return basicSalesTax.add(importTax);
	}

}
