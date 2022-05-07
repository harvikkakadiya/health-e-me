package com.healtheme.admininventory.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.healtheme.DatabaseConnectionDAO;
import com.healtheme.admininventory.database.AdminInventoryDatabaseDAO;
import com.healtheme.admininventory.model.AdminInventoryModel;

@Controller
public class AdminInventoryController {

	@Autowired
	private DatabaseConnectionDAO databaseConnection;

	@Autowired
	private AdminInventoryDatabaseDAO adminInventoryDatabaseDAO;

	@GetMapping("/admin-inventory")
	public String welcome(Model model) {

		try {
			ArrayList<AdminInventoryModel> medicineList = adminInventoryDatabaseDAO
					.getMedicineDetail(databaseConnection);
			model.addAttribute("medicineList", medicineList);
			model.addAttribute("inventory", new AdminInventoryModel());
		} catch (Exception e) {
			System.out.println(e);

		}
		return "./admininventory/admininventory";
	}
}
