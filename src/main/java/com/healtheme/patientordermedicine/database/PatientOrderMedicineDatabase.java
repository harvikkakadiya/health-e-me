package com.healtheme.patientordermedicine.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.UUID;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.healtheme.DatabaseConnectionDAO;
import com.healtheme.admininventory.model.AdminInventoryModel;
import com.healtheme.patientordermedicine.model.PatientOrderMedicineModel;

@Component("patientOrderMedicineDatabase")
public class PatientOrderMedicineDatabase implements PatientOrderMedicineDatabaseDAO {

	private static final String SELECT_MEDICINE_DETAIL = "SELECT medicine_id, medicine_name, medicine_brand_name, medicine_price, medicine_quantity from inventory;";

	private static final String SELECT_MEDICINE_DETAIL_FROM_ID = "SELECT medicine_id, medicine_name, medicine_brand_name, medicine_price, medicine_quantity from inventory where medicine_id = ?;";

	private static final String INSERT_ORDER_DETAILS = "Insert into orderdetails (id,medicine_id ,email,medicine_name,quantity,price,ischeckedout) values(?,?,?,?,?,?,?);";

	@Override
	public ArrayList<AdminInventoryModel> getMedicineDetail(DatabaseConnectionDAO databaseConnection) {

		ArrayList<AdminInventoryModel> medicineList = new ArrayList<AdminInventoryModel>();
		try {

			Connection conn = databaseConnection.getDatabaseInstance();
			Statement listStmt = conn.createStatement();
			ResultSet resultSet = listStmt.executeQuery(SELECT_MEDICINE_DETAIL);
			while (resultSet.next()) {
				AdminInventoryModel data = new AdminInventoryModel();
				data.setMedicine_id(resultSet.getString("medicine_id"));
				data.setMedicine_name(resultSet.getString("medicine_name"));
				data.setMedicine_brand_name(resultSet.getString("medicine_brand_name"));
				data.setMedicine_price(resultSet.getFloat("medicine_price"));
				data.setMedicine_quantity(resultSet.getInt("medicine_quantity"));
				medicineList.add(data);
			}

			listStmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

		}
		return medicineList;
	}

	@Override
	public boolean getMedicineDetailByID(String medicineid, int medQunatity, DatabaseConnectionDAO databaseConnection)
			throws SQLException {

		boolean outOfStock = false;
		Connection conn = databaseConnection.getDatabaseInstance();
		PreparedStatement selectStmt = conn.prepareStatement(SELECT_MEDICINE_DETAIL_FROM_ID);
		selectStmt.setString(1, medicineid);
		ResultSet resultSet = selectStmt.executeQuery();
		AdminInventoryModel data = new AdminInventoryModel();
		while (resultSet.next()) {
			data.setMedicine_name(resultSet.getString("medicine_name"));
			data.setMedicine_price(resultSet.getFloat("medicine_price"));
			data.setMedicine_quantity(resultSet.getInt("medicine_quantity"));
		}
		if (!(data.getMedicine_quantity() >= medQunatity)) {
			outOfStock = true;
		}

		return outOfStock;
	}

	@Override
	public void insertOrderDetail(@ModelAttribute PatientOrderMedicineModel p, String medicineid, String mname,
			Float mprice, DatabaseConnectionDAO databaseConnection, Model model) {

		try {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			Connection conn = databaseConnection.getDatabaseInstance();
			String cid = UUID.randomUUID().toString();
			cid = cid.substring(0, cid.indexOf("-"));
			PreparedStatement insertStmt = conn.prepareStatement(INSERT_ORDER_DETAILS);
			insertStmt.setString(1, "c" + cid);
			insertStmt.setString(2, medicineid);
			insertStmt.setString(3, auth.getName());
			insertStmt.setString(4, mname);
			insertStmt.setInt(5, p.getQuantity());
			insertStmt.setFloat(6, mprice);
			insertStmt.setInt(7, 0);
			insertStmt.executeUpdate();
			insertStmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
