package com.healtheme.patientmycart.model;

public class PatientOrderModel {

	private String orderid;

	private String email;
	private float subtotal;
	private int discount;
	private float tax;
	private float total;
	private String street_address;
	private String city;
	private String province;
	private String postal_code;

	public PatientOrderModel(String orderid, String email, double d, int discount, double e, double f,
			String street_address, String city, String province, String postal_code) {
		super();
		this.orderid = orderid;
		this.email = email;
		this.subtotal = (float) d;
		this.discount = discount;
		this.tax = (float) e;
		this.total = (float) f;
		this.street_address = street_address;
		this.city = city;
		this.province = province;
		this.postal_code = postal_code;
	}

	public PatientOrderModel() {

	}

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public float getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(float subtotal) {
		this.subtotal = subtotal;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

	public float getTax() {
		return tax;
	}

	public void setTax(float tax) {
		this.tax = tax;
	}

	public float getTotal() {
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
	}

	public String getStreet_address() {
		return street_address;
	}

	public void setStreet_address(String street_address) {
		this.street_address = street_address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getPostal_code() {
		return postal_code;
	}

	public void setPostal_code(String postal_code) {
		this.postal_code = postal_code;
	}

}
