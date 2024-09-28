package com.ramya.OnlineShoppingManagementSystem;
//admin pojo class
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
@Entity

public class Admin {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private int admin_id;
	private  String name;
	private String password;
	@OneToOne(mappedBy="admin")
	@JoinColumn(name = "app_name")
	private Website website;
	
    @OneToMany(mappedBy = "admin")
    private Set<Products> products; // Collection of Products managed by this Admin

	public int getAdmin_id() {
		return admin_id;
	}
	public void setAdmin_id(int admin_id) {
		this.admin_id = admin_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public Website getWebsite() {
		return website;
	}
	public void setWebsite(Website website) {
		this.website = website;
	}
	public Set<Products> getProducts() {
		return products;
	}
	public void setProducts(Set<Products> products) {
		this.products = products;
	}
	

}
