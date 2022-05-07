package com.healtheme.patientprescriptions.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.healtheme.DatabaseConnectionDAO;
import com.healtheme.patientprescriptions.database.PatientPrescriptionsDatabaseDAO;

@Controller
public class PatientPrescriptionController {

	@Autowired
	private DatabaseConnectionDAO databaseConnection;

	@Autowired
	private PatientPrescriptionsDatabaseDAO patientPrescription;

	@GetMapping("/patient-prescriptions")
	public String showPrescriptions(Model model) {
		patientPrescription.showPrescriptions(databaseConnection, model);
		
		return "./patientprescriptions/patientprescriptions";
	}
}
