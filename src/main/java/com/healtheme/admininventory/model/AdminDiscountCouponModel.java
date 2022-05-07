package com.healtheme.admininventory.model;

public class AdminDiscountCouponModel {

	private String couponid;
	private String coupon_name;
	private int coupon_value;

	public AdminDiscountCouponModel() {
		this.couponid = new String();
		this.coupon_name = new String();

	}

	public String getCouponid() {
		return couponid;
	}

	public void setCouponid(String couponid) {
		this.couponid = couponid;
	}

	public String getCoupon_name() {
		return coupon_name;
	}

	public void setCoupon_name(String coupon_name) {
		this.coupon_name = coupon_name;
	}

	public int getCoupon_value() {
		return coupon_value;
	}

	public void setCoupon_value(int coupon_value) {
		this.coupon_value = coupon_value;
	}

}
