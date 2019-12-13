package com.raj.jdbc.propertiesfiles;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

public class ConnectionDemo {
	
	public static void main(String[] args) {
		 
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		
		try {
			//1. Load the properties file
			Properties props = new Properties();
			props.load(new FileInputStream("demo.properties"));
			
			//2. Read the props
			String theDburl = props.getProperty("dburl");
			String theUser = props.getProperty("user");
			String thePassword = props.getProperty("password");
			
			System.out.println();
			
			//3. Get 
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
		}
	}

}
