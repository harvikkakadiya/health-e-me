package com.healtheme.patientappointments.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.healtheme.DatabaseConnectionDAO;
import com.healtheme.patientappointments.database.ShowAppointmentsDatabaseDAO;
import com.healtheme.patientappointments.model.PatientAppointmentsDoctorModel;
import com.healtheme.patientappointments.model.PatientAppointmentsLabModel;

@Controller
public class ShowAppointmentsController {

	@Autowired
	private DatabaseConnectionDAO databaseConnection;

	@Autowired
	private ShowAppointmentsDatabaseDAO showAppointmentsDatabase;

	@GetMapping("/patient-appointments")
	public String showAppointments(Model model) {

		ArrayList<PatientAppointmentsDoctorModel> doctorAppointments = showAppointmentsDatabase
				.showDoctorAppointments(databaseConnection);

		model.addAttribute("doctorAppointments", doctorAppointments);

		ArrayList<PatientAppointmentsLabModel> labAppointments = showAppointmentsDatabase
				.showLabAppointments(databaseConnection);

		model.addAttribute("labAppointments", labAppointments);

		return "./patientappointments/patientappointments";
	}
}
