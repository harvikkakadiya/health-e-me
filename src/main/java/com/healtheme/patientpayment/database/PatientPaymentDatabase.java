package com.healtheme.patientpayment.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.healtheme.DatabaseConnectionDAO;

@Component("patientPaymentDatabase")
public class PatientPaymentDatabase implements PatientPaymentDatabaseDAO {
	
	private static final String UPDATE_PATIENT_BALANCE = "UPDATE userbankaccount SET account_balance = account_balance - ? where username = ? ;";
	
	@Override
	public void accountBalance ( DatabaseConnectionDAO databaseConnection , String total) {

		try {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			Connection conn = databaseConnection.getDatabaseInstance();
			PreparedStatement updateAccountBalance = conn.prepareStatement(UPDATE_PATIENT_BALANCE);
			updateAccountBalance.setString(1, total );
			updateAccountBalance.setString(2, auth.getName());
			updateAccountBalance.executeUpdate();
			updateAccountBalance.close();
		}catch (SQLException e) {
			e.printStackTrace();
		} 
	}

}
