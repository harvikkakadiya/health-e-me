package com.healtheme.patientmycart.validation;

import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

import com.healtheme.patientmycart.model.PatientOrderModel;

@Component("patientCartValidationDAO")
public class PatientCartValidation implements PatientCartValidationDAO {

	public static final String STREET_VALIDATION = "^[a-zA-Z0-9\\s]*$";
	public static final String POSTAL_CODE_VALIDATION = "^[a-zA-Z][0-9][a-zA-Z]\\s[0-9][a-zA-Z][0-9]";
	public static final String ONLY_ALPHABETS = "^[a-zA-Z\\s]*$";

	@Override
	public String checkValidation(PatientOrderModel po) {
		String streetMessage = checkStreet(po.getStreet_address());
		String cityMessage = checkCity(po.getCity());
		String provinceMessage = checkProvince(po.getProvince());
		String postalCodeMessage = checkPostalCode(po.getPostal_code());

		String errorMessage = streetMessage + cityMessage + provinceMessage + postalCodeMessage;
		return errorMessage;
	}

	@Override
	public String checkStreet(String street) {
		boolean boolStreetValid = Pattern.matches(STREET_VALIDATION, street);
		if (boolStreetValid) {
			return "";
		}
		return " Invalid Street. ";
	}

	@Override
	public String checkCity(String city) {
		boolean boolCityValid = Pattern.matches(ONLY_ALPHABETS, city);
		if (boolCityValid) {
			return "";
		}
		return " Invalid City. ";
	}

	@Override
	public String checkProvince(String province) {
		boolean boolProvinceValid = Pattern.matches(ONLY_ALPHABETS, province);
		if (boolProvinceValid) {
			return "";
		}
		return " Invalid Province. ";
	}

	@Override
	public String checkPostalCode(String postalCode) {
		boolean boolPostalValid = Pattern.matches(POSTAL_CODE_VALIDATION, postalCode);
		if (boolPostalValid) {
			return "";
		}
		return " Invalid Postal Code. ";
	}

}
