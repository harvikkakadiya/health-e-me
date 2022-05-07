package com.healtheme;

import java.sql.Connection;

public interface DatabaseConnectionDAO {
	
	public Connection getDatabaseInstance();
	
}
