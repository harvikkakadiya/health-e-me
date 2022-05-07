package com.healtheme.patientreports.database;

import org.springframework.ui.Model;

import com.healtheme.DatabaseConnectionDAO;

public interface PatientReportsDatabaseDAO {

	void showPatientReports(DatabaseConnectionDAO databaseConnection, Model model);

}