package com.healtheme.doctorprofile.validation;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.healtheme.doctorprofile.model.DoctorProfileModel;

public class DoctorProfileValidationTest {

	DoctorProfileValidation validation = new DoctorProfileValidation();
	
	@Test
	void toTestValidationForFirstName() {

		DoctorProfileModel doctor = new DoctorProfileModel ("123","Kumar","Rathi","1998-10-16","male","9029991234","11","1991 Brunswick Street","Halifax","Nova Scotia","Canada","B2K 2Y9","Mbbs",
				"heartsurgeon","abcd12345678","100","Ankit","1234567","123","12345","");

		assertEquals(" Invalid First Name. ", validation.validateDoctorProfile(doctor));
	}
	
	@Test
	void toTestValidationForMiddleName() {

		DoctorProfileModel doctor = new DoctorProfileModel ("Ankit","123","Rathi","1998-10-16","male","9029991234","11","1991 Brunswick Street","Halifax","Nova Scotia","Canada","B2K 2Y9","Mbbs",
				"heartsurgeon","abcd12345678","100","Ankit","1234567","123","12345","");

		assertEquals(" Invalid Middle Name. ", validation.validateDoctorProfile(doctor));
	}
	@Test
	void toTestValidationForLastName() {

		DoctorProfileModel doctor = new DoctorProfileModel ("Ankit","Kumar","123","1998-10-16","male","9029991234","11","1991 Brunswick Street","Halifax","Nova Scotia","Canada","B2K 2Y9","Mbbs",
				"heartsurgeon","abcd12345678","100","Ankit","1234567","123","12345","");

		assertEquals(" Invalid Last Name. ", validation.validateDoctorProfile(doctor));
	}
	
	@Test
	void toTestValidationForDate() {

		DoctorProfileModel doctor = new DoctorProfileModel ("Ankit","Kumar","Rathi","1998-31-31","male","9029991234","11","1991 Brunswick Street","Halifax","Nova Scotia","Canada","B2K 2Y9","Mbbs",
				"heartsurgeon","abcd12345678","100","Ankit","1234567","123","12345","");

		assertEquals(" Invalid Date. ", validation.validateDoctorProfile(doctor));
	}
	
	@Test
	void toTestValidationForGender() {

		DoctorProfileModel doctor = new DoctorProfileModel ("Ankit","Kumar","Rathi","1998-10-16","hello","9029991234","11","1991 Brunswick Street","Halifax","Nova Scotia","Canada","B2K 2Y9","Mbbs",
				"heartsurgeon","abcd12345678","100","Ankit","1234567","123","12345","");

		assertEquals(" Invalid Gender. ", validation.validateDoctorProfile(doctor));
	}
	
	@Test
	void toTestValidationForPhone() {

		DoctorProfileModel doctor = new DoctorProfileModel ("Ankit","Kumar","Rathi","1998-10-16","male","abc123","11","1991 Brunswick Street","Halifax","Nova Scotia","Canada","B2K 2Y9","Mbbs",
				"heartsurgeon","abcd12345678","100","Ankit","1234567","123","12345","");

		assertEquals(" Invalid Phone Number. ", validation.validateDoctorProfile(doctor));
	}
	
	
	@Test
	void toTestValidationForApartmentNumber() {

		DoctorProfileModel doctor = new DoctorProfileModel ("Ankit","Kumar","Rathi","1998-10-16","male","9029991234","abc","1991 Brunswick Street","Halifax","Nova Scotia","Canada","B2K 2Y9","Mbbs",
				"heartsurgeon","abcd12345678","100","Ankit","1234567","123","12345","");

		assertEquals(" Invalid Apartment Number. ", validation.validateDoctorProfile(doctor));
	}
	
	@Test
	void toTestValidationForStreetAddress() {

		DoctorProfileModel doctor = new DoctorProfileModel ("Ankit","Kumar","Rathi","1998-10-16","male","9029991234","11","@","Halifax","Nova Scotia","Canada","B2K 2Y9","Mbbs",
				"heartsurgeon","abcd12345678","100","Ankit","1234567","123","12345","");

		assertEquals(" Invalid Street Address. ", validation.validateDoctorProfile(doctor));
	}
	@Test
	void toTestValidationForCity() {
		DoctorProfileModel doctor = new DoctorProfileModel ("Ankit","Kumar","Rathi","1998-10-16","male","9029991234","11","@","Halifax","Nova Scotia","Canada","B2K 2Y9","Mbbs",
				"heartsurgeon","abcd12345678","100","Ankit","1234567","123","12345","");

		assertEquals(" Invalid Street Address. ", validation.validateDoctorProfile(doctor));
	}
	
	@Test
	void toTestValidationForProvince() {

		DoctorProfileModel doctor = new DoctorProfileModel ("Ankit","Kumar","Rathi","1998-10-16","male","9029991234","11","1991 Brunswick Street","Halifax","@","Canada","B2K 2Y9","Mbbs",
				"heartsurgeon","abcd12345678","100","Ankit","1234567","123","12345","");

		assertEquals(" Invalid Province. ", validation.validateDoctorProfile(doctor));
	}
	
	@Test
	void toTestValidationForCountry() {

		DoctorProfileModel doctor = new DoctorProfileModel ("Ankit","Kumar","Rathi","1998-10-16","male","9029991234","11","1991 Brunswick Street","Halifax","Halifax","@","B2K 2Y9","Mbbs",
				"heartsurgeon","abcd12345678","100","Ankit","1234567","123","12345","");

		assertEquals(" Invalid Country. ", validation.validateDoctorProfile(doctor));
	}
	
	@Test
	void toTestValidationForPostalCode() {

		DoctorProfileModel doctor = new DoctorProfileModel ("Ankit","Kumar","Rathi","1998-10-16","male","9029991234","11","1991 Brunswick Street","Halifax","Nova Scotia","Canada","110027","Mbbs",
				"heartsurgeon","abcd12345678","100","Ankit","1234567","123","12345","");

		assertEquals(" Invalid Postal Code. ", validation.validateDoctorProfile(doctor));
	}
	
	@Test
	void toTestValidationForQualification() {

		DoctorProfileModel doctor = new DoctorProfileModel ("Ankit","Kumar","Rathi","1998-10-16","male","9029991234",
				"11","1991 Brunswick Street","Halifax","Nova Scotia","Canada","B3K 2Y9","@",
				"heartsurgeon","abcd12345678","100","Ankit","1234567","123","12345","");

		assertEquals(" Invalid Qualification. ", validation.validateDoctorProfile(doctor));
	}
	
	@Test
	void toTestValidationForSpecialisation() {

		DoctorProfileModel doctor = new DoctorProfileModel ("Ankit","Kumar","Rathi","1998-10-16","male","9029991234",
				"11","1991 Brunswick Street","Halifax","Nova Scotia","Canada","B3K 2Y9","Mbbs",
				"@","abcd12345678","100","Ankit","1234567","123","12345","");

		assertEquals(" Invalid Specialisation. ", validation.validateDoctorProfile(doctor));
	}
	@Test
	void toTestValidationForLicenseId() {

		DoctorProfileModel doctor = new DoctorProfileModel  ("Ankit","Kumar","Rathi","1998-10-16","male","9029991234","11","1991 Brunswick Street","Halifax","Nova Scotia","Canada","B3K 2Y9","Mbbs",
				"heartsurgeon","abc","100","Ankit","1234567","123","12345","");

		assertEquals(" Invalid Doctor License ID. ", validation.validateDoctorProfile(doctor));
	}
	
	@Test
	void toTestValidationForConsultationFees() {

		DoctorProfileModel doctor = new DoctorProfileModel("Ankit","Kumar","Rathi","1998-10-16","male","9029991234",
				"11","1991 Brunswick Street","Halifax","Nova Scotia","Canada","B3K 2Y9","Mbbs",
				"heartsurgeon","abcd12345678","abc","Ankit","1234567","123","12345","");

		assertEquals(" Invalid Consultation Fees. ", validation.validateDoctorProfile(doctor));
	}
	
	@Test
	void toTestValidationForAccountName() {

		DoctorProfileModel doctor = new DoctorProfileModel ("Ankit","Kumar","Rathi","1998-10-16","male","9029991234"
				,"11","1991 Brunswick Street","Halifax","Nova Scotia","Canada","B3K 2Y9","Mbbs",
				"heartsurgeon","abcd12345678","100","123","1234567","123","12345","");

		assertEquals(" Invalid Account Holder Name. ", validation.validateDoctorProfile(doctor));
	}
	
	@Test
	void toTestValidationForAccountNumber() {

		DoctorProfileModel doctor = new DoctorProfileModel ("Ankit","Kumar","Rathi","1998-10-16","male","9029991234",
				"11","1991 Brunswick Street","Halifax","Nova Scotia","Canada","B3K 2Y9","Mbbs",
				"heartsurgeon","abcd12345678","100","Ankit","1234","123","12345","");

		assertEquals(" Invalid Account Number. ", validation.validateDoctorProfile(doctor));
	}
	
	@Test
	void toTestValidationForBankNumber() {

		DoctorProfileModel doctor = new DoctorProfileModel ("Ankit","Kumar","Rathi","1998-10-16","male","9029991234",
				"11","1991 Brunswick Street","Halifax","Nova Scotia","Canada","B3K 2Y9","Mbbs",
				"heartsurgeon","abcd12345678","100","Ankit","1234567","1234","12345","");

		assertEquals(" Invalid Bank Number. ", validation.validateDoctorProfile(doctor));
	}
	
	@Test
	void toTestValidationForTransitNumber() {

		DoctorProfileModel doctor = new DoctorProfileModel ("Ankit","Kumar","Rathi","1998-10-16","male","9029991234",
				"11","1991 Brunswick Street","Halifax","Nova Scotia","Canada","B3K 2Y9","Mbbs",
				"heartsurgeon","abcd12345678","100","Ankit","1234567","123","1234","");

		assertEquals(" Invalid Transit Number. ", validation.validateDoctorProfile(doctor));
	}
}

