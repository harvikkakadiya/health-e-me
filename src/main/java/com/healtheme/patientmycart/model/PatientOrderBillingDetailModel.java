package com.healtheme.patientmycart.model;

import java.util.List;

import com.healtheme.patientordermedicine.model.PatientOrderMedicineModel;

public class PatientOrderBillingDetailModel {

	private float subtotalSum;
	private float discount;
	private float tax;
	private float total;
	private List<PatientOrderMedicineModel> list;

	public float getSubtotalSum() {
		return subtotalSum;
	}

	public void setSubtotalSum(float subtotalSum) {
		this.subtotalSum = subtotalSum;
	}

	public float getDiscount() {
		return discount;
	}

	public void setDiscount(float discount) {
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

	public List<PatientOrderMedicineModel> getList() {
		return list;
	}

	public void setList(List<PatientOrderMedicineModel> list) {
		this.list = list;
	}

}
