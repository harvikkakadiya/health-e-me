package com.healtheme.patientbooklab.validation;

public interface PatientBookLabValidationDAO {

	public String checkValidation(String patientLabDate, String patientLabTime);

	public String checkDate(String date);

	public String checkTime(String time);
}
