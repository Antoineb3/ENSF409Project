package BackEnd.Model;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import SharedObjects.Course;



/**
 * This class implements the abstract methods in the Table class for the course table.
 * @author 	Antoine Bizon
 * @version 1.0
 * @since	2018-03-30
 */
class CourseTable extends Table {

	/**
	 * Constructs an object of class CourseTable.
	 * @param execute
	 * @param tableName
	 */
	public CourseTable(StatementExecutor execute, String tableName) {
		super(execute, tableName);
	}

	
	/* (non-Javadoc)
	 * @see BackEnd.Model.Table#addToDB(java.io.Serializable)
	 */
	@Override
	public <T extends Serializable>  ArrayList<Integer> addToDB(T addition) {
		Course course  = (Course) addition;
		String update = "INSERT INTO " + tableName +
				" VALUES ( " + IDGenerator.generateID() + ", " + 
				course.getProfID() + ", '" +
				course.getName() + "', " +
				course.getActive()+ ");";
		ArrayList<Integer> result = new ArrayList<Integer>();
		result.add(execute.preformUpdate(update));
		return result;
	}

	
	/* (non-Javadoc)
	 * @see BackEnd.Model.Table#listFromResultSet(java.sql.ResultSet)
	 */
	@Override
	protected ArrayList<? extends Serializable> listFromResultSet(ResultSet results) {
		ArrayList<Course> courses = new ArrayList<Course>();
		try {
			while(results.next()) {
					courses.add(new Course(results.getInt(1), results.getInt(2), results.getString(3), results.getInt(4))); //must be edited now that ACTIVE field is a boolean
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return courses;
	}

}
