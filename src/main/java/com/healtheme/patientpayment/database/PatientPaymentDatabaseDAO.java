package com.healtheme.patientpayment.database;

import com.healtheme.DatabaseConnectionDAO;

public interface PatientPaymentDatabaseDAO {

	void accountBalance(DatabaseConnectionDAO databaseConnection, String total);

}