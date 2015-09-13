package basket;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import basket.Basket.Item;

public class BasketTest {

	private Basket basket;

	@Before
	public void setUp() throws Exception {
		basket = new Basket();
	}

	@Test
	public void should_calculate_total_costs() {
		basket.add(new Item("Banana", 5, 230));
		basket.add(new Item("Dragon Fruit", 2, 720));
		assertEquals(2590, basket.getTotalCosts());
	}

	@Test(expected = NullPointerException.class)
	public void should_fail_on_null_item() {
		basket.add(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void should_fail_on_invalid_name() {
		basket.add(new Item(" ", 100, 100));
	}

	@Test(expected = IllegalArgumentException.class)
	public void should_fail_on_negative_quantity() {
		basket.add(new Item("Apple", -1, 100));
	}

	@Test(expected = IllegalArgumentException.class)
	public void should_fail_on_negative_price() {
		basket.add(new Item("Apple", 100, -1));
	}

}
