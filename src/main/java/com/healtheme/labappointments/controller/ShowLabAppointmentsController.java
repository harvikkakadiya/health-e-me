package com.healtheme.labappointments.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.healtheme.DatabaseConnectionDAO;
import com.healtheme.labappointments.database.LabAppointmentsDatabaseDAO;

@Controller
public class ShowLabAppointmentsController {

	@Autowired
	private DatabaseConnectionDAO databaseConnection;

	@Autowired
	private LabAppointmentsDatabaseDAO labAppointmentsDatabase;

	@GetMapping("/lab-appointments")
	public String showLabAppointments(Model model) {
		labAppointmentsDatabase.showLabAppointments(databaseConnection, model);

		return "./labappointments/labappointments";
	}
}
