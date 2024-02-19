package ecommerce.shopping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ShoppingCartItem {

	
	private Product product;
	private int amount;
	private Discount discount = new DiscountWithoutDiscount();
	private List<Discount> discounts;
	
	public ShoppingCartItem(Product product, int amount) {
		super();
		this.product = product;
		this.amount = amount;
		this.discounts = new ArrayList<>();
	}

	public Product getProduct() {
		return product;
	}

	public int getAmount() {
		return amount;
	}

	public double getFinalPrice() { 
	    	return totalPriceWithoutDiscount() - discount.discountValue(this);
		}

	public double totalPriceWithoutDiscount() {
		return getProduct().getPrice() * getAmount();
	}
	
	public double getDiscount() {
	    	return discount.discountValue(this);
	
		}
	

	public void addDiscount(Discount d) {
		if(discount instanceof DiscountWithoutDiscount)
			this.discount = d;
		this.discount = new ChooseTheBetterDiscount(this.discount, d);
		
	}

	public double calculateTotal() {
	
	      double total = product.getPrice() * amount;

	        double maxDiscount = 0.0;
	        List<Discount> discounts = Arrays.asList(discount); 
	        for (Discount discount : discounts) {
	            double discountValue = discount.discountValue(this);
	            if (discountValue > maxDiscount) {
	                maxDiscount = discountValue;
	            }
	        }

	        return total - maxDiscount;
	    }
	}

	


