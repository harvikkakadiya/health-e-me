package com.healtheme.labsignup.database;

import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.ui.Model;

import com.healtheme.DatabaseConnectionDAO;
import com.healtheme.labsignup.model.LabSignupModel;

public interface LabSignupDatabaseDAO {

	boolean insertData(DatabaseConnectionDAO databaseConnection, LabSignupModel lab, Model model,
			JdbcUserDetailsManager jdbcUserDetailsManager);

}