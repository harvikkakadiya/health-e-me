package com.healtheme.patientbookdoctor.validation;

public interface PatientBookDoctorValidationDAO {

	public String checkValidation(String patientDoctorDate, String patientDoctorTime);

	public String checkDate(String date);

	public String checkTime(String time);
}
