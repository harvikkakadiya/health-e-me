package com.healtheme.patientsignup.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.healtheme.DatabaseConnectionDAO;
import com.healtheme.patientsignup.database.CreatePatientDatabaseDAO;
import com.healtheme.patientsignup.model.PatientSignupModel;
import com.healtheme.patientsignup.validation.PatientSignupValidationDAO;

@Controller
public class CreatePatientController {

	@Autowired
	private DatabaseConnectionDAO databaseConnection;

	@Autowired
	private JdbcUserDetailsManager jdbcUserDetailsManager;

	@Autowired
	private PatientSignupValidationDAO patientSignupValidation;

	@Autowired
	private CreatePatientDatabaseDAO createPatientDatabase;

	@RequestMapping(path = "/patient-signup", method = RequestMethod.POST)
	public String createPatient(@ModelAttribute PatientSignupModel patient, Model model,
			RedirectAttributes redirectAttrs) {
		String errorMessage = patientSignupValidation.checkValidation(patient);

		if (errorMessage.isEmpty()) {

			if (createPatientDatabase.createPatient(databaseConnection, jdbcUserDetailsManager, patient)) {

				redirectAttrs.addFlashAttribute("success", " Account Created. ");
				return "redirect:/login";

			} else {

				model.addAttribute("error", "  User Email already exists. ");
			}
		} else {

			model.addAttribute("error", errorMessage);
		}

		model.addAttribute("patient", patient);

		return "./patientsignup/patientsignup";
	}
}
