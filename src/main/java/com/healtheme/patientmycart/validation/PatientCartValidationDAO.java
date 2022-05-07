package com.healtheme.patientmycart.validation;

import com.healtheme.patientmycart.model.PatientOrderModel;

public interface PatientCartValidationDAO {

	String checkValidation(PatientOrderModel po);

	String checkStreet(String street);

	String checkCity(String city);

	String checkProvince(String province);

	String checkPostalCode(String postalCode);

}