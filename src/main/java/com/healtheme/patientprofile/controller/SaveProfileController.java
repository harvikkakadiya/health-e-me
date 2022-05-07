package com.healtheme.patientprofile.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.healtheme.DatabaseConnectionDAO;
import com.healtheme.patientprofile.database.SaveProfileDatabaseDAO;
import com.healtheme.patientprofile.model.PatientProfileModel;
import com.healtheme.patientprofile.validation.PatientProfileValidationDAO;

@Controller
public class SaveProfileController {

	@Autowired
	private DatabaseConnectionDAO databaseConnection;

	@Autowired
	private PatientProfileValidationDAO patientProfileValidation;

	@Autowired
	private SaveProfileDatabaseDAO saveProfileDatabase;

	@RequestMapping("/patient-profile")
	public String saveProfile(@ModelAttribute PatientProfileModel patient, Model model,
			RedirectAttributes redirectAttrs) {

		String errorMessage = patientProfileValidation.checkValidation(patient);

		if (errorMessage.isEmpty()) {
			saveProfileDatabase.saveProfile(databaseConnection, patient);
			redirectAttrs.addFlashAttribute("success", " Profile Updated. ");
			return "redirect:/patient-profile";
		}

		model.addAttribute("error", errorMessage);
		model.addAttribute("patient", patient);

		return "./patientprofile/patientprofile";
	}
}
