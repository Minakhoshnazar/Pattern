package ecommerce.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import ecommerce.shopping.AbsoluteDiscount;
import ecommerce.shopping.Discount;
import ecommerce.shopping.PercentageAfterAmountDiscount;
import ecommerce.shopping.PercentageDiscount;
import ecommerce.shopping.Product;
import ecommerce.shopping.ShoppingCart;
import ecommerce.shopping.ShoppingCartItem;
import ecommerce.shopping.WholeSaleDiscount;

class ShoppingCartTest {

	@Test
	void emptyShoppingCart() {
		ShoppingCart sc = new ShoppingCart();
		assertEquals(0.0, sc.totalValue());
		assertFalse(sc.hasDiscount());
		assertEquals(0.0, sc.moneySaving());
		assertEquals(0, sc.numberOfItems());
	}

	@Test
	void ShoppingCartOneItem() {
		ShoppingCart sc = new ShoppingCart();
		ShoppingCartItem item = new ShoppingCartItem(new Product("Dath Vader Funko", 15.0), 1);
		
		sc.addItem(item);
		assertEquals(15.0, sc.totalValue());
		assertFalse(sc.hasDiscount());
		assertEquals(0.0, sc.moneySaving());
		assertEquals(1, sc.numberOfItems());
	}
	
	@Test
	void ShoppingCartMoreThanOneOfTheSameItem() {
		ShoppingCart sc = new ShoppingCart();
		ShoppingCartItem item = new ShoppingCartItem(new Product("Dath Vader Funko", 15.0), 3);
		
		sc.addItem(item);
		assertEquals(45.0, sc.totalValue());
		assertFalse(sc.hasDiscount());
		assertEquals(0.0, sc.moneySaving());
		assertEquals(3, sc.numberOfItems());
	}
	
	@Test
	void ShoppingCartWithAbsoluteDiscount() {
		ShoppingCart sc = new ShoppingCart();
		ShoppingCartItem item = new ShoppingCartItem(new Product("Kyle Rein Funko", 15.0), 1);
		Discount d = new AbsoluteDiscount(5.0);
		item.addDiscount(d);
		sc.addItem(item);
		
		assertEquals(10.0, sc.totalValue());
		assertTrue(sc.hasDiscount());
		assertEquals(5.0, sc.moneySaving());
		assertEquals(1, sc.numberOfItems());
	}
	
	@Test
	void discountGreaterThanValue() {
		ShoppingCart sc = new ShoppingCart();
		ShoppingCartItem item = new ShoppingCartItem(new Product("Kyle Rein Funko", 15.0), 1);
		Discount d = new AbsoluteDiscount(20.0);
		item.addDiscount(d);
		sc.addItem(item);
		
		assertEquals(0.0, sc.totalValue());
		assertTrue(sc.hasDiscount());
		assertEquals(15.0, sc.moneySaving());
		assertEquals(1, sc.numberOfItems());
	}
	
	@Test
	void ShoppingCartWithPercentageDiscount() {
		ShoppingCart sc = new ShoppingCart();
		ShoppingCartItem item = new ShoppingCartItem(new Product("Kyle Rein Funko", 15.0), 1);
		Discount d = new PercentageDiscount(20.0);
		item.addDiscount(d);
		sc.addItem(item);
		
		assertEquals(12.0, sc.totalValue());
		assertTrue(sc.hasDiscount());
		assertEquals(3.0, sc.moneySaving());
		assertEquals(1, sc.numberOfItems());
	}
	
	@Test
	void ShoppingCartLotsOfItems() {
		ShoppingCart sc = new ShoppingCart();
		
		ShoppingCartItem item1 = new ShoppingCartItem(new Product("Dath Vader Funko", 15.0), 2);
		sc.addItem(item1);
		
		ShoppingCartItem item2 = new ShoppingCartItem(new Product("Kyle Rein Funko", 15.0), 2);
		Discount d1 = new AbsoluteDiscount(5.0);
		item2.addDiscount(d1);
		sc.addItem(item2);
		
		ShoppingCartItem item3 = new ShoppingCartItem(new Product("Boba Fett Funko", 15.0), 1);
		Discount d2 = new PercentageDiscount(20.0);
		item3.addDiscount(d2);
		sc.addItem(item3);
		
		assertEquals(62.0, sc.totalValue());
		assertTrue(sc.hasDiscount());
		assertEquals(13.0, sc.moneySaving());
		assertEquals(5, sc.numberOfItems());
	}
	
	@Test
	void ShoppingCartWithPercentageAfterAmountDiscount() {
		ShoppingCart sc = new ShoppingCart();
		ShoppingCartItem item = new ShoppingCartItem(new Product("BB-8 Funko", 20.0), 1);
		Discount d = new PercentageAfterAmountDiscount(10.0, 30.0);
		item.addDiscount(d);
		sc.addItem(item);
		
		assertEquals(17.0, sc.totalValue());
		assertTrue(sc.hasDiscount());
		assertEquals(3.0, sc.moneySaving());
		assertEquals(1, sc.numberOfItems());
	}
	
	@Test
	void wholeSaleDiscount() {
		ShoppingCart sc = new ShoppingCart();
		ShoppingCartItem item = new ShoppingCartItem(new Product("Dath Vader Funko", 15.0), 7);
		
		
		Discount d1 = new PercentageDiscount(20.0);
		Discount d2 = new WholeSaleDiscount(d1, 5);
		item.addDiscount(d2);
		
		
		sc.addItem(item);
		assertEquals(84.0, sc.totalValue());
		assertTrue(sc.hasDiscount());
		assertEquals(21.0, sc.moneySaving());
		assertEquals(7, sc.numberOfItems());
	}
	
	@Test
	void wholeSaleDiscountWithoutMinItems() {
		ShoppingCart sc = new ShoppingCart();
		ShoppingCartItem item = new ShoppingCartItem(new Product("Dath Vader Funko", 15.0), 7);
		
		
		Discount d1 = new PercentageDiscount(20.0);
		Discount d2 = new WholeSaleDiscount(d1, 10);
		item.addDiscount(d2);
		
		
		sc.addItem(item);
		assertEquals(105.0, sc.totalValue());
		assertFalse(sc.hasDiscount());
		assertEquals(0.0, sc.moneySaving());
		assertEquals(7, sc.numberOfItems());
	}
	
	@Test
	void choosingPercentageDiscount() {
		ShoppingCart sc = new ShoppingCart();
		Product product = new Product("Kyle Rein Funko", 15.0);
		ShoppingCartItem item = new ShoppingCartItem(product, 1);
		Discount d1 = new PercentageDiscount(20.0);
		item.addDiscount(d1);
		Discount d2 = new AbsoluteDiscount(2.0);
		item.addDiscount(d2);
		sc.addItem(item);
		
		
		assertEquals(12.0, sc.totalValue());
		assertTrue(sc.hasDiscount());
		assertEquals(3.0, sc.moneySaving());
		
	}
	
	@Test
	void discountForAllItemsInShoppingCart() {
	    ShoppingCart sc = new ShoppingCart();

	    ShoppingCartItem item1 = new ShoppingCartItem(new Product("Darth Vader Funko", 15.0), 3);
	    sc.addItem(item1);

	    ShoppingCartItem item2 = new ShoppingCartItem(new Product("Kylo Ren Funko", 15.0), 4);
	    Discount d1 = new AbsoluteDiscount(5.0);
	    item2.addDiscount(d1);
	    sc.addItem(item2);

	    ShoppingCartItem item3 = new ShoppingCartItem(new Product("Boba Fett Funko", 15.0), 1);
	    Discount d2 = new PercentageDiscount(20.0);
	    item3.addDiscount(d2);
	    sc.addItem(item3);

	   
	    sc.addDiscountToAllItems();

	    assertEquals(97.0, sc.totalValue()); 
	    assertTrue(sc.hasDiscount());
	    assertEquals(23.0, sc.moneySaving()); 
	    assertEquals(8, sc.numberOfItems()); 
	}

}
