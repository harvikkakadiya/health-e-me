package com.healtheme.patientbookdoctor.validation;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Component;

@Component("patientBookDoctorValidation")
public class PatientBookDoctorValidation implements PatientBookDoctorValidationDAO {

	@Override
	public String checkValidation(String patientDoctorDate, String patientDoctorTime) {

		String dateMessage = checkDate(patientDoctorDate);
		String timeMessage = checkTime(patientDoctorTime);

		String errorMessage = dateMessage + timeMessage;
		return errorMessage;
	}

	@Override
	public String checkDate(String date) {

		try {
			Date currentDate = java.util.Calendar.getInstance().getTime();
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date inputDate = dateFormat.parse(date);
			int result = currentDate.compareTo(inputDate);
			if (result > 0) {
				return " Doctor Appointment Date should be a Future Date. ";
			}
			return "";

		} catch (ParseException e) {

			return " Invalid Doctor Appointment Date Format. ";
		}
	}

	@Override
	public String checkTime(String time) {
		try {

			DateFormat timeFormat = new SimpleDateFormat("HH:mm");
			timeFormat.parse(time);
			return "";

		} catch (ParseException e) {

			return " Invalid Doctor Appointment Time Format. ";
		}
	}
}
