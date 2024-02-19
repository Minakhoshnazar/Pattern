package ecommerce.shopping;

public class ChooseTheBetterDiscount implements Discount {
	
	private Discount discount1;
	private Discount discount2;
	
	
	

	public ChooseTheBetterDiscount(Discount discount1, Discount discount2) {
		super();
		this.discount1 = discount1;
		this.discount2 = discount2;
	}




	@Override
	public double discountValue(ShoppingCartItem item) {
		double d1 = discount1.discountValue(item);
		double d2 = discount2.discountValue(item);
		if(d1>d2)
			return d1;
		return d2;
	}

}
