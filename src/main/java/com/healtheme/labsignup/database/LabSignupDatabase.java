package com.healtheme.labsignup.database;

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
import com.healtheme.labsignup.model.LabSignupModel;

@Component("labSignupDatabase")
public class LabSignupDatabase implements LabSignupDatabaseDAO {

	private static final String SELECT_USER_QUERY = "SELECT username FROM users where username = ? ;";
	private static final String INSERT_LAB_QUERY = "INSERT INTO lab VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
	private static final String INSERT_BANK_ACCOUNT_QUERY = "INSERT INTO userbankaccount (username,account_name,account_no,bank_institution_no,transit_no) VALUES (?,?,?,?,?);";
	private static final String ROLE_LAB = "ROLE_LAB";
	@Override
	public boolean insertData(DatabaseConnectionDAO databaseConnection, LabSignupModel lab, Model model,
			JdbcUserDetailsManager jdbcUserDetailsManager) {
		boolean userExists = false;
		
		try {
			Connection conn = databaseConnection.getDatabaseInstance();
			PreparedStatement selectUserStatement = conn.prepareStatement(SELECT_USER_QUERY);
			selectUserStatement.setString(1, lab.getLabEmail());
			ResultSet result = selectUserStatement.executeQuery();

			if (Boolean.FALSE.equals(result.next())) {
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				Date date = (Date) dateFormat.parse(lab.getLabDoe());
				java.sql.Date labDoe = new java.sql.Date(date.getTime());

				PreparedStatement insertLabStatement = conn.prepareStatement(INSERT_LAB_QUERY);
				insertLabStatement.setString(1, lab.getLabEmail().toLowerCase());
				insertLabStatement.setString(2, lab.getLabName());
				insertLabStatement.setDate(3, labDoe);
				insertLabStatement.setString(4, lab.getLabOwnerFirstName());
				insertLabStatement.setString(5, lab.getLabOwnerMiddleName());
				insertLabStatement.setString(6, lab.getLabOwnerLastName());
				insertLabStatement.setString(7, lab.getLabPhoneNumber());
				insertLabStatement.setString(8, lab.getLabUnit());
				insertLabStatement.setString(9, lab.getLabStreet());
				insertLabStatement.setString(10, lab.getLabCity());
				insertLabStatement.setString(11, lab.getLabProvince());
				insertLabStatement.setString(12, lab.getLabCountry());
				insertLabStatement.setString(13, lab.getLabPostalCode());
				insertLabStatement.setString(14, lab.getLabLicenseId());
				insertLabStatement.setString(15, lab.getLabTestsAvailable());
				insertLabStatement.execute();
				insertLabStatement.close();

				List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
				authorities.add(new SimpleGrantedAuthority(ROLE_LAB));
				User user = new User(lab.getLabEmail(), lab.getLabPassword(), authorities);
				jdbcUserDetailsManager.createUser(user);

				PreparedStatement insertBankStatement = conn.prepareStatement(INSERT_BANK_ACCOUNT_QUERY);
				insertBankStatement.setString(1, lab.getLabEmail());
				insertBankStatement.setString(2, lab.getLabAccountName());
				insertBankStatement.setString(3, lab.getLabAccountNumber());
				insertBankStatement.setString(4, lab.getLabBankInstitutionNumber());
				insertBankStatement.setString(5, lab.getLabTransitNumber());
				insertBankStatement.execute();
				insertBankStatement.close();
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
