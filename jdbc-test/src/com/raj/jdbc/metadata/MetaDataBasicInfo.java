package com.raj.jdbc.metadata;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MetaDataBasicInfo {

	public static void main(String[] args) throws SQLException {
		Connection myConn = null;
		
		try {
			//1. get a connection  to the database
			myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo","student2","student2");
			System.out.println("Connection established!");
			
			//2. get the metadata
			DatabaseMetaData databaseMetaData = myConn.getMetaData();
			
			//3. Display info about database
			System.out.println("ProductName: "+databaseMetaData.getDatabaseProductName());
			System.out.println("ProductVersion: "+databaseMetaData.getDatabaseProductVersion());
			System.out.println();
			
			//4. Display info about JDBC driver
			System.out.println("JDBC Driver name: "+databaseMetaData.getDriverName());
			System.out.println("JDBC Driver version: "+databaseMetaData.getDriverVersion());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(myConn != null) {
				myConn.close();
			}
		}
	}
}
