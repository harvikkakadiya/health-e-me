package com.healtheme.patientmycart.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.healtheme.DatabaseConnectionDAO;
import com.healtheme.admininventory.model.AdminDiscountCouponModel;
import com.healtheme.patientmycart.database.PatientMyCartDatabaseDAO;
import com.healtheme.patientmycart.model.PatientOrderBillingDetailModel;
import com.healtheme.patientmycart.model.PatientOrderModel;
import com.healtheme.patientmycart.validation.PatientCartValidationDAO;
import com.healtheme.patientordermedicine.model.PatientOrderMedicineModel;
import com.healtheme.patientpayment.model.PatientPaymentModel;

@Controller
public class PatientMyCartController {

	private PatientOrderBillingDetailModel orderdetail;

	@Autowired
	private DatabaseConnectionDAO databaseConnection;

	@Autowired
	private PatientCartValidationDAO patientCartValidationDAO;

	@Autowired
	private PatientMyCartDatabaseDAO patientMyCartDatabaseDAO;

	@GetMapping("/patient-mycart")
	public String welcome(Model model, RedirectAttributes redirectAttribute) {

		try {
			PatientMyCartMedicineList MedicineList = new PatientMyCartMedicineList();
			ArrayList<PatientOrderMedicineModel> cartList = MedicineList.getMedicineDetail(databaseConnection);
			PatientCalculateBill billdetail = new PatientCalculateBill();
			orderdetail = billdetail.calculateSubTotal(databaseConnection);
			orderdetail.setDiscount(0);
			float subtotal = orderdetail.getSubtotalSum();
			float taxvalue = (float) 0.15;
			float tax = taxvalue * subtotal;
			float total = subtotal + tax;
			orderdetail.setTax(tax);
			orderdetail.setTotal(total);
			model.addAttribute("cartList", cartList);
			model.addAttribute("input", new AdminDiscountCouponModel());
			model.addAttribute("orderdetail", orderdetail);
			model.addAttribute("address", new PatientOrderModel());

		} catch (Exception e) {
			System.out.println(e);

		} finally {

		}
		return "./patientmycart/patientmycart";
	}

	@RequestMapping(path = "/coupon", method = RequestMethod.POST)
	public String applyCoupon(@ModelAttribute AdminDiscountCouponModel d, Model model,
			RedirectAttributes redirectAttribute) {

		String errorMessage = "Coupon is not valid";

		try {

			AdminDiscountCouponModel data = patientMyCartDatabaseDAO.getCouponName(d, model, databaseConnection);
			if (d.getCoupon_name().equals(data.getCoupon_name())) {

				PatientMyCartMedicineList MedicineList = new PatientMyCartMedicineList();
				ArrayList<PatientOrderMedicineModel> cartList = MedicineList.getMedicineDetail(databaseConnection);
				model.addAttribute("cartList", cartList);
				PatientCalculateBill billdetail = new PatientCalculateBill();
				orderdetail = billdetail.calculateSubTotal(databaseConnection);
				float sub = orderdetail.getSubtotalSum();
				int dis = data.getCoupon_value();
				float totalafterdis = sub - dis;
				float taxvalue = (float) 0.15;
				float tax = taxvalue * totalafterdis;
				float total = totalafterdis + tax;
				orderdetail.setTax(tax);
				orderdetail.setTotal(total);
				orderdetail.setDiscount(data.getCoupon_value());
				orderdetail.setSubtotalSum(totalafterdis);
				model.addAttribute("orderdetail", orderdetail);
				model.addAttribute("input", new AdminDiscountCouponModel());
				model.addAttribute("address", new PatientOrderModel());

			} else {
				model.addAttribute("error", errorMessage);
				PatientCalculateBill billdetail = new PatientCalculateBill();
				orderdetail = billdetail.calculateSubTotal(databaseConnection);
				orderdetail.setDiscount(0);
				float subtotal = orderdetail.getSubtotalSum();
				float taxvalue = (float) 0.15;
				float tax = taxvalue * subtotal;
				float total = subtotal + tax;
				orderdetail.setTax(tax);
				orderdetail.setTotal(total);
				PatientMyCartMedicineList MedicineList = new PatientMyCartMedicineList();
				ArrayList<PatientOrderMedicineModel> cartList = MedicineList.getMedicineDetail(databaseConnection);
				model.addAttribute("cartList", cartList);
				model.addAttribute("input", new AdminDiscountCouponModel());
				model.addAttribute("address", new PatientOrderModel());
				model.addAttribute("orderdetail", orderdetail);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "./patientmycart/patientmycart";
	}

	@RequestMapping(path = "/payment", method = RequestMethod.POST)
	public String payment(@ModelAttribute PatientOrderMedicineModel p,
			@ModelAttribute PatientOrderMedicineModel medicinedata, @ModelAttribute PatientOrderModel po,
			PatientOrderBillingDetailModel pb, Model model, RedirectAttributes redirectAttribute) {

		String errorMessage = patientCartValidationDAO.checkValidation(po);
		if (errorMessage.isEmpty()) {
			try {

				patientMyCartDatabaseDAO.insertIntoOrders(orderdetail, po, model, databaseConnection);
				model.addAttribute("address", new PatientOrderModel());
				model.addAttribute("orderdetail", orderdetail);
				PatientPaymentModel accountDetail = patientMyCartDatabaseDAO.patientShowAccount(databaseConnection);
				model.addAttribute("accountdetail", accountDetail);
			} catch (Exception e) {
				System.out.println(e);
			}

			return "./patientpayment/patientpayment.html";
		} else {
			PatientMyCartMedicineList MedicineList = new PatientMyCartMedicineList();
			ArrayList<PatientOrderMedicineModel> cartList = MedicineList.getMedicineDetail(databaseConnection);
			model.addAttribute("validationerror", errorMessage);
			model.addAttribute("input", new AdminDiscountCouponModel());
			model.addAttribute("address", new PatientOrderModel());
			model.addAttribute("orderdetail", orderdetail);
			model.addAttribute("cartList", cartList);
			return "./patientmycart/patientmycart.html";
		}
	}

}
