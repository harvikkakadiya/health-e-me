package com.healtheme.labappointments.database;

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
import com.healtheme.labappointments.model.LabViewAppointmentModel;

@Component("labAppointmentsDatabase")
public class LabAppointmentsDatabase implements LabAppointmentsDatabaseDAO {

	private static boolean successFlag = false;
	private static final String SELECT_LAB_APPOINTMENT_QUERY = "SELECT p.patient_fname, p.patient_mname, p.patient_lname,p.patient_phone_no,p.patient_apartment_no, p.patient_street, p.patient_city, p.patient_province, p.patient_country, p.patient_postal_code, l.appointment_id, l.appointment_date, l.appointment_time, l.lab_test_type FROM patient p, labappointment l WHERE l.patient_email = p.patient_email AND l.appointment_date >= SYSDATE() AND l.lab_email = ?;";
	private static final String DELETE_LAB_APPOINTMENT_QUERY = "DELETE FROM labappointment WHERE appointment_id = ?";
	private static final String SELECT_LAB_APPOINTMENT_DETAILS_QUERY = "SELECT la.patient_email, la.lab_email, la.lab_test_fee FROM labappointment la WHERE appointment_id = ? ;";
	private static final String UPDATE_PATIENT_ACCOUNT_BALANCE = "UPDATE userbankaccount SET account_balance = account_balance + ? where username = ? ;";
	private static final String UPDATE_LAB_ACCOUNT_BALANCE = "UPDATE userbankaccount SET account_balance = account_balance - ? where username = ? ;";

	@Override
	public void showLabAppointments(DatabaseConnectionDAO databaseConnection, Model model) {
		try {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			Connection conn = databaseConnection.getDatabaseInstance();

			ArrayList<LabViewAppointmentModel> labAppointments = new ArrayList<LabViewAppointmentModel>();

			PreparedStatement selectLabAppointmentStatement = conn.prepareStatement(SELECT_LAB_APPOINTMENT_QUERY);
			selectLabAppointmentStatement.setString(1, auth.getName());
			ResultSet result = selectLabAppointmentStatement.executeQuery();

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
				String labTestName = result.getString("lab_test_type");

				LabViewAppointmentModel labAppointment = new LabViewAppointmentModel(appointmentId, patientName,
						patientPhone, patientAddress, appointmentDate, appointmentTime, labTestName);
				labAppointments.add(labAppointment);
			}

			model.addAttribute("labAppointments", labAppointments);
			
			selectLabAppointmentStatement.close();

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
	public void deleteLabAppointment(DatabaseConnectionDAO databaseConnection, String appointmentId, Model model) {
		try {
			Connection conn = databaseConnection.getDatabaseInstance();

			String patientEmail = "";
			String labEmail = "";
			String labTestFee = "";
			PreparedStatement selectLabAppointmentStatement = conn.prepareStatement(SELECT_LAB_APPOINTMENT_DETAILS_QUERY);
			selectLabAppointmentStatement.setString(1, appointmentId);
			ResultSet rs1 = selectLabAppointmentStatement.executeQuery();
			while (rs1.next()) {
				patientEmail = rs1.getString("patient_email");
				labEmail = rs1.getString("lab_email");
				labTestFee = rs1.getString("lab_test_fee");
			}

			PreparedStatement updatePatientBalanceStatement = conn.prepareStatement(UPDATE_PATIENT_ACCOUNT_BALANCE);
			updatePatientBalanceStatement.setString(1, labTestFee);
			updatePatientBalanceStatement.setString(2, patientEmail);
			updatePatientBalanceStatement.executeUpdate();

			PreparedStatement updateLabAccountBalance = conn.prepareStatement(UPDATE_LAB_ACCOUNT_BALANCE);
			updateLabAccountBalance.setString(1, labTestFee);
			updateLabAccountBalance.setString(2, labEmail);
			updateLabAccountBalance.executeUpdate();

			PreparedStatement deleteLabAppointmentStatement = conn.prepareStatement(DELETE_LAB_APPOINTMENT_QUERY);
			deleteLabAppointmentStatement.setString(1, appointmentId);
			deleteLabAppointmentStatement.executeUpdate();
			
			deleteLabAppointmentStatement.close();
			selectLabAppointmentStatement.close();
			updatePatientBalanceStatement.close();
			updateLabAccountBalance.close();

			successFlag = true;
			
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}
}
