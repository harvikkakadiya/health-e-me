package com.healtheme.labcreatereports.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import com.healtheme.DatabaseConnectionDAO;
import com.healtheme.labcreatereports.model.LabCreateReportsModel;

@Component("labCreateReportsDatabase")
public class LabCreateReportsDatabase implements LabCreateReportsDatabaseDAO {

	private static final String INSERT_LAB_REPORT_QUERY = "INSERT INTO labreports (patient_email, lab_email, report_date, report_time, doctor_email, tests_done, results) VALUES (?,?,?,?,?,?,?);";

	@Override
	public void insertLabReport(DatabaseConnectionDAO databaseConnection, LabCreateReportsModel lab, Model model,
			JdbcUserDetailsManager jdbcUserDetailsManager) {

		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;

		try {
			date = (Date) dateFormat.parse(lab.getReportDate());
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		
		DateFormat timeFormat = new SimpleDateFormat("HH:mm");
		Date time = null;
		
		try {
			time = (Date) timeFormat.parse(lab.getReportTime());
		} catch (ParseException e1) {
			e1.printStackTrace();
		}

		java.sql.Date reportDate = new java.sql.Date(date.getTime());
		java.sql.Timestamp reportTime = new java.sql.Timestamp(time.getTime());

		try {
			Connection conn = databaseConnection.getDatabaseInstance();
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			PreparedStatement insertLabReportStatement = conn.prepareStatement(INSERT_LAB_REPORT_QUERY);
			insertLabReportStatement.setString(1, lab.getPatientEmail());
			insertLabReportStatement.setString(2, auth.getName());
			insertLabReportStatement.setDate(3, reportDate);
			insertLabReportStatement.setTimestamp(4, reportTime);
			insertLabReportStatement.setString(5, lab.getPrescribedBy());
			insertLabReportStatement.setString(6, lab.getTestsDone());
			insertLabReportStatement.setString(7, lab.getResult());
			insertLabReportStatement.execute();
			insertLabReportStatement.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
