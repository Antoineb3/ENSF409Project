/**
 * 
 */
package BackEnd.BackEndTesting;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import BackEnd.Model.IDGenerator;
/**
 * Run once to set up ID table.
 * @author Antoine
 *
 */
public class IDTableCreator {
	private Connection jdbc_connection;
	private PreparedStatement preparedStatement;
	private String databaseName = "LearningPlatformDB", tableName;
	
	//Configure these variables for your own MySQL environment
	private String connectionInfo = "jdbc:mysql://localhost:3306/LearningPlatformDB?useSSL=false",  
				  login          = "root",
				  password       = "bacon";  //change password for your own machine
	
	

	//Establishes connection to the DB.
	public IDTableCreator()
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
	
	public void addItem(int id)
	{
		String sql = "INSERT INTO " + tableName +
				"(ID) VALUES" + 
				"(?)";
		try{
			jdbc_connection.setAutoCommit(false);
			preparedStatement = jdbc_connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();
			jdbc_connection.commit();
			System.out.println("item added");
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args)
	{
		IDTableCreator DB = new IDTableCreator();
		
		String param;
		
		DB.tableName = "IDTable";
		param = "CREATE TABLE " + DB.tableName + "(" +
			     "ID INT(8) NOT NULL, " +
			     "PRIMARY  KEY ( id ))";
		DB.removeTable();
		DB.createTable(param);
		
		DB.addItem(-1);
		
		System.out.println(IDGenerator.generateID());
		
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
