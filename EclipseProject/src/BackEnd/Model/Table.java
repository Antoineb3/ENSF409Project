package BackEnd.Model;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * This abstract class has methods to perform operations common to all mySQL tables.
 * Methods that are very similar for all table are concrete and ones that are more unique are abstract.
 * @author 	Antoine Bizon & Ross Bartlett
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
	public abstract <T extends Serializable> ArrayList<Integer> addToDB(T addition);
	
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
					   " WHERE " + param + "=" + key;
		ResultSet results = execute.preformQuery(query);
		return listFromResultSet(results);
	}
	
	/**
	 * @param string
	 * @param string2
	 * @param string3
	 * @param string4
	 * @return
	 */
	public ArrayList<? extends Serializable> search(String param1, String key1, String param2, String key2) {
		String query = "SELECT * FROM " + tableName + 
				   " WHERE " + param1 + "=" + key1 +
				   " AND " + param2 + "=" + key2;
		ResultSet results = execute.preformQuery(query);
		return listFromResultSet(results);
	}
	
	
	/**
	 * Updates the table with specified values and conditions. 
	 * @param param the parameter to be updated.
	 * @param key the new value of the parameter.
	 * @param condition the column to be checked for the condition.
	 * @param conditionVal the value of the condition.
	 * @return the number of affected rows.
	 */
	public ArrayList<Integer> editRow(String param, String key, String condition, String conditionVal) {
		String update = "UPDATE " + tableName + 
						" SET " + param + "=" + key +
						" WHERE "  + condition + "=" + conditionVal;

		ArrayList<Integer> result = new ArrayList<Integer>();
		result.add(execute.preformUpdate(update));
		return result;
	}
	
	/**
	 * Removes all rows in the table where param==key.
	 * @param param the parameter to be checked
	 * @param key the value of the parameter 
	 * @return the number of rows removed.
	 */
	public ArrayList<? extends Serializable> remove(String param, String key){
		String update = "DELETE FROM " + tableName + 
					   " WHERE " + param + "=" + key;
		ArrayList<Integer> result = new ArrayList<Integer>();
		result.add(execute.preformUpdate(update));
		return result;
	}


}
