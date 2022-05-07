package com.healtheme.doctorviewappointments.database;

import org.springframework.ui.Model;

import com.healtheme.DatabaseConnectionDAO;

public interface DoctorViewAppointmentsDatabaseDAO {
	public void showAppointments(DatabaseConnectionDAO databaseConnection, Model model);

	public void deleteAppointments(DatabaseConnectionDAO databaseConnection, String appointmentId, Model model);
}
