package com.healtheme.labprofile.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.healtheme.DatabaseConnectionDAO;
import com.healtheme.labprofile.database.LabProfileDatabaseDAO;

@Controller
public class ShowLabProfileController {

	@Autowired
	private DatabaseConnectionDAO databaseConnection;

	@Autowired
	private LabProfileDatabaseDAO labProfileDatabase;

	@GetMapping("/lab-profile")
	public String showLabProfile(Model model) {
		labProfileDatabase.showLabProfile(databaseConnection, model);
		
		return "./labprofile/labprofile";
	}
}
