import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcDemo {
	
	public static void main(String[] args) throws SQLException {
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		String dbUrl = "jdbc:mysql://localhost:3306/demo";
		String user = "student2";
		String pass = "student2";
		
		try {
			//1. Get a connection to database
			myConn = DriverManager.getConnection(dbUrl,user,pass);
			System.out.println("Connection created succussfully!");
			
			//2. Create a statement
			myStmt = myConn.createStatement();
			
			//3. Execute SQL query
			myRs = myStmt.executeQuery("select * from employees");
			
			//4. Process the ResultSet
			 while(myRs.next()) {
				 System.out.println(myRs.getString("last_name")+" , "+myRs.getString("first_name"));
			 }
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(myRs != null) {
				myRs.close();
			}
			
			if(myStmt != null) {
				myStmt.close();
			}
			
			if(myConn != null) {
				myConn.close();
			}
		}
		
	}
}
