package ecommerce.shopping;

public class WholeSaleDiscount implements Discount {
	
	private Discount discount;
	private int minNumberOfItems;

	public WholeSaleDiscount(Discount discount, int minNumberOfItems) {
		this.discount = discount;
		this.minNumberOfItems = minNumberOfItems;
	}

	@Override
	public double discountValue(ShoppingCartItem item) {
		if(item.getAmount() >= minNumberOfItems)
			return discount.discountValue(item);
		return 0;
	}

}
