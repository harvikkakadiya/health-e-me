package com.healtheme.labappointments.model;

public class LabViewAppointmentModel {

	private String appointmentId;
	private String patientName;
	private String patientPhone;
	private String patientAddress;
	private String appointmentDate;
	private String appointmentTime;
	private String labTestName;

	public LabViewAppointmentModel(String appointmentId, String patientName, String patientPhone, String patientAddress,
			String appointmentDate, String appointmentTime, String labTestName) {
		this.appointmentId = appointmentId;
		this.patientName = patientName;
		this.patientPhone = patientPhone;
		this.patientAddress = patientAddress;
		this.appointmentDate = appointmentDate;
		this.appointmentTime = appointmentTime;
		this.labTestName = labTestName;
	}

	public String getAppointmentId() {
		return appointmentId;
	}

	public void setAppointmentId(String appointmentId) {
		this.appointmentId = appointmentId;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public String getPatientPhone() {
		return patientPhone;
	}

	public void setPatientPhone(String patientPhone) {
		this.patientPhone = patientPhone;
	}

	public String getPatientAddress() {
		return patientAddress;
	}

	public void setPatientAddress(String patientAddress) {
		this.patientAddress = patientAddress;
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

	public String getLabTestName() {
		return labTestName;
	}

	public void setLabTestName(String labTestName) {
		this.labTestName = labTestName;
	}
}
