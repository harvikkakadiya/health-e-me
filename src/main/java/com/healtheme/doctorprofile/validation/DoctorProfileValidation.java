package com.healtheme.doctorprofile.validation;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

import com.healtheme.doctorprofile.model.DoctorProfileModel;

@Component("doctorProfileValidation")
public class DoctorProfileValidation implements DoctorProfileValidationDAO {
	private static final String NAME_VALIDATION = "^[a-zA-Z\\s]{2,}";
	private static final String MIDDLE_NAME_VALIDATION = "^[a-zA-Z\\s]{0,}";
	private static final String DATE_FORMAT_VALIDATION = "^(19|20)\\d\\d[-](0[1-9]|1[012])[-](0[1-9]|[12][0-9]|3[01])$";
	private static final Integer FEB_MONTH = 2;
	private static final Integer FEB_NON_LEAP_DAYS = 28;
	private static final Integer FEB_LEAP_DAYS = 29;
	private static final Integer MONTH_DAYS = 30;
	private static final Integer HALF_YEAR = 6;
	private static final Integer AGE_LIMIT = 150;
	private static final String PHONE_VALIDATION = "^[1-9][0-9]{9}";
	private static final String APARTMENT_VALIDATION = "^[0-9\\s#-]*$";
	private static final String STREET_VALIDATION = "^[a-zA-Z0-9\\s]*$";
	private static final String ONLY_ALPHABETS = "^[a-zA-Z\\s]*$";
	private static final String ONLY_NUMBERS = "^[0-9]*$";
	private static final String POSTAL_CODE_VALIDATION = "^[a-zA-Z][0-9][a-zA-Z]\\s[0-9][a-zA-Z][0-9]";
	private static final String QUALIFICATION_VALIDATION = "^[a-zA-Z.\\s]*$";
	private static final String LICENSE_VALIDATION = "^[a-zA-Z]{4}[0-9]{8}";
	private static final String ACCOUNT_NUMBER_VALIDATION = "^[0-9]{7}|[0-9]{12}";
	private static final String BANK_NUMBER_VALIDATION = "^[0-9]{3}";
	private static final String TRANSIT_NUMBER_VALIDATION = "^[0-9]{5}";

	@Override
	public String validateDoctorProfile(DoctorProfileModel doctorObj) {
		String errorFirstName = isFirstNameValid(doctorObj.getDoctorFname());
		String errorMiddleName = isMiddleNameValid(doctorObj.getDoctorMname());
		String errorLastName = isLastNameValid(doctorObj.getDoctorLname());
		String errorDob = isDateValid(doctorObj.getDoctorDob());
		String errorGender = isGenderValid(doctorObj.getDoctorGender());
		String errorPhone = isPhoneValid(doctorObj.getDoctorPhone());
		String errorApartment = isApartmentNumberValid(doctorObj.getDoctorApartment());
		String errorStreet = isStreetValid(doctorObj.getDoctorStreetAddress());
		String errorCity = isCityValid(doctorObj.getDoctorCity());
		String errorProvince = isProvinceValid(doctorObj.getDoctorProvince());
		String errorCountry = isCountryValid(doctorObj.getDoctorCountry());
		String errorPostal = isPostalCodeValid(doctorObj.getDoctorPostalCode());
		String errorlQualification = isQualificationValid(doctorObj.getDoctorQualification());
		String errorSpecialisation = isSpecialisationValid(doctorObj.getDoctorSpecialisation());
		String errorDoctorLicense = isDoctorLicenseIdValid(doctorObj.getDoctorLicenseId());
		String errorFees = isFeesValid(doctorObj.getDoctorConsultationFees());
		String errorAccountName = isAccountNameValid(doctorObj.getDoctorAccountName());
		String errorAccountNumber = isAccountNumberValid(doctorObj.getDoctorAccountNumber());
		String errorBankNumber = isBankNumberValid(doctorObj.getDoctorBankNumber());
		String errorTransitNumber = isTransitNumberValid(doctorObj.getDoctorTransitNumber());

		String errorMessage = errorFirstName + errorMiddleName + errorLastName + errorDob + errorGender + errorPhone
				+ errorApartment + errorStreet + errorCity + errorProvince + errorCountry + errorPostal
				+ errorlQualification + errorSpecialisation + errorDoctorLicense + errorFees + errorAccountName
				+ errorAccountNumber + errorBankNumber + errorTransitNumber;

		return errorMessage;
	}

	@Override
	public String isFirstNameValid(String name) {
		boolean nameValid = Pattern.matches(NAME_VALIDATION, name);
		
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
		boolean nameValid = Pattern.matches(NAME_VALIDATION, name);
		
		if (nameValid) {
			return "";
		}
		
		return " Invalid Last Name. ";
	}

	@Override
	public String isDateValid(String date) {
		boolean boolValidDateFormat = Pattern.matches(DATE_FORMAT_VALIDATION, date);

		if (boolValidDateFormat == false) {
			return " Invalid Date. ";
		}

		boolean boolFebDateValid = true;
		boolean boolDateValid = true;
		boolean boolNonPastDate = true;
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

		if (currentList.get(0) - inputList.get(0) >= AGE_LIMIT) {
			boolNonPastDate = false;
		}

		Integer flagYear = inputList.get(0).compareTo(currentList.get(0));
		Integer flagMonth = inputList.get(1).compareTo(currentList.get(1));

		if ((inputList.get(0) > currentList.get(0)) || ((flagYear == 0) && (inputList.get(1) > currentList.get(1)))
				|| (flagYear == 0 && flagMonth == 0 && (inputList.get(2) > currentList.get(2)))) {
			boolNonFutureDate = false;
		}

		if (boolValidDateFormat && boolFebDateValid && boolDateValid && boolNonPastDate && boolNonFutureDate) {
			return "";
		}

		return " Invalid Date. ";
	}

	@Override
	public String isGenderValid(String gender) {
		boolean boolGender = false;
		gender = gender.toLowerCase();
		
		if (gender.compareTo("female") == 0 || gender.compareTo("male") == 0 || gender.compareTo("other") == 0
				|| gender.compareTo("na") == 0)
			boolGender = true;

		if (boolGender) {
			return "";
		}

		return " Invalid Gender. ";
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
	public String isQualificationValid(String qualification) {
		boolean boolQualificationValid = Pattern.matches(QUALIFICATION_VALIDATION, qualification);

		if (boolQualificationValid) {
			return "";
		}
		
		return " Invalid Qualification. ";
	}

	@Override
	public String isSpecialisationValid(String qualification) {
		boolean boolSpecialisationValid = Pattern.matches(ONLY_ALPHABETS, qualification);
		
		if (boolSpecialisationValid) {
			return "";
		}
		
		return " Invalid Specialisation. ";
	}

	@Override
	public String isDoctorLicenseIdValid(String license) {
		boolean boolDoctorLicenseIdValid = Pattern.matches(LICENSE_VALIDATION, license);
		
		if (boolDoctorLicenseIdValid) {
			return "";
		}
		
		return " Invalid Doctor License ID. ";
	}

	@Override
	public String isFeesValid(String fees) {
		boolean boolFeesValid = Pattern.matches(ONLY_NUMBERS, fees);
		
		if (boolFeesValid) {
			return "";
		}
		
		return " Invalid Consultation Fees. ";
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
}