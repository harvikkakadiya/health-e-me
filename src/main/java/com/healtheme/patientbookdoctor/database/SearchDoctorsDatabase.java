package com.healtheme.patientbookdoctor.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import org.springframework.stereotype.Component;

import com.healtheme.DatabaseConnectionDAO;
import com.healtheme.patientbookdoctor.model.PatientBookDoctorModel;

@Component("searchDoctorsDatabse")
public class SearchDoctorsDatabase implements SearchDoctorsDatabaseDAO {

	private static final String SELECT_DOCTOR_QUERY = "SELECT doctor_email,doctor_fname,doctor_mname,doctor_lname,doctor_phone,doctor_apartment,doctor_street_address,doctor_city,doctor_province,doctor_country,doctor_postal_code,doctor_specialisation,doctor_consultation_fees FROM doctor WHERE LOWER(doctor_city) LIKE ? AND doctor_specialisation LIKE ? ;";

	@Override
	public HashMap<String, PatientBookDoctorModel> searchDoctors(DatabaseConnectionDAO databaseConnection,
			String searchDoctorLocation, String searchDoctorSpecialist) {

		HashMap<String, PatientBookDoctorModel> doctors = new HashMap<>();

		try {
			Connection connection = databaseConnection.getDatabaseInstance();
			PreparedStatement selectDoctorQuery = connection.prepareStatement(SELECT_DOCTOR_QUERY);
			selectDoctorQuery.setString(1, '%' + searchDoctorLocation.toLowerCase() + '%');
			selectDoctorQuery.setString(2, '%' + searchDoctorSpecialist.toLowerCase() + '%');
			ResultSet result = selectDoctorQuery.executeQuery();

			while (result.next()) {

				String doctorName = "Dr. " + result.getString("doctor_fname") + " " + result.getString("doctor_mname") + " "
						+ result.getString("doctor_lname");
				String doctorSpecialisation = result.getString("doctor_specialisation");
				String doctorPhone = "+1-" + result.getString("doctor_phone");
				String doctorConsultationFee = result.getString("doctor_consultation_fees") + " CAD";
				String doctorAddress = result.getString("doctor_apartment") + ", " + result.getString("doctor_street_address")
						+ ", " + result.getString("doctor_city") + ", " + result.getString("doctor_province") + ", "
						+ result.getString("doctor_country") + " - " + result.getString("doctor_postal_code");
				String doctorEmail = result.getString("doctor_email");

				PatientBookDoctorModel doctor = new PatientBookDoctorModel(doctorName, doctorSpecialisation,
						doctorPhone, doctorConsultationFee, doctorAddress, doctorEmail);
				doctors.put(doctorEmail, doctor);
			}

			selectDoctorQuery.close();
		}

		catch (SQLException e) {
			System.out.println(e);
		}

		return doctors;
	}
}
