package com.healtheme.patientprescriptions.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import com.healtheme.DatabaseConnectionDAO;
import com.healtheme.patientprescriptions.model.PatientPrescriptionsModel;

@Component("patientPrescription")
public class PatientPrescriptionsDatabase implements PatientPrescriptionsDatabaseDAO {
	private boolean successFlag = false;
	private static final String SELECT_DOCTOR_PRESCRIPTION_QUERY = "select d.doctor_fname, d.doctor_mname, d.doctor_lname, p.prescription_date, p.prescription_time, p.prescription_symptom, p.prescription_diagnosis,p.prescription_medicine from doctor d, prescription p where p.doctor_email = d.doctor_email and p.patient_email = ?;";

	@Override
	public void showPrescriptions(DatabaseConnectionDAO databaseConnection, Model model) {
		
		try {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			Connection conn = databaseConnection.getDatabaseInstance();

			ArrayList<PatientPrescriptionsModel> patientPrescriptions = new ArrayList<PatientPrescriptionsModel>();

			PreparedStatement selectPrescriptionStatement = conn.prepareStatement(SELECT_DOCTOR_PRESCRIPTION_QUERY);
			selectPrescriptionStatement.setString(1, auth.getName());
			ResultSet result = selectPrescriptionStatement.executeQuery();

			while (result.next()) {
				String doctorName = result.getString("doctor_fname") + " " + result.getString("doctor_mname") + " "
						+ result.getString("doctor_lname");
				String appointmentDate = result.getString("prescription_date");
				String appointmentTime = result.getString("prescription_time");
				String symptoms = result.getString("prescription_symptom");
				String diagnosis = result.getString("prescription_diagnosis");
				String medicines = result.getString("prescription_medicine");
				PatientPrescriptionsModel patientPrescription = new PatientPrescriptionsModel(doctorName,
						appointmentDate, appointmentTime, symptoms, diagnosis, medicines);
				
				patientPrescriptions.add(patientPrescription);
			}

			model.addAttribute("patientPrescriptions", patientPrescriptions);
			selectPrescriptionStatement.close();

			if (successFlag) {
				model.addAttribute("success", 1);
				successFlag = false;
			}
		}
		catch (Exception e) {
			System.out.println(e);
		}
	}
}
