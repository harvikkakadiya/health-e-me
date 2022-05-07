package com.healtheme.patientsignup.validation;

import com.healtheme.patientsignup.model.PatientSignupModel;

public interface PatientSignupValidationDAO {

	public String checkValidation(PatientSignupModel patient);

	public String checkName(String name);

	public String checkDate(String date);

	public String checkGender(String gender);

	public String checkPhoneNo(String phoneNumber);

	public String checkEmail(String email);

	public String checkPassword(String password);

	public String checkConfirmPassword(String password, String confirmPassword);

	public String checkBloodGroup(String bloodGroup);

	public String checkApartmentNumber(String aptNumber);

	public String checkStreet(String street);

	public String checkCity(String city);

	public String checkProvince(String province);

	public String checkCountry(String country);

	public String checkPostalCode(String postalCode);

	public String checkAccountName(String accountName);

	public String checkAccountNumber(String accountNumber);

	public String checkBankNumber(String bankNumber);

	public String checkTransitNumber(String transitNumber);
}
