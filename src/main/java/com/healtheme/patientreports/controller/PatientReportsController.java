package com.healtheme.patientreports.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.healtheme.DatabaseConnectionDAO;
import com.healtheme.patientreports.database.PatientReportsDatabaseDAO;

@Controller
public class PatientReportsController {

	@Autowired
	private DatabaseConnectionDAO databaseConnection;

	@Autowired
	private PatientReportsDatabaseDAO patientReport;

	@GetMapping("/patient-reports")
	public String showPatientReports(Model model) {
		patientReport.showPatientReports(databaseConnection, model);

		return "./patientreports/patientreports";
	}
}
