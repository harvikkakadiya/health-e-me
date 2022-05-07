package com.healtheme.patientmycart.database;

import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import com.healtheme.DatabaseConnectionDAO;
import com.healtheme.admininventory.model.AdminDiscountCouponModel;
import com.healtheme.patientmycart.model.PatientOrderBillingDetailModel;
import com.healtheme.patientmycart.model.PatientOrderModel;
import com.healtheme.patientpayment.model.PatientPaymentModel;

@Component("patientMyCartDatabaseDAO")
public interface PatientMyCartDatabaseDAO {

	AdminDiscountCouponModel getCouponName(AdminDiscountCouponModel d, Model model,
			DatabaseConnectionDAO databaseConnection);

	void insertIntoOrders(PatientOrderBillingDetailModel orderdetail, PatientOrderModel po, Model model,
			DatabaseConnectionDAO databaseConnection);

	PatientPaymentModel patientShowAccount(DatabaseConnectionDAO databaseConnection);

}