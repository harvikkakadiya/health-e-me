package com.healtheme.doctorsignup.database;

import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.ui.Model;

import com.healtheme.DatabaseConnectionDAO;
import com.healtheme.doctorsignup.model.DoctorSignupModel;

public interface DoctorSignupDatabaseDAO {
	public boolean insertData(DatabaseConnectionDAO databaseConnection, DoctorSignupModel doctorObj, Model model,
			JdbcUserDetailsManager jdbcUserDetailsManager);
}
