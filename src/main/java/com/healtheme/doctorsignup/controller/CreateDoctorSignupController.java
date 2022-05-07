package com.healtheme.doctorsignup.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.healtheme.DatabaseConnectionDAO;
import com.healtheme.doctorsignup.database.DoctorSignupDatabaseDAO;
import com.healtheme.doctorsignup.model.DoctorSignupModel;
import com.healtheme.doctorsignup.validation.DoctorSignupValidationDAO;

@Controller
public class CreateDoctorSignupController {

	@Autowired
	JdbcUserDetailsManager jdbcUserDetailsManager;

	@Autowired
	private DatabaseConnectionDAO databaseConnection;

	@Autowired
	private DoctorSignupValidationDAO doctorSignupValidation;

	@Autowired
	private DoctorSignupDatabaseDAO doctorSignup;

	@RequestMapping(path = "/doctor-signup", method = RequestMethod.POST)
	public String handleDoctorSignup(@ModelAttribute DoctorSignupModel doctor, Model model) {
		String error = doctorSignupValidation.validateDoctorSignup(doctor);
		boolean userExists = false;

		if (error.isEmpty()) {
			userExists = doctorSignup.insertData(databaseConnection, doctor, model, jdbcUserDetailsManager);
			if (userExists) {
				model.addAttribute("error", " User Already Exists. ");
			}
			else
				model.addAttribute("success", " Profile Created. ");
		} else {
			model.addAttribute("error", error);
			model.addAttribute("doctor", doctor);
			return "./doctorsignup/doctorsignup";
		}
		
		model.addAttribute("doctor", doctor);

		return "./home/login";
	}
}
