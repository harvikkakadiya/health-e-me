package com.healtheme.patientordermedicine.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.healtheme.DatabaseConnectionDAO;
import com.healtheme.patientordermedicine.database.PatientOrderMedicineDatabaseDAO;
import com.healtheme.patientordermedicine.model.PatientOrderMedicineModel;

@Controller
public class PatientAddToCartController {

	@Autowired
	private DatabaseConnectionDAO databaseConnection;

	@Autowired
	private PatientOrderMedicineDatabaseDAO patientOrderMedicineDatabase;

	@RequestMapping(path = "/patient-addtocart/{medicineid}/{mname}/{mprice}", method = RequestMethod.POST)
	public String addtoCart(@ModelAttribute PatientOrderMedicineModel p, RedirectAttributes redirectAttribute,
			@PathVariable(value = "medicineid") String medicineid, @PathVariable(value = "mname") String mname,
			@PathVariable(value = "mprice") float mprice, Model model) {

		String errorMessage = "We do not have your desired quantity";
		try {
			int get_quantity = p.getQuantity();
			if (!(patientOrderMedicineDatabase.getMedicineDetailByID(medicineid, get_quantity, databaseConnection))) {
				patientOrderMedicineDatabase.insertOrderDetail(p, medicineid, mname, mprice, databaseConnection, model);
			} else {
				redirectAttribute.addFlashAttribute("error", errorMessage);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return "redirect:/patient-order-medicine";

	}
}
