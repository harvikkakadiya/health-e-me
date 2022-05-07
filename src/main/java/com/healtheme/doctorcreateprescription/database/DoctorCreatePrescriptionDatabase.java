package com.healtheme.doctorcreateprescription.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import com.healtheme.DatabaseConnectionDAO;
import com.healtheme.doctorcreateprescription.model.DoctorCreatePrescriptionModel;

@Component("createPrescription")
public class DoctorCreatePrescriptionDatabase implements DoctorCreatePrescriptionDatabaseDAO {

	private static final String INSERT_PRESCRIPTION_QUERY = "INSERT INTO prescription (patient_email,doctor_email,prescription_date,prescription_time,prescription_symptom,prescription_diagnosis,prescription_medicine) VALUES (?,?,?,?,?,?,?);";
	
	@Override
	public void insertData(DatabaseConnectionDAO databaseConnection, DoctorCreatePrescriptionModel prescriptionObj,Model model) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		
		try {
			date = (Date) dateFormat.parse(prescriptionObj.getPrescriptionAppointmentDate());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		java.sql.Date appointmentDate = new java.sql.Date(date.getTime());
		DateFormat timeFormat = new SimpleDateFormat("HH:mm");
		Date time = null;
		
		try {
			time = (Date) timeFormat.parse(prescriptionObj.getPrescriptionAppointmentTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		java.sql.Timestamp appointmentTime = new java.sql.Timestamp(time.getTime());

		try {
			Connection conn = databaseConnection.getDatabaseInstance();
			PreparedStatement insertPrescriptionStatement = conn.prepareStatement(INSERT_PRESCRIPTION_QUERY);
			
			insertPrescriptionStatement.setString(1, prescriptionObj.getPatientPrescriptionEmail().toLowerCase());
			insertPrescriptionStatement.setString(2, prescriptionObj.getDoctorPrescriptionEmail().toLowerCase());
			insertPrescriptionStatement.setDate(3, appointmentDate);
			insertPrescriptionStatement.setTimestamp(4, appointmentTime);
			insertPrescriptionStatement.setString(5, prescriptionObj.getPrescriptionrPatientSymptoms());
			insertPrescriptionStatement.setString(6, prescriptionObj.getPrescriptionPatientDiagnosis());
			insertPrescriptionStatement.setString(7, prescriptionObj.getPrescriptionPatientMedicines());
			insertPrescriptionStatement.execute();
			insertPrescriptionStatement.close();
		}

		catch (Exception e) {
			System.out.println(e);

		}
	}
}
