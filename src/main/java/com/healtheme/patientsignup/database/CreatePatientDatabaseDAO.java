package com.healtheme.patientsignup.database;

import org.springframework.security.provisioning.JdbcUserDetailsManager;

import com.healtheme.DatabaseConnectionDAO;
import com.healtheme.patientsignup.model.PatientSignupModel;

public interface CreatePatientDatabaseDAO {

	boolean createPatient(DatabaseConnectionDAO databaseConnection, JdbcUserDetailsManager jdbcUserDetailsManager,
			PatientSignupModel patient);
}