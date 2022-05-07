package com.healtheme.labcreatereports.validation;

import com.healtheme.labcreatereports.model.LabCreateReportsModel;

public interface LabCreateReportsValidationDAO {

	public String validateLabCreateReports(LabCreateReportsModel labObj);

	public String isPatientEmailValid(String email);

	public String isDateValid(String date);

	public String isTimeValid(String time);

	public String isDoctorEmailValid(String email);

}
