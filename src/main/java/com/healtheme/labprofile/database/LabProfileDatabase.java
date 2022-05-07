package com.healtheme.labprofile.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import com.healtheme.DatabaseConnectionDAO;
import com.healtheme.labprofile.model.LabProfileModel;

@Component("labProfileDatabase")
public class LabProfileDatabase implements LabProfileDatabaseDAO {

	private static final String SELECT_LAB_QUERY = "select l.lab_name, l.lab_doe, l.lab_owner_fname, l.lab_owner_mname, l.lab_owner_lname, l.lab_phone, l.lab_unit, l.lab_street_address, l.lab_city, l.lab_province, l.lab_country, l.lab_postal_code, l.lab_license_id, l.lab_tests_available, u.username, b.account_name, b.account_no, b.bank_institution_no, b.transit_no, b.account_balance from lab l, users u, userbankaccount b where  u.username = l.lab_email and b.username = l.lab_email and l.lab_email = ?;";
	private static final String UPDATE_LAB_QUERY = "Update lab set lab_name = ?, lab_owner_fname = ?, lab_owner_mname = ?, lab_owner_lname = ?, lab_phone = ?, lab_unit = ?, lab_street_address = ?, lab_city = ?, lab_province = ?, lab_country = ?, lab_postal_code = ?, lab_license_id = ?, lab_tests_available = ? where lab_email = ? ;";
	private static final String UPDATE_USER_BANK_QUERY = "UPDATE userbankaccount SET account_name = ?, account_no = ?, bank_institution_no = ?, transit_no = ? WHERE username = ? ;";

	@Override
	public void showLabProfile(DatabaseConnectionDAO databaseConnection, Model model) {
		LabProfileModel lab = new LabProfileModel("", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
				"", "");

		try {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			Connection conn = databaseConnection.getDatabaseInstance();
			PreparedStatement selectLabStatement = conn.prepareStatement(SELECT_LAB_QUERY);
			selectLabStatement.setString(1, auth.getName());
			ResultSet rs = selectLabStatement.executeQuery();
			rs.next();

			String labName = rs.getString("lab_name");
			String labDoe = rs.getString("lab_doe");
			String labOwnerFirstName = rs.getString("lab_owner_fname");
			String labOwnerMiddleName = rs.getString("lab_owner_mname");
			String labOwnerLastName = rs.getString("lab_owner_lname");
			String labPhoneNumber = rs.getString("lab_phone");
			String labUnit = rs.getString("lab_unit");
			String labStreet = rs.getString("lab_street_address");
			String labCity = rs.getString("lab_city");
			String labProvince = rs.getString("lab_province");
			String labCountry = rs.getString("lab_country");
			String labPostalCode = rs.getString("lab_postal_code");
			String labLicenseId = rs.getString("lab_license_id");
			String labTestsAvailable = rs.getString("lab_tests_available");
			String labAccountName = rs.getString("account_name");
			String labAccountNumber = rs.getString("account_no");
			String labBankInstitutionNumber = rs.getString("bank_institution_no");
			String labTransitNumber = rs.getString("transit_no");
			String labAccountBalance = rs.getString("account_balance") + " CAD";

			lab = new LabProfileModel(labName, labDoe, labOwnerFirstName, labOwnerMiddleName, labOwnerLastName,
					labPhoneNumber, labUnit, labStreet, labCity, labProvince, labCountry, labPostalCode, labLicenseId,
					labTestsAvailable, labAccountName, labAccountNumber, labBankInstitutionNumber, labTransitNumber,
					labAccountBalance);
			model.addAttribute("lab", lab);
			
			selectLabStatement.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void saveLabProfile(DatabaseConnectionDAO databaseConnection, LabProfileModel lab) {
		try {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			Connection conn = databaseConnection.getDatabaseInstance();

			PreparedStatement updateLabStatement = conn.prepareStatement(UPDATE_LAB_QUERY);
			updateLabStatement.setString(1, lab.getLabName());
			updateLabStatement.setString(2, lab.getLabOwnerFirstName());
			updateLabStatement.setString(3, lab.getLabOwnerMiddleName());
			updateLabStatement.setString(4, lab.getLabOwnerLastName());
			updateLabStatement.setString(5, lab.getLabPhoneNumber());
			updateLabStatement.setString(6, lab.getLabUnit());
			updateLabStatement.setString(7, lab.getLabStreet());
			updateLabStatement.setString(8, lab.getLabCity());
			updateLabStatement.setString(9, lab.getLabProvince());
			updateLabStatement.setString(10, lab.getLabCountry());
			updateLabStatement.setString(11, lab.getLabPostalCode());
			updateLabStatement.setString(12, lab.getLabLicenseId());
			updateLabStatement.setString(13, lab.getLabTestsAvailable());
			updateLabStatement.setString(14, auth.getName());
			updateLabStatement.executeUpdate();
			updateLabStatement.close();

			PreparedStatement updateUserBankStatement = conn.prepareStatement(UPDATE_USER_BANK_QUERY);
			updateUserBankStatement.setString(1, lab.getLabAccountName());
			updateUserBankStatement.setString(2, lab.getLabAccountNumber());
			updateUserBankStatement.setString(3, lab.getLabBankInstitutionNumber());
			updateUserBankStatement.setString(4, lab.getLabTransitNumber());
			updateUserBankStatement.setString(5, auth.getName());
			updateUserBankStatement.executeUpdate();
			updateUserBankStatement.close();
		}
		catch (Exception e) {
			System.out.println(e);
		}
	}
}
