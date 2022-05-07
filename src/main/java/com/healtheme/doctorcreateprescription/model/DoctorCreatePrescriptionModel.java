package com.healtheme.doctorcreateprescription.model;

public class DoctorCreatePrescriptionModel {
	private String patientPrescriptionEmail;
	private String doctorPrescriptionEmail;
	private String prescriptionAppointmentDate;
	private String prescriptionAppointmentTime;
	private String prescriptionrPatientSymptoms;
	private String prescriptionPatientDiagnosis;
	private String prescriptionPatientMedicines;

	public DoctorCreatePrescriptionModel(String patientPrescriptionEmail, String doctorPrescriptionEmail,
			String prescriptionAppointmentDate, String prescriptionAppointmentTime, String prescriptionrPatientSymptoms,
			String prescriptionPatientDiagnosis, String prescriptionPatientMedicines) {
		this.patientPrescriptionEmail = patientPrescriptionEmail;
		this.doctorPrescriptionEmail = doctorPrescriptionEmail;
		this.prescriptionAppointmentDate = prescriptionAppointmentDate;
		this.prescriptionAppointmentTime = prescriptionAppointmentTime;
		this.prescriptionrPatientSymptoms = prescriptionrPatientSymptoms;
		this.prescriptionPatientDiagnosis = prescriptionPatientDiagnosis;
		this.prescriptionPatientMedicines = prescriptionPatientMedicines;
	}

	public String getPatientPrescriptionEmail() {
		return patientPrescriptionEmail;
	}

	public void setPatientPrescriptionEmail(String patientPrescriptionEmail) {
		this.patientPrescriptionEmail = patientPrescriptionEmail;
	}

	public String getDoctorPrescriptionEmail() {
		return doctorPrescriptionEmail;
	}

	public void setDoctorPrescriptionEmail(String doctorPrescriptionEmail) {
		this.doctorPrescriptionEmail = doctorPrescriptionEmail;
	}

	public String getPrescriptionAppointmentDate() {
		return prescriptionAppointmentDate;
	}

	public void setPrescriptionAppointmentDate(String prescriptionAppointmentDate) {
		this.prescriptionAppointmentDate = prescriptionAppointmentDate;
	}

	public String getPrescriptionAppointmentTime() {
		return prescriptionAppointmentTime;
	}

	public void setPrescriptionAppointmentTime(String prescriptionAppointmentTime) {
		this.prescriptionAppointmentTime = prescriptionAppointmentTime;
	}

	public String getPrescriptionrPatientSymptoms() {
		return prescriptionrPatientSymptoms;
	}

	public void setPrescriptionrPatientSymptoms(String prescriptionrPatientSymptoms) {
		this.prescriptionrPatientSymptoms = prescriptionrPatientSymptoms;
	}

	public String getPrescriptionPatientDiagnosis() {
		return prescriptionPatientDiagnosis;
	}

	public void setPrescriptionPatientDiagnosis(String prescriptionPatientDiagnosis) {
		this.prescriptionPatientDiagnosis = prescriptionPatientDiagnosis;
	}

	public String getPrescriptionPatientMedicines() {
		return prescriptionPatientMedicines;
	}

	public void setPrescriptionPatientMedicines(String prescriptionPatientMedicines) {
		this.prescriptionPatientMedicines = prescriptionPatientMedicines;
	}

}
