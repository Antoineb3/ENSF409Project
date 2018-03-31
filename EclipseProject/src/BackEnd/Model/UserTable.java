/**
 * 
 */
package BackEnd.Model;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * 
 * 
 * @author 	Antoine Bizon
 * @version 1.0
 * @since	2018-03-30
 */
public class UserTable extends Table{

	/**
	 * Constructs an object of class UserTable
	 * @param execute
	 * @param tableName
	 */
	UserTable(StatementExecutor execute, String tableName) {
		super(execute, tableName);
	}

	/* (non-Javadoc)
	 * @see BackEnd.Model.Table#addToDB(java.io.Serializable)
	 */
	@Override
	protected <T extends Serializable> int addToDB(T adition) {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see BackEnd.Model.Table#listFromResultSet(java.sql.ResultSet)
	 */
	@Override
	protected ArrayList<? extends Serializable> listFromResultSet(ResultSet results) {
		// TODO Auto-generated method stub
		return null;
	}




	
}
