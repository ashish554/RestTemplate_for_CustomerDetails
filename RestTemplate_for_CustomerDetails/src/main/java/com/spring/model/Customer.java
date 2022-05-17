package com.spring.model;

public class Customer {

	int customerId;
	String cName;
	String cEmail;
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public String getcName() {
		return cName;
	}
	public void setcName(String cName) {
		this.cName = cName;
	}
	public String getcEmail() {
		return cEmail;
	}
	public void setcEmail(String cEmail) {
		this.cEmail = cEmail;
	}
	public Customer(int customerId, String cName, String cEmail) {
		super();
		this.customerId = customerId;
		this.cName = cName;
		this.cEmail = cEmail;
	}
	
	public Customer()
	{
		
	}
}
