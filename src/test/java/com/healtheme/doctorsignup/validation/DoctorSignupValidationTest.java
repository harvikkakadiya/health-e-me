package com.healtheme.doctorsignup.validation;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.healtheme.doctorsignup.model.DoctorSignupModel;

public class DoctorSignupValidationTest {

	DoctorSignupValidation validation = new DoctorSignupValidation();
	
	@Test
	void toTestValidationForFirstName() {

		DoctorSignupModel doctor = new DoctorSignupModel ("123","Kumar","Rathi","1998-10-16","male","9029991234","ankitrathi@gmail.com","Ankit@123",
				"Ankit@123","11","1991 Brunswick Street","Halifax","Nova Scotia","Canada","B2K 2Y9","Mbbs",
				"heartsurgeon","abcd12345678","100","Ankit","1234567","123","12345");

		assertEquals(" Invalid First Name. ", validation.validateDoctorSignup(doctor));
	}
	
	@Test
	void toTestValidationForMiddleName() {

		DoctorSignupModel doctor = new DoctorSignupModel ("Ankit","123","Rathi","1998-10-16","male","9029991234","ankitrathi@gmail.com","Ankit@123",
				"Ankit@123","11","1991 Brunswick Street","Halifax","Nova Scotia","Canada","B2K 2Y9","Mbbs",
				"heartsurgeon","abcd12345678","100","Ankit","1234567","123","12345");

		assertEquals(" Invalid Middle Name. ", validation.validateDoctorSignup(doctor));
	}
	@Test
	void toTestValidationForLastName() {

		DoctorSignupModel doctor = new DoctorSignupModel ("Ankit","Kumar","123","1998-10-16","male","9029991234","ankitrathi@gmail.com","Ankit@123",
				"Ankit@123","11","1991 Brunswick Street","Halifax","Nova Scotia","Canada","B2K 2Y9","Mbbs",
				"heartsurgeon","abcd12345678","100","Ankit","1234567","123","12345");

		assertEquals(" Invalid Last Name. ", validation.validateDoctorSignup(doctor));
	}
	
	@Test
	void toTestValidationForDate() {

		DoctorSignupModel doctor = new DoctorSignupModel ("Ankit","Kumar","Rathi","1998-31-31","male","9029991234","ankitrathi@gmail.com","Ankit@123",
				"Ankit@123","11","1991 Brunswick Street","Halifax","Nova Scotia","Canada","B2K 2Y9","Mbbs",
				"heartsurgeon","abcd12345678","100","Ankit","1234567","123","12345");

		assertEquals(" Invalid Date. ", validation.validateDoctorSignup(doctor));
	}
	
	@Test
	void toTestValidationForGender() {

		DoctorSignupModel doctor = new DoctorSignupModel ("Ankit","Kumar","Rathi","1998-10-16","hello","9029991234","ankitrathi@gmail.com","Ankit@123",
				"Ankit@123","11","1991 Brunswick Street","Halifax","Nova Scotia","Canada","B2K 2Y9","Mbbs",
				"heartsurgeon","abcd12345678","100","Ankit","1234567","123","12345");

		assertEquals(" Invalid Gender. ", validation.validateDoctorSignup(doctor));
	}
	
	@Test
	void toTestValidationForPhone() {

		DoctorSignupModel doctor = new DoctorSignupModel ("Ankit","Kumar","Rathi","1998-10-16","male","abc123","ankitrathi@gmail.com","Ankit@123",
				"Ankit@123","11","1991 Brunswick Street","Halifax","Nova Scotia","Canada","B2K 2Y9","Mbbs",
				"heartsurgeon","abcd12345678","100","Ankit","1234567","123","12345");

		assertEquals(" Invalid Phone Number. ", validation.validateDoctorSignup(doctor));
	}
	
	@Test
	void toTestValidationForEmail() {

		DoctorSignupModel doctor = new DoctorSignupModel ("Ankit","Kumar","Rathi","1998-10-16","male","9029991234","ankitrathigmail.com","Ankit@123",
				"Ankit@123","11","1991 Brunswick Street","Halifax","Nova Scotia","Canada","B2K 2Y9","Mbbs",
				"heartsurgeon","abcd12345678","100","Ankit","1234567","123","12345");

		assertEquals(" Invalid Email. ", validation.validateDoctorSignup(doctor));
	}
	
	@Test
	void toTestValidationForPassword() {

		DoctorSignupModel doctor = new DoctorSignupModel ("Ankit","Kumar","Rathi","1998-10-16","male","9029991234","ankitrathi@gmail.com","ankit123",
				"ankit123","11","1991 Brunswick Street","Halifax","Nova Scotia","Canada","B2K 2Y9","Mbbs",
				"heartsurgeon","abcd12345678","100","Ankit","1234567","123","12345");

		assertEquals(" Invalid Password. ", validation.validateDoctorSignup(doctor));
	}
	
	@Test
	void toTestValidationForConfirmPassword() {

		DoctorSignupModel doctor = new DoctorSignupModel ("Ankit","Kumar","Rathi","1998-10-16","male","9029991234","ankitrathi@gmail.com","Ankit@123",
				"ankit123","11","1991 Brunswick Street","Halifax","Nova Scotia","Canada","B2K 2Y9","Mbbs",
				"heartsurgeon","abcd12345678","100","Ankit","1234567","123","12345");

		assertEquals(" Password doesn't match. ", validation.validateDoctorSignup(doctor));
	}
	@Test
	void toTestValidationForApartmentNumber() {

		DoctorSignupModel doctor = new DoctorSignupModel ("Ankit","Kumar","Rathi","1998-10-16","male","9029991234","ankitrathi@gmail.com","Ankit@123",
				"Ankit@123","abc","1991 Brunswick Street","Halifax","Nova Scotia","Canada","B2K 2Y9","Mbbs",
				"heartsurgeon","abcd12345678","100","Ankit","1234567","123","12345");

		assertEquals(" Invalid Apartment Number. ", validation.validateDoctorSignup(doctor));
	}
	
	@Test
	void toTestValidationForStreetAddress() {

		DoctorSignupModel doctor = new DoctorSignupModel ("Ankit","Kumar","Rathi","1998-10-16","male","9029991234","ankitrathi@gmail.com","Ankit@123",
				"Ankit@123","11","@","Halifax","Nova Scotia","Canada","B2K 2Y9","Mbbs",
				"heartsurgeon","abcd12345678","100","Ankit","1234567","123","12345");

		assertEquals(" Invalid Street Address. ", validation.validateDoctorSignup(doctor));
	}
	@Test
	void toTestValidationForCity() {

		DoctorSignupModel doctor = new DoctorSignupModel ("Ankit","Kumar","Rathi","1998-10-16","male","9029991234","ankitrathi@gmail.com","Ankit@123",
				"Ankit@123","11","1991 Brunswick Street","123","Nova Scotia","Canada","B2K 2Y9","Mbbs",
				"heartsurgeon","abcd12345678","100","Ankit","1234567","123","12345");

		assertEquals(" Invalid City. ", validation.validateDoctorSignup(doctor));
	}
	
	@Test
	void toTestValidationForProvince() {

		DoctorSignupModel doctor = new DoctorSignupModel ("Ankit","Kumar","Rathi","1998-10-16","male","9029991234","ankitrathi@gmail.com","Ankit@123",
				"Ankit@123","11","1991 Brunswick Street","Halifax","@","Canada","B2K 2Y9","Mbbs",
				"heartsurgeon","abcd12345678","100","Ankit","1234567","123","12345");

		assertEquals(" Invalid Province. ", validation.validateDoctorSignup(doctor));
	}
	
	@Test
	void toTestValidationForCountry() {

		DoctorSignupModel doctor = new DoctorSignupModel ("Ankit","Kumar","Rathi","1998-10-16","male","9029991234","ankitrathi@gmail.com","Ankit@123",
				"Ankit@123","11","1991 Brunswick Street","Halifax","Halifax","@","B2K 2Y9","Mbbs",
				"heartsurgeon","abcd12345678","100","Ankit","1234567","123","12345");

		assertEquals(" Invalid Country. ", validation.validateDoctorSignup(doctor));
	}
	
	@Test
	void toTestValidationForPostalCode() {

		DoctorSignupModel doctor = new DoctorSignupModel ("Ankit","Kumar","Rathi","1998-10-16","male","9029991234","ankitrathi@gmail.com","Ankit@123",
				"Ankit@123","11","1991 Brunswick Street","Halifax","Nova Scotia","Canada","110027","Mbbs",
				"heartsurgeon","abcd12345678","100","Ankit","1234567","123","12345");

		assertEquals(" Invalid Postal Code. ", validation.validateDoctorSignup(doctor));
	}
	
	@Test
	void toTestValidationForQualification() {

		DoctorSignupModel doctor = new DoctorSignupModel ("Ankit","Kumar","Rathi","1998-10-16","male","9029991234","ankitrathi@gmail.com","Ankit@123",
				"Ankit@123","11","1991 Brunswick Street","Halifax","Nova Scotia","Canada","B3K 2Y9","@",
				"heartsurgeon","abcd12345678","100","Ankit","1234567","123","12345");

		assertEquals(" Invalid Qualification. ", validation.validateDoctorSignup(doctor));
	}
	
	@Test
	void toTestValidationForSpecialisation() {

		DoctorSignupModel doctor = new DoctorSignupModel ("Ankit","Kumar","Rathi","1998-10-16","male","9029991234","ankitrathi@gmail.com","Ankit@123",
				"Ankit@123","11","1991 Brunswick Street","Halifax","Nova Scotia","Canada","B3K 2Y9","Mbbs",
				"@","abcd12345678","100","Ankit","1234567","123","12345");

		assertEquals(" Invalid Specialisation. ", validation.validateDoctorSignup(doctor));
	}
	@Test
	void toTestValidationForLicenseId() {

		DoctorSignupModel doctor = new DoctorSignupModel ("Ankit","Kumar","Rathi","1998-10-16","male","9029991234","ankitrathi@gmail.com","Ankit@123",
				"Ankit@123","11","1991 Brunswick Street","Halifax","Nova Scotia","Canada","B3K 2Y9","Mbbs",
				"heartsurgeon","abc","100","Ankit","1234567","123","12345");

		assertEquals(" Invalid Doctor License ID. ", validation.validateDoctorSignup(doctor));
	}
	
	@Test
	void toTestValidationForConsultationFees() {

		DoctorSignupModel doctor = new DoctorSignupModel ("Ankit","Kumar","Rathi","1998-10-16","male","9029991234","ankitrathi@gmail.com","Ankit@123",
				"Ankit@123","11","1991 Brunswick Street","Halifax","Nova Scotia","Canada","B3K 2Y9","Mbbs",
				"heartsurgeon","abcd12345678","abc","Ankit","1234567","123","12345");

		assertEquals(" Invalid Consultation Fees. ", validation.validateDoctorSignup(doctor));
	}
	
	@Test
	void toTestValidationForAccountName() {

		DoctorSignupModel doctor = new DoctorSignupModel ("Ankit","Kumar","Rathi","1998-10-16","male","9029991234","ankitrathi@gmail.com","Ankit@123",
				"Ankit@123","11","1991 Brunswick Street","Halifax","Nova Scotia","Canada","B3K 2Y9","Mbbs",
				"heartsurgeon","abcd12345678","100","123","1234567","123","12345");

		assertEquals(" Invalid Account Holder Name. ", validation.validateDoctorSignup(doctor));
	}
	
	@Test
	void toTestValidationForAccountNumber() {

		DoctorSignupModel doctor = new DoctorSignupModel ("Ankit","Kumar","Rathi","1998-10-16","male","9029991234","ankitrathi@gmail.com","Ankit@123",
				"Ankit@123","11","1991 Brunswick Street","Halifax","Nova Scotia","Canada","B3K 2Y9","Mbbs",
				"heartsurgeon","abcd12345678","100","Ankit","1234","123","12345");

		assertEquals(" Invalid Account Number. ", validation.validateDoctorSignup(doctor));
	}
	
	@Test
	void toTestValidationForBankNumber() {

		DoctorSignupModel doctor = new DoctorSignupModel ("Ankit","Kumar","Rathi","1998-10-16","male","9029991234","ankitrathi@gmail.com","Ankit@123",
				"Ankit@123","11","1991 Brunswick Street","Halifax","Nova Scotia","Canada","B3K 2Y9","Mbbs",
				"heartsurgeon","abcd12345678","100","Ankit","1234567","1234","12345");

		assertEquals(" Invalid Bank Number. ", validation.validateDoctorSignup(doctor));
	}
	
	@Test
	void toTestValidationForTransitNumber() {

		DoctorSignupModel doctor = new DoctorSignupModel ("Ankit","Kumar","Rathi","1998-10-16","male","9029991234","ankitrathi@gmail.com","Ankit@123",
				"Ankit@123","11","1991 Brunswick Street","Halifax","Nova Scotia","Canada","B3K 2Y9","Mbbs",
				"heartsurgeon","abcd12345678","100","Ankit","1234567","123","1234");

		assertEquals(" Invalid Transit Number. ", validation.validateDoctorSignup(doctor));
	}
}
