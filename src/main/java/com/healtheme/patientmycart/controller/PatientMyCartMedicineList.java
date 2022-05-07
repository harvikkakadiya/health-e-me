package com.healtheme.patientmycart.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.healtheme.DatabaseConnectionDAO;
import com.healtheme.patientordermedicine.model.PatientOrderMedicineModel;

public class PatientMyCartMedicineList {

	private static final String SELECT_ORDER_DETAILS = "select id, medicine_id, email, medicine_name, quantity, price, ischeckedout from orderdetails where ischeckedout = 0 and email = ? ;";

	public ArrayList<PatientOrderMedicineModel> getMedicineDetail(DatabaseConnectionDAO databaseConnection) {

		ArrayList<PatientOrderMedicineModel> cartList = new ArrayList<PatientOrderMedicineModel>();
		try {

			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			Connection conn = databaseConnection.getDatabaseInstance();
			PreparedStatement selectStmt = conn.prepareStatement(SELECT_ORDER_DETAILS);
			selectStmt.setString(1, auth.getName());
			ResultSet resultSet = selectStmt.executeQuery();
			while (resultSet.next()) {
				PatientOrderMedicineModel medicinedata = new PatientOrderMedicineModel();
				medicinedata.setMedicine_id(resultSet.getString("medicine_id"));
				medicinedata.setEmail(resultSet.getString("email"));
				medicinedata.setMedicine_name(resultSet.getString("medicine_name"));
				medicinedata.setQuantity(resultSet.getInt("quantity"));
				medicinedata.setPrice(resultSet.getFloat("price"));
				int q = resultSet.getInt("quantity");
				float p = resultSet.getFloat("price");
				float subtotal = q * p;
				medicinedata.setSubtotal(subtotal);
				cartList.add(medicinedata);
			}
			selectStmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cartList;
	}
}
