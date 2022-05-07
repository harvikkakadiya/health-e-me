package com.healtheme.labcreatereports.database;

import java.text.ParseException;

import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.ui.Model;

import com.healtheme.DatabaseConnectionDAO;
import com.healtheme.labcreatereports.model.LabCreateReportsModel;

public interface LabCreateReportsDatabaseDAO {

	void insertLabReport(DatabaseConnectionDAO databaseConnection, LabCreateReportsModel lab, Model model,
			JdbcUserDetailsManager jdbcUserDetailsManager) throws ParseException;

}