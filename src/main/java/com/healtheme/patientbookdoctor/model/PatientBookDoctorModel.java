package com.healtheme.patientbookdoctor.model;

public class PatientBookDoctorModel {

	private String doctorName;
	private String doctorSpecialisation;
	private String doctorPhone;
	private String doctorConsultationFee;
	private String doctorAddress;
	private String doctorEmail;

	public PatientBookDoctorModel(String doctorName, String doctorSpecialisation, String doctorPhone,
			String doctorConsultationFee, String doctorAddress, String doctorEmail) {
		super();
		this.doctorName = doctorName;
		this.doctorSpecialisation = doctorSpecialisation;
		this.doctorPhone = doctorPhone;
		this.doctorConsultationFee = doctorConsultationFee;
		this.doctorAddress = doctorAddress;
		this.doctorEmail = doctorEmail;
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

	public String getDoctorPhone() {
		return doctorPhone;
	}

	public void setDoctorPhone(String doctorPhone) {
		this.doctorPhone = doctorPhone;
	}

	public String getDoctorConsultationFee() {
		return doctorConsultationFee;
	}

	public void setDoctorConsultationFee(String doctorConsultationFee) {
		this.doctorConsultationFee = doctorConsultationFee;
	}

	public String getDoctorAddress() {
		return doctorAddress;
	}

	public void setDoctorAddress(String doctorAddress) {
		this.doctorAddress = doctorAddress;
	}

	public String getDoctorEmail() {
		return doctorEmail;
	}

	public void setDoctorEmail(String doctorEmail) {
		this.doctorEmail = doctorEmail;
	}
}
