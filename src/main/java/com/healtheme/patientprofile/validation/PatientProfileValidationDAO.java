package com.healtheme.patientprofile.validation;

import com.healtheme.patientprofile.model.PatientProfileModel;

public interface PatientProfileValidationDAO {

	public String checkValidation(PatientProfileModel patient);

	public String checkName(String name);

	public String checkDate(String date);

	public String checkGender(String gender);

	public String checkPhoneNo(String phoneNumber);

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
