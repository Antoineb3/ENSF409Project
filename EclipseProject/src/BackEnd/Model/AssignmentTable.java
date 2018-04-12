package BackEnd.Model;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import SharedObjects.Assignment;


/**
 * This class implements the abstract methods in the Table class for the assignment table.
 * @author 	Antoine Bizon, Ross Bartlett 
 * @version 1.0
 * @since	2018-03-30
 */
public class AssignmentTable extends Table {

	/**
	 * Constructs an object of class AssignmentTable.
	 * @param execute the Statement executor object.
	 * @param tableName the name of the table.
	 */
	public AssignmentTable(StatementExecutor execute, String tableName) {
		super(execute, tableName);
	}

	/* (non-Javadoc)
	 * @see BackEnd.Model.Table#addToDB(java.io.Serializable)
	 */
	@Override
	public <T extends Serializable>  ArrayList<Integer> addToDB(T addition) {
		Assignment assignment = (Assignment) addition;
		String update = "INSERT INTO " + tableName +
				" VALUES ( " + IDGenerator.generateID() + ", " + 
				assignment.getCourseID() + ", '" +
				assignment.getTitle() + "', '" +
				assignment.getPath() + "', " +
				assignment.getActive() + ", '" +
				assignment.getDueDate() + "');";
		ArrayList<Integer> result = new ArrayList<Integer>();
		result.add(execute.preformUpdate(update));
		return result;
	}

	/* (non-Javadoc)
	 * @see BackEnd.Model.Table#listFromResultSet(java.sql.ResultSet)
	 */
	@Override
	protected ArrayList<? extends Serializable> listFromResultSet(ResultSet results) {
		ArrayList<Assignment> assignment = new ArrayList<Assignment>();
		try {
			while(results.next()) {
				assignment.add(new Assignment(results.getInt(1), results.getInt(2), results.getString(4),
						results.getString(3), results.getBoolean(5), results.getString(6))); 
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return assignment;
	}

}
