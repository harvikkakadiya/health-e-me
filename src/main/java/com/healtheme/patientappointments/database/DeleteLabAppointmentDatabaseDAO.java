package com.healtheme.patientappointments.database;

import com.healtheme.DatabaseConnectionDAO;

public interface DeleteLabAppointmentDatabaseDAO {

	boolean deleteLabAppointment(DatabaseConnectionDAO databaseConnection, String appointmentId);

	boolean updatePatientAccountBalance(DatabaseConnectionDAO databaseConnection, String labTestFee,
			String patientEmail);

	boolean updateLabAccountBalance(DatabaseConnectionDAO databaseConnection, String labTestFee, String labEmail);
}