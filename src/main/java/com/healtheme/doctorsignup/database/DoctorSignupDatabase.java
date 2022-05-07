package com.healtheme.doctorsignup.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import com.healtheme.DatabaseConnectionDAO;
import com.healtheme.doctorsignup.model.DoctorSignupModel;

@Component("doctorSignup")
public class DoctorSignupDatabase implements DoctorSignupDatabaseDAO {

	private static final String SELECT_USER_QUERY = "SELECT username FROM users where username = ? ;";
	private static final String INSERT_DOCTOR_QUERY = "INSERT INTO doctor (doctor_email,doctor_fname,doctor_mname,doctor_lname,doctor_dob,doctor_gender,doctor_phone,doctor_apartment,doctor_street_address,doctor_city,doctor_province,doctor_country,doctor_postal_code,doctor_qualification,doctor_specialisation,doctor_license_id,doctor_consultation_fees) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
	private static final String INSERT_USER_BANK_ACCOUNT_QUERY = "INSERT INTO userbankaccount (username,account_name,account_no,bank_institution_no,transit_no) VALUES (?,?,?,?,?);";
	private static final String ROLE_DOCTOR = "ROLE_DOCTOR";

	@Override
	public boolean insertData(DatabaseConnectionDAO databaseConnection, DoctorSignupModel doctorObj, Model model,
			JdbcUserDetailsManager jdbcUserDetailsManager) {
		boolean userExists = false;

		try {
			Connection conn = databaseConnection.getDatabaseInstance();
			PreparedStatement selectUserStatement = conn.prepareStatement(SELECT_USER_QUERY);
			selectUserStatement.setString(1, doctorObj.getDoctorEmail());
			ResultSet result = selectUserStatement.executeQuery();

			if (Boolean.FALSE.equals(result.next())) {

				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				Date date = (Date) dateFormat.parse(doctorObj.getDoctorDob());
				java.sql.Date doctorDob = new java.sql.Date(date.getTime());

				PreparedStatement insertDoctorStatement = conn.prepareStatement(INSERT_DOCTOR_QUERY);
				insertDoctorStatement.setString(1, doctorObj.getDoctorEmail().toLowerCase());
				insertDoctorStatement.setString(2, doctorObj.getDoctorFname());
				insertDoctorStatement.setString(3, doctorObj.getDoctorMname());
				insertDoctorStatement.setString(4, doctorObj.getDoctorLname());
				insertDoctorStatement.setDate(5, doctorDob);
				insertDoctorStatement.setString(6, doctorObj.getDoctorGender());
				insertDoctorStatement.setString(7, doctorObj.getDoctorPhone());
				insertDoctorStatement.setString(8, doctorObj.getDoctorApartment());
				insertDoctorStatement.setString(9, doctorObj.getDoctorStreetAddress());
				insertDoctorStatement.setString(10, doctorObj.getDoctorCity());
				insertDoctorStatement.setString(11, doctorObj.getDoctorProvince());
				insertDoctorStatement.setString(12, doctorObj.getDoctorCountry());
				insertDoctorStatement.setString(13, doctorObj.getDoctorPostalCode());
				insertDoctorStatement.setString(14, doctorObj.getDoctorQualification());
				insertDoctorStatement.setString(15, doctorObj.getDoctorSpecialisation());
				insertDoctorStatement.setString(16, doctorObj.getDoctorLicenseId());
				insertDoctorStatement.setString(17, doctorObj.getDoctorConsultationFees());

				insertDoctorStatement.execute();
				insertDoctorStatement.close();

				List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
				authorities.add(new SimpleGrantedAuthority(ROLE_DOCTOR));
				User user = new User(doctorObj.getDoctorEmail(), doctorObj.getDoctorPassword(), authorities);
				jdbcUserDetailsManager.createUser(user);

				PreparedStatement insertUserBankStatement = conn.prepareStatement(INSERT_USER_BANK_ACCOUNT_QUERY);
				insertUserBankStatement.setString(1, doctorObj.getDoctorEmail());
				insertUserBankStatement.setString(2, doctorObj.getDoctorAccountName());
				insertUserBankStatement.setString(3, doctorObj.getDoctorAccountNumber());
				insertUserBankStatement.setString(4, doctorObj.getDoctorBankNumber());
				insertUserBankStatement.setString(5, doctorObj.getDoctorTransitNumber());
				insertUserBankStatement.execute();
				insertUserBankStatement.close();

			} else {
				userExists = true;
			}
			selectUserStatement.close();

		} catch (Exception e) {
			System.out.println(e);

		}
		
		return userExists;
	}
}
