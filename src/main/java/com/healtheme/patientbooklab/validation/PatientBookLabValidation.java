package com.healtheme.patientbooklab.validation;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Component;

@Component("patientBookLabValidation")
public class PatientBookLabValidation implements PatientBookLabValidationDAO {

	@Override
	public String checkValidation(String patientLabDate, String patientLabTime) {

		String dateMessage = checkDate(patientLabDate);
		String timeMessage = checkTime(patientLabTime);

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
				
				return " Lab Appointment Date should be a Future Date. ";
			}
			return "";

		} catch (ParseException e) {

			return " Invalid Lab Appointment Date Format. ";
		}
	}

	@Override
	public String checkTime(String time) {
		try {

			DateFormat timeFormat = new SimpleDateFormat("HH:mm");
			timeFormat.parse(time);
			return "";

		} catch (ParseException e) {

			return " Invalid Lab Appointment Time Format. ";
		}
	}
}
