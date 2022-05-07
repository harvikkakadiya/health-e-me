package com.healtheme.patientbookdoctor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.healtheme.DatabaseConnectionDAO;
import com.healtheme.patientbookdoctor.database.SaveDoctorAppointmentDatabaseDAO;
import com.healtheme.patientbookdoctor.validation.PatientBookDoctorValidationDAO;

@Controller
public class SaveDoctorAppointmentController {

	@Autowired
	private DatabaseConnectionDAO databaseConnection;

	@Autowired
	private PatientBookDoctorValidationDAO patientBookDoctorValidation;

	@Autowired
	private SaveDoctorAppointmentDatabaseDAO saveDoctorAppointmentDatabase;

	@RequestMapping(value = "/patient-book-appointment")
	public String saveAppointment(@RequestParam(value = "patientAppointmentDate") String patientAppointmentDate,
			@RequestParam(value = "patientAppointmentTime") String patientAppointmentTime,
			@RequestParam(value = "selectedDoctorEmail") String selectedDoctorEmail,
			@RequestParam(value = "selectedDoctorConsultationFee") String selectedDoctorConsultationFee, Model model) {

		String errorMessage = patientBookDoctorValidation.checkValidation(patientAppointmentDate,
				patientAppointmentTime);

		if (errorMessage.isEmpty()) {

			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			String patientEmail = auth.getName();
			selectedDoctorConsultationFee = selectedDoctorConsultationFee.split(" ")[0];

			if (saveDoctorAppointmentDatabase.checkPatientAccountBalance(databaseConnection,
					selectedDoctorConsultationFee, patientEmail)) {
				if (saveDoctorAppointmentDatabase.insertDoctorAppointment(databaseConnection, patientAppointmentDate,
						patientAppointmentTime, selectedDoctorEmail, patientEmail, selectedDoctorConsultationFee)
						&& saveDoctorAppointmentDatabase.updatePatientAccountBalance(databaseConnection,
								selectedDoctorConsultationFee, patientEmail)
						&& saveDoctorAppointmentDatabase.updateDoctorAccountBalance(databaseConnection,
								selectedDoctorConsultationFee, selectedDoctorEmail)) {

					model.addAttribute("success", " Doctor Appointment Booked. ");
				}
			} else {
				
				model.addAttribute("error", " Insufficient Account Balance. ");
			}
		} else {

			model.addAttribute("error", errorMessage);
		}

		return "./patientbookdoctor/patientbookdoctor";
	}
}
