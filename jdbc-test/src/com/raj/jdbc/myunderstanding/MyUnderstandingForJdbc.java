
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
		
		//Searching parameters
		String firstName="Raj";
		String lastName="Khare";

		try {
			// 1. Create the connection
			System.out.println("Creating connection...");
			myConn = DriverManager.getConnection(dbUrl, name, pass);
			System.out.println("Connection created!");
			
			System.out.println("Before update the deatils searching for Employee LastName = "+lastName+", FirstName = "+firstName+"...");

			displayDetaisForEmployee(myConn, myStmt, myRs, lastName, firstName);
			
			System.out.println("Updating the details...");

			updateDetailsForEmployee("1.raj.khare@gmail.com", firstName, lastName, myConn, myStmt);
			
			System.out.println("After update the deatils searching for Employee LastName = "+lastName+", FirstName = "+firstName+"...");
			
			displayDetaisForEmployee(myConn, myStmt, myRs, lastName, firstName);
			
			System.out.println("Deleting the Employee LastName = "+lastName+", FirstName= "+firstName);
			deleteEmployee(myConn,myStmt,lastName,firstName);
			
			System.out.println("After delete the deatils searching for Employee LastName = "+lastName+", FirstName = "+firstName+"...");
			displayDetaisForEmployee(myConn, myStmt, myRs, lastName, firstName);

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

	private static void deleteEmployee(Connection myConn, PreparedStatement myStmt, String lastName, String firstName) {
		try {
			//prepare statement
			myStmt = myConn.prepareStatement("delete from employees"
					+ " where last_name=? and first_name=?");
			
			//set param
			myStmt.setString(1, lastName);
			myStmt.setString(2, firstName);
			
			//execute query
			int rowAffected = myStmt.executeUpdate();
			System.out.println("No. of Rows Affected: "+rowAffected);
			System.out.println("Record DELETED!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
			boolean found = false;
			while (myRs.next()) {
				System.out.printf("Found employee: %s %s\n", firstName, lastName);
				System.out.println("Details are:");
				System.out.println(+myRs.getInt("id") + " | " + myRs.getString("last_name") + " | "
						+ myRs.getString("first_name") + " | " + myRs.getString("email") + " | "
						+ myRs.getString("department") + " | " + myRs.getString("salary"));
				found = true;
			}
			
			if(!found) {
				System.out.println("Employee NOT FOUND: "+firstName+" "+lastName);
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
