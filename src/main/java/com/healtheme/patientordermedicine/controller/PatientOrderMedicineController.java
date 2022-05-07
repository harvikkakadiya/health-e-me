package com.healtheme.patientordermedicine.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.healtheme.DatabaseConnectionDAO;
import com.healtheme.admininventory.model.AdminInventoryModel;
import com.healtheme.patientordermedicine.database.PatientOrderMedicineDatabaseDAO;
import com.healtheme.patientordermedicine.model.PatientOrderMedicineModel;

@Controller
public class PatientOrderMedicineController {

	@Autowired
	private DatabaseConnectionDAO databaseConnection;

	@Autowired
	private PatientOrderMedicineDatabaseDAO patientOrderMedicineDatabase;

	@GetMapping("/patient-order-medicine")
	public String welcome(Model model) {
		model.addAttribute("inventory", new AdminInventoryModel());
		try {
			ArrayList<AdminInventoryModel> medicineList = patientOrderMedicineDatabase
					.getMedicineDetail(databaseConnection);
			model.addAttribute("medicineList", medicineList);
			model.addAttribute("input", new PatientOrderMedicineModel());
		} catch (Exception e) {
			System.out.println(e);

		}
		return "./patientordermedicine/patientordermedicine";
	}

}
