package com.ramya.OnlineShoppingManagementSystem;
//login pojo class
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Login {
	@Id
	
	private int loginId;
	
	private String user_name;
	private String password;
	private String forgot_password;
	private String sign_up;
	@OneToOne(mappedBy="login")
	@JoinColumn(name = "c_id")
	private Customer customer;
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public int getLoginId() {
		return loginId;
	}
	public void setLoginId(int loginId) {
		this.loginId = loginId;
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
	public void setPassword(String password) {
		this.password = password;
	}
	public String getForgot_password() {
		return forgot_password;
	}
	public void setForgot_password(String forgot_password) {
		this.forgot_password = forgot_password;
	}
	public String getSign_up() {
		return sign_up;
	}
	public void setSign_up(String sign_up) {
		this.sign_up = sign_up;
	}
	
	

}
