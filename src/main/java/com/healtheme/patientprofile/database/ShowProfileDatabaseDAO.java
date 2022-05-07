package com.healtheme.patientprofile.database;

import com.healtheme.DatabaseConnectionDAO;
import com.healtheme.patientprofile.model.PatientProfileModel;

public interface ShowProfileDatabaseDAO {

	PatientProfileModel showProfile(DatabaseConnectionDAO databaseConnection);
}