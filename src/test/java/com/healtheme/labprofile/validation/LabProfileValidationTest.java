package com.healtheme.labprofile.validation;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.healtheme.labprofile.model.LabProfileModel;

public class LabProfileValidationTest {

	LabProfileValidation val = new LabProfileValidation();

	@Test
	void toTestValidationForLabName() {
		LabProfileModel lab = new LabProfileModel("123", "1997-02-02", "Mahesh", "Kr", "Verma", "1234567890", "12",
				"Charles St", "Halifax", "Nova Scotia", "Canada", "B3K 1K5", "1234567", "covid:10,xray:5", "Metro",
				"1234567", "123", "12345", "100");
		assertEquals(" Invalid lab name. ", val.validateLabProfile(lab));
	}

	@Test
	void toTestValidationForLabDoe() {
		LabProfileModel lab = new LabProfileModel("MetroLabs", "199-02-02", "Mahesh", "Kr", "Verma", "1234567890", "12",
				"Charles St", "Halifax", "Nova Scotia", "Canada", "B3K 1K5", "1234567", "covid:10,xray:5", "Metro",
				"1234567", "123", "12345", "100");
		assertEquals(" Invalid Date. ", val.validateLabProfile(lab));
	}

	@Test
	void toTestValidationForLabOwnerFirstName() {
		LabProfileModel lab = new LabProfileModel("MetroLabs", "1994-02-02", "Mahesh1", "Kr", "Verma", "1234567890",
				"12", "Charles St", "Halifax", "Nova Scotia", "Canada", "B3K 1K5", "1234567", "covid:10,xray:5",
				"Metro", "1234567", "123", "12345", "100");
		assertEquals(" Invalid First Name. ", val.validateLabProfile(lab));
	}

	@Test
	void toTestValidationForLabOwnerMiddleName() {
		LabProfileModel lab = new LabProfileModel("MetroLabs", "1994-02-02", "Mahesh", "Kr2", "Verma", "1234567890",
				"12", "Charles St", "Halifax", "Nova Scotia", "Canada", "B3K 1K5", "1234567", "covid:10,xray:5",
				"Metro", "1234567", "123", "12345", "100");
		assertEquals(" Invalid Middle Name. ", val.validateLabProfile(lab));
	}

	@Test
	void toTestValidationForLabOwnerLastName() {
		LabProfileModel lab = new LabProfileModel("MetroLabs", "1994-02-02", "Mahesh", "Kr", "Verma3", "1234567890",
				"12", "Charles St", "Halifax", "Nova Scotia", "Canada", "B3K 1K5", "1234567", "covid:10,xray:5",
				"Metro", "1234567", "123", "12345", "100");
		assertEquals(" Invalid Last Name. ", val.validateLabProfile(lab));
	}

	@Test
	void toTestValidationForLabPhoneNumber() {
		LabProfileModel lab = new LabProfileModel("MetroLabs", "1994-02-02", "Mahesh", "Kr", "Verma", "123456789", "12",
				"Charles St", "Halifax", "Nova Scotia", "Canada", "B3K 1K5", "1234567", "covid:10,xray:5", "Metro",
				"1234567", "123", "12345", "100");
		assertEquals(" Invalid Phone Number. ", val.validateLabProfile(lab));
	}

	@Test
	void toTestValidationForLabUnit() {
		LabProfileModel lab = new LabProfileModel("MetroLabs", "1994-02-02", "Mahesh", "Kr", "Verma", "1234567890",
				"12B", "Charles St", "Halifax", "Nova Scotia", "Canada", "B3K 1K5", "1234567", "covid:10,xray:5",
				"Metro", "1234567", "123", "12345", "100");
		assertEquals(" Invalid Apartment Number. ", val.validateLabProfile(lab));
	}

	@Test
	void toTestValidationForLabStreet() {
		LabProfileModel lab = new LabProfileModel("MetroLabs", "1994-02-02", "Mahesh", "Kr", "Verma", "1234567890",
				"12", "Charles St!", "Halifax", "Nova Scotia", "Canada", "B3K 1K5", "1234567", "covid:10,xray:5",
				"Metro", "1234567", "123", "12345", "100");
		assertEquals(" Invalid Street Address. ", val.validateLabProfile(lab));
	}

	@Test
	void toTestValidationForLabCity() {
		LabProfileModel lab = new LabProfileModel("MetroLabs", "1994-02-02", "Mahesh", "Kr", "Verma", "1234567890",
				"12", "Charles St", "Halifax1", "Nova Scotia", "Canada", "B3K 1K5", "1234567", "covid:10,xray:5",
				"Metro", "1234567", "123", "12345", "100");
		assertEquals(" Invalid City. ", val.validateLabProfile(lab));
	}

	@Test
	void toTestValidationForLabProvince() {
		LabProfileModel lab = new LabProfileModel("MetroLabs", "1994-02-02", "Mahesh", "Kr", "Verma", "1234567890",
				"12", "Charles St", "Halifax", "Nova Scotia2", "Canada", "B3K 1K5", "1234567", "covid:10,xray:5",
				"Metro", "1234567", "123", "12345", "100");
		assertEquals(" Invalid Province. ", val.validateLabProfile(lab));
	}

	@Test
	void toTestValidationForLabCountry() {
		LabProfileModel lab = new LabProfileModel("MetroLabs", "1994-02-02", "Mahesh", "Kr", "Verma", "1234567890",
				"12", "Charles St", "Halifax", "Nova Scotia", "Canada1", "B3K 1K5", "1234567", "covid:10,xray:5",
				"Metro", "1234567", "123", "12345", "100");
		assertEquals(" Invalid Country. ", val.validateLabProfile(lab));
	}

	@Test
	void toTestValidationForLabPostalCode() {
		LabProfileModel lab = new LabProfileModel("MetroLabs", "1994-02-02", "Mahesh", "Kr", "Verma", "1234567890",
				"12", "Charles St", "Halifax", "Nova Scotia", "Canada", "B3K1K5", "1234567", "covid:10,xray:5", "Metro",
				"1234567", "123", "12345", "100");
		assertEquals(" Invalid Postal Code. ", val.validateLabProfile(lab));
	}

	@Test
	void toTestValidationForLabLicenseId() {
		LabProfileModel lab = new LabProfileModel("MetroLabs", "1994-02-02", "Mahesh", "Kr", "Verma", "1234567890",
				"12", "Charles St", "Halifax", "Nova Scotia", "Canada", "B3K 1K5", "123467", "covid:10,xray:5", "Metro",
				"1234567", "123", "12345", "100");
		assertEquals(" Invalid lab license ID. ", val.validateLabProfile(lab));
	}

	@Test
	void toTestValidationForLabAccountName() {
		LabProfileModel lab = new LabProfileModel("MetroLabs", "1994-02-02", "Mahesh", "Kr", "Verma", "1234567890",
				"12", "Charles St", "Halifax", "Nova Scotia", "Canada", "B3K 1K5", "1234567", "covid:10,xray:5",
				"Metr1o", "1234567", "123", "12345", "100");
		assertEquals(" Invalid Account Holder Name. ", val.validateLabProfile(lab));
	}

	@Test
	void toTestValidationForLabAccountNumber() {
		LabProfileModel lab = new LabProfileModel("MetroLabs", "1994-02-02", "Mahesh", "Kr", "Verma", "1234567890",
				"12", "Charles St", "Halifax", "Nova Scotia", "Canada", "B3K 1K5", "1234567", "covid:10,xray:5",
				"Metro", "123456", "123", "12345", "100");
		assertEquals(" Invalid Account Number. ", val.validateLabProfile(lab));
	}

	@Test
	void toTestValidationForLabBankInstitutionNumber() {
		LabProfileModel lab = new LabProfileModel("MetroLabs", "1994-02-02", "Mahesh", "Kr", "Verma", "1234567890",
				"12", "Charles St", "Halifax", "Nova Scotia", "Canada", "B3K 1K5", "1234567", "covid:10,xray:5",
				"Metro", "1234567", "1234", "12345", "100");
		assertEquals(" Invalid Bank Number. ", val.validateLabProfile(lab));
	}

	@Test
	void toTestValidationForLabTransitNumber() {
		LabProfileModel lab = new LabProfileModel("MetroLabs", "1994-02-02", "Mahesh", "Kr", "Verma", "1234567890",
				"12", "Charles St", "Halifax", "Nova Scotia", "Canada", "B3K 1K5", "1234567", "covid:10,xray:5",
				"Metro", "1234567", "123", "1234w5", "100");
		assertEquals(" Invalid Transit Number. ", val.validateLabProfile(lab));
	}

}
