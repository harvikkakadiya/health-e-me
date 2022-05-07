package com.healtheme.admininventory.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.healtheme.DatabaseConnectionDAO;
import com.healtheme.admininventory.database.AdminInventoryDatabaseDAO;
import com.healtheme.admininventory.model.AdminInventoryModel;

@Controller
public class AdminEditInventoryController {

	@Autowired
	private DatabaseConnectionDAO databaseConnection;

	@Autowired
	private AdminInventoryDatabaseDAO adminInventoryDatabaseDAO;

	@RequestMapping(path = "/update-medDetail/{id}", method = RequestMethod.GET)
	public String showMedDetailfromID(@ModelAttribute AdminInventoryModel i, @PathVariable(value = "id") String id,
			Model model) {

		try {
			AdminInventoryModel data = adminInventoryDatabaseDAO.showMedDetailfromID(i, model, databaseConnection, id);
			model.addAttribute("editdata", data);
		} catch (Exception e) {
			System.out.println(e);
		}
		return "./admininventory/admineditinventory";
	}

	@RequestMapping(path = "/updateMedDetail", method = RequestMethod.POST)
	public String updateMedDetail(@ModelAttribute AdminInventoryModel i, Model model) {

		try {
			adminInventoryDatabaseDAO.updateMedicine(i, model, databaseConnection);
			ArrayList<AdminInventoryModel> medicineDetail = adminInventoryDatabaseDAO
					.getMedicineDetail(databaseConnection);
			model.addAttribute("inventory", new AdminInventoryModel());
			model.addAttribute("medicineList", medicineDetail);
		} catch (Exception e) {
			System.out.println(e);
		}

		return "redirect:/admin-inventory";

	}

}
