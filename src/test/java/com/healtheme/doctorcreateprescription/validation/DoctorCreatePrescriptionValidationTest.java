package com.healtheme.doctorcreateprescription.validation;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import com.healtheme.doctorcreateprescription.model.DoctorCreatePrescriptionModel;

public class DoctorCreatePrescriptionValidationTest {

	DoctorCreatePrescriptionValidation validation = new DoctorCreatePrescriptionValidation();
	LocalDate currentDate =LocalDate.now();
	String date = currentDate.toString();
	
	@Test
	void toTestValidationForPatientEmail() {

		DoctorCreatePrescriptionModel prescription = new DoctorCreatePrescriptionModel ("brucegmail.com","ankitrathi@gmail.com",date,"11:00","Fever","Viral","Crocin");
		assertEquals(" Invalid Patient Email. ", validation.checkValidation(prescription));
	}
	
	@Test
	void toTestValidationForDoctorEmail() {

		DoctorCreatePrescriptionModel prescription = new DoctorCreatePrescriptionModel ("bruce@gmail.com","ankitrathigmail.com",date,"11:00","Fever","Viral","Crocin");
		assertEquals(" Invalid Doctor Email. ", validation.checkValidation(prescription));
	}
	
	@Test
	void toTestValidationForDate() {

		DoctorCreatePrescriptionModel prescription = new DoctorCreatePrescriptionModel ("bruce@gmail.com","ankitrathi@gmail.com","2021-11-23","11:00","Fever","Viral","Crocin");
		assertEquals(" Invalid Date. ", validation.checkValidation(prescription));
	}
	
	@Test
	void toTestValidationForTime() {

		DoctorCreatePrescriptionModel prescription = new DoctorCreatePrescriptionModel ("bruce@gmail.com","ankitrathi@gmail.com",date,"1 o clock","Fever","Viral","Crocin");
		assertEquals(" Invalid Time. ", validation.checkValidation(prescription));
	}
	
	@Test
	void toTestValidationForSymptoms() {

		DoctorCreatePrescriptionModel prescription = new DoctorCreatePrescriptionModel ("bruce@gmail.com","ankitrathi@gmail.com",date,"11:00","@","Viral","Crocin");
		assertEquals(" Invalid Symptoms. ", validation.checkValidation(prescription));
	}
	
	@Test
	void toTestValidationForDiagnosis() {

		DoctorCreatePrescriptionModel prescription = new DoctorCreatePrescriptionModel ("bruce@gmail.com","ankitrathi@gmail.com",date,"11:00","Fever","@","Crocin");
		assertEquals(" Invalid Diagnosis. ", validation.checkValidation(prescription));
	}
	
	@Test
	void toTestValidationForMedicines() {

		DoctorCreatePrescriptionModel prescription = new DoctorCreatePrescriptionModel ("bruce@gmail.com","ankitrathi@gmail.com",date,"11:00","Fever","Viral","@");
		assertEquals(" Invalid Medicines. ", validation.checkValidation(prescription));
	}
}
