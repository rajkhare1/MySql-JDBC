package com.raj.jdbc.propertiesfiles;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class ConnectionDemo {
	
	public static void main(String[] args) throws SQLException {
		 
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
			
			System.out.println("Connection to databae...");
			System.out.println("Dabase URL: "+theDburl);
			System.out.println("User: "+theUser);
			
			
			//3. Get connection to database
				myConn =  DriverManager.getConnection(theDburl, theUser, thePassword);
				System.out.println("Connection established!");
			
			//3. Create Statement
				myStmt = myConn.createStatement();
				
			//4. Execute sql query
				myRs = myStmt.executeQuery("select * from employees");
				
			//Process the ResultSet
				while(myRs.next()) {
					System.out.println(myRs.getInt("id")+" | "+myRs.getString("last_name")+" | "+myRs.getString("first_name")+" | "+myRs.getString("email")+
							" | "+myRs.getString("department")+" | "+myRs.getDouble("salary"));
				}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(myConn !=null) {
				myConn.close();
			}
			
			if(myStmt !=null) {
				myStmt.close();
			}
			
			if(myRs !=null) {
				myRs.close();
			}
		}
	}

}
