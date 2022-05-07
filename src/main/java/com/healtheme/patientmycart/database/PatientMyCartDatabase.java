package com.healtheme.patientmycart.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.healtheme.DatabaseConnectionDAO;
import com.healtheme.admininventory.model.AdminDiscountCouponModel;
import com.healtheme.patientmycart.controller.PatientMyCartMedicineList;
import com.healtheme.patientmycart.model.PatientOrderBillingDetailModel;
import com.healtheme.patientmycart.model.PatientOrderModel;
import com.healtheme.patientordermedicine.model.PatientOrderMedicineModel;
import com.healtheme.patientpayment.model.PatientPaymentModel;

@Component("patientMyCartDatabaseDAO")
public class PatientMyCartDatabase implements PatientMyCartDatabaseDAO {

	private static final String SELECT_DISCOUNT_COUPON = "SELECT couponid, coupon_name, coupon_value from discountcoupon where coupon_name = ? ;";

	private static final String INSERT_ORDER = "INSERT into orders (orderid, email,	subtotal, discount, tax, total,	street_address, city, province,	postal_code) values(?,?,?,?,?,?,?,?,?,?);";

	private static final String INSERT_ORDER_ITEMS = "Insert into orderitems (orderid, email,	medicine_id , medicine_name, quantity) values(?,?,?,?,?);";

	private static final String UPDATE_INVENTORY = "UPDATE inventory SET medicine_quantity = medicine_quantity - ? where medicine_id = ? ;";

	private static final String UPDATE_ORDER_DETAIL = "UPDATE orderdetails SET ischeckedout = 1 WHERE email = ? ;";

	private static final String SELECT_USER_BANK_ACCOUNT = "SELECT username, account_name, account_no, bank_institution_no, transit_no, account_balance from userbankaccount where username = ? ;";

	@Override
	public AdminDiscountCouponModel getCouponName(AdminDiscountCouponModel d, Model model,
			DatabaseConnectionDAO databaseConnection) {
		AdminDiscountCouponModel data = new AdminDiscountCouponModel();
		try {
			Connection conn = databaseConnection.getDatabaseInstance();
			PreparedStatement selectStmt = conn.prepareStatement(SELECT_DISCOUNT_COUPON);
			selectStmt.setString(1, d.getCoupon_name());
			ResultSet resultSet = selectStmt.executeQuery();
			while (resultSet.next()) {
				data.setCouponid(resultSet.getString("couponid"));
				data.setCoupon_name(resultSet.getString("coupon_name"));
				data.setCoupon_value(resultSet.getInt("coupon_value"));
			}
			selectStmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return data;
	}

	@Override
	public void insertIntoOrders(PatientOrderBillingDetailModel orderdetail, @ModelAttribute PatientOrderModel po,
			Model model, DatabaseConnectionDAO databaseConnection) {
		try {

			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			Connection conn = databaseConnection.getDatabaseInstance();
			String oid = UUID.randomUUID().toString();
			oid = oid.substring(0, oid.indexOf("-"));
			PreparedStatement insertStmt = conn.prepareStatement(INSERT_ORDER);
			insertStmt.setString(1, "o" + oid);
			insertStmt.setString(2, auth.getName());
			insertStmt.setFloat(3, orderdetail.getSubtotalSum());
			insertStmt.setFloat(4, orderdetail.getDiscount());
			insertStmt.setFloat(5, orderdetail.getTax());
			insertStmt.setFloat(6, orderdetail.getTotal());
			insertStmt.setString(7, po.getStreet_address());
			insertStmt.setString(8, po.getCity());
			insertStmt.setString(9, po.getProvince());
			insertStmt.setString(10, po.getPostal_code());
			insertStmt.executeUpdate();
			insertStmt.close();

			PatientMyCartMedicineList MedicineList = new PatientMyCartMedicineList();
			ArrayList<PatientOrderMedicineModel> cartList = MedicineList.getMedicineDetail(databaseConnection);

			for (int i = 0; i < cartList.size(); i++) {

				PreparedStatement insertitems = conn.prepareStatement(INSERT_ORDER_ITEMS);
				insertitems.setString(1, "o" + oid);
				insertitems.setString(2, cartList.get(i).getEmail());
				insertitems.setString(3, cartList.get(i).getMedicine_id());
				insertitems.setString(4, cartList.get(i).getMedicine_name());
				insertitems.setInt(5, cartList.get(i).getQuantity());
				insertitems.executeUpdate();
				insertitems.close();

				PreparedStatement updateQuantity = conn.prepareStatement(UPDATE_INVENTORY);
				updateQuantity.setInt(1, cartList.get(i).getQuantity());
				updateQuantity.setString(2, cartList.get(i).getMedicine_id());
				updateQuantity.executeUpdate();
				updateQuantity.close();
			}

			PreparedStatement preparedUpdate = conn.prepareStatement(UPDATE_ORDER_DETAIL);
			preparedUpdate.setString(1, auth.getName());
			preparedUpdate.executeUpdate();
			preparedUpdate.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public PatientPaymentModel patientShowAccount(DatabaseConnectionDAO databaseConnection) {

		PatientPaymentModel accountDetail = new PatientPaymentModel();
		try {

			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			Connection conn = databaseConnection.getDatabaseInstance();
			PreparedStatement preparedSelect = conn.prepareStatement(SELECT_USER_BANK_ACCOUNT);
			preparedSelect.setString(1, auth.getName());
			ResultSet resultSet = preparedSelect.executeQuery();
			while (resultSet.next()) {
				accountDetail.setAccount_name(resultSet.getString("account_name"));
				accountDetail.setAccount_no(resultSet.getString("account_no"));
				accountDetail.setBank_institution_no(resultSet.getString("bank_institution_no"));
				accountDetail.setTransit_no(resultSet.getString("transit_no"));
			}
			preparedSelect.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return accountDetail;
	}

}
