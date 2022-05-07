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
import com.healtheme.patientappointments.database.DeleteDoctorAppointmentDatabaseDAO;

@Controller
public class DeleteDoctorAppointmentController {

	@Autowired
	private DatabaseConnectionDAO databaseConnection;

	@Autowired
	private DeleteDoctorAppointmentDatabaseDAO deleteDoctorAppointmentDatabase;

	@RequestMapping(value = "/delete-doctor-appointment")
	public String deleteDoctorAppointment(@RequestParam(value = "appointmentId") String appointmentId,
			@RequestParam(value = "doctorConsultationFee") String doctorConsultationFee,
			@RequestParam(value = "doctorEmail") String doctorEmail, Model model, RedirectAttributes redirectAttrs) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String patientEmail = auth.getName();
		if (deleteDoctorAppointmentDatabase.deleteDoctorAppointment(databaseConnection, appointmentId)) {
			if (deleteDoctorAppointmentDatabase.updatePatientAccountBalance(databaseConnection, doctorConsultationFee,
					patientEmail)
					&& deleteDoctorAppointmentDatabase.updateDoctorAccountBalance(databaseConnection,
							doctorConsultationFee, doctorEmail)) {

				redirectAttrs.addFlashAttribute("success", " Doctor Appointment Cancelled. ");
			}
		} else {

			redirectAttrs.addFlashAttribute("error", " Some Error Occurred. ");
		}

		return "redirect:/patient-appointments";
	}
}
