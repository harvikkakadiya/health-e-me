package com.healtheme.labcreatereports.validation;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.*;

import org.springframework.stereotype.Component;

import com.healtheme.labcreatereports.model.LabCreateReportsModel;

@Component("labCreateReportsValidation")
public class LabCreateReportsValidation implements LabCreateReportsValidationDAO {

	private static final String DATE_FORMAT_VALIDATION = "^(19|20)\\d\\d[-](0[1-9]|1[012])[-](0[1-9]|[12][0-9]|3[01])$";
	private static final Integer FEB_MONTH = 2;
	private static final Integer FEB_NON_LEAP_DAYS = 28;
	private static final Integer FEB_LEAP_DAYS = 29;
	private static final Integer MONTH_DAYS = 30;
	private static final Integer HALF_YEAR = 6;
	private static final String TIME_FORMAT_VALIDATION = "^([0-1]?[0-9]|2[0-3]):[0-5][0-9]$";
	private static final String EMAIL_VALIDATION = "^([\\w-\\.]+){1,64}@([\\w&&[^_]]+){2,255}.[a-z]{2,}$";

	@Override
	public String validateLabCreateReports(LabCreateReportsModel labObj) {

		String errorPatientEmail = isPatientEmailValid(labObj.getPatientEmail());
		String errorReportDate = isDateValid(labObj.getReportDate());
		String errorReportTime = isTimeValid(labObj.getReportTime());
		String errorDoctorEmail = isDoctorEmailValid(labObj.getPrescribedBy());
		String errorMessage = errorPatientEmail + errorReportDate + errorReportTime + errorDoctorEmail;

		return errorMessage;
	}

	@Override
	public String isPatientEmailValid(String patientEmail) {
		patientEmail = patientEmail.toLowerCase();
		boolean boolValidEmail = Pattern.matches(EMAIL_VALIDATION, patientEmail);

		if (boolValidEmail) {
			return "";
		}

		return " Invalid Patient Email. ";
	}

	@Override
	public String isDateValid(String date) {
		boolean boolValidDateFormat = Pattern.matches(DATE_FORMAT_VALIDATION, date);

		if (boolValidDateFormat == false) {
			return " Invalid Report Date. ";
		}

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
	public String isTimeValid(String time) {
		boolean boolValidTimeFormat = Pattern.matches(TIME_FORMAT_VALIDATION, time);

		if (boolValidTimeFormat) {
			return "";
		}

		return " Invalid Report Time. ";
	}

	@Override
	public String isDoctorEmailValid(String doctorEmail) {
		doctorEmail = doctorEmail.toLowerCase();
		boolean boolValidEmail = Pattern.matches(EMAIL_VALIDATION, doctorEmail);

		if (boolValidEmail) {
			return "";
		}

		return " Invalid Doctor Email. ";
	}
}
