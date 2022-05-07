package com.healtheme.patientprofile.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.healtheme.DatabaseConnectionDAO;
import com.healtheme.patientprofile.model.PatientProfileModel;

@Component("showProfileDatabase")
public class ShowProfileDatabase implements ShowProfileDatabaseDAO {

	private static final String SELECT_PATIENT_QUERY = "SELECT p.patient_fname,p.patient_mname,p.patient_lname,p.patient_dob,p.patient_gender,p.patient_phone_no,p.patient_blood_group,p.patient_apartment_no,p.patient_street,p.patient_city,p.patient_province,p.patient_country,p.patient_postal_code,p.patient_medical_history,p.patient_emergency_contact_name,p.patient_emergency_contact_phone_no,b.account_name,b.account_no,b.bank_institution_no,b.transit_no,b.account_balance FROM patient p, userbankaccount b WHERE p.patient_email = b.username AND p.patient_email = ?;";

	@Override
	public PatientProfileModel showProfile(DatabaseConnectionDAO databaseConnection) {

		PatientProfileModel patient = new PatientProfileModel("", "", "", "", "", "", "", "", "", "", "", "", "", "",
				"", "", "", "", "", "", "");

		try {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			Connection connection = databaseConnection.getDatabaseInstance();
			PreparedStatement selectPatientQuery = connection.prepareStatement(SELECT_PATIENT_QUERY);
			selectPatientQuery.setString(1, auth.getName());
			ResultSet result = selectPatientQuery.executeQuery();
			result.next();

			String patientFirstName = result.getString("patient_fname");
			String patientMiddleName = result.getString("patient_mname");
			String patientLastName = result.getString("patient_lname");
			String patientDOB = result.getString("patient_dob");
			String patientGender = result.getString("patient_gender");
			String patientPhoneNo = result.getString("patient_phone_no");
			String patientBloodGroup = result.getString("patient_blood_group");
			String patientApartmentNo = result.getString("patient_apartment_no");
			String patientStreet = result.getString("patient_street");
			String patientCity = result.getString("patient_city");
			String patientProvince = result.getString("patient_province");
			String patientCountry = result.getString("patient_country");
			String patientPostalCode = result.getString("patient_postal_code");
			String patientMedicalHistory = result.getString("patient_medical_history");
			String patientEmergencyContactName = result.getString("patient_emergency_contact_name");
			String patientEmergencyContactPhoneNo = result.getString("patient_emergency_contact_phone_no");
			String patientAccountName = result.getString("account_name");
			String patientAccountNo = result.getString("account_no");
			String patientBankInstitutionNo = result.getString("bank_institution_no");
			String patientTransitNo = result.getString("transit_no");
			String patientAccountBalance = result.getString("account_balance") + " CAD";

			patient = new PatientProfileModel(patientFirstName, patientMiddleName, patientLastName, patientDOB,
					patientGender, patientPhoneNo, patientBloodGroup, patientApartmentNo, patientStreet, patientCity,
					patientProvince, patientCountry, patientPostalCode, patientMedicalHistory,
					patientEmergencyContactName, patientEmergencyContactPhoneNo, patientAccountName, patientAccountNo,
					patientBankInstitutionNo, patientTransitNo, patientAccountBalance);

			selectPatientQuery.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return patient;
	}
}
