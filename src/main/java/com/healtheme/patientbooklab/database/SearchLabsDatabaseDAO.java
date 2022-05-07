package com.healtheme.patientbooklab.database;

import java.util.HashMap;

import com.healtheme.DatabaseConnectionDAO;
import com.healtheme.patientbooklab.model.PatientBookLabModel;

public interface SearchLabsDatabaseDAO {

	HashMap<String, PatientBookLabModel> searchLabs(DatabaseConnectionDAO databaseConnection, String searchLabLocation,
			String searchLabName);
}