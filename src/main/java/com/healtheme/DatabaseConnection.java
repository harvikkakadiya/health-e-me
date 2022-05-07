package com.healtheme;

import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.Connection;

@Component("databaseConnection")
public class DatabaseConnection implements DatabaseConnectionDAO{

	@Value("${spring.datasource.url}")
	private String databaseURL;

	@Value("${spring.datasource.username}")
	private String databaseUsername;

	@Value("${spring.datasource.password}")
	private String databasePassword;

	private static Connection conn = null;
	
	@Override
	public Connection getDatabaseInstance()
	{

		if (conn == null) {
			try {
				conn=DriverManager.getConnection(databaseURL,databaseUsername,databasePassword);  
			}
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
		}
		return conn;
	}
}
