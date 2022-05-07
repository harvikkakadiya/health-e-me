package com.healtheme.adminorder.database;

import java.util.ArrayList;

import com.healtheme.DatabaseConnectionDAO;
import com.healtheme.adminorder.model.AdminOrderDetailModel;
import com.healtheme.adminorder.model.AdminOrderModel;

public interface AdminOrderDatabaseDAO {

	ArrayList<AdminOrderModel> getOrderDetail(DatabaseConnectionDAO databaseConnection);

	ArrayList<AdminOrderDetailModel> showOrderItems(DatabaseConnectionDAO databaseConnection, String orderid);

}