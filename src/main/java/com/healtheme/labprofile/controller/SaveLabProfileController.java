package com.healtheme.labprofile.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.healtheme.DatabaseConnectionDAO;
import com.healtheme.labprofile.database.LabProfileDatabaseDAO;
import com.healtheme.labprofile.model.LabProfileModel;
import com.healtheme.labprofile.validation.LabProfileValidationDAO;

@Controller
public class SaveLabProfileController {

	@Autowired
	private DatabaseConnectionDAO databaseConnection;

	@Autowired
	private LabProfileValidationDAO labProfileValidation;

	@Autowired
	private LabProfileDatabaseDAO labProfileDatabase;

	@RequestMapping("/lab-profile")
	public String saveLabProfile(@ModelAttribute LabProfileModel lab, Model model) {
		String error = labProfileValidation.validateLabProfile(lab);

		if (error.isEmpty()) {
			labProfileDatabase.saveLabProfile(databaseConnection, lab);
			model.addAttribute("success", " Profile Updated. ");
		} else {
			model.addAttribute("error", error);
		}
		model.addAttribute("lab", lab);

		return "./labprofile/labprofile";
	}
}
