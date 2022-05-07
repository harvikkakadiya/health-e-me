package com.healtheme.adminorder.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.springframework.stereotype.Component;

import com.healtheme.DatabaseConnectionDAO;
import com.healtheme.adminorder.model.AdminOrderDetailModel;
import com.healtheme.adminorder.model.AdminOrderModel;

@Component("adminOrderDatabase")
public class AdminOrderDatabase implements AdminOrderDatabaseDAO {

	private static final String SELECT_PATIENT_ORDER = "SELECT orderid, email, subtotal, discount, tax, total, street_address, city, province, postal_code from orders;";

	private static final String SELECT_PATIENT_ORDER_FROM_ID = "SELECT orderid, email,	medicine_id, medicine_name, quantity from orderitems where orderid = ?;";

	@Override
	public ArrayList<AdminOrderModel> getOrderDetail(DatabaseConnectionDAO databaseConnection) {

		ArrayList<AdminOrderModel> orderList = new ArrayList<AdminOrderModel>();
		try {

			Connection conn = databaseConnection.getDatabaseInstance();
			Statement listStmt = conn.createStatement();
			ResultSet resultSet = listStmt.executeQuery(SELECT_PATIENT_ORDER);
			while (resultSet.next()) {
				AdminOrderModel data = new AdminOrderModel();
				data.setOrderid(resultSet.getString("orderid"));
				data.setEmail(resultSet.getString("email"));
				data.setSubtotal(resultSet.getFloat("subtotal"));
				data.setDiscount(resultSet.getInt("discount"));
				data.setTax(resultSet.getFloat("tax"));
				data.setTotal(resultSet.getFloat("total"));
				data.setStreet_address(resultSet.getString("street_address"));
				data.setCity(resultSet.getString("city"));
				data.setProvince(resultSet.getString("province"));
				data.setPostal_code(resultSet.getString("postal_code"));
				orderList.add(data);
			}

			listStmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return orderList;
	}

	@Override
	public ArrayList<AdminOrderDetailModel> showOrderItems(DatabaseConnectionDAO databaseConnection, String orderid) {

		ArrayList<AdminOrderDetailModel> orderDetailList = new ArrayList<AdminOrderDetailModel>();
		try {
			Connection conn = databaseConnection.getDatabaseInstance();
			PreparedStatement selectStmt = conn.prepareStatement(SELECT_PATIENT_ORDER_FROM_ID);
			selectStmt.setString(1, orderid);
			ResultSet resultSet = selectStmt.executeQuery();
			while (resultSet.next()) {
				AdminOrderDetailModel data = new AdminOrderDetailModel();
				data.setOrderid(resultSet.getString("orderid"));
				data.setMedicine_name(resultSet.getString("medicine_name"));
				data.setQuantity(resultSet.getInt("quantity"));
				orderDetailList.add(data);
			}
			selectStmt.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return orderDetailList;
	}

}
