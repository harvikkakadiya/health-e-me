package com.healtheme.labsignup.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.healtheme.labsignup.model.LabSignupModel;

@Controller
public class ShowLabSignupFormController {

	@GetMapping("/lab-signup")
	public String showLabSignupForm(Model model) {
		LabSignupModel lab = new LabSignupModel("", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
				"", "", "");

		model.addAttribute("lab", lab);
		
		return "./labsignup/labsignup";
	}
}
