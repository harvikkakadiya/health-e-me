package com.healtheme.patientappointments.model;

public class PatientAppointmentsLabModel {

	private String appointmentId;
	private String appointmentDate;
	private String appointmentTime;
	private String labTestType;
	private String labTestFee;
	private String labEmail;
	private String labName;
	private String labAddress;
	private String labPhone;

	public PatientAppointmentsLabModel(String appointmentId, String appointmentDate, String appointmentTime,
			String labTestType, String labTestFee, String labEmail, String labName, String labAddress,
			String labPhone) {
		super();
		this.appointmentId = appointmentId;
		this.appointmentDate = appointmentDate;
		this.appointmentTime = appointmentTime;
		this.labTestType = labTestType;
		this.labTestFee = labTestFee;
		this.labEmail = labEmail;
		this.labName = labName;
		this.labAddress = labAddress;
		this.labPhone = labPhone;
	}

	public String getLabEmail() {
		return labEmail;
	}

	public void setLabEmail(String labEmail) {
		this.labEmail = labEmail;
	}

	public String getLabTestFee() {
		return labTestFee;
	}

	public void setLabTestFee(String labTestFee) {
		this.labTestFee = labTestFee;
	}

	public String getAppointmentId() {
		return appointmentId;
	}

	public void setAppointmentId(String appointmentId) {
		this.appointmentId = appointmentId;
	}

	public String getAppointmentDate() {
		return appointmentDate;
	}

	public void setAppointmentDate(String appointmentDate) {
		this.appointmentDate = appointmentDate;
	}

	public String getAppointmentTime() {
		return appointmentTime;
	}

	public void setAppointmentTime(String appointmentTime) {
		this.appointmentTime = appointmentTime;
	}

	public String getLabTestType() {
		return labTestType;
	}

	public void setLabTestType(String labTestType) {
		this.labTestType = labTestType;
	}

	public String getLabName() {
		return labName;
	}

	public void setLabName(String labName) {
		this.labName = labName;
	}

	public String getLabAddress() {
		return labAddress;
	}

	public void setLabAddress(String labAddress) {
		this.labAddress = labAddress;
	}

	public String getLabPhone() {
		return labPhone;
	}

	public void setLabPhone(String labPhone) {
		this.labPhone = labPhone;
	}
}
