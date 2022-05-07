package com.healtheme.labsignup.validation;

import com.healtheme.labsignup.model.LabSignupModel;

public interface LabSignupValidationDAO {

	public String validateLabSignup(LabSignupModel labObj);

	public String isLabNameValid(String labName);

	public String isFirstNameValid(String name);

	public String isMiddleNameValid(String name);

	public String isLastNameValid(String name);

	public String isDateValid(String date);

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

	public String isAccountNameValid(String accountName);

	public String isAccountNumberValid(String accountNumber);

	public String isBankNumberValid(String bankNumber);

	public String isTransitNumberValid(String transitNumber);

	public String isLabLicenseValid(String license);

}
