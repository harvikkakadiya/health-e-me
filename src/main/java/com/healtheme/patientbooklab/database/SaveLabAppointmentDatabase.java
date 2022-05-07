package com.healtheme.patientbooklab.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Component;

import com.healtheme.DatabaseConnectionDAO;

@Component("saveLabAppointmentDatabase")
public class SaveLabAppointmentDatabase implements SaveLabAppointmentDatabaseDAO {

	private static final String CHECK_PATIENT_ACCOUNT_BALANCE_QUERY = "SELECT account_balance FROM userbankaccount WHERE username = ?";

	private static final String INSERT_LAB_APPOINTMENT_QUERY = "INSERT INTO labappointment (patient_email,lab_email,appointment_date,appointment_time,lab_test_type,lab_test_fee) VALUES (?,?,?,?,?,?) ;";

	private static final String UPDATE_PATIENT_ACCOUNT_BALANCE_QUERY = "UPDATE userbankaccount SET account_balance = account_balance - ? where username = ? ;";

	private static final String UPDATE_LAB_ACCOUNT_BALANCE_QUERY = "UPDATE userbankaccount SET account_balance = account_balance + ? where username = ? ;";

	@Override
	public boolean checkPatientAccountBalance(DatabaseConnectionDAO databaseConnection, String patientLabTestFee,
			String patientEmail) {

		try {
			Connection connection = databaseConnection.getDatabaseInstance();
			PreparedStatement checkPatientAccountBalanceQuery = connection.prepareStatement(CHECK_PATIENT_ACCOUNT_BALANCE_QUERY);
			checkPatientAccountBalanceQuery.setString(1, patientEmail);
			ResultSet result = checkPatientAccountBalanceQuery.executeQuery();
			result.next();
			String accountBalance = result.getString("account_balance");
			checkPatientAccountBalanceQuery.close();
			if (Double.valueOf(accountBalance) >= Double.valueOf(patientLabTestFee)) {
				
				return true;
			}
			
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return false;
	}

	@Override
	public boolean insertLabAppointment(DatabaseConnectionDAO databaseConnection, String patientEmail,
			String selectedLabEmail, String patientLabTestDate, String patientLabTestTime, String patientLabTestType,
			String patientLabTestFee) {

		try {
			Connection connection = databaseConnection.getDatabaseInstance();
			PreparedStatement insertLabAppointmentQuery = connection.prepareStatement(INSERT_LAB_APPOINTMENT_QUERY);
			insertLabAppointmentQuery.setString(1, patientEmail);
			insertLabAppointmentQuery.setString(2, selectedLabEmail);
			insertLabAppointmentQuery.setString(3, patientLabTestDate);
			insertLabAppointmentQuery.setString(4, patientLabTestTime);
			insertLabAppointmentQuery.setString(5, patientLabTestType);
			insertLabAppointmentQuery.setString(6, patientLabTestFee);
			insertLabAppointmentQuery.executeUpdate();
			insertLabAppointmentQuery.close();

			return true;
			
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return false;
	}

	@Override
	public boolean updatePatientAccountBalance(DatabaseConnectionDAO databaseConnection, String patientLabTestFee,
			String patientEmail) {

		try {
			Connection connection = databaseConnection.getDatabaseInstance();
			PreparedStatement updatePatientAccountBalanceQuery = connection.prepareStatement(UPDATE_PATIENT_ACCOUNT_BALANCE_QUERY);
			updatePatientAccountBalanceQuery.setString(1, patientLabTestFee);
			updatePatientAccountBalanceQuery.setString(2, patientEmail);
			updatePatientAccountBalanceQuery.executeUpdate();
			updatePatientAccountBalanceQuery.close();

			return true;
			
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return false;
	}

	@Override
	public boolean updateLabAccountBalance(DatabaseConnectionDAO databaseConnection, String patientLabTestFee,
			String selectedLabEmail) {

		try {
			Connection connection = databaseConnection.getDatabaseInstance();
			PreparedStatement updateLabAccountBalanceQuery = connection.prepareStatement(UPDATE_LAB_ACCOUNT_BALANCE_QUERY);
			updateLabAccountBalanceQuery.setString(1, patientLabTestFee);
			updateLabAccountBalanceQuery.setString(2, selectedLabEmail);
			updateLabAccountBalanceQuery.executeUpdate();
			updateLabAccountBalanceQuery.close();

			return true;
			
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return false;
	}
}
