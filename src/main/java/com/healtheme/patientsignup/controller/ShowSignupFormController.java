package com.healtheme.patientsignup.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.healtheme.patientsignup.model.PatientSignupModel;

@Controller
public class ShowSignupFormController {

	@GetMapping("/patient-signup")
	public String welcome(Model model) {

		PatientSignupModel patient = new PatientSignupModel("", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
				"", "", "", "", "", "", "", "");

		model.addAttribute("patient", patient);

		return "./patientsignup/patientsignup";
	}
}
