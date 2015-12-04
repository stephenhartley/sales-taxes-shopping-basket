package com.hartley.salestaxes.services;

import static com.hartley.salestaxes.model.Category.OTHER;
import static com.hartley.salestaxes.model.Category.BOOKS;
import static com.hartley.salestaxes.model.Category.FOOD;
import static com.hartley.salestaxes.model.ImportStatus.DOMESTIC;
import static com.hartley.salestaxes.model.ImportStatus.IMPORTED;
import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.hartley.salestaxes.model.Category;
import com.hartley.salestaxes.model.Item;

@Test
public final class ReceiptServiceTest {

	private List<Item> basket1 = new ArrayList<>();
	private List<Item> basket2 = new ArrayList<>();
	private List<Item> basket3 = new ArrayList<>();

	private ReceiptService service = new ReceiptServiceImpl();

	private static final Item BASKET1_ITEM1 = Item.of("book", new BigDecimal(
			"12.49"), BOOKS, DOMESTIC);
	private static final Item BASKET1_ITEM2 = Item.of("music CD",
			new BigDecimal("14.99"), OTHER, DOMESTIC);
	private static final Item BASKET1_ITEM3 = Item.of("chocolate bar",
			new BigDecimal("0.85"), FOOD, DOMESTIC);

	private static final Item BASKET2_ITEM1 = Item.of(
			"imported box chocolates", new BigDecimal("10.00"), FOOD, IMPORTED);
	private static final Item BASKET2_ITEM2 = Item
			.of("imported bottle perfume", new BigDecimal("47.50"), OTHER,
					IMPORTED);

	private static final Item BASKET3_ITEM1 = Item
			.of("imported bottle perfume", new BigDecimal("27.99"), OTHER,
					IMPORTED);
	private static final Item BASKET3_ITEM2 = Item.of("bottle perfume",
			new BigDecimal("18.99"), OTHER, DOMESTIC);
	private static final Item BASKET3_ITEM3 = Item.of("packet headache pills",
			new BigDecimal("9.75"), Category.MEDICAL_PRODUCT, DOMESTIC);
	private static final Item BASKET3_ITEM4 = Item.of("imported chocolates",
			new BigDecimal("11.25"), FOOD, IMPORTED);

	@BeforeClass
	public void setup() {
		basket1.add(BASKET1_ITEM1);
		basket1.add(BASKET1_ITEM2);
		basket1.add(BASKET1_ITEM3);

		basket2.add(BASKET2_ITEM1);
		basket2.add(BASKET2_ITEM2);

		basket3.add(BASKET3_ITEM1);
		basket3.add(BASKET3_ITEM2);
		basket3.add(BASKET3_ITEM3);
		basket3.add(BASKET3_ITEM4);
	}

	@DataProvider(name = "baskets")
	private Object[][] createBasket1() {
		return new Object[][] {
				{ basket1, new BigDecimal("1.50"), new BigDecimal("29.83") },
				{ basket2, new BigDecimal("7.65"), new BigDecimal("65.15") },
				{ basket3, new BigDecimal("6.70"), new BigDecimal("74.68") }, };
	}

	@Test(dataProvider = "baskets")
	public void totalSalesTaxIsCorrectlyCalculated(List<Item> basket,
			BigDecimal expectedTotalSalesTax, BigDecimal notUsedInThisTest) {
		BigDecimal actualTotalSalesTax = service.getTotalTaxes(basket);
		assertThat(actualTotalSalesTax).isEqualTo(expectedTotalSalesTax);
	}

	@Test(dataProvider = "baskets")
	public void grandTotalIsCorrectlyCalculated(List<Item> basket,
			BigDecimal notUsedInThisTest, BigDecimal expectedGrandTotal) {
		BigDecimal actualGrandTotal = service.getReceiptTotal(basket);
		assertThat(actualGrandTotal).isEqualTo(expectedGrandTotal);
	}

}
