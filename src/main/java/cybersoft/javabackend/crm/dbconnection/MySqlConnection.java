package cybersoft.javabackend.crm.dbconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlConnection {
	private static Connection connection = null;
	
	private static final String URL = "jdbc:mysql://localhost:3306/crm";
	private static final String USERNAME = "root";
	private static final String PASWORD = "admin";
	
	public static Connection getConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			if(connection == null || connection.isClosed())
				connection = DriverManager.getConnection(URL, USERNAME, PASWORD);
			
			return connection;
		} catch (ClassNotFoundException e) {
			System.out.println("Driver could not found.");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Database connection could not establish.");
			e.printStackTrace();
		}
		
		return null;
	}
}
