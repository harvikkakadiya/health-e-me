package com.healtheme.doctorcreateprescription.validation;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

import com.healtheme.doctorcreateprescription.model.DoctorCreatePrescriptionModel;

@Component("doctorCreatePrescriptionValidation")
public class DoctorCreatePrescriptionValidation implements DoctorCreatePrescriptionValidationDAO {

	private static final String DATE_FORMAT_VALIDATION = "^(19|20)\\d\\d[-](0[1-9]|1[012])[-](0[1-9]|[12][0-9]|3[01])$";
	private static final String EMAIL_VALIDATION = "^([\\w-\\.]+){1,64}@([\\w&&[^_]]+){2,255}.[a-z]{2,}$";
	private static final String TIME_FORMAT_VALIDATION = "^([0-1]?[0-9]|2[0-3]):[0-5][0-9]$";
	private static final String TEXT_VALIDATION = "^[a-zA-z0-9\\s-,]{1,200}$";

	@Override
	public String checkValidation(DoctorCreatePrescriptionModel prescriptionObj) {
		String errorPatientEmail = isPatientEmailValid(prescriptionObj.getPatientPrescriptionEmail());
		String errorDoctorEmail = isDoctorEmailValid(prescriptionObj.getDoctorPrescriptionEmail());
		String errorAppointmentDate = isDateValid(prescriptionObj.getPrescriptionAppointmentDate());
		String errorAppointmentTime = isTimeValid(prescriptionObj.getPrescriptionAppointmentTime());
		String errorSymptoms = isSymptomsValid(prescriptionObj.getPrescriptionrPatientSymptoms());
		String errorDiagnosis = isDiagnosisValid(prescriptionObj.getPrescriptionPatientDiagnosis());
		String errorMedicines = isMedicinesValid(prescriptionObj.getPrescriptionPatientMedicines());

		String error = errorPatientEmail + errorDoctorEmail + errorAppointmentDate + errorAppointmentTime
				+ errorSymptoms + errorDiagnosis + errorMedicines;

		return error;
	}

	@Override
	public String isPatientEmailValid(String email) {
		email = email.toLowerCase();
		boolean boolValidEmail = Pattern.matches(EMAIL_VALIDATION, email);

		if (boolValidEmail) {
			return "";
		}

		return " Invalid Patient Email. ";
	}

	@Override
	public String isDoctorEmailValid(String email) {
		email = email.toLowerCase();
		boolean boolValidEmail = Pattern.matches(EMAIL_VALIDATION, email);

		if (boolValidEmail) {
			return "";
		}

		return " Invalid Doctor Email. ";
	}

	@Override
	public String isDateValid(String date) {
		boolean boolValidDateFormat = Pattern.matches(DATE_FORMAT_VALIDATION, date);

		if (boolValidDateFormat == false) {
			return " Invalid Date. ";
		}

		boolean boolDateToday = false;
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

		Integer flagYear = inputList.get(0).compareTo(currentList.get(0));
		Integer flagMonth = inputList.get(1).compareTo(currentList.get(1));
		Integer flagDay = inputList.get(2).compareTo(currentList.get(2));

		if ((flagYear == 0) && (flagMonth == 0) && (flagDay == 0)) {
			boolDateToday = true;
		}

		if (boolValidDateFormat && boolDateToday) {
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

		return " Invalid Time. ";
	}

	@Override
	public String isSymptomsValid(String text) {
		boolean boolTextValid = Pattern.matches(TEXT_VALIDATION, text);

		if (boolTextValid) {
			return "";
		}

		return " Invalid Symptoms. ";
	}

	@Override
	public String isDiagnosisValid(String text) {
		boolean boolTextValid = Pattern.matches(TEXT_VALIDATION, text);

		if (boolTextValid) {
			return "";
		}

		return " Invalid Diagnosis. ";
	}

	@Override
	public String isMedicinesValid(String text) {
		boolean boolTextValid = Pattern.matches(TEXT_VALIDATION, text);

		if (boolTextValid) {
			return "";
		}

		return " Invalid Medicines. ";
	}
}
