package com.healtheme.patientbookdoctor.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.healtheme.DatabaseConnectionDAO;
import com.healtheme.patientbookdoctor.database.SearchDoctorsDatabaseDAO;
import com.healtheme.patientbookdoctor.model.PatientBookDoctorModel;

@Controller
public class SearchDoctorsController {

	@Autowired
	private DatabaseConnectionDAO databaseConnection;

	@Autowired
	private SearchDoctorsDatabaseDAO searchDoctorsDatabse;

	private HashMap<String, PatientBookDoctorModel> doctors = new HashMap<>();

	@GetMapping(value = "/patient-book-appointment")
	public String searchDoctors(
			@RequestParam(value = "searchDoctorLocation", required = false) String searchDoctorLocation,
			@RequestParam(value = "searchDoctorSpecialist", required = false) String searchDoctorSpecialist,
			@RequestParam(value = "selectedDoctorEmail", required = false) String selectedDoctorEmail, Model model) {

		if (searchDoctorLocation != null && searchDoctorSpecialist != null) {

			doctors.clear();
			doctors = searchDoctorsDatabse.searchDoctors(databaseConnection, searchDoctorLocation,
					searchDoctorSpecialist);
			model.addAttribute("doctors", doctors);
		}

		if (selectedDoctorEmail != null) {

			PatientBookDoctorModel selectedDoctor = doctors.get(selectedDoctorEmail);
			model.addAttribute("doctors", doctors);
			model.addAttribute("selectedDoctor", selectedDoctor);
		}

		return "./patientbookdoctor/patientbookdoctor";
	}
}
