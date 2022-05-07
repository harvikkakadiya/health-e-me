package com.healtheme.patientappointments.database;

import java.util.ArrayList;

import com.healtheme.DatabaseConnectionDAO;
import com.healtheme.patientappointments.model.PatientAppointmentsDoctorModel;
import com.healtheme.patientappointments.model.PatientAppointmentsLabModel;

public interface ShowAppointmentsDatabaseDAO {

	ArrayList<PatientAppointmentsDoctorModel> showDoctorAppointments(DatabaseConnectionDAO databaseConnection);

	ArrayList<PatientAppointmentsLabModel> showLabAppointments(DatabaseConnectionDAO databaseConnection);
}