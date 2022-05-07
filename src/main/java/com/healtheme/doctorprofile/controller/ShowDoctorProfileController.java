package com.healtheme.doctorprofile.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.healtheme.DatabaseConnectionDAO;
import com.healtheme.doctorprofile.database.DoctorProfileDatabaseDAO;

@Controller
public class ShowDoctorProfileController {

	@Autowired
	private DatabaseConnectionDAO databaseConnection;

	@Autowired
	private DoctorProfileDatabaseDAO doctorProfile;

	@GetMapping("/doctor-profile")
	public String showDoctorProfile(Model model) {
		doctorProfile.showDoctorProfile(databaseConnection, model);

		return "./doctorprofile/doctorprofile";
	}
}
