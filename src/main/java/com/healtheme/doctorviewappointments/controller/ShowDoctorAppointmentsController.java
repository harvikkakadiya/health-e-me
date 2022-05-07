package com.healtheme.doctorviewappointments.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.healtheme.DatabaseConnectionDAO;
import com.healtheme.doctorviewappointments.database.DoctorViewAppointmentsDatabaseDAO;

@Controller
public class ShowDoctorAppointmentsController {

	@Autowired
	private DatabaseConnectionDAO databaseConnection;

	@Autowired
	private DoctorViewAppointmentsDatabaseDAO doctorViewAppointments;

	@GetMapping("/doctor-view-appointments")
	public String viewDoctorAppointments(Model model) {
		doctorViewAppointments.showAppointments(databaseConnection, model);

		return "./doctorappointments/doctorappointments";
	}
}
