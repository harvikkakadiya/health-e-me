package com.healtheme.doctorsignup.validation;

import com.healtheme.doctorsignup.model.DoctorSignupModel;

public interface DoctorSignupValidationDAO {
	public String validateDoctorSignup(DoctorSignupModel doctorObj);

	public String isFirstNameValid(String name);

	public String isMiddleNameValid(String name);

	public String isLastNameValid(String name);

	public String isDateValid(String date);

	public String isGenderValid(String gender);

	public String isPhoneValid(String phoneNumber);

	public String isEmailValid(String email);

	public String isPasswordValid(String password);

	public String isConfirmPasswordValid(String password, String confirmPassword);

	public String isApartmentNumberValid(String aptNumber);

	public String isStreetValid(String street);

	public String isCityValid(String city);

	public String isProvinceValid(String province);

	public String isCountryValid(String country);

	public String isPostalCodeValid(String postalCode);

	public String isQualificationValid(String qualification);

	public String isSpecialisationValid(String qualification);

	public String isDoctorLicenseIdValid(String license);

	public String isFeesValid(String fees);

	public String isAccountNameValid(String accountName);

	public String isAccountNumberValid(String accountNumber);

	public String isBankNumberValid(String bankNumber);

	public String isTransitNumberValid(String transitNumber);

}
