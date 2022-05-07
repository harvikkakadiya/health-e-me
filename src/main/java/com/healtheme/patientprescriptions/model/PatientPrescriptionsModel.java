package com.healtheme.patientprescriptions.model;

public class PatientPrescriptionsModel {

	private String doctorName;
	private String appointmentDate;
	private String appointmentTime;
	private String prescriptionSymptoms;
	private String prescriptionDiagnosis;
	private String prescriptionMedicines;

	public PatientPrescriptionsModel(String doctorName, String appointmentDate, String appointmentTime,
			String prescriptionSymptoms, String prescriptionDiagnosis, String prescriptionMedicines) {

		this.doctorName = doctorName;
		this.appointmentDate = appointmentDate;
		this.appointmentTime = appointmentTime;
		this.prescriptionSymptoms = prescriptionSymptoms;
		this.prescriptionDiagnosis = prescriptionDiagnosis;
		this.prescriptionMedicines = prescriptionMedicines;
	}

	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
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

	public String getPrescriptionSymptoms() {
		return prescriptionSymptoms;
	}

	public void setPrescriptionSymptoms(String prescriptionSymptoms) {
		this.prescriptionSymptoms = prescriptionSymptoms;
	}

	public String getPrescriptionDiagnosis() {
		return prescriptionDiagnosis;
	}

	public void setPrescriptionDiagnosis(String prescriptionDiagnosis) {
		this.prescriptionDiagnosis = prescriptionDiagnosis;
	}

	public String getPrescriptionMedicines() {
		return prescriptionMedicines;
	}

	public void setPrescriptionMedicines(String prescriptionMedicines) {
		this.prescriptionMedicines = prescriptionMedicines;
	}

}
