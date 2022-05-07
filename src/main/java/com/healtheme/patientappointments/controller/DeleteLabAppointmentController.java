package com.healtheme.patientappointments.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.healtheme.DatabaseConnectionDAO;
import com.healtheme.patientappointments.database.DeleteLabAppointmentDatabaseDAO;

@Controller
public class DeleteLabAppointmentController {

	@Autowired
	private DatabaseConnectionDAO databaseConnection;

	@Autowired
	private DeleteLabAppointmentDatabaseDAO deleteLabAppointmentDatabase;

	@RequestMapping(value = "/delete-lab-appointment")
	public String deleteLabAppointment(@RequestParam(value = "appointmentId") String appointmentId,
			@RequestParam(value = "labTestFee") String labTestFee, @RequestParam(value = "labEmail") String labEmail,
			Model model, RedirectAttributes redirectAttrs) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String patientEmail = auth.getName();

		if (deleteLabAppointmentDatabase.deleteLabAppointment(databaseConnection, appointmentId)) {
			if (deleteLabAppointmentDatabase.updatePatientAccountBalance(databaseConnection, labTestFee, patientEmail)
					&& deleteLabAppointmentDatabase.updateLabAccountBalance(databaseConnection, labTestFee, labEmail)) {

				redirectAttrs.addFlashAttribute("success", " Lab Appointment Cancelled. ");
			}
		} else {
			
			redirectAttrs.addFlashAttribute("error", " Some Error Occurred. ");
		}

		return "redirect:/patient-appointments";
	}
}
