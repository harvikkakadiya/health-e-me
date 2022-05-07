package com.healtheme.doctorviewappointments.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.healtheme.DatabaseConnectionDAO;
import com.healtheme.doctorviewappointments.database.DoctorViewAppointmentsDatabaseDAO;

@Controller
public class DeleteDoctorApppointmentsController {

	@Autowired
	private DatabaseConnectionDAO databaseConnection;

	@Autowired
	private DoctorViewAppointmentsDatabaseDAO doctorViewAppointments;

	@RequestMapping(value = "/doctor-delete-appointment")
	public String deleteAppointment(@RequestParam(value = "appointmentId") String appointmentId, Model model) {
		doctorViewAppointments.deleteAppointments(databaseConnection, appointmentId, model);

		return "redirect:/doctor-view-appointments";
	}
}
