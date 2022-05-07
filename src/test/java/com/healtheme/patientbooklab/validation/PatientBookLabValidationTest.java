package com.healtheme.patientbooklab.validation;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class PatientBookLabValidationTest {

	private PatientBookLabValidation val = new PatientBookLabValidation();

	@Test
	void toTestIfValidationWorksForIncorrectDateAndTimeFormat() {
		assertEquals(" Invalid Lab Appointment Date Format. " + " Invalid Lab Appointment Time Format. ",
				val.checkValidation("XXXX", "YYYY"));
	}

	@Test
	void toTestIfValidationWorksForIncorrectDateFormat() {
		assertEquals(" Invalid Lab Appointment Date Format. ", val.checkValidation("XXXX", "23:00"));
	}

	@Test
	void toTestIfValidationWorksForIncorrectTimeFormat() {
		assertEquals(" Invalid Lab Appointment Time Format. ", val.checkValidation("2030-12-06", "XXXX"));
	}

	@Test
	void toTestIfValidationWorksForPastDate() {
		assertEquals(" Lab Appointment Date should be a Future Date. ", val.checkValidation("2021-11-06", "23:00"));
	}

	@Test
	void toTestIfValidationWorksForCorrectInputs() {
		assertEquals("", val.checkValidation("2030-12-06", "23:00"));
	}
}
