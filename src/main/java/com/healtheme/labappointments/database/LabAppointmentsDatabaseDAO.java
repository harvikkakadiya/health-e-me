package com.healtheme.labappointments.database;

import org.springframework.ui.Model;

import com.healtheme.DatabaseConnectionDAO;

public interface LabAppointmentsDatabaseDAO {

	void showLabAppointments(DatabaseConnectionDAO databaseConnection, Model model);

	void deleteLabAppointment(DatabaseConnectionDAO databaseConnection, String appointmentId, Model model);

}