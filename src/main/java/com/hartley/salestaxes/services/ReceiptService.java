package com.hartley.salestaxes.services;

import java.math.BigDecimal;
import java.util.List;

import com.hartley.salestaxes.model.Item;
import com.hartley.salestaxes.model.ReceiptItem;

public interface ReceiptService {
	
	List<ReceiptItem> getReceiptItems(List<Item> items);
	
	BigDecimal getTotalTaxes(List<Item> items);

	BigDecimal getReceiptTotal(List<Item> items);

}
