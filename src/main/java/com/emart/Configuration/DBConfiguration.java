package com.emart.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;

import com.emart.constants.ApplicationConstants;

public class DBConfiguration {

	public static Connection getDBConnection()
	{
		
		Connection conn = null;
		
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			//Class.forName("com.mysql.cj.jdbc.Driver");
			
			 conn = DriverManager.getConnection(ApplicationConstants.DB_URL, ApplicationConstants.DB_USERNAME, ApplicationConstants.DB_PASSWORD);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return conn;
		
	}
}
