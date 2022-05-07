package com.healtheme.labcreatereports.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.healtheme.labcreatereports.model.LabCreateReportsModel;

@Controller
public class ShowLabCreateReportsFormController {

	@GetMapping("/lab-create-reports")
	public String createLabReport(Model model) {
		LabCreateReportsModel lab = new LabCreateReportsModel("", "", "", "", "", "");
		model.addAttribute("lab", lab);

		return "./labcreatereports/labcreatereports";
	}
}
