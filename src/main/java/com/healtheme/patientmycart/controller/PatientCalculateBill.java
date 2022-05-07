package com.healtheme.patientmycart.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.healtheme.DatabaseConnectionDAO;
import com.healtheme.patientmycart.model.PatientOrderBillingDetailModel;
import com.healtheme.patientordermedicine.model.PatientOrderMedicineModel;

public class PatientCalculateBill {

	private static final String SELECT_ORDER_DETAILS = "select id, medicine_id, email, medicine_name, quantity, price, ischeckedout from orderdetails where ischeckedout = 0 and email = ? ;";

	public PatientOrderBillingDetailModel calculateSubTotal(DatabaseConnectionDAO databaseConnection) {

		PatientOrderBillingDetailModel orderDetail = new PatientOrderBillingDetailModel();
		List<PatientOrderMedicineModel> medList = new ArrayList<PatientOrderMedicineModel>();
		try {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			Connection conn = databaseConnection.getDatabaseInstance();
			float subtotal = 0;
			PreparedStatement selectStmt = conn.prepareStatement(SELECT_ORDER_DETAILS);
			selectStmt.setString(1, auth.getName());
			ResultSet resultSet = selectStmt.executeQuery();
			while (resultSet.next()) {
				PatientOrderMedicineModel med = new PatientOrderMedicineModel();
				med.setQuantity(resultSet.getInt("quantity"));
				med.setPrice(resultSet.getFloat("price"));
				int q = resultSet.getInt("quantity");
				float p = resultSet.getFloat("price");
				float singlesubtotal = q * p;
				subtotal = singlesubtotal + subtotal;
				medList.add(med);
			}
			orderDetail.setSubtotalSum(subtotal);
			selectStmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return orderDetail;
	}

}
