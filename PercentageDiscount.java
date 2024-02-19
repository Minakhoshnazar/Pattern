package ecommerce.shopping;

public class PercentageDiscount implements Discount {
	
	private double percentage;

	public PercentageDiscount(double percentage) {
		this.percentage = percentage;
	}

	@Override
	public double discountValue(ShoppingCartItem item) {
		
		return (item.totalPriceWithoutDiscount() * percentage)/ 100;
	}

}
