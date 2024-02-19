package ecommerce.shopping;

public class PercentageAfterAmountDiscount implements Discount {
	
	private double amount;
	private double percentage;

	public PercentageAfterAmountDiscount(double amount, double percentage) {
		this.amount = amount;
		this.percentage = percentage;
	}

	@Override
	public double discountValue(ShoppingCartItem item) {
		return ((item.totalPriceWithoutDiscount() - amount) * percentage)/ 100;
	}

}
