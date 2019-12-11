package com.raj.jdbc.metadata;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SchemaInfo {

	@SuppressWarnings("resource")
	public static void main(String[] args) throws SQLException {
		String catalog = null;
		String schemaPattern = null;
		String tableNamePattern = null;
		String columnNamePattern = null;
		String[] types = null;
		
		Connection myConn = null;
		ResultSet myRs = null;
		
		try {
			//1. Get a connection to database
			myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo","student2","student2");
			System.out.println("Connection established!");
			
			//2. Get metadata
			DatabaseMetaData databaseMeataData = myConn.getMetaData();
			
			//3. Get list of tables
			System.out.println("List of Tables");
			System.out.println("----------------------");
			
			myRs = databaseMeataData.getTables(catalog, schemaPattern, tableNamePattern, types);
			
			while(myRs.next()) {
				System.out.println(myRs.getString("TABLE_NAME"));
			}
			
			//4. Get the list of columns
			System.out.println("\n\nList of Columns");
			System.out.println("-------------------------");
			
			myRs = databaseMeataData.getColumns(catalog, schemaPattern, "employees", columnNamePattern);
			
			while(myRs.next()) {
				System.out.println(myRs.getString("COLUMN_NAME"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(myConn != null) {
				myConn.close();
			}
			if(myRs != null) {
				myRs.close();
			}
		}
		
	}
}
