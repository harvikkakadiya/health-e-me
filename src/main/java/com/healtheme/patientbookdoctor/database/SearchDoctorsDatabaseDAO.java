package com.healtheme.patientbookdoctor.database;

import java.util.HashMap;

import com.healtheme.DatabaseConnectionDAO;
import com.healtheme.patientbookdoctor.model.PatientBookDoctorModel;

public interface SearchDoctorsDatabaseDAO {

	HashMap<String, PatientBookDoctorModel> searchDoctors(DatabaseConnectionDAO databaseConnection,
			String searchDoctorLocation, String searchDoctorSpecialist);
}