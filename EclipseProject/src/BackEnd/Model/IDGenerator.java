package BackEnd.Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * This class has a static method that 
 * @author 	Antoine Bizon
 * @version 1.0
 * @since	2018-04-02
 */
public class IDGenerator {
	private static PreparedStatement preparedStatement;
	private static Connection jdbc_connection;
	
	//Configure these variables for your own MySQL environment
	private static String connectionInfo = "jdbc:mysql://localhost:3306/LearningPlatformDB?useSSL=false",  
				  login          = "root",
				  password       = "bacon",  //change password for your own machine
				  tableName		 = "IDTable";

	/**
	 * Returns the incremented highest id value in the database.
	 * @return Highest id value + 1
	 * @throws SQLException
	 */
	public synchronized static int generateID() {
		int rv = 0;
		try {
			jdbc_connection = DriverManager.getConnection(connectionInfo, login, password);
			rv = getCurrentIDValue();
			setCurrentIDValue(++rv);
			preparedStatement.close();
			jdbc_connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			return rv;
		}
	}

	/**
	 * Returns the highest id value in the database.
	 * @return the current value of the largest id in the database.
	 * @throws SQLException 
	 */
	private static int getCurrentIDValue() throws SQLException {
		jdbc_connection.setAutoCommit(true);
		String statement = "SELECT * FROM " + tableName;
		preparedStatement = jdbc_connection.prepareStatement(statement);
		ResultSet id = preparedStatement.executeQuery();
		id.next();
		return id.getInt("ID");
	}
	
	
	/**
	 * Sets the highest id value in the database.
	 * @param the new vale of the largest id in the database.
	 * @throws SQLException 
	 */
	private static void setCurrentIDValue(int id) throws SQLException {
		jdbc_connection.setAutoCommit(true);
		String statement = "UPDATE " + tableName + 
				" SET  ID=? WHERE ID=?";
		preparedStatement = jdbc_connection.prepareStatement(statement);
		preparedStatement.setInt(1, id);
		preparedStatement.setInt(2, id-1);
		preparedStatement.executeUpdate();
		
	}
}
