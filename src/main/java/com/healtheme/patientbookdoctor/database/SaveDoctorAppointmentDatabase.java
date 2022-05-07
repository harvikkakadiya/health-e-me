package com.healtheme.patientbookdoctor.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Component;

import com.healtheme.DatabaseConnectionDAO;

@Component("saveDoctorAppointmentDatabase")
public class SaveDoctorAppointmentDatabase implements SaveDoctorAppointmentDatabaseDAO {

	private static final String CHECK_PATIENT_ACCOUNT_BALANCE_QUERY = "SELECT account_balance FROM userbankaccount WHERE username = ?";

	private static final String INSERT_DOCTOR_APPOINTMENT_QUERY = "INSERT INTO doctorappointment (patient_email,doctor_email,appointment_date,appointment_time,consultation_fee) VALUES (?,?,?,?,?) ;";

	private static final String UPDATE_PATIENT_ACCOUNT_BALANCE_QUERY = "UPDATE userbankaccount SET account_balance = account_balance - ? where username = ? ;";

	private static final String UPDATE_DOCTOR_ACCOUNT_BALANCE_QUERY = "UPDATE userbankaccount SET account_balance = account_balance + ? where username = ? ;";

	@Override
	public boolean checkPatientAccountBalance(DatabaseConnectionDAO databaseConnection,
			String selectedDoctorConsultationFee, String patientEmail) {

		try {
			Connection connection = databaseConnection.getDatabaseInstance();
			PreparedStatement checkPatientAccountBalanceQuery = connection.prepareStatement(CHECK_PATIENT_ACCOUNT_BALANCE_QUERY);
			checkPatientAccountBalanceQuery.setString(1, patientEmail);
			ResultSet result = checkPatientAccountBalanceQuery.executeQuery();
			result.next();
			String accountBalance = result.getString("account_balance");
			checkPatientAccountBalanceQuery.close();
			if (Double.valueOf(accountBalance) >= Double.valueOf(selectedDoctorConsultationFee)) {
				return true;
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return false;
	}

	@Override
	public boolean insertDoctorAppointment(DatabaseConnectionDAO databaseConnection, String patientAppointmentDate,
			String patientAppointmentTime, String selectedDoctorEmail, String patientEmail,
			String selectedDoctorConsultationFee) {

		try {
			Connection connection = databaseConnection.getDatabaseInstance();
			PreparedStatement insertDoctorAppointmentQuery = connection.prepareStatement(INSERT_DOCTOR_APPOINTMENT_QUERY);
			insertDoctorAppointmentQuery.setString(1, patientEmail);
			insertDoctorAppointmentQuery.setString(2, selectedDoctorEmail);
			insertDoctorAppointmentQuery.setString(3, patientAppointmentDate);
			insertDoctorAppointmentQuery.setString(4, patientAppointmentTime);
			insertDoctorAppointmentQuery.setString(5, selectedDoctorConsultationFee);
			insertDoctorAppointmentQuery.executeUpdate();
			insertDoctorAppointmentQuery.close();

			return true;
			
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return false;
	}

	@Override
	public boolean updatePatientAccountBalance(DatabaseConnectionDAO databaseConnection,
			String selectedDoctorConsultationFee, String patientEmail) {

		try {
			Connection connection = databaseConnection.getDatabaseInstance();
			PreparedStatement updatePatientAccountBalanceQuery = connection.prepareStatement(UPDATE_PATIENT_ACCOUNT_BALANCE_QUERY);
			updatePatientAccountBalanceQuery.setString(1, selectedDoctorConsultationFee);
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
	public boolean updateDoctorAccountBalance(DatabaseConnectionDAO databaseConnection,
			String selectedDoctorConsultationFee, String selectedDoctorEmail) {

		try {
			Connection connection = databaseConnection.getDatabaseInstance();
			PreparedStatement updateDoctorAccountBalanceQuery = connection.prepareStatement(UPDATE_DOCTOR_ACCOUNT_BALANCE_QUERY);
			updateDoctorAccountBalanceQuery.setString(1, selectedDoctorConsultationFee);
			updateDoctorAccountBalanceQuery.setString(2, selectedDoctorEmail);
			updateDoctorAccountBalanceQuery.executeUpdate();
			updateDoctorAccountBalanceQuery.close();

			return true;
			
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return false;
	}
}
