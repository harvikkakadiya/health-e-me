package com.healtheme.doctorviewappointments.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import com.healtheme.DatabaseConnectionDAO;
import com.healtheme.doctorviewappointments.model.DoctorViewAppointmentsModel;

@Component("doctorViewAppointments")
public class DoctorViewAppointmentsDatabase implements DoctorViewAppointmentsDatabaseDAO {
	private static boolean successFlag = false;

	private static final String SELECT_DOCTOR_APPOINTMENT_QUERY = "SELECT p.patient_fname, p.patient_mname, p.patient_lname,p.patient_phone_no,p.patient_apartment_no, p.patient_street, p.patient_city, p.patient_province, p.patient_country, p.patient_postal_code, d.appointment_id, d.appointment_date, d.appointment_time FROM patient p, doctorappointment d WHERE d.patient_email = p.patient_email AND d.appointment_date >= SYSDATE() AND d.doctor_email = ?;";
	private static final String DELETE_DOCTOR_APPOINTMENT_QUERY = "DELETE FROM doctorappointment WHERE appointment_id = ? ;";
	private static final String SELECT_APPOINTMENT_DETAILS_QUERY = "SELECT da.patient_email,da.doctor_email,da.consultation_fee FROM doctorappointment da WHERE appointment_id = ? ;";
	private static final String UPDATE_PATIENT_ACCOUNT_BALANCE = "UPDATE userbankaccount SET account_balance = account_balance + ? where username = ? ;";
	private static final String UPDATE_DOCTOR_ACCOUNT_BALANCE = "UPDATE userbankaccount SET account_balance = account_balance - ? where username = ? ;";

	@Override
	public void showAppointments(DatabaseConnectionDAO databaseConnection, Model model) {

		try {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			Connection conn = databaseConnection.getDatabaseInstance();

			ArrayList<DoctorViewAppointmentsModel> doctorAppointments = new ArrayList<DoctorViewAppointmentsModel>();

			PreparedStatement selectDoctorStatement = conn.prepareStatement(SELECT_DOCTOR_APPOINTMENT_QUERY);
			selectDoctorStatement.setString(1, auth.getName());
			ResultSet result = selectDoctorStatement.executeQuery();

			while (result.next()) {
				String appointmentId = result.getString("appointment_id");
				String patientName = result.getString("patient_fname") + " " + result.getString("patient_mname") + " "
						+ result.getString("patient_lname");
				String patientPhone = "+1-" + result.getString("patient_phone_no");
				String patientAddress = result.getString("patient_apartment_no") + ", " + result.getString("patient_street")
						+ ", " + result.getString("patient_city") + ", " + result.getString("patient_province") + ", "
						+ result.getString("patient_country") + " - " + result.getString("patient_postal_code");
				String appointmentDate = result.getString("appointment_date");
				String appointmentTime = result.getString("appointment_time");

				DoctorViewAppointmentsModel doctorAppointment = new DoctorViewAppointmentsModel(appointmentId,
						patientName, patientPhone, patientAddress, appointmentDate, appointmentTime);
				
				doctorAppointments.add(doctorAppointment);
			}

			model.addAttribute("doctorAppointments", doctorAppointments);
			selectDoctorStatement.close();

			if (successFlag) {
				model.addAttribute("success", 1);
				successFlag = false;
			}
		}
		catch (Exception e) {
			System.out.println(e);
		}
	}

	@Override
	public void deleteAppointments(DatabaseConnectionDAO databaseConnection, String appointmentId, Model model) {
		try {
			Connection conn = databaseConnection.getDatabaseInstance();

			String patientEmail = "";
			String doctorEmail = "";
			String consultationFee = "";
			PreparedStatement selectAppointmentStatement = conn.prepareStatement(SELECT_APPOINTMENT_DETAILS_QUERY);
			selectAppointmentStatement.setString(1, appointmentId);
			ResultSet result = selectAppointmentStatement.executeQuery();
			while (result.next()) {
				patientEmail = result.getString("patient_email");
				doctorEmail = result.getString("doctor_email");
				consultationFee = result.getString("consultation_fee");
			}

			PreparedStatement updatePatientBalanceStatement = conn.prepareStatement(UPDATE_PATIENT_ACCOUNT_BALANCE);
			updatePatientBalanceStatement.setString(1, consultationFee);
			updatePatientBalanceStatement.setString(2, patientEmail);
			updatePatientBalanceStatement.executeUpdate();

			PreparedStatement updateDoctorBalanceStatement = conn.prepareStatement(UPDATE_DOCTOR_ACCOUNT_BALANCE);
			updateDoctorBalanceStatement.setString(1, consultationFee);
			updateDoctorBalanceStatement.setString(2, doctorEmail);
			updateDoctorBalanceStatement.executeUpdate();

			PreparedStatement deleteAppointmentStatement = conn.prepareStatement(DELETE_DOCTOR_APPOINTMENT_QUERY);
			deleteAppointmentStatement.setString(1, appointmentId);
			deleteAppointmentStatement.executeUpdate();
			
			deleteAppointmentStatement.close();
			deleteAppointmentStatement.close();
			deleteAppointmentStatement.close();
			deleteAppointmentStatement.close();
			
			successFlag = true;
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}
}
