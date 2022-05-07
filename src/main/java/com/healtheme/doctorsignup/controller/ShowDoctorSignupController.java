package com.healtheme.doctorsignup.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.healtheme.doctorsignup.model.DoctorSignupModel;

@Controller
public class ShowDoctorSignupController {

	@GetMapping("/doctor-signup")
	public String showDoctorSignupForm(Model model) {

		DoctorSignupModel doctor = new DoctorSignupModel("", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
				"", "", "", "", "", "", "");
		model.addAttribute("doctor", doctor);

		return "./doctorsignup/doctorsignup";
	}
}
