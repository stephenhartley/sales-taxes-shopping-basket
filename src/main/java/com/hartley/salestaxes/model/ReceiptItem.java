package com.hartley.salestaxes.model;

import java.math.BigDecimal;
import java.util.Objects;

import org.apache.commons.lang.StringUtils;

public final class ReceiptItem {

	private String name;
	private BigDecimal priceIncludingTax;

	@Override
	public int hashCode() {
		return Objects.hash(this.name, this.priceIncludingTax);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final ReceiptItem other = (ReceiptItem) obj;
		return Objects.equals(this.name, other.name)
				&& Objects.equals(this.priceIncludingTax,
						other.priceIncludingTax);
	}

	private ReceiptItem(String name, BigDecimal priceIncludingTax) {
		super();
		this.name = name;
		this.priceIncludingTax = priceIncludingTax;
	}

	public static ReceiptItem of(String name, BigDecimal priceIncludingTax) {
		if (StringUtils.isBlank(name)) {
			throw new IllegalArgumentException("empty name passed");
		}
		if (priceIncludingTax == null) {
			throw new IllegalArgumentException("null passed for price");
		}
		return new ReceiptItem(name, priceIncludingTax);
	}

}
