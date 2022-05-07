package com.healtheme.doctorprofile.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.healtheme.DatabaseConnectionDAO;
import com.healtheme.doctorprofile.database.DoctorProfileDatabaseDAO;
import com.healtheme.doctorprofile.model.DoctorProfileModel;
import com.healtheme.doctorprofile.validation.DoctorProfileValidationDAO;

@Controller
public class SaveDoctorProfileController {

	@Autowired
	private DatabaseConnectionDAO databaseConnection;

	@Autowired
	private DoctorProfileValidationDAO doctorProfileValidation;

	@Autowired
	private DoctorProfileDatabaseDAO doctorProfile;

	@RequestMapping("/doctor-profile")
	public String saveProfile(@ModelAttribute DoctorProfileModel doctor, Model model) {
		String error = doctorProfileValidation.validateDoctorProfile(doctor);

		if (error.isEmpty()) {
			doctorProfile.saveDoctorProfile(databaseConnection, doctor, model);
			model.addAttribute("success", " Profile Updated. ");
		} else {
			model.addAttribute("error", error);
		}

		model.addAttribute("doctor", doctor);

		return "./doctorprofile/doctorprofile";
	}
}
