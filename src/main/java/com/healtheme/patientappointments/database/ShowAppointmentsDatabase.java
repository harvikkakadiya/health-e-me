package com.healtheme.patientappointments.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.healtheme.DatabaseConnectionDAO;
import com.healtheme.patientappointments.model.PatientAppointmentsDoctorModel;
import com.healtheme.patientappointments.model.PatientAppointmentsLabModel;

@Component("showAppointmentsDatabase")
public class ShowAppointmentsDatabase implements ShowAppointmentsDatabaseDAO {

	private static final String SELECT_DOCTOR_APPOINTMENT_QUERY = "SELECT a.appointment_id, a.appointment_date, a.appointment_time, a.consultation_fee, d.doctor_email, d.doctor_fname, d.doctor_mname, d.doctor_lname, d.doctor_specialisation, d.doctor_apartment,\r\n"
			+ "d.doctor_street_address, d.doctor_city, d.doctor_province, d.doctor_country, d.doctor_postal_code, d.doctor_phone FROM doctorappointment a, doctor d\r\n"
			+ "WHERE a.doctor_email = d.doctor_email AND a.appointment_date >= SYSDATE() AND a.patient_email = ? ;";

	private static final String SELECT_LAB_APPOINTMENT_QUERY = "SELECT a.appointment_id, a.appointment_date, a.appointment_time, a.lab_test_type, a.lab_test_fee, l.lab_email, l.lab_name, l.lab_phone, l.lab_unit, l.lab_street_address,\r\n"
			+ "l.lab_city, l.lab_province, l.lab_country, l.lab_postal_code FROM labappointment a, lab l\r\n"
			+ "where a.lab_email = l.lab_email AND a.appointment_date >= SYSDATE() AND a.patient_email = ? ;";

	@Override
	public ArrayList<PatientAppointmentsDoctorModel> showDoctorAppointments(DatabaseConnectionDAO databaseConnection) {

		ArrayList<PatientAppointmentsDoctorModel> doctorAppointments = new ArrayList<PatientAppointmentsDoctorModel>();

		try {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			Connection connection = databaseConnection.getDatabaseInstance();

			PreparedStatement selectDoctorAppointmentQuery = connection.prepareStatement(SELECT_DOCTOR_APPOINTMENT_QUERY);
			selectDoctorAppointmentQuery.setString(1, auth.getName());
			ResultSet result = selectDoctorAppointmentQuery.executeQuery();

			while (result.next()) {

				String appointmentId = result.getString("appointment_id");
				String appointmentDate = result.getString("appointment_date");
				String appointmentTime = result.getString("appointment_time");
				String consultationFee = result.getString("consultation_fee");
				String doctorEmail = result.getString("doctor_email");
				String doctorName = "Dr. " + result.getString("doctor_fname") + " " + result.getString("doctor_mname") + " "
						+ result.getString("doctor_lname");
				String doctorSpecialisation = result.getString("doctor_specialisation");
				String doctorAddress = result.getString("doctor_apartment") + ", " + result.getString("doctor_street_address")
						+ ", " + result.getString("doctor_city") + ", " + result.getString("doctor_province") + ", "
						+ result.getString("doctor_country") + " - " + result.getString("doctor_postal_code");
				String doctorPhone = "+1-" + result.getString("doctor_phone");

				PatientAppointmentsDoctorModel doctorAppointment = new PatientAppointmentsDoctorModel(appointmentId,
						appointmentDate, appointmentTime, consultationFee, doctorEmail, doctorName,
						doctorSpecialisation, doctorAddress, doctorPhone);
				doctorAppointments.add(doctorAppointment);
			}
			
			selectDoctorAppointmentQuery.close();
			
		} catch (SQLException e) {
			System.out.println(e);
		}

		return doctorAppointments;
	}

	@Override
	public ArrayList<PatientAppointmentsLabModel> showLabAppointments(DatabaseConnectionDAO databaseConnection) {

		ArrayList<PatientAppointmentsLabModel> labAppointments = new ArrayList<PatientAppointmentsLabModel>();

		try {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			Connection connection = databaseConnection.getDatabaseInstance();

			PreparedStatement selectLabAppointmentQuery = connection.prepareStatement(SELECT_LAB_APPOINTMENT_QUERY);
			selectLabAppointmentQuery.setString(1, auth.getName());
			ResultSet result = selectLabAppointmentQuery.executeQuery();

			while (result.next()) {

				String appointmentId = result.getString("appointment_id");
				String appointmentDate = result.getString("appointment_date");
				String appointmentTime = result.getString("appointment_time");
				String labTestType = result.getString("lab_test_type");
				String labTestFee = result.getString("lab_test_fee");
				String labEmail = result.getString("lab_email");
				String labName = result.getString("lab_name");
				String labPhone = "+1-" + result.getString("lab_phone");
				String labAddress = result.getString("lab_unit") + ", " + result.getString("lab_street_address") + ", "
						+ result.getString("lab_city") + ", " + result.getString("lab_province") + ", "
						+ result.getString("lab_country") + " - " + result.getString("lab_postal_code");

				PatientAppointmentsLabModel labAppointment = new PatientAppointmentsLabModel(appointmentId,
						appointmentDate, appointmentTime, labTestType, labTestFee, labEmail, labName, labAddress,
						labPhone);

				labAppointments.add(labAppointment);
			}
			
			selectLabAppointmentQuery.close();
			
		} catch (SQLException e) {
			System.out.println(e);
		}

		return labAppointments;
	}
}
