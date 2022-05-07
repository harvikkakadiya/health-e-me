package com.healtheme.labprofile.database;

import org.springframework.ui.Model;

import com.healtheme.DatabaseConnectionDAO;
import com.healtheme.labprofile.model.LabProfileModel;

public interface LabProfileDatabaseDAO {

	void showLabProfile(DatabaseConnectionDAO databaseConnection, Model model);

	void saveLabProfile(DatabaseConnectionDAO databaseConnection, LabProfileModel lab);

}