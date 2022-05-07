package com.healtheme.admininventory.model;

public class AdminInventoryModel {
	private String medicine_id;
	private String medicine_name;
	private String medicine_brand_name;
	private float medicine_price;
	private int medicine_quantity;

	public String getMedicine_id() {
		return medicine_id;
	}

	public void setMedicine_id(String medicine_id) {
		this.medicine_id = medicine_id;
	}

	public String getMedicine_name() {
		return medicine_name;
	}

	public void setMedicine_name(String medicine_name) {
		this.medicine_name = medicine_name;
	}

	public String getMedicine_brand_name() {
		return medicine_brand_name;
	}

	public void setMedicine_brand_name(String medicine_brand_name) {
		this.medicine_brand_name = medicine_brand_name;
	}

	public float getMedicine_price() {
		return medicine_price;
	}

	public void setMedicine_price(float medicine_price) {
		this.medicine_price = medicine_price;
	}

	public int getMedicine_quantity() {
		return medicine_quantity;
	}

	public void setMedicine_quantity(int medicine_quantity) {
		this.medicine_quantity = medicine_quantity;
	}

}
