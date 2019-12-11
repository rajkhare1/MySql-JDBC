package com.raj.jdbc.transactions;

import java.sql.*;
import java.util.Scanner;

public class TransactionDemo {

	public static void main(String[] args) throws Exception {

		Connection myConn = null;
		Statement myStmt = null;

		try {
			// Get a connection to database
			myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "student2", "student2");

			System.out.println("Connection established!");

			// turn off auto-commit
			myConn.setAutoCommit(false);

			// Show salaries BEFORE
			System.out.println("Salaries BEFORE\n");
			showSalaries(myConn, "HR");
			showSalaries(myConn, "Engineering");

			// Transaction Step 1: Delete all HR employees
			myStmt = myConn.createStatement();
			myStmt.executeUpdate("delete from employees where department='HR'");

			// Transaction Step 2: Set salaries to 300000 for all Engineering employees
			myStmt.executeUpdate("update employees set salary=300000 where department='Engineering'");

			System.out.println("\n>> Transaction steps are ready.\n");

			// Ask user if it ok to Save
			boolean ok = askUserIfOkToSave();

			if (ok) {
				// store in database
				myConn.commit();
				System.out.println("\n>> Transaction COMMITED.");
			} else {
				// discard
				myConn.rollback();
				System.out.println("\n>> Transaction ROLLED BACK.");

			}

			// Show salaries AFTER
			System.out.println("\n\nSalaries AFTER\n");
			showSalaries(myConn, "HR");
			showSalaries(myConn, "Engineering");

		} catch (Exception exc) {
			exc.printStackTrace();
		} finally {
			close(myConn, myStmt, null);
		}
	}

	private static boolean askUserIfOkToSave() {
		Scanner scanner = new Scanner(System.in);

		System.out.println("Is it ok to save? yes/no:");
		String input = scanner.nextLine();

		// close the scanner
		scanner.close();

		return input.equalsIgnoreCase("yes");
	}

	private static void showSalaries(Connection myConn, String theDepartment) throws SQLException {
		PreparedStatement myStmt = null;
		ResultSet myRs = null;

		System.out.println("Show Salaries for Department: " + theDepartment);

		try {
			// Prepare statement
			myStmt = myConn.prepareStatement("select * from employees where department=?");

			myStmt.setString(1, theDepartment);

			// Execute SQL query
			myRs = myStmt.executeQuery();

			// Process result set
			while (myRs.next()) {
				String lastName = myRs.getString("last_name");
				String firstName = myRs.getString("first_name");
				double salary = myRs.getDouble("salary");
				String department = myRs.getString("department");

				System.out.printf("%s, %s, %s, %.2f\n", lastName, firstName, department, salary);
			}
		} catch (Exception exc) {
			exc.printStackTrace();
		} finally {
			close(myStmt, myRs);
		}

	}

	private static void close(Connection myConn, Statement myStmt, ResultSet myRs) throws SQLException {
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

	private static void close(Statement myStmt, ResultSet myRs) throws SQLException {

		close(null, myStmt, myRs);
	}
}
