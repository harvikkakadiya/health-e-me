package com.healtheme.patientbooklab.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import org.springframework.stereotype.Component;

import com.healtheme.DatabaseConnectionDAO;
import com.healtheme.patientbooklab.model.PatientBookLabModel;

@Component("searchLabsDatabase")
public class SearchLabsDatabase implements SearchLabsDatabaseDAO {

	private static final String SELECT_LAB_QUERY = "SELECT lab_email,lab_name,lab_phone,lab_unit,lab_street_address,lab_city,lab_province,lab_country,lab_postal_code,lab_tests_available FROM lab WHERE LOWER(lab_city) LIKE ? AND lab_name LIKE ? ;";

	@Override
	public HashMap<String, PatientBookLabModel> searchLabs(DatabaseConnectionDAO databaseConnection,
			String searchLabLocation, String searchLabName) {

		HashMap<String, PatientBookLabModel> labs = new HashMap<>();

		try {

			Connection connection = databaseConnection.getDatabaseInstance();
			PreparedStatement selectLabQuery = connection.prepareStatement(SELECT_LAB_QUERY);
			selectLabQuery.setString(1, '%' + searchLabLocation.toLowerCase() + '%');
			selectLabQuery.setString(2, '%' + searchLabName.toLowerCase() + '%');
			ResultSet result = selectLabQuery.executeQuery();

			while (result.next()) {

				String labName = result.getString("lab_name");
				String labPhone = "+1-" + result.getString("lab_phone");
				String labAddress = result.getString("lab_unit") + ", " + result.getString("lab_street_address") + ", "
						+ result.getString("lab_city") + ", " + result.getString("lab_province") + ", "
						+ result.getString("lab_country") + " - " + result.getString("lab_postal_code");
				String labEmail = result.getString("lab_email");
				String labTests = result.getString("lab_tests_available");

				PatientBookLabModel lab = new PatientBookLabModel(labName, labPhone, labAddress, labTests, labEmail);
				labs.put(labEmail, lab);
			}

			selectLabQuery.close();
			
		} catch (SQLException e) {
			System.out.println(e);
		}

		return labs;
	}
}
