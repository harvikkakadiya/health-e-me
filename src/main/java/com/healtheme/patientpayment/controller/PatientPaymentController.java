package com.healtheme.patientpayment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.healtheme.DatabaseConnectionDAO;
import com.healtheme.patientpayment.database.PatientPaymentDatabaseDAO;

@Controller

public class PatientPaymentController {

	@Autowired
	private DatabaseConnectionDAO databaseConnection;

	@Autowired
	private PatientPaymentDatabaseDAO patientPaymentDatabase;

	@RequestMapping(path = "/payment-order/{total}", method = RequestMethod.POST)
	public String welcome(Model model, @PathVariable(value = "total") String total) {
		try {

			patientPaymentDatabase.accountBalance(databaseConnection, total);

		} catch (Exception e) {
			System.out.println(e);
		}
		model.addAttribute("success", "Order Placed Sucessfully");
		return "./patientpayment/patientordersucessfull";
	}

}
