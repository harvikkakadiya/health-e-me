package com.healtheme.patientappointments.model;

public class PatientAppointmentsDoctorModel {

	private String appointmentId;
	private String appointmentDate;
	private String appointmentTime;
	private String consultationFee;
	private String doctorEmail;
	private String doctorName;
	private String doctorSpecialisation;
	private String doctorAddress;
	private String doctorPhone;

	public PatientAppointmentsDoctorModel(String appointmentId, String appointmentDate, String appointmentTime,
			String consultationFee, String doctorEmail, String doctorName, String doctorSpecialisation,
			String doctorAddress, String doctorPhone) {
		super();
		this.appointmentId = appointmentId;
		this.appointmentDate = appointmentDate;
		this.appointmentTime = appointmentTime;
		this.consultationFee = consultationFee;
		this.doctorEmail = doctorEmail;
		this.doctorName = doctorName;
		this.doctorSpecialisation = doctorSpecialisation;
		this.doctorAddress = doctorAddress;
		this.doctorPhone = doctorPhone;
	}

	public String getDoctorEmail() {
		return doctorEmail;
	}

	public void setDoctorEmail(String doctorEmail) {
		this.doctorEmail = doctorEmail;
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

	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	public String getDoctorSpecialisation() {
		return doctorSpecialisation;
	}

	public void setDoctorSpecialisation(String doctorSpecialisation) {
		this.doctorSpecialisation = doctorSpecialisation;
	}

	public String getDoctorAddress() {
		return doctorAddress;
	}

	public void setDoctorAddress(String doctorAddress) {
		this.doctorAddress = doctorAddress;
	}

	public String getDoctorPhone() {
		return doctorPhone;
	}

	public void setDoctorPhone(String doctorPhone) {
		this.doctorPhone = doctorPhone;
	}

	public String getConsultationFee() {
		return consultationFee;
	}

	public void setConsultationFee(String consultationFee) {
		this.consultationFee = consultationFee;
	}
}
