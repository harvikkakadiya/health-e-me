package com.healtheme.adminorder.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.healtheme.DatabaseConnectionDAO;
import com.healtheme.adminorder.database.AdminOrderDatabaseDAO;
import com.healtheme.adminorder.model.AdminOrderModel;

@Controller
public class AdminOrderController {

	@Autowired
	private DatabaseConnectionDAO databaseConnection;

	@Autowired
	private AdminOrderDatabaseDAO adminOrderDatabase;

	@GetMapping("/admin-order")
	public String welcome(Model model) {
		try {
			ArrayList<AdminOrderModel> orderList = adminOrderDatabase.getOrderDetail(databaseConnection);
			model.addAttribute("orderList", orderList);
		} catch (Exception e) {
			System.out.println(e);

		}
		return "./adminorder/adminorder";
	}

}
