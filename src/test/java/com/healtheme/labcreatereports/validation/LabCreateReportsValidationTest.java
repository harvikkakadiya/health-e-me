package com.healtheme.labcreatereports.validation;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.healtheme.labcreatereports.model.LabCreateReportsModel;

public class LabCreateReportsValidationTest {

	LabCreateReportsValidation val = new LabCreateReportsValidation();

	@Test
	void toTestValidationForPatientEmail() {
		LabCreateReportsModel lab = new LabCreateReportsModel("Petergmail.com", "2021-11-20", "18:00",
				"Manish@gmail.com", "fever,cold", "negative");
		assertEquals(" Invalid Patient Email. ", val.validateLabCreateReports(lab));
	}

	@Test
	void toTestValidationForReportDate() {
		LabCreateReportsModel lab = new LabCreateReportsModel("Peter@gmail.com", "2021-11-2A", "18:00",
				"Manish@gmail.com", "fever,cold", "negative");
		assertEquals(" Invalid Report Date. ", val.validateLabCreateReports(lab));
	}

	@Test
	void toTestValidationForReportTime() {
		LabCreateReportsModel lab = new LabCreateReportsModel("Peter@gmail.com", "2021-11-21", "18:0",
				"Manish@gmail.com", "fever,cold", "negative");
		assertEquals(" Invalid Report Time. ", val.validateLabCreateReports(lab));
	}

	@Test
	void toTestValidationForDoctorEmail() {
		LabCreateReportsModel lab = new LabCreateReportsModel("Peter@gmail.com", "2021-11-21", "18:00",
				"Manishgmail.com", "fever,cold", "negative");
		assertEquals(" Invalid Doctor Email. ", val.validateLabCreateReports(lab));
	}
}
