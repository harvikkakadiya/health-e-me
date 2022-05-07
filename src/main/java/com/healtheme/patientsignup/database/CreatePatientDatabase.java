package com.healtheme.patientsignup.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Component;

import com.healtheme.DatabaseConnectionDAO;
import com.healtheme.patientsignup.model.PatientSignupModel;

@Component("createPatientDatabase")
public class CreatePatientDatabase implements CreatePatientDatabaseDAO {

	private static final String SELECT_USER_QUERY = "SELECT username FROM users where username = ? ;";

	private static final String INSERT_PATIENT_QUERY = "INSERT INTO patient (patient_email,patient_fname,patient_mname,patient_lname,patient_dob,patient_gender,patient_phone_no,patient_blood_group,patient_apartment_no,patient_street,patient_city,patient_province,patient_country,patient_postal_code,patient_medical_history,patient_emergency_contact_name,patient_emergency_contact_phone_no) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ;";

	private static final String INSERT_USER_BANK_ACCOUNT_QUERY = "INSERT INTO userbankaccount (username,account_name,account_no,bank_institution_no,transit_no) values (?,?,?,?,?) ;";

	@Override
	public boolean createPatient(DatabaseConnectionDAO databaseConnection,
			JdbcUserDetailsManager jdbcUserDetailsManager, PatientSignupModel patient) {

		try {
			Connection connection = databaseConnection.getDatabaseInstance();
			PreparedStatement selectUserQuery = connection.prepareStatement(SELECT_USER_QUERY);
			selectUserQuery.setString(1, patient.getPatientEmail());
			ResultSet result = selectUserQuery.executeQuery();

			if (Boolean.FALSE.equals(result.next())) {

				PreparedStatement insertPatientQuery = connection.prepareStatement(INSERT_PATIENT_QUERY);
				insertPatientQuery.setString(1, patient.getPatientEmail().toLowerCase());
				insertPatientQuery.setString(2, patient.getPatientFirstName());
				insertPatientQuery.setString(3, patient.getPatientMiddleName());
				insertPatientQuery.setString(4, patient.getPatientLastName());
				insertPatientQuery.setString(5, patient.getPatientDOB());
				insertPatientQuery.setString(6, patient.getPatientGender());
				insertPatientQuery.setString(7, patient.getPatientPhoneNo());
				insertPatientQuery.setString(8, patient.getPatientBloodGroup());
				insertPatientQuery.setString(9, patient.getPatientApartmentNo());
				insertPatientQuery.setString(10, patient.getPatientStreet());
				insertPatientQuery.setString(11, patient.getPatientCity());
				insertPatientQuery.setString(12, patient.getPatientProvince());
				insertPatientQuery.setString(13, patient.getPatientCountry());
				insertPatientQuery.setString(14, patient.getPatientPostalCode());
				insertPatientQuery.setString(15, patient.getPatientMedicalHistory());
				insertPatientQuery.setString(16, patient.getPatientEmergencyContactName());
				insertPatientQuery.setString(17, patient.getPatientEmergencyContactPhoneNo());
				insertPatientQuery.executeUpdate();
				insertPatientQuery.close();

				List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
				authorities.add(new SimpleGrantedAuthority("ROLE_PATIENT"));
				User user = new User(patient.getPatientEmail().toLowerCase(), patient.getPatientPassword(),
						authorities);
				jdbcUserDetailsManager.createUser(user);

				PreparedStatement insertUserBankAccountQuery = connection.prepareStatement(INSERT_USER_BANK_ACCOUNT_QUERY);
				insertUserBankAccountQuery.setString(1, patient.getPatientEmail().toLowerCase());
				insertUserBankAccountQuery.setString(2, patient.getPatientAccountName());
				insertUserBankAccountQuery.setString(3, patient.getPatientAccountNo());
				insertUserBankAccountQuery.setString(4, patient.getPatientBankInstitutionNo());
				insertUserBankAccountQuery.setString(5, patient.getPatientTransitNo());

				insertUserBankAccountQuery.executeUpdate();
				insertUserBankAccountQuery.close();

				return true;
			}

			selectUserQuery.close();

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return false;
	}
}
