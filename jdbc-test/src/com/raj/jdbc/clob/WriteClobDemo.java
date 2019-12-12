package com.raj.jdbc.clob;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class WriteClobDemo {

	public static void main(String[] args) throws SQLException, IOException {
		
		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		FileReader input = null;
		
		try {
			//1. Get a connection to a database
			myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo","student2","student2");
			System.out.println("Connection established!");
			
			//2. Prepare statement
			String sql = "update employees set resume=? where email='john.doe@foo.com'";
			myStmt = myConn.prepareStatement(sql);
			
			//3 Set parameter for resume file name
			File theFile = new File("sample_resume.txt");
			input = new FileReader(theFile);
			myStmt.setCharacterStream(1, input);
			
			System.out.println("Reading input file: "+ theFile.getAbsolutePath());
			
			//4. Execute statement
			System.out.println("\nStoring resume in databse: "+theFile);
			System.out.println(sql);
			
			myStmt.executeUpdate();
			
			System.out.println("\nCompleted successfully!");
			
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
			
			if(input != null) {
				input.close();
			}
		}
	}
}
