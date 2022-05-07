package com.healtheme.adminstatistics.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import org.springframework.stereotype.Component;

import com.healtheme.DatabaseConnectionDAO;
import com.healtheme.adminstatistics.model.AdminStatisticsModel;

@Component("adminStatisticsDatabaseDAO")
public class AdminStatisticsDatabase implements AdminStatisticsDatabaseDAO {

	private static final String SELECT_PATIENT_INFORMATION = "SELECT patient_email, patient_fname, patient_mname, patient_lname, patient_dob, patient_gender, patient_phone_no, patient_blood_group, patient_apartment_no, patient_street, patient_city, patient_province, patient_country, patient_postal_code, patient_medical_history, patient_emergency_contact_name, patient_emergency_contact_phone_no from patient;";

	private static final String SELECT_DOCTOR_INFORMATION = " SELECT doctor_email, doctor_fname, doctor_mname, doctor_lname, doctor_dob, doctor_gender,doctor_phone, doctor_apartment, doctor_street_address, doctor_city, doctor_province, doctor_country, doctor_postal_code, doctor_qualification, doctor_specialisation, doctor_license_id, doctor_consultation_fees  FROM doctor; ";

	private static final String SELECT_LAB_INFORMATION = "SELECT lab_email, lab_name, lab_doe, lab_owner_fname, lab_owner_mname, lab_owner_lname, lab_phone, lab_unit, lab_street_address, lab_city, lab_province, lab_country, lab_postal_code, lab_license_id, lab_tests_available from lab;";

	@Override
	public ArrayList<AdminStatisticsModel> showPatientStat(DatabaseConnectionDAO databaseConnection) {

		ArrayList<AdminStatisticsModel> patientStat = new ArrayList<AdminStatisticsModel>();
		try {
			Connection conn = databaseConnection.getDatabaseInstance();
			Statement patientStmt = conn.createStatement();
			ResultSet resultSet = patientStmt.executeQuery(SELECT_PATIENT_INFORMATION);
			while (resultSet.next()) {
				AdminStatisticsModel data = new AdminStatisticsModel();
				data.setPatient_email(resultSet.getString("patient_email"));
				data.setPatient_fname(resultSet.getString("patient_fname"));
				data.setPatient_mname(resultSet.getString("patient_mname"));
				data.setPatient_lname(resultSet.getString("patient_lname"));
				data.setPatient_dob(resultSet.getString("patient_dob"));
				data.setPatient_gender(resultSet.getString("patient_gender"));
				data.setPatient_phone_no(resultSet.getString("patient_phone_no"));
				data.setPatient_blood_group(resultSet.getString("patient_blood_group"));
				data.setPatient_apartment(resultSet.getString("patient_apartment_no"));
				data.setPatient_street(resultSet.getString("patient_street"));
				data.setPatient_city(resultSet.getString("patient_city"));
				data.setPatient_province(resultSet.getString("patient_province"));
				data.setPatient_country(resultSet.getString("patient_country"));
				data.setPatient_postal_code(resultSet.getString("patient_postal_code"));
				data.setPatient_medical_history(resultSet.getString("patient_medical_history"));
				data.setPatient_emergency_contact_name(resultSet.getString("patient_emergency_contact_name"));
				data.setPatient_emergency_contact_phone_no(resultSet.getString("patient_emergency_contact_phone_no"));
				patientStat.add(data);
			}
			patientStmt.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return patientStat;
	}

	@Override
	public ArrayList<AdminStatisticsModel> showDoctorStat(DatabaseConnectionDAO databaseConnection) {

		ArrayList<AdminStatisticsModel> doctorStat = new ArrayList<AdminStatisticsModel>();
		try {
			Connection conn = databaseConnection.getDatabaseInstance();
			Statement doctorStmt = conn.createStatement();
			ResultSet resultSet = doctorStmt.executeQuery(SELECT_DOCTOR_INFORMATION);
			while (resultSet.next()) {

				AdminStatisticsModel data = new AdminStatisticsModel();
				data.setDoctor_email(resultSet.getString("doctor_email"));
				data.setDoctor_fname(resultSet.getString("doctor_fname"));
				data.setDoctor_mname(resultSet.getString("doctor_mname"));
				data.setDoctor_lname(resultSet.getString("doctor_lname"));
				data.setDoctor_dob(resultSet.getString("doctor_dob"));
				data.setDoctor_gender(resultSet.getString("doctor_gender"));
				data.setDoctor_phone(resultSet.getString("doctor_phone"));
				data.setDoctor_apartment(resultSet.getString("doctor_apartment"));
				data.setDoctor_street_address(resultSet.getString("doctor_street_address"));
				data.setDoctor_city(resultSet.getString("doctor_city"));
				data.setDoctor_province(resultSet.getString("doctor_province"));
				data.setDoctor_country(resultSet.getString("doctor_country"));
				data.setDoctor_postal_code(resultSet.getString("doctor_postal_code"));
				data.setDoctor_qualification(resultSet.getString("doctor_qualification"));
				data.setDoctor_specialisation(resultSet.getString("doctor_specialisation"));
				data.setDoctor_license_id(resultSet.getString("doctor_license_id"));
				data.setDoctor_consultation_fees(resultSet.getString("doctor_consultation_fees"));
				doctorStat.add(data);
			}
			doctorStmt.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return doctorStat;
	}

	@Override
	public ArrayList<AdminStatisticsModel> showLabStat(DatabaseConnectionDAO databaseConnection) {

		ArrayList<AdminStatisticsModel> labStat = new ArrayList<AdminStatisticsModel>();
		try {
			Connection conn = databaseConnection.getDatabaseInstance();
			Statement labStmt = conn.createStatement();
			ResultSet resultSet = labStmt.executeQuery(SELECT_LAB_INFORMATION);
			while (resultSet.next()) {

				AdminStatisticsModel data = new AdminStatisticsModel();
				data.setLab_email(resultSet.getString("lab_email"));
				data.setLab_name(resultSet.getString("lab_name"));
				data.setLab_doe(resultSet.getString("lab_doe"));
				data.setLab_owner_fname(resultSet.getString("lab_owner_fname"));
				data.setLab_owner_mname(resultSet.getString("lab_owner_mname"));
				data.setLab_owner_lname(resultSet.getString("lab_owner_lname"));
				data.setLab_phone(resultSet.getString("lab_phone"));
				data.setLab_unit(resultSet.getString("lab_unit"));
				data.setLab_street_address(resultSet.getString("lab_street_address"));
				data.setLab_city(resultSet.getString("lab_city"));
				data.setLab_province(resultSet.getString("lab_province"));
				data.setLab_country(resultSet.getString("lab_country"));
				data.setLab_postal_code(resultSet.getString("lab_postal_code"));
				data.setLab_license_id(resultSet.getString("lab_license_id"));
				data.setLab_tests_available(resultSet.getString("lab_tests_available"));
				labStat.add(data);

			}
			labStmt.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return labStat;
	}

}
