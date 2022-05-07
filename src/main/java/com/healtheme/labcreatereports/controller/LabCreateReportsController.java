package com.healtheme.labcreatereports.controller;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.healtheme.DatabaseConnectionDAO;
import com.healtheme.labcreatereports.database.LabCreateReportsDatabaseDAO;
import com.healtheme.labcreatereports.model.LabCreateReportsModel;
import com.healtheme.labcreatereports.validation.LabCreateReportsValidationDAO;

@Controller
public class LabCreateReportsController {
	@Autowired
	private DatabaseConnectionDAO databaseConnection;

	@Autowired
	private LabCreateReportsValidationDAO labCreateReportsValidation;

	@Autowired
	private LabCreateReportsDatabaseDAO labCreateReportsDatabase;

	@Autowired
	JdbcUserDetailsManager jdbcUserDetailsManager;

	@RequestMapping(path = "/lab-create-reports", method = RequestMethod.POST)
	public String handleCreateLabReport(@ModelAttribute LabCreateReportsModel lab, Model model) throws ParseException {
		String error = labCreateReportsValidation.validateLabCreateReports(lab);

		if (error.isEmpty()) {
			labCreateReportsDatabase.insertLabReport(databaseConnection, lab, model, jdbcUserDetailsManager);
			model.addAttribute("success", "Lab Report uploaded");
		} else {
			model.addAttribute("error", error);
			model.addAttribute("lab", lab);
			
			return "./labcreatereports/labcreatereports";
		}

		LabCreateReportsModel labsuccess = new LabCreateReportsModel("", "", "", "", "", "");
		model.addAttribute("lab", labsuccess);

		return "./labcreatereports/labcreatereports";
	}
}
