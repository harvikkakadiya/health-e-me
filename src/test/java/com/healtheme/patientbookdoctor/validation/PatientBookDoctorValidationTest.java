package com.healtheme.patientbookdoctor.validation;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.*;

public class PatientBookDoctorValidationTest {

	@Test
	void toTestIfValidationWorksForIncorrectDateAndTimeFormat() {

		PatientBookDoctorValidation val = new PatientBookDoctorValidation();
		assertEquals(" Invalid Doctor Appointment Date Format. " + " Invalid Doctor Appointment Time Format. ",
				val.checkValidation("XXXX", "YYYY"));
	}

	@Test
	void toTestIfValidationWorksForIncorrectDateFormat() {

		PatientBookDoctorValidation val = new PatientBookDoctorValidation();
		assertEquals(" Invalid Doctor Appointment Date Format. ", val.checkValidation("XXXX", "23:00"));
	}

	@Test
	void toTestIfValidationWorksForIncorrectTimeFormat() {

		PatientBookDoctorValidation val = new PatientBookDoctorValidation();
		assertEquals(" Invalid Doctor Appointment Time Format. ", val.checkValidation("2030-12-06", "XXXX"));
	}

	@Test
	void toTestIfValidationWorksForPastDate() {

		PatientBookDoctorValidation val = new PatientBookDoctorValidation();
		assertEquals(" Doctor Appointment Date should be a Future Date. ", val.checkValidation("2021-11-04", "23:00"));
	}

	@Test
	void toTestIfValidationWorksForCorrectInputs() {

		PatientBookDoctorValidation val = new PatientBookDoctorValidation();
		assertEquals("", val.checkValidation("2030-12-06", "23:00"));
	}
}
