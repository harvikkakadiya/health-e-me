package com.healtheme.patientordermedicine.database;

import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.ui.Model;

import com.healtheme.DatabaseConnectionDAO;
import com.healtheme.admininventory.model.AdminInventoryModel;
import com.healtheme.patientordermedicine.model.PatientOrderMedicineModel;

public interface PatientOrderMedicineDatabaseDAO {

	ArrayList<AdminInventoryModel> getMedicineDetail(DatabaseConnectionDAO databaseConnection);

	boolean getMedicineDetailByID(String medicineid, int medQunatity, DatabaseConnectionDAO databaseConnection)
			throws SQLException;

	void insertOrderDetail(PatientOrderMedicineModel p, String medicineid, String mname, Float mprice,
			DatabaseConnectionDAO databaseConnection, Model model);

}