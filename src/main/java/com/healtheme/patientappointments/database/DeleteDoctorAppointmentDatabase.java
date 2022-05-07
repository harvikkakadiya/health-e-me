package com.healtheme.patientappointments.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.stereotype.Component;

import com.healtheme.DatabaseConnectionDAO;

@Component("deleteDoctorAppointmentDatabase")
public class DeleteDoctorAppointmentDatabase implements DeleteDoctorAppointmentDatabaseDAO {

	private static final String DELETE_DOCTOR_APPOINTMENT_QUERY = "DELETE FROM doctorappointment WHERE appointment_id = ?";

	private static final String UPDATE_PATIENT_ACCOUNT_BALANCE_QUERY = "UPDATE userbankaccount SET account_balance = account_balance + ? where username = ? ;";

	private static final String UPDATE_DOCTOR_ACCOUNT_BALANCE_QUERY = "UPDATE userbankaccount SET account_balance = account_balance - ? where username = ? ;";

	@Override
	public boolean deleteDoctorAppointment(DatabaseConnectionDAO databaseConnection, String appointmentId) {

		try {
			Connection connection = databaseConnection.getDatabaseInstance();
			PreparedStatement deleteDoctorAppointmentQuery = connection.prepareStatement(DELETE_DOCTOR_APPOINTMENT_QUERY);
			deleteDoctorAppointmentQuery.setString(1, appointmentId);
			deleteDoctorAppointmentQuery.executeUpdate();
			deleteDoctorAppointmentQuery.close();

			return true;
			
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return false;
	}

	@Override
	public boolean updatePatientAccountBalance(DatabaseConnectionDAO databaseConnection, String doctorConsultationFee,
			String patientEmail) {

		try {
			Connection connection = databaseConnection.getDatabaseInstance();
			PreparedStatement updatePatientAccountBalanceQuery = connection.prepareStatement(UPDATE_PATIENT_ACCOUNT_BALANCE_QUERY);
			updatePatientAccountBalanceQuery.setString(1, doctorConsultationFee);
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
	public boolean updateDoctorAccountBalance(DatabaseConnectionDAO databaseConnection, String doctorConsultationFee,
			String doctorEmail) {

		try {
			Connection connection = databaseConnection.getDatabaseInstance();
			PreparedStatement updateDoctorAccountBalanceQuery = connection.prepareStatement(UPDATE_DOCTOR_ACCOUNT_BALANCE_QUERY);
			updateDoctorAccountBalanceQuery.setString(1, doctorConsultationFee);
			updateDoctorAccountBalanceQuery.setString(2, doctorEmail);
			updateDoctorAccountBalanceQuery.executeUpdate();
			updateDoctorAccountBalanceQuery.close();

			return true;
			
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return false;
	}
}
