package com.healtheme.doctorviewappointments.model;

public class DoctorViewAppointmentsModel {
	private String appointmentId;
	private String patientName;
	private String patientPhone;
	private String patientAddress;
	private String appointmentDate;
	private String appointmentTime;

	public DoctorViewAppointmentsModel(String appointmentId, String patientName, String patientPhone,
			String patientAddress, String appointmentDate, String appointmentTime) {
		this.appointmentId = appointmentId;
		this.patientName = patientName;
		this.patientPhone = patientPhone;
		this.patientAddress = patientAddress;
		this.appointmentDate = appointmentDate;
		this.appointmentTime = appointmentTime;
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
}
