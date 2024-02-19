package ecommerce.shopping;

public class AbsoluteDiscount implements Discount {

	private double value;

	public AbsoluteDiscount(double value) {
		this.value = value;
	}

	@Override
	public double discountValue(ShoppingCartItem item) {
		if(value > item.getProduct().getPrice()) {
			return item.totalPriceWithoutDiscount();
		
	}
		 return item.getAmount() * value;
		
		} 

}
