package com.healtheme.admininventory.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;	
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.healtheme.DatabaseConnectionDAO;
import com.healtheme.admininventory.model.AdminInventoryModel;

@Component("adminInventoryDatabaseDAO")
public class AdminInventoryDatabase implements AdminInventoryDatabaseDAO {

	private static final String SELECT_MEDICINE_DETAIL = "SELECT medicine_id, medicine_name, medicine_brand_name, medicine_price, medicine_quantity from inventory;";

	private static final String INSERT_MEDICINE_DETAIL = "INSERT into inventory (medicine_id, medicine_name, medicine_brand_name, medicine_price, medicine_quantity) values(?,?,?,?,?);";

	private static final String SELECT_MEDICINE_DETAIL_FROM_ID = "SELECT medicine_id, medicine_name, medicine_brand_name, medicine_price, medicine_quantity from inventory where medicine_id = ?;";

	private static final String UPDATE_MEDICINE_DETAIL_FROM_ID = "UPDATE inventory SET medicine_name = ? , medicine_brand_name = ? , medicine_price =  ? , medicine_quantity = ? WHERE medicine_id= ?; ";

	private static final String DELETE_MEDICINE_DETAIL_FROM_ID = "DELETE from inventory where medicine_id = ?  ;";

	@Override
	public ArrayList<AdminInventoryModel> getMedicineDetail(DatabaseConnectionDAO databaseConnection) {

		ArrayList<AdminInventoryModel> medicineList = new ArrayList<AdminInventoryModel>();
		try {

			Connection conn = databaseConnection.getDatabaseInstance();
			Statement listStmt;
			listStmt = conn.createStatement();
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
		} catch (Exception e) {
			System.out.println(e);
		}
		return medicineList;
	}

	@Override
	public void addNewMedicine(@ModelAttribute AdminInventoryModel i, Model model,
			DatabaseConnectionDAO databaseConnection) {

		try {
			Connection conn = databaseConnection.getDatabaseInstance();
			String id = UUID.randomUUID().toString();
			id = id.substring(0, id.indexOf("-"));
			PreparedStatement addNewMedicineStatement = conn.prepareStatement(INSERT_MEDICINE_DETAIL);
			addNewMedicineStatement.setString(1, "m" + id);
			addNewMedicineStatement.setString(2, i.getMedicine_name());
			addNewMedicineStatement.setString(3, i.getMedicine_brand_name());
			addNewMedicineStatement.setFloat(4, i.getMedicine_price());
			addNewMedicineStatement.setInt(5, i.getMedicine_quantity());
			addNewMedicineStatement.executeUpdate();
			addNewMedicineStatement.close();

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	@Override
	public AdminInventoryModel showMedDetailfromID(@ModelAttribute AdminInventoryModel i, Model model,
			DatabaseConnectionDAO databaseConnection, String id) {

		AdminInventoryModel data = new AdminInventoryModel();
		try {
			Connection conn = databaseConnection.getDatabaseInstance();
			PreparedStatement selectStmt = conn.prepareStatement(SELECT_MEDICINE_DETAIL_FROM_ID);
			selectStmt.setString(1, id);
			ResultSet resultSet = selectStmt.executeQuery();
			while (resultSet.next()) {
				data.setMedicine_id(resultSet.getString("medicine_id"));
				data.setMedicine_name(resultSet.getString("medicine_name"));
				data.setMedicine_brand_name(resultSet.getString("medicine_brand_name"));
				data.setMedicine_price(resultSet.getFloat("medicine_price"));
				data.setMedicine_quantity(resultSet.getInt("medicine_quantity"));
			}
			selectStmt.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return data;
	}

	@Override
	public void updateMedicine(@ModelAttribute AdminInventoryModel i, Model model,
			DatabaseConnectionDAO databaseConnection) {

		try {
			Connection conn = databaseConnection.getDatabaseInstance();
			PreparedStatement updateStmt = conn.prepareStatement(UPDATE_MEDICINE_DETAIL_FROM_ID);
			updateStmt.setString(1, i.getMedicine_name());
			updateStmt.setString(2, i.getMedicine_brand_name());
			updateStmt.setFloat(3, i.getMedicine_price());
			updateStmt.setInt(4, i.getMedicine_quantity());
			updateStmt.setString(5, i.getMedicine_id());
			updateStmt.executeUpdate();
			updateStmt.close();

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	@Override
	public void deleteMedicine(@ModelAttribute AdminInventoryModel i, Model model,
			DatabaseConnectionDAO databaseConnection, String id) {

		try {
			Connection conn = databaseConnection.getDatabaseInstance();
			PreparedStatement deleteStmt = conn.prepareStatement(DELETE_MEDICINE_DETAIL_FROM_ID);
			deleteStmt.setString(1, id);
			deleteStmt.executeUpdate();
			deleteStmt.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
