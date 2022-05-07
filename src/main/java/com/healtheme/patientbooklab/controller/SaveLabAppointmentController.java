package com.healtheme.patientbooklab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.healtheme.DatabaseConnectionDAO;
import com.healtheme.patientbooklab.database.SaveLabAppointmentDatabaseDAO;
import com.healtheme.patientbooklab.validation.PatientBookLabValidationDAO;

@Controller
public class SaveLabAppointmentController {

	@Autowired
	private DatabaseConnectionDAO databaseConnection;

	@Autowired
	private PatientBookLabValidationDAO patientBookLabValidation;

	@Autowired
	private SaveLabAppointmentDatabaseDAO saveLabAppointmentDatabase;

	@RequestMapping(value = "/patient-book-lab-test")
	public String saveAppointment(@RequestParam(value = "patientLabTest") String patientLabTest,
			@RequestParam(value = "patientLabTestDate") String patientLabTestDate,
			@RequestParam(value = "patientLabTestTime") String patientLabTestTime,
			@RequestParam(value = "selectedLabEmail") String selectedLabEmail, Model model) {

		String errorMessage = patientBookLabValidation.checkValidation(patientLabTestDate, patientLabTestTime);

		if (errorMessage.isEmpty()) {

			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			String patientEmail = auth.getName();
			String[] labTestAttributes = patientLabTest.split(":");
			String patientLabTestType = labTestAttributes[0];
			String patientLabTestFee = labTestAttributes[1].split(" ")[0];

			if (saveLabAppointmentDatabase.checkPatientAccountBalance(databaseConnection, patientLabTestFee,
					patientEmail)) {
				if (saveLabAppointmentDatabase.insertLabAppointment(databaseConnection, patientEmail, selectedLabEmail,
						patientLabTestDate, patientLabTestTime, patientLabTestType, patientLabTestFee)
						&& saveLabAppointmentDatabase.updatePatientAccountBalance(databaseConnection, patientLabTestFee,
								patientEmail)
						&& saveLabAppointmentDatabase.updateLabAccountBalance(databaseConnection, patientLabTestFee,
								selectedLabEmail)) {

					model.addAttribute("success", " Lab Appointment Booked. ");
				}
			} else {
				
				model.addAttribute("error", " Insufficient Account Balance. ");
			}
		} else {

			model.addAttribute("error", errorMessage);
		}

		return "./patientbooklab/patientbooklab";
	}
}
