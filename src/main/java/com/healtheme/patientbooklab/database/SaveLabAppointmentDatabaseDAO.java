package com.healtheme.patientbooklab.database;

import com.healtheme.DatabaseConnectionDAO;

public interface SaveLabAppointmentDatabaseDAO {

	boolean checkPatientAccountBalance(DatabaseConnectionDAO databaseConnection, String patientLabTestFee,
			String patientEmail);

	boolean insertLabAppointment(DatabaseConnectionDAO databaseConnection, String patientEmail, String selectedLabEmail,
			String patientLabTestDate, String patientLabTestTime, String patientLabTestType, String patientLabTestFee);

	boolean updatePatientAccountBalance(DatabaseConnectionDAO databaseConnection, String patientLabTestFee,
			String patientEmail);

	boolean updateLabAccountBalance(DatabaseConnectionDAO databaseConnection, String patientLabTestFee,
			String selectedLabEmail);
}