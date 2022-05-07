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
public class AdminDeleteMedicineController {

	@Autowired
	private DatabaseConnectionDAO databaseConnection;

	@Autowired
	private AdminInventoryDatabaseDAO adminInventoryDatabaseDAO;

	@RequestMapping(path = "/delete-medDetail/{id}", method = RequestMethod.GET)
	public String deleteMedDetailfromID(@ModelAttribute AdminInventoryModel i, @PathVariable(value = "id") String id,
			Model model) {

		try {
			adminInventoryDatabaseDAO.deleteMedicine(i, model, databaseConnection, id);
			ArrayList<AdminInventoryModel> medicineDetail = adminInventoryDatabaseDAO
					.getMedicineDetail(databaseConnection);
			model.addAttribute("medicineList", medicineDetail);
			model.addAttribute("inventory", new AdminInventoryModel());
		} catch (Exception e) {
			System.out.println(e);
		}

		return "redirect:/admin-inventory";
	}

}
