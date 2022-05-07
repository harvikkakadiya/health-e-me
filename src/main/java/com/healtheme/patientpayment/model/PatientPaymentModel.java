package com.healtheme.patientpayment.model;

public class PatientPaymentModel {

	private String username;
	private String account_name;
	private String account_no;
	private String bank_institution_no;
	private String transit_no;
	private String account_balance;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAccount_name() {
		return account_name;
	}

	public void setAccount_name(String account_name) {
		this.account_name = account_name;
	}

	public String getAccount_no() {
		return account_no;
	}

	public void setAccount_no(String account_no) {
		this.account_no = account_no;
	}

	public String getBank_institution_no() {
		return bank_institution_no;
	}

	public void setBank_institution_no(String bank_institution_no) {
		this.bank_institution_no = bank_institution_no;
	}

	public String getTransit_no() {
		return transit_no;
	}

	public void setTransit_no(String transit_no) {
		this.transit_no = transit_no;
	}

	public String getAccount_balance() {
		return account_balance;
	}

	public void setAccount_balance(String account_balance) {
		this.account_balance = account_balance;
	}

}
