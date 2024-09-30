package com.ramya.OnlineShoppingManagementSystem;
//products pojo class
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
@Entity
public class Products {
	@Id
	
	private int p_id;
	private String p_name;
	private String p_category;
	private String p_availability;
	private int p_price;
	
	@ManyToOne
	@JoinColumn(name = "admin_id") // Foreign key column name in the Products table
	private Admin admin;
	
	 @ManyToOne
	 @JoinColumn(name = "c_id")
	    private Customer customer; // This is the field to reference Customer
	 
	@ManyToOne
	 @JoinColumn(name ="cartId")
	private ShoppingCart shoppingCart;
	public ShoppingCart getShoppingCart() {
		return shoppingCart;
	}
	public void setShoppingCart(ShoppingCart shoppingCart) {
		this.shoppingCart = shoppingCart;
	}
	public Admin getAdmin() {
		return admin;
	}
	public void setAdmin(Admin admin) {
		this.admin = admin;
	}
	public int getP_id() {
		return p_id;
	}
	public void setP_id(int p_id) {
		this.p_id = p_id;
	}
	public String getP_name() {
		return p_name;
	}
	public void setP_name(String p_name) {
		this.p_name = p_name;
	}
	public String getP_category() {
		return p_category;
	}
	public void setP_category(String p_category) {
		this.p_category = p_category;
	}
	public String getP_availability() {
		return p_availability;
	}
	public void setP_availability(String p_availability) {
		this.p_availability = p_availability;
	}
	public int getP_price() {
		return p_price;
	}
	public void setP_price(int p_price) {
		this.p_price = p_price;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	

	
	
	

}
