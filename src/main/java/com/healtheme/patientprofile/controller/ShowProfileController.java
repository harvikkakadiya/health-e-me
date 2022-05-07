package com.healtheme.patientprofile.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.healtheme.DatabaseConnectionDAO;
import com.healtheme.patientprofile.database.ShowProfileDatabaseDAO;
import com.healtheme.patientprofile.model.PatientProfileModel;

@Controller
public class ShowProfileController {

	@Autowired
	private DatabaseConnectionDAO databaseConnection;

	@Autowired
	private ShowProfileDatabaseDAO showProfileDatabase;

	@GetMapping("/patient-profile")
	public String showProfile(Model model) {

		PatientProfileModel patient = showProfileDatabase.showProfile(databaseConnection);

		model.addAttribute("patient", patient);

		return "./patientprofile/patientprofile";
	}
}
