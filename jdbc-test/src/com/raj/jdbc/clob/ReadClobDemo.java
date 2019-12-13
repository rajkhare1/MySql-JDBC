package com.raj.jdbc.clob;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
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
		Writer output = null;
		
		try {
			// 1. Get the connection to database
			myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo","student2","student2");
			System.out.println("Connection established!");
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
