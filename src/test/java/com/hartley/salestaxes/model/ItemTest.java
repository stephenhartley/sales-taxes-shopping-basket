package com.hartley.salestaxes.model;

import static org.assertj.core.api.Assertions.assertThat;
import static com.hartley.salestaxes.model.Category.BOOKS;
import static com.hartley.salestaxes.model.Category.FOOD;
import static com.hartley.salestaxes.model.Category.MEDICAL_PRODUCT;
import static com.hartley.salestaxes.model.Category.OTHER;
import static com.hartley.salestaxes.model.ImportStatus.DOMESTIC;
import static com.hartley.salestaxes.model.ImportStatus.IMPORTED;
import static java.math.BigDecimal.ZERO;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.collections.Lists;

@Test
public final class ItemTest {

	@DataProvider(name = "itemsWhichAttractSalesTax")
	private Object[][] createItemsWhichAttractSalesTax() {
		return new Object[][] {
				{ Item.of("an item", new BigDecimal("20.00"), OTHER, DOMESTIC),
						new BigDecimal("2.00"), new BigDecimal("2.00"), new BigDecimal("22.00") },
				{
						Item.of("another item", new BigDecimal("15.00"), OTHER,
								IMPORTED), new BigDecimal("1.50"),
						new BigDecimal("2.25"),
						new BigDecimal("17.25") }, };
	}

	@DataProvider(name = "itemsWhichDoNotAttractSalesTax")
	private Object[][] createItemsWhichDoNotAttractSalesTax() {
		return new Object[][] {
				{ Item.of("an item", new BigDecimal("20.00"), BOOKS, DOMESTIC), },
				{ Item.of("yet another item", new BigDecimal("20.00"),
						MEDICAL_PRODUCT, DOMESTIC), },
				{ Item.of("another item", new BigDecimal("15.00"), FOOD,
						IMPORTED), }, };
	}

	@DataProvider(name = "itemsWhichAttractImportTax")
	private Object[][] createItemsWhichAttractImportTax() {
		return new Object[][] {
				{ Item.of("an item", new BigDecimal("20.00"), OTHER, IMPORTED),
						new BigDecimal("1.00"), new BigDecimal("3.00"), new BigDecimal("23.00") },
				{
						Item.of("another item", new BigDecimal("15.00"), OTHER,
								IMPORTED), new BigDecimal("0.75"),
						new BigDecimal("2.25"), new BigDecimal("17.25") }, };
	}

	@DataProvider(name = "itemsWhichDoNotAttractImportTax")
	private Object[][] createItemsWhichDoNotAttractImportTax() {
		return new Object[][] {
				{ Item.of("an item", new BigDecimal("20.00"), BOOKS, DOMESTIC), },
				{ Item.of("yet another item", new BigDecimal("20.00"),
						MEDICAL_PRODUCT, DOMESTIC), },
				{ Item.of("another item", new BigDecimal("15.00"), FOOD,
						DOMESTIC), }, };
	}

	@Test(dataProvider = "itemsWhichAttractSalesTax")
	public void basicSalesTaxCorrectlyCalculatedWhenSalesTaxDue(Item item,
			BigDecimal expectedSalesTax, BigDecimal notUsedInThisTest, BigDecimal alsoNotUsedInThisTest) {
		assertThat(item.calculateBasicSalesTax()).isEqualTo(expectedSalesTax);
	}

	@Test(dataProvider = "itemsWhichDoNotAttractSalesTax")
	public void basicSalesTaxCorrectlyCalculatedWhenSalesTaxNotDue(Item item) {
		assertThat(item.calculateBasicSalesTax()).isEqualTo(ZERO);
	}

	@Test(dataProvider = "itemsWhichAttractImportTax")
	public void importTaxCorrectlyCalculatedWhenImportTaxDue(Item item,
			BigDecimal expectedImportTax, BigDecimal notUsedInThisTest, BigDecimal alsoNotUsedInThisTest) {
		assertThat(item.calculateImportTax()).isEqualTo(expectedImportTax);
	}

	@Test(dataProvider = "itemsWhichDoNotAttractImportTax")
	public void importTaxCorrectlyCalculatedWhenImportTaxNotDue(Item item) {
		assertThat(item.calculateImportTax()).isEqualTo(ZERO);
	}

	@DataProvider
	public Object[][] itemsWhichAttractAnyTypeOfTax() {
		List<Object[]> result = Lists.newArrayList();
		result.addAll(Arrays.asList(createItemsWhichAttractSalesTax()));
		result.addAll(Arrays.asList(createItemsWhichAttractImportTax()));
		return result.toArray(new Object[result.size()][]);
	}

	@Test(dataProvider = "itemsWhichAttractAnyTypeOfTax")
	public void totalTaxCorrectlyCalculated(Item item,
			BigDecimal notUsedInThisTest, BigDecimal expectedTotalTax, BigDecimal alsoNotUsedInThisTest) {
		assertThat(item.getTax()).isEqualTo(expectedTotalTax);
	}

	@Test(dataProvider = "itemsWhichAttractAnyTypeOfTax")
	public void priceIncludingTaxCorrectlyCalculated(Item item,
			BigDecimal notUsedInThisTest, BigDecimal alsoNotUsedInThisTest, BigDecimal expectedPriceIncludingTax) {
		assertThat(item.getPriceIncludingTax()).isEqualTo(expectedPriceIncludingTax);
	}

}
