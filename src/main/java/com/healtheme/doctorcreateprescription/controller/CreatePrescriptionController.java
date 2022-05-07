package com.healtheme.doctorcreateprescription.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.healtheme.DatabaseConnectionDAO;
import com.healtheme.doctorcreateprescription.database.DoctorCreatePrescriptionDatabaseDAO;
import com.healtheme.doctorcreateprescription.model.DoctorCreatePrescriptionModel;
import com.healtheme.doctorcreateprescription.validation.DoctorCreatePrescriptionValidationDAO;

@Controller
public class CreatePrescriptionController {

	@Autowired
	private DoctorCreatePrescriptionValidationDAO doctorCreatePrescriptionValidation;

	@Autowired
	private DatabaseConnectionDAO databaseConnection;

	@Autowired
	private DoctorCreatePrescriptionDatabaseDAO doctorCreatePrescription;

	@RequestMapping(path = "/create-prescription", method = RequestMethod.POST)
	public String createDoctorPrescriptions(@ModelAttribute DoctorCreatePrescriptionModel prescription, Model model) {
		String error = doctorCreatePrescriptionValidation.checkValidation(prescription);

		if (error.isEmpty()) {
			doctorCreatePrescription.insertData(databaseConnection, prescription, model);
			model.addAttribute("success", " Prescription Uploaded. ");
		} else {
			model.addAttribute("error", error);
			model.addAttribute("prescription", prescription);
			return "./doctorcreateprescription/doctorcreateprescription";
		}

		DoctorCreatePrescriptionModel prescriptionSuccess = new DoctorCreatePrescriptionModel("", "", "", "", "", "","");
		model.addAttribute("prescription", prescriptionSuccess);

		return "./doctorcreateprescription/doctorcreateprescription";
	}
}
