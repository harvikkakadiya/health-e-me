package com.healtheme.patientbooklab.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.healtheme.DatabaseConnectionDAO;
import com.healtheme.patientbooklab.database.SearchLabsDatabaseDAO;
import com.healtheme.patientbooklab.model.PatientBookLabModel;

@Controller
public class SearchLabsController {

	@Autowired
	private DatabaseConnectionDAO databaseConnection;

	@Autowired
	private SearchLabsDatabaseDAO searchLabsDatabase;

	private HashMap<String, PatientBookLabModel> labs = new HashMap<>();

	@GetMapping(value = "/patient-book-lab-test")
	public String searchLabs(@RequestParam(value = "searchLabLocation", required = false) String searchLabLocation,
			@RequestParam(value = "searchLabName", required = false) String searchLabName,
			@RequestParam(value = "selectedLabEmail", required = false) String selectedLabEmail, Model model) {

		if (searchLabLocation != null && searchLabName != null) {

			labs.clear();
			labs = searchLabsDatabase.searchLabs(databaseConnection, searchLabLocation, searchLabName);
			model.addAttribute("labs", labs);
		}

		if (selectedLabEmail != null) {

			PatientBookLabModel selectedLab = labs.get(selectedLabEmail);
			Stream<String> stream = Arrays.stream(selectedLab.getLabTests().toUpperCase().split(","));
			List<String> labTestsList = stream.map(s -> s.concat(" CAD")).collect(Collectors.<String>toList());
			model.addAttribute("labs", labs);
			model.addAttribute("selectedLab", selectedLab);
			model.addAttribute("labTests", labTestsList);
		}

		return "./patientbooklab/patientbooklab";
	}
}
