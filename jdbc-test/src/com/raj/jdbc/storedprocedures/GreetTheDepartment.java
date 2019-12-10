package com.raj.jdbc.storedprocedures;

import java.sql.*;

public class GreetTheDepartment {
	
	public static void main(String[] args) throws SQLException {
		Connection myConn = null;
		CallableStatement myStmt = null;
		
		
		try {
			// Get a connection to database
			myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo","student2","student2");
			System.out.println("Connection Established!!!");
			
			String department = "Engineering";
			
			//prepare the stored procedure call
			myStmt = myConn.prepareCall("{call greet_the_department(?)}");
			
			//set the parameter
			myStmt.registerOutParameter(1, Types.VARCHAR);
			myStmt.setString(1,department);
			
			//call the stored procedure
			System.out.println("Calling stored procedure greet_the_department('"+department+"')");
			myStmt.execute();
			System.out.println("Finished calling stored procedure");
			
			//get the value of INOUT parameter
			String theResult = myStmt.getString(1);
			System.out.println("\nThe result = "+theResult);
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(myConn !=null) {
				myConn.close();
			}
			if(myStmt !=null) {
				myStmt.close();
			}
		}
	}

}
