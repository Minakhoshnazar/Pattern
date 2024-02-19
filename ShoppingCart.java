package ecommerce.shopping;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BooleanSupplier;

public class ShoppingCart {
	
	private List<ShoppingCartItem> items = new ArrayList<>();


	public double totalValue() {
		double total = 0.0;
		for (ShoppingCartItem item : items) 
		    total +=  item.getFinalPrice();
		return total;
	}
	

	public boolean hasDiscount() {
		for (ShoppingCartItem item : items)
	        if(item.getDiscount() > 0.00) return true;
		return false;
	        	
	}

	public double moneySaving() {
		double total = 0.0;
		for (ShoppingCartItem item : items) 
		    total +=  item.getDiscount();
		return total;
	}

	public int numberOfItems() {
		int number = 0;
		for (ShoppingCartItem item : items) 
		    number +=  item.getAmount();
		return number;
	}


	public void addItem(ShoppingCartItem item) {
		items.add(item);
		
	}
	
	  public void addDiscountToAllItems() {
	        for (ShoppingCartItem item : items) {
	            item.calculateTotal();
	        }
	    }

}
