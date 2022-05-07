package com.healtheme.admininventory.database;

import java.util.ArrayList;

import org.springframework.ui.Model;

import com.healtheme.DatabaseConnectionDAO;
import com.healtheme.admininventory.model.AdminInventoryModel;

public interface AdminInventoryDatabaseDAO {

	ArrayList<AdminInventoryModel> getMedicineDetail(DatabaseConnectionDAO databaseConnection);

	void addNewMedicine(AdminInventoryModel i, Model model, DatabaseConnectionDAO databaseConnection);

	AdminInventoryModel showMedDetailfromID(AdminInventoryModel i, Model model,
			DatabaseConnectionDAO databaseConnection, String id);

	void updateMedicine(AdminInventoryModel i, Model model, DatabaseConnectionDAO databaseConnection);

	void deleteMedicine(AdminInventoryModel i, Model model, DatabaseConnectionDAO databaseConnection, String id);

}