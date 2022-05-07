package com.healtheme.patientreports.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import com.healtheme.DatabaseConnectionDAO;
import com.healtheme.patientreports.model.PatientReportsModel;

@Component("patientReport")
public class PatientReportsDatabase implements PatientReportsDatabaseDAO {

	private static final String SELECT_LAB_REPORT_QUERY = "SELECT l.tests_done, l.report_date, l.report_time, d.doctor_fname, d.doctor_mname, d.doctor_lname, l.results from labreports l, doctor d where l.doctor_email = d.doctor_email and l.patient_email = ?;";
	private boolean successFlag = false;

	@Override
	public void showPatientReports(DatabaseConnectionDAO databaseConnection, Model model) {
		try {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			Connection conn = databaseConnection.getDatabaseInstance();

			ArrayList<PatientReportsModel> patientReports = new ArrayList<PatientReportsModel>();

			PreparedStatement selectLabReportStatement = conn.prepareStatement(SELECT_LAB_REPORT_QUERY);
			selectLabReportStatement.setString(1, auth.getName());
			ResultSet result = selectLabReportStatement.executeQuery();

			while (result.next()) {
				String testName = result.getString("tests_done");
				String reportDate = result.getString("report_date");
				String reportTime = result.getString("report_time");
				String prescribedBy = result.getString("doctor_fname") + " " + result.getString("doctor_mname") + " "
						+ result.getString("doctor_lname");
				String testResult = result.getString("results");

				PatientReportsModel patientReport = new PatientReportsModel(testName, reportDate, reportTime,
						prescribedBy, testResult);
				patientReports.add(patientReport);
			}

			model.addAttribute("patientReports", patientReports);

			selectLabReportStatement.close();

			if (successFlag) {
				model.addAttribute("success", 1);
				successFlag = false;
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
