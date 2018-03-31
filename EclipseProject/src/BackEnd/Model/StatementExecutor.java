package BackEnd.Model;

import java.sql.*;

/**
 * This class executes updates and query to any mySQL database.
 * @author 	Antoine Bizon
 * @version 1.0
 * @since	2018-03-30
 */
class StatementExecutor {

	private Connection jdbc_connection;
	
	private Statement statement;
	
	/**
	 * Constructs a StatementExecutor object with a connection to a database.
	 * Connection information to the database is provided by the given parameters.
	 * @param connectionInfo the database connection information.
	 * @param login the user account login. 
	 * @param password the user account password.
	 */
	StatementExecutor(String connectionInfo, String login, String password) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			jdbc_connection = DriverManager.getConnection(connectionInfo, login, password);
		} 
		catch (SQLException e) {e.printStackTrace();} 
		catch (ClassNotFoundException e) {e.printStackTrace();}
	}
	

	/**
	 * Performs a query on a table in a database and returns the resultset. 
	 * @param query the query being performed.
	 * @return the result of the query as a ResultSet.
	 */
	ResultSet preformQuery(String query) {
		ResultSet result = null;
		try {
			statement = jdbc_connection.createStatement();
			result = statement.executeQuery(query);
		} 
		catch (SQLException e) {e.printStackTrace();}

		return result;
	}
	
	/**
	 * Performs an update on a table in a database and returns the number of affected rows. 
	 * @param update the update being performed.
	 * @return the number of rows affected by the update. 
	 */
	int preformUpdate(String update) {
		int affectedRows = 0;
		try {
			statement = jdbc_connection.createStatement();
			affectedRows = statement.executeUpdate(update);
		} 
		catch (SQLException e) {e.printStackTrace();}

		return affectedRows;
	}
}
