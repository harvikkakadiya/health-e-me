package com.healtheme.doctorprofile.validation;

import com.healtheme.doctorprofile.model.DoctorProfileModel;

public interface DoctorProfileValidationDAO {
	public String validateDoctorProfile(DoctorProfileModel doctorObj);

	public String isFirstNameValid(String name);

	public String isMiddleNameValid(String name);

	public String isLastNameValid(String name);

	public String isDateValid(String date);

	public String isGenderValid(String gender);

	public String isPhoneValid(String phoneNumber);

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
