package com.healtheme.patientordermedicine.model;

public class PatientOrderMedicineModel {

	private String id;
	private String medicine_id;
	private String email;
	private String medicine_name;
	private int quantity;
	private float price;
	private String ischeckedout;
	private float subtotal;

	public float getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(float subtotal) {
		this.subtotal = subtotal;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMedicine_id() {
		return medicine_id;
	}

	public void setMedicine_id(String medicine_id) {
		this.medicine_id = medicine_id;
	}

	public String getEmail() {
		return email;
	}

	@Override
	public String toString() {
		return "PatientOrderMedicineModel [id=" + id + ", medicine_id=" + medicine_id + ", email=" + email
				+ ", medicine_name=" + medicine_name + ", quantity=" + quantity + ", price=" + price + ", ischeckedout="
				+ ischeckedout + ", subtotal=" + subtotal + "]";
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMedicine_name() {
		return medicine_name;
	}

	public void setMedicine_name(String medicine_name) {
		this.medicine_name = medicine_name;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getIscheckedout() {
		return ischeckedout;
	}

	public void setIscheckedout(String ischeckedout) {
		this.ischeckedout = ischeckedout;
	}
}
