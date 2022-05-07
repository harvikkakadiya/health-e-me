package com.healtheme.adminstatistics.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.healtheme.DatabaseConnectionDAO;
import com.healtheme.adminstatistics.database.AdminStatisticsDatabaseDAO;
import com.healtheme.adminstatistics.model.AdminStatisticsModel;

@Controller

public class AdminStatisticsController {

	@Autowired
	private DatabaseConnectionDAO databaseConnection;

	@Autowired
	private AdminStatisticsDatabaseDAO adminStatisticsDatabaseDAO;

	@GetMapping("/admin-statistics")
	public String welcome( Model model) {	

		try
		{	
			ArrayList<AdminStatisticsModel> patientStat = adminStatisticsDatabaseDAO.showPatientStat(databaseConnection);
			model.addAttribute("patientStat", patientStat);
			model.addAttribute("patientcount", patientStat.size());
		}
		catch(Exception e)
		{ 
			System.out.println(e);
		} 

		try
		{	
			ArrayList<AdminStatisticsModel> doctorStat = adminStatisticsDatabaseDAO.showDoctorStat(databaseConnection);
			model.addAttribute("doctorStat", doctorStat);
			model.addAttribute("doctorcount", doctorStat.size());

		}
		catch(Exception e)
		{ 
			System.out.println(e);

		} 


		try 
		{	
			ArrayList<AdminStatisticsModel> labStat = adminStatisticsDatabaseDAO.showLabStat(databaseConnection);
			model.addAttribute("labStat", labStat);
			model.addAttribute("labcount", labStat.size());
		}

		catch(Exception e)
		{ 
			System.out.println(e);

		} 		

		return "./adminstatistics/adminstatistics";
	}
}
