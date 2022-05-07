package com.healtheme.doctorcreateprescription.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.healtheme.doctorcreateprescription.model.DoctorCreatePrescriptionModel;

@Controller
public class ShowPrescriptionFormController {
	
	@GetMapping("/doctor-create-prescriptions")
	public String showPrescriptionForm(Model model) {
		DoctorCreatePrescriptionModel prescription = new DoctorCreatePrescriptionModel("", "", "", "", "", "", "");
		model.addAttribute("prescription", prescription);
		
		return "./doctorcreateprescription/doctorcreateprescription";
	}

}
