package com.raj.jdbc.blob;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ReadBlobDemo {

	public static void main(String[] args) throws SQLException, IOException {
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		InputStream input = null;
		FileOutputStream output = null;
		
		try {
			//1. Get connection to a database
			myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo","student2","student2");
			System.out.println("Connection established!");
			
			//2. Execute statement
			myStmt = myConn.createStatement();
			String sql = "select resume from employees where email='john.doe@foo.com'";
			myRs = myStmt.executeQuery(sql);
			
			//3. Set up a handle to a file
			File theFile = new File("resume_from_db.pdf");
			output = new FileOutputStream(theFile);
			
			if(myRs.next()) {
				input = myRs.getBinaryStream("resume");
				System.out.println("Reading resume from databse...");
				System.out.println(sql);
				
				byte[] buffer = new byte[1024];
				while(input.read(buffer) > 0) {
					output.write(buffer);
				}
				
				System.out.println("\nSaved to a file: "+theFile.getAbsolutePath());
				System.out.println("\nCompleted successfully!");
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
			
			if(input != null) {
				input.close();
			}
			
			if(output != null) {
				output.close();
			}
		}
	}
}
