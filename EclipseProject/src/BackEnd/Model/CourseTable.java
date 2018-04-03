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
	protected <T extends Serializable> int addToDB(T adition) {
		Course course  = (Course) adition;
		String update = "INSERT INTO " + tableName +
				" VALUES ( " + IDGenerator.generateID() + ", " + 
				course.getProfID() + ", '" +
				course.getName() + "', " +
				course.getActive()+ ");";
		return execute.preformUpdate(update);
	}

	
	/* (non-Javadoc)
	 * @see BackEnd.Model.Table#listFromResultSet(java.sql.ResultSet)
	 */
	@Override
	protected ArrayList<? extends Serializable> listFromResultSet(ResultSet results) {
		ArrayList<Course> courses = new ArrayList<Course>();
		try {
			while(results.next()) {
					courses.add(new Course(results.getInt(1), results.getInt(2), results.getString(3), results.getInt(4)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return courses;
	}

}
