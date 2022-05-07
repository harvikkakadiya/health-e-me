package com.healtheme.doctorprofile.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import com.healtheme.DatabaseConnectionDAO;
import com.healtheme.doctorprofile.model.DoctorProfileModel;

@Component("doctorProfile")
public class DoctorProfileDatabase implements DoctorProfileDatabaseDAO {

	private static final String SELECT_DOCTOR_QUERY = "select d.doctor_fname,d.doctor_mname,d.doctor_lname,d.doctor_dob,d.doctor_gender,d.doctor_phone,d.doctor_apartment,d.doctor_street_address,d.doctor_city,d.doctor_province,d.doctor_country,d.doctor_postal_code,d.doctor_qualification,d.doctor_specialisation,d.doctor_license_id,d.doctor_consultation_fees,u.username, b.account_name,b.account_no,b.bank_institution_no,b.transit_no,b.account_balance from doctor d, users u, userbankaccount b where  u.username = d.doctor_email and b.username = d.doctor_email and d.doctor_email = ? ;";
	private static final String UPDATE_DOCTOR_QUERY = "Update doctor set doctor_fname = ?, doctor_mname = ?, doctor_lname = ?, doctor_dob = ?, doctor_gender = ?, doctor_phone = ?, doctor_apartment = ?, doctor_street_address = ?, doctor_city =?, doctor_province = ?, doctor_country = ?, doctor_postal_code = ?, doctor_qualification = ?, doctor_specialisation = ?, doctor_license_id = ?, doctor_consultation_fees = ? where doctor_email = ? ;";
	private static final String UPDATE_USER_BANK_QUERY = "Update userbankaccount set account_name = ?, account_no = ?, bank_institution_no = ?, transit_no = ? where username = ? ;";

	@Override
	public void showDoctorProfile(DatabaseConnectionDAO databaseConnection, Model model) {
		DoctorProfileModel doctor = new DoctorProfileModel("", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
				"", "", "", "", "", "");
		
		try {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			Connection conn = databaseConnection.getDatabaseInstance();
			PreparedStatement selectDoctorStatement = conn.prepareStatement(SELECT_DOCTOR_QUERY);
			selectDoctorStatement.setString(1, auth.getName());
			ResultSet result = selectDoctorStatement.executeQuery();
			result.next();

			String doctorFname = result.getString("doctor_fname");
			String doctorMname = result.getString("doctor_mname");
			String doctorLname = result.getString("doctor_lname");
			String doctorDob = result.getString("doctor_dob");
			String doctorGender = result.getString("doctor_gender");
			String doctorPhone = result.getString("doctor_phone");
			String doctorApartment = result.getString("doctor_apartment");
			String doctorStreetAddress = result.getString("doctor_street_address");
			String doctorCity = result.getString("doctor_city");
			String doctorProvince = result.getString("doctor_province");
			String doctorCountry = result.getString("doctor_country");
			String doctorPostalCode = result.getString("doctor_postal_code");
			String doctorQualification = result.getString("doctor_qualification");
			String doctorSpecialisation = result.getString("doctor_specialisation");
			String doctorLicenseId = result.getString("doctor_license_id");
			String doctorConsultationFees = result.getString("doctor_consultation_fees");
			String doctorAccountName = result.getString("account_name");
			String doctorAccountNumber = result.getString("account_no");
			String doctorBankNumber = result.getString("bank_institution_no");
			String doctorTransitNumber = result.getString("transit_no");
			String doctorAccountBalance = result.getString("account_balance") + " CAD";

			doctor = new DoctorProfileModel(doctorFname, doctorMname, doctorLname, doctorDob, doctorGender, doctorPhone,
					doctorApartment, doctorStreetAddress, doctorCity, doctorProvince, doctorCountry, doctorPostalCode,
					doctorQualification, doctorSpecialisation, doctorLicenseId, doctorConsultationFees,
					doctorAccountName, doctorAccountNumber, doctorBankNumber, doctorTransitNumber,
					doctorAccountBalance);

			model.addAttribute("doctor", doctor);

			selectDoctorStatement.close();
		}
		catch (Exception e) {
			System.out.println(e);
		}
	}

	@Override
	public void saveDoctorProfile(DatabaseConnectionDAO databaseConnection, DoctorProfileModel doctor, Model model) {
		try {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			Connection conn = databaseConnection.getDatabaseInstance();

			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date date = (Date) dateFormat.parse(doctor.getDoctorDob());
			java.sql.Date doctorDob = new java.sql.Date(date.getTime());

			PreparedStatement updateDoctorStatement = conn.prepareStatement(UPDATE_DOCTOR_QUERY);

			updateDoctorStatement.setString(1, doctor.getDoctorFname());
			updateDoctorStatement.setString(2, doctor.getDoctorMname());
			updateDoctorStatement.setString(3, doctor.getDoctorLname());
			updateDoctorStatement.setDate(4, doctorDob);
			updateDoctorStatement.setString(5, doctor.getDoctorGender());
			updateDoctorStatement.setString(6, doctor.getDoctorPhone());
			updateDoctorStatement.setString(7, doctor.getDoctorApartment());
			updateDoctorStatement.setString(8, doctor.getDoctorStreetAddress());
			updateDoctorStatement.setString(9, doctor.getDoctorCity());
			updateDoctorStatement.setString(10, doctor.getDoctorProvince());
			updateDoctorStatement.setString(11, doctor.getDoctorCountry());
			updateDoctorStatement.setString(12, doctor.getDoctorPostalCode());
			updateDoctorStatement.setString(13, doctor.getDoctorQualification());
			updateDoctorStatement.setString(14, doctor.getDoctorSpecialisation());
			updateDoctorStatement.setString(15, doctor.getDoctorLicenseId());
			updateDoctorStatement.setString(16, doctor.getDoctorConsultationFees());
			updateDoctorStatement.setString(17, auth.getName());

			updateDoctorStatement.execute();

			PreparedStatement updateUserBankStatement = conn.prepareStatement(UPDATE_USER_BANK_QUERY);
			updateUserBankStatement.setString(1, doctor.getDoctorAccountName());
			updateUserBankStatement.setString(2, doctor.getDoctorAccountNumber());
			updateUserBankStatement.setString(3, doctor.getDoctorBankNumber());
			updateUserBankStatement.setString(4, doctor.getDoctorTransitNumber());
			updateUserBankStatement.setString(5, auth.getName());

			updateUserBankStatement.execute();
		}
		catch (Exception e) {
			System.out.println(e);
		}
	}
}
