package com.raj.jdbc.storedprocedures;

import java.sql.*;

public class GetEmployeesForDepartment {

	public static void main(String[] args) throws SQLException {
		Connection myConn = null;
		CallableStatement myStmt = null;
		ResultSet myRs = null;

		try {
			// Get a connection to database
			myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "student2", "student2");
			System.out.println("Connection Established!!!");

			String department = "Engineering";

			// prepare the stored procedure call
			myStmt = myConn.prepareCall("{call get_employees_for_department(?)}");

			// set the parameter
			myStmt.setString(1, department);

			// call the stored procedure
			System.out.println("Calling stored procedure get_employees_for_department('" + department + "')");
			myStmt.execute();
			System.out.println("Finished calling stored procedure.\n");

			// get the result set
			myRs = myStmt.getResultSet();

			// display the result set
			display(myRs);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(myConn, myStmt, myRs);
		}
	}

	private static void close(Connection myConn, CallableStatement myStmt, ResultSet myRs) throws SQLException {
		if (myConn != null) {
			myConn.close();
		}

		if (myStmt != null) {
			myStmt.close();
		}

		if (myRs != null) {
			myRs.close();
		}

	}

	private static void display(ResultSet myRs) throws SQLException {
		// process the result set
		while (myRs.next()) {
			String lastName = myRs.getString("last_name");
			String firstName = myRs.getString("first_name");
			double salary = myRs.getDouble("salary");
			String department = myRs.getString("department");

			System.out.printf("%s | %s | %s | %.2f \n", lastName, firstName, department, salary);
		}

	}

}
