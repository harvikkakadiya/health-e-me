package com.healtheme.adminstatistics.database;

import java.util.ArrayList;

import com.healtheme.DatabaseConnectionDAO;
import com.healtheme.adminstatistics.model.AdminStatisticsModel;

public interface AdminStatisticsDatabaseDAO {

	ArrayList<AdminStatisticsModel> showPatientStat(DatabaseConnectionDAO databaseConnection);

	ArrayList<AdminStatisticsModel> showDoctorStat(DatabaseConnectionDAO databaseConnection);

	ArrayList<AdminStatisticsModel> showLabStat(DatabaseConnectionDAO databaseConnection);

}