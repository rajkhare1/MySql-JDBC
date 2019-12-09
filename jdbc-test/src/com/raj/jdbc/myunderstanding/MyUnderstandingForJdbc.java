
package com.raj.jdbc.myunderstanding;
import java.sql.*;

public class MyUnderstandingForJdbc {

	public static void main(String[] args) throws SQLException {
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;

		String dbUrl = "jdbc:mysql://localhost:3306/demo";
		String name = "student2";
		String pass = "student2";

		try {
			// 1. Create the connection
			System.out.println("Creating connection...");
			myConn = DriverManager.getConnection(dbUrl, name, pass);
			System.out.println("Connection created!");
			
			System.out.println("Before update the deatils for Employee LastName = Khare , FirstName = Raj");

			displayDetaisForEmployee(myConn, myStmt, myRs, "Khare", "Raj");
			
			System.out.println("Updating the details...");

			updateDetailsForEmployee("1.raj.khare@gmail.com", "Raj", "Khare", myConn, myStmt);
			
			System.out.println("After update the deatils for Employee LastName = Khare , FirstName = Raj");
			
			displayDetaisForEmployee(myConn, myStmt, myRs, "Khare", "Raj");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (myRs != null) {
				myRs.close();
			}
			if (myStmt != null) {
				myStmt.close();
			}
			if (myConn != null) {
				myConn.close();
			}
		}

	}

	private static void displayDetaisForEmployee(Connection myConn, PreparedStatement myStmt, ResultSet myRs,
			String lastName, String firstName) {
		try {
			myStmt = myConn.prepareStatement("select * from employees" + " where last_name=? and first_name=?");
			// set param
			myStmt.setString(1, lastName);
			myStmt.setString(2, firstName);

			// execute query
			myRs = myStmt.executeQuery();

			// process result set
			while (myRs.next()) {
				System.out.println(myRs.getInt("id") + " | " + myRs.getString("last_name") + " | "
						+ myRs.getString("first_name") + " | " + myRs.getString("email") + " | "
						+ myRs.getString("department") + " | " + myRs.getString("salary"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static void updateDetailsForEmployee(String email, String firstName, String lastName, Connection myConn,
			PreparedStatement myStmt) {
		// Prepare Statement
		try {
			myStmt = myConn
					.prepareStatement("update employees" + " set email=?" + " where last_name=? and first_name=?");

			// set param one-based index
			myStmt.setString(1, email);
			myStmt.setString(2, lastName);
			myStmt.setString(3, firstName);

			// execute statement
			int rowAffected = myStmt.executeUpdate();
			System.out.println("No. of Row affected: " + rowAffected);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
