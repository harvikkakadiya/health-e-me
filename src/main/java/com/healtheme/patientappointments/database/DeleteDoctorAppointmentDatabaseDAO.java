package com.healtheme.patientappointments.database;

import com.healtheme.DatabaseConnectionDAO;

public interface DeleteDoctorAppointmentDatabaseDAO {

	boolean deleteDoctorAppointment(DatabaseConnectionDAO databaseConnection, String appointmentId);

	boolean updatePatientAccountBalance(DatabaseConnectionDAO databaseConnection, String doctorConsultationFee,
			String patientEmail);

	boolean updateDoctorAccountBalance(DatabaseConnectionDAO databaseConnection, String doctorConsultationFee,
			String doctorEmail);
}