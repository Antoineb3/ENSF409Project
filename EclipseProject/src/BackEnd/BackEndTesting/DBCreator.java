package BackEnd.BackEndTesting;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBCreator {
	public Connection jdbc_connection;
	public PreparedStatement preparedStatement;
	public String databaseName = "LearningPlatformDB";
	
	//Configure these variables for your own MySQL environment
	public String connectionInfo = "jdbc:mysql://localhost:3306",  
				  login          = "root",
				  password       = "bacon";
	
	//Establishes connection to the DB.
	public DBCreator()
	{
		try{
			// If this throws an error, make sure you have added the mySQL connector JAR to the project
			Class.forName("com.mysql.jdbc.Driver");
			
			// If this fails make sure your connectionInfo and login/password are correct
			jdbc_connection = DriverManager.getConnection(connectionInfo, login, password);
			System.out.println("Connected to: " + connectionInfo + "\n");
		}
		catch(SQLException e) { e.printStackTrace(); }
		catch(Exception e) { e.printStackTrace(); }
	}
	
	//Creates the DB. 
	public void createDB()
	{
		try {
			jdbc_connection.setAutoCommit(true);
			preparedStatement = jdbc_connection.prepareStatement("CREATE DATABASE " + databaseName);
			preparedStatement.executeUpdate();
			System.out.println("Created Database " + databaseName);
		} 
		catch( SQLException e)
		{
			e.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		DBCreator DB = new DBCreator();
		DB.createDB();
	}
}
