package com.healtheme.doctorcreateprescription.database;

import org.springframework.ui.Model;

import com.healtheme.DatabaseConnectionDAO;
import com.healtheme.doctorcreateprescription.model.DoctorCreatePrescriptionModel;

public interface DoctorCreatePrescriptionDatabaseDAO {
	
	public void insertData(DatabaseConnectionDAO databaseConnection, DoctorCreatePrescriptionModel prescriptionObj,Model model);
}
