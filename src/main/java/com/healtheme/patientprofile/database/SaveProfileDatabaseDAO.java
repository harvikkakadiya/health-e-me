package com.healtheme.patientprofile.database;

import com.healtheme.DatabaseConnectionDAO;
import com.healtheme.patientprofile.model.PatientProfileModel;

public interface SaveProfileDatabaseDAO {

	void saveProfile(DatabaseConnectionDAO databaseConnection, PatientProfileModel patient);
}