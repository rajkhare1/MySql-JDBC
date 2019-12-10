package com.raj.jdbc.storedprocedures;

import java.sql.*;

public class GetCountForDepartment {
	
	public static void main(String[] args) throws SQLException {
		Connection myConn = null;
		CallableStatement myStmt = null;
		
		
		try {
			// Get a connection to database
			myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo","student2","student2");
			System.out.println("Connection Established!!!");
			
			String department = "Engineering";
			
			//prepare the stored procedure call
			myStmt = myConn.prepareCall("{call get_count_for_department(?,?)}");
			
			//set the parameter
			myStmt.setString(1,department);
			myStmt.registerOutParameter(2, Types.INTEGER);
			
			//call the stored procedure
			System.out.println("Calling stored procedure get_count_for_department('"+department+"',?)");
			myStmt.execute();
			System.out.println("Finished calling stored procedure");
			
			//get the value of OUT parameter
			int theCount = myStmt.getInt(2);
			System.out.println("\nThe result = "+theCount);
			
			
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
