package com.healtheme.labsignup.validation;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.healtheme.labsignup.model.LabSignupModel;

public class LabSignupValidationTest {

	LabSignupValidation val = new LabSignupValidation();

	@Test
	void toTestValidationForLabName() {
		LabSignupModel lab = new LabSignupModel("123", "1997-02-02", "Mahesh", "Kr", "Verma", "metrolabs@gmail.com",
				"Metro@100", "Metro@100", "1234567890", "12", "Charles St", "Halifax", "Nova Scotia", "Canada",
				"B3K 1K5", "1234567", "covid:10,xray:5", "Metro", "1234567", "123", "12345");
		assertEquals(" Invalid lab name. ", val.validateLabSignup(lab));
	}

	@Test
	void toTestValidationForLabDoe() {
		LabSignupModel lab = new LabSignupModel("MetroLabs", "14-Oct-2011", "Mahesh", "Kr", "Verma",
				"metrolabs@gmail.com", "Metro@100", "Metro@100", "1234567890", "12", "Charles St", "Halifax",
				"Nova Scotia", "Canada", "B3K 1K5", "1234567", "covid:10,xray:5", "Metro", "1234567", "123", "12345");
		assertEquals(" Invalid Date. ", val.validateLabSignup(lab));
	}

	@Test
	void toTestValidationForLabOwnerFirstName() {
		LabSignupModel lab = new LabSignupModel("MetroLabs", "1997-02-02", "Mahesh12", "Kr", "Verma",
				"metrolabs@gmail.com", "Metro@100", "Metro@100", "1234567890", "12", "Charles St", "Halifax",
				"Nova Scotia", "Canada", "B3K 1K5", "1234567", "covid:10,xray:5", "Metro", "1234567", "123", "12345");
		assertEquals(" Invalid First Name. ", val.validateLabSignup(lab));
	}

	@Test
	void toTestValidationForLabOwnerMiddleName() {
		LabSignupModel lab = new LabSignupModel("MetroLabs", "1997-02-02", "Mahesh", "Kr2", "Verma",
				"metrolabs@gmail.com", "Metro@100", "Metro@100", "1234567890", "12", "Charles St", "Halifax",
				"Nova Scotia", "Canada", "B3K 1K5", "1234567", "covid:10,xray:5", "Metro", "1234567", "123", "12345");
		assertEquals(" Invalid Middle Name. ", val.validateLabSignup(lab));
	}

	@Test
	void toTestValidationForLabOwnerLastName() {
		LabSignupModel lab = new LabSignupModel("MetroLabs", "1997-02-02", "Mahesh", "Kr", "Verma!",
				"metrolabs@gmail.com", "Metro@100", "Metro@100", "1234567890", "12", "Charles St", "Halifax",
				"Nova Scotia", "Canada", "B3K 1K5", "1234567", "covid:10,xray:5", "Metro", "1234567", "123", "12345");
		assertEquals(" Invalid Last Name. ", val.validateLabSignup(lab));
	}

	@Test
	void toTestValidationForLabEmail() {
		LabSignupModel lab = new LabSignupModel("MetroLabs", "1997-02-02", "Mahesh", "Kr", "Verma",
				"metrolabsgmail.com", "Metro@100", "Metro@100", "1234567890", "12", "Charles St", "Halifax",
				"Nova Scotia", "Canada", "B3K 1K5", "1234567", "covid:10,xray:5", "Metro", "1234567", "123", "12345");
		assertEquals(" Invalid Email. ", val.validateLabSignup(lab));
	}

	@Test
	void toTestValidationForLabPassword() {
		LabSignupModel lab = new LabSignupModel("MetroLabs", "1997-02-02", "Mahesh", "Kr", "Verma",
				"metrolabs@gmail.com", "metro100", "metro100", "1234567890", "12", "Charles St", "Halifax",
				"Nova Scotia", "Canada", "B3K 1K5", "1234567", "covid:10,xray:5", "Metro", "1234567", "123", "12345");
		assertEquals(" Invalid password. ", val.validateLabSignup(lab));
	}

	@Test
	void toTestValidationForLabConfirmPassword() {
		LabSignupModel lab = new LabSignupModel("MetroLabs", "1997-02-02", "Mahesh", "Kr", "Verma",
				"metrolabs@gmail.com", "Metro@100", "metro@100", "1234567890", "12", "Charles St", "Halifax",
				"Nova Scotia", "Canada", "B3K 1K5", "1234567", "covid:10,xray:5", "Metro", "1234567", "123", "12345");
		assertEquals(" Passwords do not match. ", val.validateLabSignup(lab));
	}

	@Test
	void toTestValidationForLabPhoneNumber() {
		LabSignupModel lab = new LabSignupModel("MetroLabs", "1997-02-02", "Mahesh", "Kr", "Verma",
				"metrolabs@gmail.com", "Metro@100", "Metro@100", "123456789", "12", "Charles St", "Halifax",
				"Nova Scotia", "Canada", "B3K 1K5", "1234567", "covid:10,xray:5", "Metro", "1234567", "123", "12345");
		assertEquals(" Invalid Phone Number. ", val.validateLabSignup(lab));
	}

	@Test
	void toTestValidationForLabUnit() {
		LabSignupModel lab = new LabSignupModel("MetroLabs", "1997-02-02", "Mahesh", "Kr", "Verma",
				"metrolabs@gmail.com", "Metro@100", "Metro@100", "1234567890", "1B", "Charles St", "Halifax",
				"Nova Scotia", "Canada", "B3K 1K5", "1234567", "covid:10,xray:5", "Metro", "1234567", "123", "12345");
		assertEquals(" Invalid Apartment Number. ", val.validateLabSignup(lab));
	}

	@Test
	void toTestValidationForLabStreet() {
		LabSignupModel lab = new LabSignupModel("MetroLabs", "1997-02-02", "Mahesh", "Kr", "Verma",
				"metrolabs@gmail.com", "Metro@100", "Metro@100", "1234567890", "1", "Charles St!", "Halifax",
				"Nova Scotia", "Canada", "B3K 1K5", "1234567", "covid:10,xray:5", "Metro", "1234567", "123", "12345");
		assertEquals(" Invalid Street Address. ", val.validateLabSignup(lab));
	}

	@Test
	void toTestValidationForLabCity() {
		LabSignupModel lab = new LabSignupModel("MetroLabs", "1997-02-02", "Mahesh", "Kr", "Verma",
				"metrolabs@gmail.com", "Metro@100", "Metro@100", "1234567890", "1", "Charles St", "Halifax1",
				"Nova Scotia", "Canada", "B3K 1K5", "1234567", "covid:10,xray:5", "Metro", "1234567", "123", "12345");
		assertEquals(" Invalid City. ", val.validateLabSignup(lab));
	}

	@Test
	void toTestValidationForLabProvince() {
		LabSignupModel lab = new LabSignupModel("MetroLabs", "1997-02-02", "Mahesh", "Kr", "Verma",
				"metrolabs@gmail.com", "Metro@100", "Metro@100", "1234567890", "1", "Charles St", "Halifax",
				"Nova Scotia1", "Canada", "B3K 1K5", "1234567", "covid:10,xray:5", "Metro", "1234567", "123", "12345");
		assertEquals(" Invalid Province. ", val.validateLabSignup(lab));
	}

	@Test
	void toTestValidationForLabCountry() {
		LabSignupModel lab = new LabSignupModel("MetroLabs", "1997-02-02", "Mahesh", "Kr", "Verma",
				"metrolabs@gmail.com", "Metro@100", "Metro@100", "1234567890", "1", "Charles St", "Halifax",
				"Nova Scotia", "Canada2", "B3K 1K5", "1234567", "covid:10,xray:5", "Metro", "1234567", "123", "12345");
		assertEquals(" Invalid Country. ", val.validateLabSignup(lab));
	}

	@Test
	void toTestValidationForLabPostalCode() {
		LabSignupModel lab = new LabSignupModel("MetroLabs", "1997-02-02", "Mahesh", "Kr", "Verma",
				"metrolabs@gmail.com", "Metro@100", "Metro@100", "1234567890", "1", "Charles St", "Halifax",
				"Nova Scotia", "Canada", "B3K1K5", "1234567", "covid:10,xray:5", "Metro", "1234567", "123", "12345");
		assertEquals(" Invalid Postal Code. ", val.validateLabSignup(lab));
	}

	@Test
	void toTestValidationForLabLicenseId() {
		LabSignupModel lab = new LabSignupModel("MetroLabs", "1997-02-02", "Mahesh", "Kr", "Verma",
				"metrolabs@gmail.com", "Metro@100", "Metro@100", "1234567890", "1", "Charles St", "Halifax",
				"Nova Scotia", "Canada", "B3K 1K5", "123456", "covid:10,xray:5", "Metro", "1234567", "123", "12345");
		assertEquals(" Invalid lab license ID. ", val.validateLabSignup(lab));
	}

	@Test
	void toTestValidationForLabAccountName() {
		LabSignupModel lab = new LabSignupModel("MetroLabs", "1997-02-02", "Mahesh", "Kr", "Verma",
				"metrolabs@gmail.com", "Metro@100", "Metro@100", "1234567890", "1", "Charles St", "Halifax",
				"Nova Scotia", "Canada", "B3K 1K5", "1234567", "covid:10,xray:5", "Metro1", "1234567", "123", "12345");
		assertEquals(" Invalid Account Holder Name. ", val.validateLabSignup(lab));
	}

	@Test
	void toTestValidationForLabAccountNumber() {
		LabSignupModel lab = new LabSignupModel("MetroLabs", "1997-02-02", "Mahesh", "Kr", "Verma",
				"metrolabs@gmail.com", "Metro@100", "Metro@100", "1234567890", "1", "Charles St", "Halifax",
				"Nova Scotia", "Canada", "B3K 1K5", "1234567", "covid:10,xray:5", "Metro", "12347", "123", "12345");
		assertEquals(" Invalid Account Number. ", val.validateLabSignup(lab));
	}

	@Test
	void toTestValidationForLabBankInstitutionNumber() {
		LabSignupModel lab = new LabSignupModel("MetroLabs", "1997-02-02", "Mahesh", "Kr", "Verma",
				"metrolabs@gmail.com", "Metro@100", "Metro@100", "1234567890", "1", "Charles St", "Halifax",
				"Nova Scotia", "Canada", "B3K 1K5", "1234567", "covid:10,xray:5", "Metro", "1234567", "1234", "12345");
		assertEquals(" Invalid Bank Number. ", val.validateLabSignup(lab));
	}

	@Test
	void toTestValidationForLabTransitNumber() {
		LabSignupModel lab = new LabSignupModel("MetroLabs", "1997-02-02", "Mahesh", "Kr", "Verma",
				"metrolabs@gmail.com", "Metro@100", "Metro@100", "1234567890", "1", "Charles St", "Halifax",
				"Nova Scotia", "Canada", "B3K 1K5", "1234567", "covid:10,xray:5", "Metro", "1234567", "123", "123456");
		assertEquals(" Invalid Transit Number. ", val.validateLabSignup(lab));
	}

}
