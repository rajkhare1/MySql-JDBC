package com.raj.jdbc.metadata;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class ResultSetDemo {
	
	public static void main(String[] args) throws SQLException {
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			//1. Get a connection to database
			myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo","student2","student2");
			System.out.println("Connection established!");
			
			//2. Run query
			myStmt = myConn.createStatement();
			myRs = myStmt.executeQuery("select id, last_name, first_name, salary from employees");
			
			//3. Get result set metadata
			ResultSetMetaData myRsMetaData = myRs.getMetaData();
			
			//4. Display info
			int columnCount = myRsMetaData.getColumnCount();
			System.out.println("Column count: "+columnCount);
			
			for(int column = 1; column < columnCount; column++) {
				System.out.println("Column name: "+myRsMetaData.getColumnName(column));
				System.out.println("Column type name: "+myRsMetaData.getColumnTypeName(column));
				System.out.println("Is Nullable: "+myRsMetaData.isNullable(column));
				System.out.println("Is Auto Incremented: "+myRsMetaData.isAutoIncrement(column));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(myConn != null) {
				myConn.close();
			}
			
			if(myStmt != null) {
				myStmt.close();
			}
			
			if(myRs != null) {
				myRs.close();
			}
		}
				
	}

}
