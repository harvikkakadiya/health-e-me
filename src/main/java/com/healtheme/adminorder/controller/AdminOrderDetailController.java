package com.healtheme.adminorder.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.healtheme.DatabaseConnectionDAO;
import com.healtheme.adminorder.database.AdminOrderDatabaseDAO;
import com.healtheme.adminorder.model.AdminOrderDetailModel;

@Controller
public class AdminOrderDetailController {

	@Autowired
	private DatabaseConnectionDAO databaseConnection;

	@Autowired
	private AdminOrderDatabaseDAO adminOrderDatabase;

	@RequestMapping(path = "/show-orderDetail/{id}", method = RequestMethod.GET)
	public String showOrderDetailfromID(@PathVariable(value = "id") String orderid, Model model) {

		try {
			ArrayList<AdminOrderDetailModel> orderDetailList = adminOrderDatabase.showOrderItems(databaseConnection,
					orderid);
			model.addAttribute("orderDetailList", orderDetailList);
		} catch (Exception e) {
			System.out.println(e);
		}

		return "./adminorder/adminorderdetails";
	}

}
