package com.raj.jdbc.clob;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ReadClobDemo {

	public static void main(String[] args) throws SQLException, IOException {

		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;

		Reader input = null;
		FileWriter output = null;

		try {
			// 1. Get the connection to database
			myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "student2", "student2");
			System.out.println("Connection established!");

			// 2. Execute statement
			myStmt = myConn.createStatement();
			String sql = "select resume from employees where email='john.doe@foo.com'";
			myRs = myStmt.executeQuery(sql);

			// 3. setup the handle to the file
			File theFile = new File("resume_from_db.txt");
			output = new FileWriter(theFile);

			if (myRs.next()) {
				input = myRs.getCharacterStream("resume");
				System.out.println("Reading resume from databse...");
				System.out.println(sql);

				int theChar;
				while ((theChar = input.read()) > 0) {
					output.write(theChar);

				}
				System.out.println("\nSaved to file: " + theFile.getAbsolutePath());
				System.out.println("\nCompleted successfully!");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (myConn != null) {
				myConn.close();
			}

			if (myStmt != null) {
				myStmt.close();
			}

			if (myRs != null) {
				myRs.close();
			}

			if (input != null) {
				input.close();
			}

			if (output != null) {
				output.close();
			}
		}
	}

}
