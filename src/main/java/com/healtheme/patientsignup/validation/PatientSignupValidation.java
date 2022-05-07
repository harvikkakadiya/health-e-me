package com.healtheme.patientsignup.validation;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

import com.healtheme.patientsignup.model.PatientSignupModel;

@Component("patientSignupValidation")
public class PatientSignupValidation implements PatientSignupValidationDAO {

	private static final String NAME_VALIDATION = "^[a-zA-Z\\s]{2,}";
	private static final String PHONE_VALIDATION = "^[1-9][0-9]{9}";
	private static final String EMAIL_VALIDATION = "^^([\\w-\\.]+){1,64}@([\\w&&[^_]]+){2,255}.[a-z]{2,}$";
	private static final String PASSWORD_VALIDATION = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
	private static final String APARTMENT_VALIDATION = "^[0-9\\s#-]*$";
	private static final String STREET_VALIDATION = "^[a-zA-Z0-9\\s]*$";
	private static final String ONLY_ALPHABETS = "^[a-zA-Z\\s]*$";
	private static final String POSTAL_CODE_VALIDATION = "^[a-zA-Z][0-9][a-zA-Z]\\s[0-9][a-zA-Z][0-9]";
	private static final String ACCOUNT_NUMBER_VALIDATION = "^[0-9]{7}|[0-9]{12}";
	private static final String BANK_NUMBER_VALIDATION = "^[0-9]{3}";
	private static final String TRANSIT_NUMBER_VALIDATION = "^[0-9]{5}";
	private static final String BLOOD_GROUP_VALIDATION = "^(A|B|AB|O)[+-]$";

	@Override
	public String checkValidation(PatientSignupModel patient) {

		String nameMessage = checkName(
				patient.getPatientFirstName() + patient.getPatientMiddleName() + patient.getPatientLastName());
		String dateMessage = checkDate(patient.getPatientDOB());
		String genderMessage = checkGender(patient.getPatientGender());
		String phoneNoMessage = checkPhoneNo(patient.getPatientPhoneNo());
		String emailMessage = checkEmail(patient.getPatientEmail());
		String passwordMessage = checkPassword(patient.getPatientPassword());
		String confirmPasswordMessage = checkConfirmPassword(patient.getPatientPassword(),
				patient.getPatientConfirmPassword());
		String bloodGroupMessage = checkBloodGroup(patient.getPatientBloodGroup());
		String apartmentNumberMessage = checkApartmentNumber(patient.getPatientApartmentNo());
		String streetMessage = checkStreet(patient.getPatientStreet());
		String cityMessage = checkCity(patient.getPatientCity());
		String provinceMessage = checkProvince(patient.getPatientProvince());
		String countryMessage = checkCountry(patient.getPatientCountry());
		String postalCodeMessage = checkPostalCode(patient.getPatientPostalCode());
		String accountNameMessage = checkAccountName(patient.getPatientAccountName());
		String accountNumberMessage = checkAccountNumber(patient.getPatientAccountNo());
		String bankNumberMessage = checkBankNumber(patient.getPatientBankInstitutionNo());
		String transitNumberMessage = checkTransitNumber(patient.getPatientTransitNo());

		String errorMessage = nameMessage + dateMessage + genderMessage + phoneNoMessage + emailMessage
				+ passwordMessage + confirmPasswordMessage + bloodGroupMessage + apartmentNumberMessage + streetMessage
				+ cityMessage + provinceMessage + countryMessage + postalCodeMessage + accountNameMessage
				+ accountNumberMessage + bankNumberMessage + transitNumberMessage;
		return errorMessage;
	}

	@Override
	public String checkName(String name) {
		boolean boolNameValid = Pattern.matches(NAME_VALIDATION, name);
		if (boolNameValid) {
			
			return "";
		}

		return " Invalid Name. ";
	}

	@Override
	public String checkDate(String date) {

		try {
			Date currentDate = java.util.Calendar.getInstance().getTime();
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date inputDate = dateFormat.parse(date);
			int result = currentDate.compareTo(inputDate);
			if (result < 0) {
				
				return " Date of Birth should be a Past Date. ";
			}
			return "";

		} catch (ParseException e) {

			return " Invalid Date of Birth. ";
		}
	}

	@Override
	public String checkGender(String gender) {

		gender = gender.toLowerCase();

		if (gender.equals("female") || gender.equals("male") || gender.equals("other")) {
			
			return "";
		}

		return " Invalid Gender. ";
	}

	@Override
	public String checkPhoneNo(String phoneNumber) {

		boolean boolValidPhoneNumber = Pattern.matches(PHONE_VALIDATION, phoneNumber);

		if (boolValidPhoneNumber) {
			
			return "";
		}

		return " Invalid Phone Number. ";
	}

	@Override
	public String checkEmail(String email) {

		email = email.toLowerCase();
		boolean boolValidEmail = Pattern.matches(EMAIL_VALIDATION, email);

		if (boolValidEmail) {
			
			return "";
		}

		return " Invalid Email. ";
	}

	@Override
	public String checkPassword(String password) {
		boolean boolValidPassword = Pattern.matches(PASSWORD_VALIDATION, password);
		if (boolValidPassword) {
			
			return "";
		}

		return " Invalid Password. ";
	}

	@Override
	public String checkConfirmPassword(String password, String confirmPassword) {
		if (password.equals(confirmPassword)) {
			
			return "";
		}
		
		return " Passwords Do not Match. ";
	}

	@Override
	public String checkBloodGroup(String bloodGroup) {
		boolean boolBloodGroupValid = Pattern.matches(BLOOD_GROUP_VALIDATION, bloodGroup);
		if (boolBloodGroupValid) {
			
			return "";
		}

		return " Invalid Blood Group. ";
	}

	@Override
	public String checkApartmentNumber(String aptNumber) {
		boolean boolValidAptNumber = Pattern.matches(APARTMENT_VALIDATION, aptNumber);
		if (boolValidAptNumber) {
			
			return "";
		}

		return " Invalid Apartment Number. ";
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
	public String checkCountry(String country) {
		boolean boolCountryValid = Pattern.matches(ONLY_ALPHABETS, country);
		if (boolCountryValid) {
			
			return "";
		}

		return " Invalid Country. ";
	}

	@Override
	public String checkPostalCode(String postalCode) {
		boolean boolPostalValid = Pattern.matches(POSTAL_CODE_VALIDATION, postalCode);
		if (boolPostalValid) {
			
			return "";
		}

		return " Invalid Postal Code. ";
	}

	@Override
	public String checkAccountName(String accountName) {
		boolean boolAccountNameValid = Pattern.matches(ONLY_ALPHABETS, accountName);
		if (boolAccountNameValid) {
			
			return "";
		}

		return " Invalid Account Name. ";
	}

	@Override
	public String checkAccountNumber(String accountNumber) {
		boolean boolAccountNumberValid = Pattern.matches(ACCOUNT_NUMBER_VALIDATION, accountNumber);
		if (boolAccountNumberValid) {
			
			return "";
		}

		return " Invalid Account Number. ";
	}

	@Override
	public String checkBankNumber(String bankNumber) {
		boolean boolAccountNumberValid = Pattern.matches(BANK_NUMBER_VALIDATION, bankNumber);
		if (boolAccountNumberValid) {
			
			return "";
		}

		return " Invalid Bank Institution Number. ";
	}

	@Override
	public String checkTransitNumber(String transitNumber) {
		boolean boolAccountNumberValid = Pattern.matches(TRANSIT_NUMBER_VALIDATION, transitNumber);
		if (boolAccountNumberValid) {
			
			return "";
		}

		return " Invalid Transit Number. ";
	}
}
