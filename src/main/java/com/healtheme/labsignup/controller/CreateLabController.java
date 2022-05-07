package com.healtheme.labsignup.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.healtheme.DatabaseConnectionDAO;
import com.healtheme.labsignup.database.LabSignupDatabaseDAO;
import com.healtheme.labsignup.model.LabSignupModel;
import com.healtheme.labsignup.validation.LabSignupValidationDAO;

@Controller
public class CreateLabController {

	@Autowired
	private DatabaseConnectionDAO databaseConnection;

	@Autowired
	private LabSignupValidationDAO labSignupValidation;

	@Autowired
	private LabSignupDatabaseDAO labSignupDatabase;

	@Autowired
	JdbcUserDetailsManager jdbcUserDetailsManager;

	@RequestMapping(path = "/lab-signup", method = RequestMethod.POST)
	public String createLab(@ModelAttribute LabSignupModel lab, Model model, RedirectAttributes redirectAttrs) {
		String error = labSignupValidation.validateLabSignup(lab);
		boolean userExists = false;

		if (error.isEmpty()) {
			userExists = labSignupDatabase.insertData(databaseConnection, lab, model, jdbcUserDetailsManager);
			if (userExists)
				model.addAttribute("error", " User Already Exists. ");
			else
				model.addAttribute("success", " Profile Created. ");
		}
		else {
			model.addAttribute("error", error);
			model.addAttribute("lab", lab);
			return "./labsignup/labsignup";
		}

		model.addAttribute("lab", lab);

		return "./home/login";
	}
}
