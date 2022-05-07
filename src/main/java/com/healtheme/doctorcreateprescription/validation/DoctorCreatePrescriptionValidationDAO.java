package com.healtheme.doctorcreateprescription.validation;

import com.healtheme.doctorcreateprescription.model.DoctorCreatePrescriptionModel;

public interface DoctorCreatePrescriptionValidationDAO {
	public String checkValidation(DoctorCreatePrescriptionModel prescriptionObj);

	public String isPatientEmailValid(String email);

	public String isDoctorEmailValid(String email);

	public String isDateValid(String date);

	public String isTimeValid(String time);

	public String isSymptomsValid(String text);

	public String isDiagnosisValid(String text);

	public String isMedicinesValid(String text);
}
