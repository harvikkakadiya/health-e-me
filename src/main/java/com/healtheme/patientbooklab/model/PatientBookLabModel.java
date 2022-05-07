package com.healtheme.patientbooklab.model;

public class PatientBookLabModel {

	private String labName;
	private String labPhone;
	private String labAddress;
	private String labTests;
	private String labEmail;

	public PatientBookLabModel(String labName, String labPhone, String labAddress, String labTests, String labEmail) {

		this.labName = labName;
		this.labPhone = labPhone;
		this.labAddress = labAddress;
		this.labTests = labTests;
		this.labEmail = labEmail;
	}

	public String getLabName() {
		return labName;
	}

	public void setLabName(String labName) {
		this.labName = labName;
	}

	public String getLabPhone() {
		return labPhone;
	}

	public void setLabPhone(String labPhone) {
		this.labPhone = labPhone;
	}

	public String getLabAddress() {
		return labAddress;
	}

	public void setLabAddress(String labAddress) {
		this.labAddress = labAddress;
	}

	public String getLabTests() {
		return labTests;
	}

	public void setLabTests(String labTests) {
		this.labTests = labTests;
	}

	public String getLabEmail() {
		return labEmail;
	}

	public void setLabEmail(String labEmail) {
		this.labEmail = labEmail;
	}
}
