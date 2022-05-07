package com.healtheme.patientprescriptions.database;

import org.springframework.ui.Model;

import com.healtheme.DatabaseConnectionDAO;

public interface PatientPrescriptionsDatabaseDAO {
	public void showPrescriptions(DatabaseConnectionDAO databaseConnection, Model model);
}
