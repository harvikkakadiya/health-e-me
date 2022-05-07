package com.healtheme.patientprofile.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.healtheme.DatabaseConnectionDAO;
import com.healtheme.patientprofile.model.PatientProfileModel;

@Component("saveProfileDatabase")
public class SaveProfileDatabase implements SaveProfileDatabaseDAO {

	private static final String UPDATE_PATIENT_QUERY = "UPDATE patient SET patient_fname = ?, patient_mname = ?, patient_lname = ?, patient_dob = ?, patient_gender = ?, patient_phone_no = ?, patient_blood_group = ?, patient_apartment_no = ?, patient_street = ?, patient_city =?, patient_province = ?, patient_country = ?, patient_postal_code = ?, patient_medical_history = ?, patient_emergency_contact_name = ?, patient_emergency_contact_phone_no = ? WHERE patient_email = ? ;";

	private static final String UPDATE_USER_QUERY = "UPDATE userbankaccount SET account_name = ?, account_no = ?, bank_institution_no = ?, transit_no = ? WHERE username = ? ;";

	@Override
	public void saveProfile(DatabaseConnectionDAO databaseConnection, PatientProfileModel patient) {

		try {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			Connection connection = databaseConnection.getDatabaseInstance();

			PreparedStatement updatePatientQuery = connection.prepareStatement(UPDATE_PATIENT_QUERY);
			updatePatientQuery.setString(1, patient.getPatientFirstName());
			updatePatientQuery.setString(2, patient.getPatientMiddleName());
			updatePatientQuery.setString(3, patient.getPatientLastName());
			updatePatientQuery.setString(4, patient.getPatientDOB());
			updatePatientQuery.setString(5, patient.getPatientGender());
			updatePatientQuery.setString(6, patient.getPatientPhoneNo());
			updatePatientQuery.setString(7, patient.getPatientBloodGroup());
			updatePatientQuery.setString(8, patient.getPatientApartmentNo());
			updatePatientQuery.setString(9, patient.getPatientStreet());
			updatePatientQuery.setString(10, patient.getPatientCity());
			updatePatientQuery.setString(11, patient.getPatientProvince());
			updatePatientQuery.setString(12, patient.getPatientCountry());
			updatePatientQuery.setString(13, patient.getPatientPostalCode());
			updatePatientQuery.setString(14, patient.getPatientMedicalHistory());
			updatePatientQuery.setString(15, patient.getPatientEmergencyContactName());
			updatePatientQuery.setString(16, patient.getPatientEmergencyContactPhoneNo());
			updatePatientQuery.setString(17, auth.getName());
			updatePatientQuery.executeUpdate();
			updatePatientQuery.close();

			PreparedStatement updateUserQuery = connection.prepareStatement(UPDATE_USER_QUERY);
			updateUserQuery.setString(1, patient.getPatientAccountName());
			updateUserQuery.setString(2, patient.getPatientAccountNo());
			updateUserQuery.setString(3, patient.getPatientBankInstitutionNo());
			updateUserQuery.setString(4, patient.getPatientTransitNo());
			updateUserQuery.setString(5, auth.getName());
			updateUserQuery.executeUpdate();
			updateUserQuery.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
