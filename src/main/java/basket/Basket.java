package basket;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a shopping basket full of line items that each have a name, quantity and price per
 * item.
 */
public class Basket {

	/**
	 * Duplicates are possible, nulls will have to be prevented by the API.
	 */
	private List<Item> content = new ArrayList<>();

	/**
	 * Adds an item to the basket.
	 *
	 * @param item
	 *            the item to add
	 * @throws NullPointerException
	 *             if the item is null
	 */
	public void add(Item item) {
		if (item == null) {
			throw new NullPointerException("item cannot be null");
		}
		content.add(item);
	}

	// TODO Additional methods for removing items, changing quantities etc.

	/**
	 * Sums up all the items in the basket.
	 *
	 * @return the total costs for the basket in pence
	 */
	public long getTotalCosts() {
		return content.stream().mapToLong(Item::getSubTotal).sum();
	}

	/**
	 * Represents an item in the basket with a name and a price.
	 */
	static final class Item {

		private final String name;

		private final int quantity;

		/**
		 * Price in pence. Keeping this as an int will avoid overflows when multiplied by the
		 * quantity and since we're dealing in fruit we'll not require numbers larger than 2^31 − 1.
		 */
		private final int price;

		public Item(String name, int quantity, int price) {
			if (name == null || name.trim().isEmpty()) {
				throw new IllegalArgumentException("name cannot be null or empty");
			}
			this.name = name;

			// Checking upper boundaries might make sense as well
			if (quantity < 0) {
				throw new IllegalArgumentException("quantity cannot be negative");
			}
			this.quantity = quantity;
			if (price < 0) {
				throw new IllegalArgumentException("price cannot be negative");
			}
			this.price = price;
		}

		public String getName() {
			return name;
		}

		public long getSubTotal() {
			return quantity * price;
		}

	}

}
