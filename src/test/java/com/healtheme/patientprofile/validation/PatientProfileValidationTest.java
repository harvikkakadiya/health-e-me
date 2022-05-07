package com.healtheme.patientprofile.validation;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.healtheme.patientprofile.model.PatientProfileModel;

public class PatientProfileValidationTest {

	PatientProfileValidation val = new PatientProfileValidation();

	@Test
	void toTestIfValidationWorksForInvalidName() {

		PatientProfileModel patient = new PatientProfileModel("123", "M", "Parker", "1997-02-02", "Male", "1234567890",
				"B+", "1234", "1333 South St", "Halifax", "Nova Scotia", "Canada", "B3J 2K9", "Fever", "Aunt May",
				"1234567890", "Peter Parker", "1234567", "012", "12456", "100 CAD");

		assertEquals(" Invalid Name. ", val.checkValidation(patient));
	}

	@Test
	void toTestIfValidationWorksForDateofBirth() {

		PatientProfileModel patient = new PatientProfileModel("Peter", "M", "Parker", "XXXX", "Male", "1234567890",
				"B+", "1234", "1333 South St", "Halifax", "Nova Scotia", "Canada", "B3J 2K9", "Fever", "Aunt May",
				"1234567890", "Peter Parker", "1234567", "012", "12456", "100 CAD");

		assertEquals(" Invalid Date of Birth. ", val.checkValidation(patient));

	}

	@Test
	void toTestIfValidationWorksForFutureDateofBirth() {

		PatientProfileModel patient = new PatientProfileModel("Peter", "M", "Parker", "2022-01-01", "Male",
				"1234567890", "B+", "1234", "1333 South St", "Halifax", "Nova Scotia", "Canada", "B3J 2K9", "Fever",
				"Aunt May", "1234567890", "Peter Parker", "1234567", "012", "12456", "100 CAD");

		assertEquals(" Date of Birth should be a Past Date. ", val.checkValidation(patient));

	}

	@Test
	void toTestIfValidationWorksForGender() {

		PatientProfileModel patient = new PatientProfileModel("Peter", "M", "Parker", "1997-01-01", "XXXX",
				"1234567890", "B+", "1234", "1333 South St", "Halifax", "Nova Scotia", "Canada", "B3J 2K9", "Fever",
				"Aunt May", "1234567890", "Peter Parker", "1234567", "012", "12456", "100 CAD");

		assertEquals(" Invalid Gender. ", val.checkValidation(patient));
	}

	@Test
	void toTestIfValidationWorksForPhoneNo() {

		PatientProfileModel patient = new PatientProfileModel("Peter", "M", "Parker", "1997-01-01", "Male", "123456789",
				"B+", "1234", "1333 South St", "Halifax", "Nova Scotia", "Canada", "B3J 2K9", "Fever", "Aunt May",
				"1234567890", "Peter Parker", "1234567", "012", "12456", "100 CAD");

		assertEquals(" Invalid Phone Number. ", val.checkValidation(patient));
	}

	@Test
	void toTestIfValidationWorksForBloodGroup() {

		PatientProfileModel patient = new PatientProfileModel("Peter", "M", "Parker", "1997-01-01", "Male",
				"1234567890", "P+", "1234", "1333 South St", "Halifax", "Nova Scotia", "Canada", "B3J 2K9", "Fever",
				"Aunt May", "1234567890", "Peter Parker", "1234567", "012", "12456", "100 CAD");

		assertEquals(" Invalid Blood Group. ", val.checkValidation(patient));
	}

	@Test
	void toTestIfValidationWorksForApartmentNumber() {

		PatientProfileModel patient = new PatientProfileModel("Peter", "M", "Parker", "1997-01-01", "Male",
				"1234567890", "B+", "ABC", "1333 South St", "Halifax", "Nova Scotia", "Canada", "B3J 2K9", "Fever",
				"Aunt May", "1234567890", "Peter Parker", "1234567", "012", "12456", "100 CAD");

		assertEquals(" Invalid Apartment Number. ", val.checkValidation(patient));
	}

	@Test
	void toTestIfValidationWorksForStreet() {

		PatientProfileModel patient = new PatientProfileModel("Peter", "M", "Parker", "1997-01-01", "Male",
				"1234567890", "B+", "1245", "XXX&XXX", "Halifax", "Nova Scotia", "Canada", "B3J 2K9", "Fever",
				"Aunt May", "1234567890", "Peter Parker", "1234567", "012", "12456", "100 CAD");

		assertEquals(" Invalid Street. ", val.checkValidation(patient));
	}

	@Test
	void toTestIfValidationWorksForCity() {

		PatientProfileModel patient = new PatientProfileModel("Peter", "M", "Parker", "1997-01-01", "Male",
				"1234567890", "B+", "1245", "1333 South St", "1234", "Nova Scotia", "Canada", "B3J 2K9", "Fever",
				"Aunt May", "1234567890", "Peter Parker", "1234567", "012", "12456", "100 CAD");

		assertEquals(" Invalid City. ", val.checkValidation(patient));
	}

	@Test
	void toTestIfValidationWorksForProvince() {

		PatientProfileModel patient = new PatientProfileModel("Peter", "M", "Parker", "1997-01-01", "Male",
				"1234567890", "B+", "1245", "1333 South St", "Halifax", "&&", "Canada", "B3J 2K9", "Fever", "Aunt May",
				"1234567890", "Peter Parker", "1234567", "012", "12456", "100 CAD");

		assertEquals(" Invalid Province. ", val.checkValidation(patient));
	}

	@Test
	void toTestIfValidationWorksForCountry() {

		PatientProfileModel patient = new PatientProfileModel("Peter", "M", "Parker", "1997-01-01", "Male",
				"1234567890", "B+", "1245", "1333 South St", "Halifax", "Nova Scotia", "&&", "B3J 2K9", "Fever",
				"Aunt May", "1234567890", "Peter Parker", "1234567", "012", "12456", "100 CAD");

		assertEquals(" Invalid Country. ", val.checkValidation(patient));
	}

	@Test
	void toTestIfValidationWorksForPostalCode() {

		PatientProfileModel patient = new PatientProfileModel("Peter", "M", "Parker", "1997-01-01", "Male",
				"1234567890", "B+", "1245", "1333 South St", "Halifax", "Nova Scotia", "Canada", "XXXX", "Fever",
				"Aunt May", "1234567890", "Peter Parker", "1234567", "012", "12456", "100 CAD");

		assertEquals(" Invalid Postal Code. ", val.checkValidation(patient));
	}

	@Test
	void toTestIfValidationWorksForAccountName() {

		PatientProfileModel patient = new PatientProfileModel("Peter", "M", "Parker", "1997-01-01", "Male",
				"1234567890", "B+", "1245", "1333 South St", "Halifax", "Nova Scotia", "Canada", "B3J 2K9", "Fever",
				"Aunt May", "1234567890", "1.Peter", "1234567", "012", "12456", "100 CAD");

		assertEquals(" Invalid Account Name. ", val.checkValidation(patient));
	}

	@Test
	void toTestIfValidationWorksForAccountNumber() {

		PatientProfileModel patient = new PatientProfileModel("Peter", "M", "Parker", "1997-01-01", "Male",
				"1234567890", "B+", "1245", "1333 South St", "Halifax", "Nova Scotia", "Canada", "B3J 2K9", "Fever",
				"Aunt May", "1234567890", "Peter Parker", "123456", "012", "12456", "100 CAD");

		assertEquals(" Invalid Account Number. ", val.checkValidation(patient));
	}

	@Test
	void toTestIfValidationWorksForBankNumber() {

		PatientProfileModel patient = new PatientProfileModel("Peter", "M", "Parker", "1997-01-01", "Male",
				"1234567890", "B+", "1245", "1333 South St", "Halifax", "Nova Scotia", "Canada", "B3J 2K9", "Fever",
				"Aunt May", "1234567890", "Peter Parker", "1234567", "0123", "12456", "100 CAD");

		assertEquals(" Invalid Bank Institution Number. ", val.checkValidation(patient));
	}

	@Test
	void toTestIfValidationWorksForTransitNumber() {

		PatientProfileModel patient = new PatientProfileModel("Peter", "M", "Parker", "1997-01-01", "Male",
				"1234567890", "B+", "1245", "1333 South St", "Halifax", "Nova Scotia", "Canada", "B3J 2K9", "Fever",
				"Aunt May", "1234567890", "Peter Parker", "1234567", "012", "124567", "100 CAD");

		assertEquals(" Invalid Transit Number. ", val.checkValidation(patient));
	}

	@Test
	void toTestIfValidationWorksForCorrectDetails() {

		PatientProfileModel patient = new PatientProfileModel("Peter", "M", "Parker", "1997-01-01", "Male",
				"1234567890", "B+", "1245", "1333 South St", "Halifax", "Nova Scotia", "Canada", "B3J 2K9", "Fever",
				"Aunt May", "1234567890", "Peter Parker", "1234567", "012", "12456", "100 CAD");

		assertEquals("", val.checkValidation(patient));
	}
}
