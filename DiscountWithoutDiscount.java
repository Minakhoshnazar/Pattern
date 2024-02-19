package ecommerce.shopping;

public class DiscountWithoutDiscount implements Discount {

	@Override
	public double discountValue(ShoppingCartItem item) {
		
		return 0;
	}

}
