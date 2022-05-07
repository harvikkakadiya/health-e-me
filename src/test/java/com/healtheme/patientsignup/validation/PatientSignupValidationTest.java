package com.healtheme.patientsignup.validation;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.healtheme.patientsignup.model.PatientSignupModel;

public class PatientSignupValidationTest {

	PatientSignupValidation val = new PatientSignupValidation();

	@Test
	void toTestIfValidationWorksForInvalidName() {

		PatientSignupModel patient = new PatientSignupModel("123", "M", "Parker", "1997-02-02", "Male", "1234567890",
				"Peter@gmail.com", "Peter@123", "Peter@123", "B+", "1234", "1333 South St", "Halifax", "Nova Scotia",
				"Canada", "B3J 2K9", "Fever", "Aunt May", "1234567890", "Peter Parker", "1234567", "012", "12456");

		assertEquals(" Invalid Name. ", val.checkValidation(patient));
	}

	@Test
	void toTestIfValidationWorksForDateofBirth() {

		PatientSignupModel patient = new PatientSignupModel("Peter", "M", "Parker", "XXXX", "Male", "1234567890",
				"Peter@gmail.com", "Peter@123", "Peter@123", "B+", "1234", "1333 South St", "Halifax", "Nova Scotia",
				"Canada", "B3J 2K9", "Fever", "Aunt May", "1234567890", "Peter Parker", "1234567", "012", "12456");

		assertEquals(" Invalid Date of Birth. ", val.checkValidation(patient));

	}

	@Test
	void toTestIfValidationWorksForFutureDateofBirth() {

		PatientSignupModel patient = new PatientSignupModel("Peter", "M", "Parker", "2022-02-02", "Male", "1234567890",
				"Peter@gmail.com", "Peter@123", "Peter@123", "B+", "1234", "1333 South St", "Halifax", "Nova Scotia",
				"Canada", "B3J 2K9", "Fever", "Aunt May", "1234567890", "Peter Parker", "1234567", "012", "12456");

		assertEquals(" Date of Birth should be a Past Date. ", val.checkValidation(patient));

	}

	@Test
	void toTestIfValidationWorksForGender() {

		PatientSignupModel patient = new PatientSignupModel("Peter", "M", "Parker", "1997-02-02", "XXXX", "1234567890",
				"Peter@gmail.com", "Peter@123", "Peter@123", "B+", "1234", "1333 South St", "Halifax", "Nova Scotia",
				"Canada", "B3J 2K9", "Fever", "Aunt May", "1234567890", "Peter Parker", "1234567", "012", "12456");

		assertEquals(" Invalid Gender. ", val.checkValidation(patient));
	}

	@Test
	void toTestIfValidationWorksForPhoneNo() {

		PatientSignupModel patient = new PatientSignupModel("Peter", "M", "Parker", "1997-02-02", "Male", "123456789",
				"Peter@gmail.com", "Peter@123", "Peter@123", "B+", "1234", "1333 South St", "Halifax", "Nova Scotia",
				"Canada", "B3J 2K9", "Fever", "Aunt May", "1234567890", "Peter Parker", "1234567", "012", "12456");

		assertEquals(" Invalid Phone Number. ", val.checkValidation(patient));
	}

	@Test
	void toTestIfValidationWorksForEmail() {

		PatientSignupModel patient = new PatientSignupModel("Peter", "M", "Parker", "1997-02-02", "Male", "1234567890",
				"Petergmail.com", "Peter@123", "Peter@123", "B+", "1234", "1333 South St", "Halifax", "Nova Scotia",
				"Canada", "B3J 2K9", "Fever", "Aunt May", "1234567890", "Peter Parker", "1234567", "012", "12456");

		assertEquals(" Invalid Email. ", val.checkValidation(patient));
	}

	@Test
	void toTestIfValidationWorksForPassword() {

		PatientSignupModel patient = new PatientSignupModel("Peter", "M", "Parker", "1997-02-02", "Male", "1234567890",
				"Peter@gmail.com", "abc123", "abc123", "B+", "1234", "1333 South St", "Halifax", "Nova Scotia",
				"Canada", "B3J 2K9", "Fever", "Aunt May", "1234567890", "Peter Parker", "1234567", "012", "12456");

		assertEquals(" Invalid Password. ", val.checkValidation(patient));
	}

	@Test
	void toTestIfValidationWorksForConfirmPassword() {

		PatientSignupModel patient = new PatientSignupModel("Peter", "M", "Parker", "1997-02-02", "Male", "1234567890",
				"Peter@gmail.com", "Peter@123", "Peter@456", "B+", "1234", "1333 South St", "Halifax", "Nova Scotia",
				"Canada", "B3J 2K9", "Fever", "Aunt May", "1234567890", "Peter Parker", "1234567", "012", "12456");

		assertEquals(" Passwords Do not Match. ", val.checkValidation(patient));
	}

	@Test
	void toTestIfValidationWorksForBloodGroup() {

		PatientSignupModel patient = new PatientSignupModel("Peter", "M", "Parker", "1997-02-02", "Male", "1234567890",
				"Peter@gmail.com", "Peter@123", "Peter@123", "P+", "1234", "1333 South St", "Halifax", "Nova Scotia",
				"Canada", "B3J 2K9", "Fever", "Aunt May", "1234567890", "Peter Parker", "1234567", "012", "12456");

		assertEquals(" Invalid Blood Group. ", val.checkValidation(patient));
	}

	@Test
	void toTestIfValidationWorksForApartmentNumber() {

		PatientSignupModel patient = new PatientSignupModel("Peter", "M", "Parker", "1997-02-02", "Male", "1234567890",
				"Peter@gmail.com", "Peter@123", "Peter@123", "B+", "&&", "1333 South St", "Halifax", "Nova Scotia",
				"Canada", "B3J 2K9", "Fever", "Aunt May", "1234567890", "Peter Parker", "1234567", "012", "12456");

		assertEquals(" Invalid Apartment Number. ", val.checkValidation(patient));
	}

	@Test
	void toTestIfValidationWorksForStreet() {

		PatientSignupModel patient = new PatientSignupModel("Peter", "M", "Parker", "1997-02-02", "Male", "1234567890",
				"Peter@gmail.com", "Peter@123", "Peter@123", "B+", "1234", "&&", "Halifax", "Nova Scotia", "Canada",
				"B3J 2K9", "Fever", "Aunt May", "1234567890", "Peter Parker", "1234567", "012", "12456");

		assertEquals(" Invalid Street. ", val.checkValidation(patient));
	}

	@Test
	void toTestIfValidationWorksForCity() {

		PatientSignupModel patient = new PatientSignupModel("Peter", "M", "Parker", "1997-02-02", "Male", "1234567890",
				"Peter@gmail.com", "Peter@123", "Peter@123", "B+", "1234", "1333 South St", "&&", "Nova Scotia",
				"Canada", "B3J 2K9", "Fever", "Aunt May", "1234567890", "Peter Parker", "1234567", "012", "12456");

		assertEquals(" Invalid City. ", val.checkValidation(patient));
	}

	@Test
	void toTestIfValidationWorksForProvince() {

		PatientSignupModel patient = new PatientSignupModel("Peter", "M", "Parker", "1997-02-02", "Male", "1234567890",
				"Peter@gmail.com", "Peter@123", "Peter@123", "B+", "1234", "1333 South St", "Halifax", "&&", "Canada",
				"B3J 2K9", "Fever", "Aunt May", "1234567890", "Peter Parker", "1234567", "012", "12456");

		assertEquals(" Invalid Province. ", val.checkValidation(patient));
	}

	@Test
	void toTestIfValidationWorksForCountry() {

		PatientSignupModel patient = new PatientSignupModel("Peter", "M", "Parker", "1997-02-02", "Male", "1234567890",
				"Peter@gmail.com", "Peter@123", "Peter@123", "B+", "1234", "1333 South St", "Halifax", "Nova Scotia",
				"&&", "B3J 2K9", "Fever", "Aunt May", "1234567890", "Peter Parker", "1234567", "012", "12456");

		assertEquals(" Invalid Country. ", val.checkValidation(patient));
	}

	@Test
	void toTestIfValidationWorksForPostalCode() {

		PatientSignupModel patient = new PatientSignupModel("Peter", "M", "Parker", "1997-02-02", "Male", "1234567890",
				"Peter@gmail.com", "Peter@123", "Peter@123", "B+", "1234", "1333 South St", "Halifax", "Nova Scotia",
				"Canada", "XXXX", "Fever", "Aunt May", "1234567890", "Peter Parker", "1234567", "012", "12456");

		assertEquals(" Invalid Postal Code. ", val.checkValidation(patient));
	}

	@Test
	void toTestIfValidationWorksForAccountName() {

		PatientSignupModel patient = new PatientSignupModel("Peter", "M", "Parker", "1997-02-02", "Male", "1234567890",
				"Peter@gmail.com", "Peter@123", "Peter@123", "B+", "1234", "1333 South St", "Halifax", "Nova Scotia",
				"Canada", "B3J 2K9", "Fever", "Aunt May", "1234567890", "&&", "1234567", "012", "12456");

		assertEquals(" Invalid Account Name. ", val.checkValidation(patient));
	}

	@Test
	void toTestIfValidationWorksForAccountNumber() {

		PatientSignupModel patient = new PatientSignupModel("Peter", "M", "Parker", "1997-02-02", "Male", "1234567890",
				"Peter@gmail.com", "Peter@123", "Peter@123", "B+", "1234", "1333 South St", "Halifax", "Nova Scotia",
				"Canada", "B3J 2K9", "Fever", "Aunt May", "1234567890", "Peter Parker", "123456", "012", "12456");

		assertEquals(" Invalid Account Number. ", val.checkValidation(patient));
	}

	@Test
	void toTestIfValidationWorksForBankNumber() {

		PatientSignupModel patient = new PatientSignupModel("Peter", "M", "Parker", "1997-02-02", "Male", "1234567890",
				"Peter@gmail.com", "Peter@123", "Peter@123", "B+", "1234", "1333 South St", "Halifax", "Nova Scotia",
				"Canada", "B3J 2K9", "Fever", "Aunt May", "1234567890", "Peter Parker", "1234567", "0123", "12456");

		assertEquals(" Invalid Bank Institution Number. ", val.checkValidation(patient));
	}

	@Test
	void toTestIfValidationWorksForTransitNumber() {

		PatientSignupModel patient = new PatientSignupModel("Peter", "M", "Parker", "1997-02-02", "Male", "1234567890",
				"Peter@gmail.com", "Peter@123", "Peter@123", "B+", "1234", "1333 South St", "Halifax", "Nova Scotia",
				"Canada", "B3J 2K9", "Fever", "Aunt May", "1234567890", "Peter Parker", "1234567", "012", "124567");

		assertEquals(" Invalid Transit Number. ", val.checkValidation(patient));
	}

	@Test
	void toTestIfValidationWorksForCorrectDetails() {

		PatientSignupModel patient = new PatientSignupModel("Peter", "M", "Parker", "1997-02-02", "Male", "1234567890",
				"Peter@gmail.com", "Peter@123", "Peter@123", "B+", "1234", "1333 South St", "Halifax", "Nova Scotia",
				"Canada", "B3J 2K9", "Fever", "Aunt May", "1234567890", "Peter Parker", "1234567", "012", "12456");

		assertEquals("", val.checkValidation(patient));
	}

}
