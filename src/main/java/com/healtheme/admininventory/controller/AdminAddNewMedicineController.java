package com.healtheme.admininventory.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.healtheme.DatabaseConnectionDAO;
import com.healtheme.admininventory.database.AdminInventoryDatabaseDAO;
import com.healtheme.admininventory.model.AdminInventoryModel;

@Controller
public class AdminAddNewMedicineController {

	@Autowired
	private DatabaseConnectionDAO databaseConnection;

	@Autowired
	private AdminInventoryDatabaseDAO adminInventoryDatabaseDAO;

	@RequestMapping(path = "/admin-inventory", method = RequestMethod.POST)
	public String addNewMedicine(@ModelAttribute AdminInventoryModel i, Model model) {
		try {
			adminInventoryDatabaseDAO.addNewMedicine(i, model, databaseConnection);
			ArrayList<AdminInventoryModel> medicineDetail = adminInventoryDatabaseDAO
					.getMedicineDetail(databaseConnection);
			model.addAttribute("inventory", new AdminInventoryModel());
			model.addAttribute("medicineList", medicineDetail);
		}

		catch (Exception e) {
			System.out.println(e);
		}

		return "./admininventory/admininventory";
	}

}
