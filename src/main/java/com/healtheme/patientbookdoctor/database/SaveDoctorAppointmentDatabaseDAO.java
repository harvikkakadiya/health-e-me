package com.healtheme.patientbookdoctor.database;

import com.healtheme.DatabaseConnectionDAO;

public interface SaveDoctorAppointmentDatabaseDAO {

	boolean checkPatientAccountBalance(DatabaseConnectionDAO databaseConnection, String selectedDoctorConsultationFee,
			String patientEmail);

	boolean insertDoctorAppointment(DatabaseConnectionDAO databaseConnection, String patientAppointmentDate,
			String patientAppointmentTime, String selectedDoctorEmail, String patientEmail,
			String selectedDoctorConsultationFee);

	boolean updatePatientAccountBalance(DatabaseConnectionDAO databaseConnection, String selectedDoctorConsultationFee,
			String patientEmail);

	boolean updateDoctorAccountBalance(DatabaseConnectionDAO databaseConnection, String selectedDoctorConsultationFee,
			String selectedDoctorEmail);
}