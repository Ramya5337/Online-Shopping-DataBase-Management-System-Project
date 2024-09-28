package com.ramya.OnlineShoppingManagementSystem;
//shopping cart pojo class
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class ShoppingCart {
	@Id
	
	private int cartId;
	public int getCartId() {
		return cartId;
	}
	public void setCartId(int cartId) {
		this.cartId = cartId;
	}
	private String P_name;
	private int billing_products;
	private int total_price;
	@OneToMany(mappedBy="shoppingCart")
	
	private Set<Products> products = new HashSet<>();
	
	public Set<Products> getProducts() {
		return products;
	}
	public void setProducts(Set<Products> products) {
		this.products = products;
	}
	public String getP_name() {
		return P_name;
	}
	public void setP_name(String p_name) {
		P_name = p_name;
	}
	public int getBilling_products() {
		return billing_products;
	}
	public void setBilling_products(int billing_products) {
		this.billing_products = billing_products;
	}
	public int getTotal_price() {
		return total_price;
	}
	public void setTotal_price(int total_price) {
		this.total_price = total_price;
	}
	

}
