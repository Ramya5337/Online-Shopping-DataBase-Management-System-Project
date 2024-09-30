package com.ramya.OnlineShoppingManagementSystem;
//customer pojo class
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
@Entity
public class Customer {
	@Id
	
	private int c_id;
	private String user_name;
	private String password;
	private int mobile_no;
	private String email;
	private String address;	
	
	//mapping customer,website with ManyToMany relation
	@ManyToMany
    @JoinTable(
        name = "customer_website", // join table name
        joinColumns = @JoinColumn(name = "customer_id"), // foreign key for Customer
        inverseJoinColumns = @JoinColumn(name = "app_name") // foreign key for Website
    )
	 private Set<Website> website;

    @OneToMany(mappedBy = "customer")
    private Set<Products> products;
    @OneToOne
    private Login login;
	public Login getLogin() {
		return login;
	}
	public void setLogin(Login login) {
		this.login = login;
	}
	public int getC_id() {
		return c_id;
	}
	public void setC_id(int c_id) {
		this.c_id = c_id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getPassword() {
		return password;
	}
	public Set<Products> getProducts() {
		return products;
	}
	public void setProducts(Set<Products> products) {
		this.products = products;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getMobile_no() {
		return mobile_no;
	}
	public void setMobile_no(int mobile_no) {
		this.mobile_no = mobile_no;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	
	
	public Set<Website> getWebsite() {
		return website;
	}
	public void setWebsite(Set<Website> website) {
		this.website = website;
	}
	
	
	
	

}
