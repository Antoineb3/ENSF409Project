package BackEnd.Model;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * This abstract class has methods to perform operations common to all mySQL tables.
 * Methods that are very similar for all table are concrete and ones that are more unique are abstract.
 * @author 	Antoine Bizon
 * @version 1.0
 * @since	2018-03-30
 */
public abstract class Table {

	protected StatementExecutor execute;
	
	protected String tableName;
	
	/**
	 * Adds a new row to a table in the database given an object of they type in the table.
	 * @param addition the object being added to a row.
	 * @return The number of rows affected by the update, i.e. if the object was added.
	 */
	protected abstract <T extends Serializable> int addToDB(T addition);
	
	/**
	 * Generates a arrayList from a result set returned after a query.
	 * @param results the result of the query.
	 * 
	 * @return the arrayList generated from the result set. 
	 */
	protected abstract ArrayList<? extends Serializable> listFromResultSet(ResultSet results);
	
	/**
	 * Constructs a table object.
	 * @param execute
	 * @param tableName
	 */
	protected Table(StatementExecutor execute, String tableName) {
		this.execute = execute;
		this.tableName = tableName;
	}
	
	/**
	 * Searches the table of this object for all parameters with the matching key.
	 * @param param
	 * @param key
	 * @return
	 */
	public ArrayList<? extends Serializable> search(String param, String key){
		String query = "SELECT * FROM " + tableName + 
					   " WHERE " + param + "='" + key + "'";
		ResultSet results = execute.preformQuery(query);
		return listFromResultSet(results);
	}
	
	/**
	 * Updates the table with specified values and conditions. 
	 * @param param the parameter to be updated.
	 * @param key the new value of the parameter.
	 * @param condition the column to be checked for the condition.
	 * @param coditionVal the value of the condition.
	 * @return the number of affected rows.
	 */
	public int editRow(String param, String key, String condition, String coditionVal) {
		String update = "UPDATE " + tableName + 
						" SET " + param + "='" + key +
						"' WHERE " + condition + "='" + coditionVal + "'";
		return execute.preformUpdate(update);
	}
	
}
