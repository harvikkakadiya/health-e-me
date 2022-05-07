package com.healtheme.patientappointments.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.stereotype.Component;

import com.healtheme.DatabaseConnectionDAO;

@Component("deleteLabAppointmentDatabase")
public class DeleteLabAppointmentDatabase implements DeleteLabAppointmentDatabaseDAO {

	private static final String DELETE_LAB_APPOINTMENT_QUERY = "DELETE FROM labappointment WHERE appointment_id = ?";

	private static final String UPDATE_PATIENT_ACCOUNT_BALANCE_QUERY = "UPDATE userbankaccount SET account_balance = account_balance + ? where username = ? ;";

	private static final String UPDATE_LAB_ACCOUNT_BALANCE_QUERY = "UPDATE userbankaccount SET account_balance = account_balance - ? where username = ? ;";

	@Override
	public boolean deleteLabAppointment(DatabaseConnectionDAO databaseConnection, String appointmentId) {

		try {

			Connection connection = databaseConnection.getDatabaseInstance();
			PreparedStatement deleteLabAppointmentQuery = connection.prepareStatement(DELETE_LAB_APPOINTMENT_QUERY);
			deleteLabAppointmentQuery.setString(1, appointmentId);
			deleteLabAppointmentQuery.executeUpdate();
			deleteLabAppointmentQuery.close();

			return true;
			
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return false;
	}

	@Override
	public boolean updatePatientAccountBalance(DatabaseConnectionDAO databaseConnection, String labTestFee,
			String patientEmail) {

		try {
			Connection connection = databaseConnection.getDatabaseInstance();
			PreparedStatement updatePatientAccountBalanceQuery = connection.prepareStatement(UPDATE_PATIENT_ACCOUNT_BALANCE_QUERY);
			updatePatientAccountBalanceQuery.setString(1, labTestFee);
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
	public boolean updateLabAccountBalance(DatabaseConnectionDAO databaseConnection, String labTestFee,
			String labEmail) {

		try {
			Connection connection = databaseConnection.getDatabaseInstance();
			PreparedStatement updateLabAccountBalanceQuery = connection.prepareStatement(UPDATE_LAB_ACCOUNT_BALANCE_QUERY);
			updateLabAccountBalanceQuery.setString(1, labTestFee);
			updateLabAccountBalanceQuery.setString(2, labEmail);
			updateLabAccountBalanceQuery.executeUpdate();
			updateLabAccountBalanceQuery.close();

			return true;
			
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return false;
	}
}
