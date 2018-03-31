package BackEnd.BackEndTesting;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Sets up the database for the program to use.
 * You must edit 
 * @author Antoine
 *
 */
public class DBTableCreator {
	public Connection jdbc_connection;
	public PreparedStatement preparedStatement;
	public String databaseName = "LearningPlatformDB", tableName;
	
	//Configure these variables for your own MySQL environment
	public String connectionInfo = "jdbc:mysql://localhost:3306/LearningPlatformDB?useSSL=false",  
				  login          = "root",
				  password       = "bacon";  //change password for your own machine
	
	

	//Establishes connection to the DB.
	public DBTableCreator()
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
	
	
	// Create a data table inside of the database to hold tools
	public void createTable(String tableParam)
	{
		
		try{
			jdbc_connection.setAutoCommit(true);
			preparedStatement = jdbc_connection.prepareStatement(tableParam);
			preparedStatement.executeUpdate();
			System.out.println("Created Table " + tableName);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	//Removes a table from the DB.
	public void removeTable()
	{
		String sql = "DROP TABLE " + tableName;
		try{
			jdbc_connection.setAutoCommit(true);
			preparedStatement = jdbc_connection.prepareStatement(sql);
			preparedStatement.executeUpdate();
			System.out.println("Removed Table " + tableName);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args)
	{
		DBTableCreator DB = new DBTableCreator();
		
		String tableParam;
		
		DB.tableName = "UserTable";
		//DB.removeTable();
		tableParam = "CREATE TABLE " + DB.tableName + "(" +
			     "ID INT(8) NOT NULL, " +
			     "PASSWORD VARCHAR(20) NOT NULL, " + 
			     "EMAIL VARCHAR(50) NOT NULL, " + 
			     "FIRSTNAME VARCHAR(30) NOT NULL, " + 
			     "LASTNAME VARCHAR(30) NOT NULL, " + 
			     "TYPE CHAR(1) NOT NULL, " + 
			     "PRIMARY KEY ( id ))";
		
		DB.createTable(tableParam);
		
		DB.tableName = "CourseTable";
		//DB.removeTable();
		tableParam = "CREATE TABLE " + DB.tableName + "(" +
			     "ID INT(8) NOT NULL, " +
			     "PROFID INT(8) NOT NULL, " +
			     "COURSENAME VARCHAR(50) NOT NULL, " + 
			     "ACTIVE BIT(1) NOT NULL," +
			     "PRIMARY  KEY ( id ))";
		DB.createTable(tableParam);
		
		DB.tableName = "StudentEnrollmentTable";
		//DB.removeTable();
		tableParam = "CREATE TABLE " + DB.tableName + "(" +
			     "ID INT(8) NOT NULL, " +
			     "STUDENTID INT(8) NOT NULL, " +
			     "COURSEID INT(8) NOT NULL, " +
			     "PRIMARY  KEY ( id ))";
		DB.createTable(tableParam);

		
		DB.tableName = "AssignmentTable";
		//DB.removeTable();
		tableParam = "CREATE TABLE " + DB.tableName + "(" +
			     "ID INT(8) NOT NULL, " +
			     "COURSEID INT(8) NOT NULL, " +
			     "TITLE VARCHAR(50) NOT NULL, " + 
			     "PATH VARCHAR(100) NOT NULL, " + 
			     "ACTIVE BIT(1) NOT NULL," +
			     "DUEDATE CHAR(16) NOT NULL, " + 
			     "PRIMARY  KEY ( id ))";
		DB.createTable(tableParam);
		
		DB.tableName = "SubmissionTable";
		//DB.removeTable();
		tableParam = "CREATE TABLE " + DB.tableName + "(" +
			     "ID INT(8) NOT NULL, " +
			     "ASSIGNID INT(8) NOT NULL, " +
			     "STUDENTID INT(8) NOT NULL, " +
			     "PATH VARCHAR(100) NOT NULL, " + 
			     "TITLE VARCHAR(50) NOT NULL, " + 
			     "SUBMISSIONGRADE INT(3) NOT NULL, " +
			     "COMMENTS VARCHAR(140) NOT NULL, " + 
			     "TIMESTAMP CHAR(16) NOT NULL, " + 
			     "PRIMARY  KEY ( id ))";
		DB.createTable(tableParam);
		
		DB.tableName = "GradeTable";
		//DB.removeTable();
		tableParam = "CREATE TABLE " + DB.tableName + "(" +
			     "ID INT(8) NOT NULL, " +
			     "ASSIGN INT(8) NOT NULL, " +
			     "STUDENT INT(8) NOT NULL, " +
			     "COURSEID INT(8) NOT NULL, " +
			     "ASSIGNMENTGRADE INT(3) NOT NULL, " +
			     "PRIMARY  KEY ( id ))";
		DB.createTable(tableParam);
		
		
		try {
			DB.preparedStatement.close();
			DB.jdbc_connection.close();
		} 
		catch (SQLException e) { e.printStackTrace(); }
		finally
		{
			System.out.println("\nThe program is finished running");
		}
	}
}
