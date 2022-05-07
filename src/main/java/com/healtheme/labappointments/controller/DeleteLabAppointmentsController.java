package com.healtheme.labappointments.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.healtheme.DatabaseConnectionDAO;
import com.healtheme.labappointments.database.LabAppointmentsDatabaseDAO;

@Controller
public class DeleteLabAppointmentsController {

	@Autowired
	private DatabaseConnectionDAO databaseConnection;

	@Autowired
	LabAppointmentsDatabaseDAO labAppointmentsDatabase;

	@RequestMapping(value = "/delete-appointment")
	public String deleteAppointment(@RequestParam(value = "appointmentId") String appointmentId, Model model) {

		labAppointmentsDatabase.deleteLabAppointment(databaseConnection, appointmentId, model);

		return "redirect:/lab-appointments";
	}
}
