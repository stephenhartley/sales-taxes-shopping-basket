package com.hartley.salestaxes.services;

import static java.math.BigDecimal.ZERO;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.hartley.salestaxes.model.Item;
import com.hartley.salestaxes.model.ReceiptItem;

public final class ReceiptServiceImpl implements ReceiptService {

	public List<ReceiptItem> getReceiptItems(List<Item> items) {
		List<ReceiptItem> result = new ArrayList<>();
		for (Item item : items) {
			result.add(ReceiptItem.of(item.getName(),
					item.getPriceIncludingTax()));
		}
		return result;
	}

	public BigDecimal getTotalTaxes(List<Item> items) {
		BigDecimal result = ZERO;
		for (Item item : items) {
			result = result.add(item.getTax());
		}
		return result;
	}

	public BigDecimal getReceiptTotal(List<Item> items) {
		BigDecimal result = ZERO;
		for (Item item : items) {
			result = result.add(item.getPriceIncludingTax());
		}
		return result;
	}

}
