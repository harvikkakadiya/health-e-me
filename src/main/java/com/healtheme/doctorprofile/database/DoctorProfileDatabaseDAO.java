package com.healtheme.doctorprofile.database;

import org.springframework.ui.Model;

import com.healtheme.DatabaseConnectionDAO;
import com.healtheme.doctorprofile.model.DoctorProfileModel;

public interface DoctorProfileDatabaseDAO {
	public void showDoctorProfile(DatabaseConnectionDAO databaseConnection, Model model);

	public void saveDoctorProfile(DatabaseConnectionDAO databaseConnection, DoctorProfileModel doctorObj, Model model);
}
