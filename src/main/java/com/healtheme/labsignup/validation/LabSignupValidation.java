package com.healtheme.labsignup.validation;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

import com.healtheme.labsignup.model.LabSignupModel;

@Component("labSignupValidation")
public class LabSignupValidation implements LabSignupValidationDAO {

	private static final String FIRST_NAME_VALIDATION = "^[a-zA-Z\\s]{2,}";
	private static final String MIDDLE_NAME_VALIDATION = "^[a-zA-Z\\s]{0,}";
	private static final String DATE_FORMAT_VALIDATION = "^(19|20)\\d\\d[-](0[1-9]|1[012])[-](0[1-9]|[12][0-9]|3[01])$";
	private static final Integer FEB_MONTH = 2;
	private static final Integer FEB_NON_LEAP_DAYS = 28;
	private static final Integer FEB_LEAP_DAYS = 29;
	private static final Integer MONTH_DAYS = 30;
	private static final Integer HALF_YEAR = 6;
	private static final String PHONE_VALIDATION = "^[1-9][0-9]{9}";
	private static final String EMAIL_VALIDATION = "^([\\w-\\.]+){1,64}@([\\w&&[^_]]+){2,255}.[a-z]{2,}$";
	private static final String PASSWORD_VALIDATION = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
	private static final String APARTMENT_VALIDATION = "^[0-9\\s#-]*$";
	private static final String STREET_VALIDATION = "^[a-zA-Z0-9\\s]*$";
	private static final String ONLY_ALPHABETS = "^[a-zA-Z\\s]*$";
	private static final String POSTAL_CODE_VALIDATION = "^[a-zA-Z][0-9][a-zA-Z]\\s[0-9][a-zA-Z][0-9]";
	private static final String ACCOUNT_NUMBER_VALIDATION = "^[0-9]{7}|[0-9]{12}";
	private static final String BANK_NUMBER_VALIDATION = "^[0-9]{3}";
	private static final String TRANSIT_NUMBER_VALIDATION = "^[0-9]{5}";
	private static final String LAB_LICENSE_VALIDATION = "^[0-9]{7}";

	@Override
	public String validateLabSignup(LabSignupModel labObj) {

		String errorLabName = isLabNameValid(labObj.getLabName());
		String errorFirstName = isFirstNameValid(labObj.getLabOwnerFirstName());
		String errorMiddleName = isMiddleNameValid(labObj.getLabOwnerMiddleName());
		String errorLastName = isLastNameValid(labObj.getLabOwnerLastName());
		String errorDob = isDateValid(labObj.getLabDoe());
		String errorPhone = isPhoneValid(labObj.getLabPhoneNumber());
		String errorEmailValid = isEmailValid(labObj.getLabEmail());
		String errorPasswordValid = isPasswordValid(labObj.getLabPassword());
		String errorConfirmPasswordValid = isConfirmPasswordValid(labObj.getLabPassword(),
				labObj.getLabConfirmPassword());
		String errorApartment = isApartmentNumberValid(labObj.getLabUnit());
		String errorStreet = isStreetValid(labObj.getLabStreet());
		String errorCity = isCityValid(labObj.getLabCity());
		String errorProvince = isProvinceValid(labObj.getLabProvince());
		String errorCountry = isCountryValid(labObj.getLabCountry());
		String errorPostal = isPostalCodeValid(labObj.getLabPostalCode());
		String errorLabLicense = isLabLicenseValid(labObj.getLabLicenseId());
		String errorAccountName = isAccountNameValid(labObj.getLabAccountName());
		String errorAccountNumber = isAccountNumberValid(labObj.getLabAccountNumber());
		String errorBankNumber = isBankNumberValid(labObj.getLabBankInstitutionNumber());
		String errorTransitNumber = isTransitNumberValid(labObj.getLabTransitNumber());
		String errorMessage = errorLabName + errorFirstName + errorMiddleName + errorLastName + errorDob + errorPhone
				+ errorEmailValid + errorPasswordValid + errorConfirmPasswordValid + errorApartment + errorStreet
				+ errorCity + errorProvince + errorCountry + errorPostal + errorLabLicense + errorAccountName
				+ errorAccountNumber + errorBankNumber + errorTransitNumber;

		return errorMessage;
	}

	@Override
	public String isLabNameValid(String labName) {
		boolean boolLabNameValid = Pattern.matches(ONLY_ALPHABETS, labName);
		if (boolLabNameValid) {
			return "";
		}

		return " Invalid lab name. ";
	}

	@Override
	public String isFirstNameValid(String name) {
		boolean nameValid = Pattern.matches(FIRST_NAME_VALIDATION, name);
		if (nameValid) {
			return "";
		}

		return " Invalid First Name. ";
	}

	@Override
	public String isMiddleNameValid(String name) {
		boolean nameValid = Pattern.matches(MIDDLE_NAME_VALIDATION, name);
		if (nameValid) {
			return "";
		}

		return " Invalid Middle Name. ";
	}

	@Override
	public String isLastNameValid(String name) {
		boolean nameValid = Pattern.matches(FIRST_NAME_VALIDATION, name);
		if (nameValid) {
			return "";
		}

		return " Invalid Last Name. ";
	}

	@Override
	public String isDateValid(String date) {
		boolean boolValidDateFormat = Pattern.matches(DATE_FORMAT_VALIDATION, date);

		if (boolValidDateFormat == false)
			return " Invalid Date. ";

		boolean boolFebDateValid = true;
		boolean boolDateValid = true;
		boolean boolNonFutureDate = true;

		LocalDate currentDate = LocalDate.now();
		List<Integer> currentList = new ArrayList<Integer>();
		List<Integer> inputList = new ArrayList<Integer>();

		currentList.add(currentDate.getYear());
		currentList.add(currentDate.getMonthValue());
		currentList.add(currentDate.getDayOfMonth());

		String[] dateString = date.split("-");
		for (String i : dateString) {
			inputList.add(Integer.parseInt(i));
		}

		boolean isLeapYear = false;
		Integer year = inputList.get(0);
		if ((year % 400 == 0) || ((year % 4 == 0) && (year % 100 != 0))) {
			isLeapYear = true;
		}
		if (isLeapYear == false && inputList.get(1) == FEB_MONTH && inputList.get(2) > FEB_NON_LEAP_DAYS) {
			boolFebDateValid = false;
		}
		if (inputList.get(1) == FEB_MONTH && inputList.get(2) > FEB_LEAP_DAYS) {
			boolFebDateValid = false;
		}

		if (inputList.get(1) % 2 == 0 && inputList.get(1) < HALF_YEAR + 1 && inputList.get(2) > MONTH_DAYS) {
			boolDateValid = false;
		}
		if (inputList.get(1) % 2 != 0 && inputList.get(1) > HALF_YEAR + 1 && inputList.get(2) > MONTH_DAYS) {
			boolDateValid = false;
		}

		Integer flagYear = inputList.get(0).compareTo(currentList.get(0));
		Integer flagMonth = inputList.get(1).compareTo(currentList.get(1));

		if ((inputList.get(0) > currentList.get(0)) || ((flagYear == 0) && (inputList.get(1) > currentList.get(1)))
				|| (flagYear == 0 && flagMonth == 0 && (inputList.get(2) > currentList.get(2)))) {
			boolNonFutureDate = false;
		}

		if (boolValidDateFormat && boolFebDateValid && boolDateValid && boolNonFutureDate) {
			return "";
		}

		return " Invalid Date. ";
	}

	@Override
	public String isPhoneValid(String phoneNumber) {
		boolean boolValidPhoneNumber = Pattern.matches(PHONE_VALIDATION, phoneNumber);

		if (boolValidPhoneNumber) {
			return "";
		}

		return " Invalid Phone Number. ";
	}

	@Override
	public String isEmailValid(String email) {
		email = email.toLowerCase();
		boolean boolValidEmail = Pattern.matches(EMAIL_VALIDATION, email);

		if (boolValidEmail) {
			return "";
		}

		return " Invalid Email. ";
	}

	@Override
	public String isPasswordValid(String password) {
		boolean boolValidPassword = Pattern.matches(PASSWORD_VALIDATION, password);
		if (boolValidPassword) {
			return "";
		} else {
			return " Invalid password. ";
		}
	}

	@Override
	public String isConfirmPasswordValid(String password, String confirmPassword) {

		if (password.compareTo(confirmPassword) == 0) {
			return "";
		} else {
			return " Passwords do not match. ";
		}
	}

	@Override
	public String isApartmentNumberValid(String aptNumber) {
		boolean boolValidAptNumber = Pattern.matches(APARTMENT_VALIDATION, aptNumber);
		if (boolValidAptNumber) {
			return "";
		}
		
		return " Invalid Apartment Number. ";
	}

	@Override
	public String isStreetValid(String street) {
		boolean boolStreetValid = Pattern.matches(STREET_VALIDATION, street);
		if (boolStreetValid) {
			return "";
		}
		
		return " Invalid Street Address. ";
	}

	@Override
	public String isCityValid(String city) {
		boolean boolCityValid = Pattern.matches(ONLY_ALPHABETS, city);
		if (boolCityValid) {
			return "";
		}

		return " Invalid City. ";
	}

	@Override
	public String isProvinceValid(String province) {
		boolean boolProvinceValid = Pattern.matches(ONLY_ALPHABETS, province);

		if (boolProvinceValid) {
			return "";
		}

		return " Invalid Province. ";
	}

	@Override
	public String isCountryValid(String country) {
		boolean boolCountryValid = Pattern.matches(ONLY_ALPHABETS, country);
		if (boolCountryValid) {
			return "";
		}
		
		return " Invalid Country. ";
	}

	@Override
	public String isPostalCodeValid(String postalCode) {
		boolean boolPostalValid = Pattern.matches(POSTAL_CODE_VALIDATION, postalCode);
		if (boolPostalValid) {
			return "";
		}

		return " Invalid Postal Code. ";
	}

	@Override
	public String isAccountNameValid(String accountName) {
		boolean boolAccountNameValid = Pattern.matches(ONLY_ALPHABETS, accountName);
		if (boolAccountNameValid) {
			return "";
		}

		return " Invalid Account Holder Name. ";
	}

	@Override
	public String isAccountNumberValid(String accountNumber) {
		boolean boolAccountNumberValid = Pattern.matches(ACCOUNT_NUMBER_VALIDATION, accountNumber);
		if (boolAccountNumberValid) {
			return "";
		}

		return " Invalid Account Number. ";
	}

	@Override
	public String isBankNumberValid(String bankNumber) {
		boolean boolAccountNumberValid = Pattern.matches(BANK_NUMBER_VALIDATION, bankNumber);
		if (boolAccountNumberValid) {
			return "";
		}

		return " Invalid Bank Number. ";
	}

	@Override
	public String isTransitNumberValid(String transitNumber) {
		boolean boolAccountNumberValid = Pattern.matches(TRANSIT_NUMBER_VALIDATION, transitNumber);
		if (boolAccountNumberValid) {
			return "";
		}

		return " Invalid Transit Number. ";
	}

	@Override
	public String isLabLicenseValid(String license) {
		boolean boolLabLicenseValid = Pattern.matches(LAB_LICENSE_VALIDATION, license);

		if (boolLabLicenseValid) {
			return "";
		} else {
			return " Invalid lab license ID. ";
		}
	}
}
