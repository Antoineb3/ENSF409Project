package BackEnd.Model;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import SharedObjects.Grade;

/**
 * This class implements the abstract methods in the Table class for the grade table.
 * @author 	Antoine Bizon & Ross Bartlett
 * @version 1.0
 * @since	2018-03-30
 */
public class GradeTable extends Table {

	/**
	 * Constructs an object of class GradeTable.
	 * @param execute
	 * @param tableName
	 */
	public GradeTable(StatementExecutor execute, String tableName) {
		super(execute, tableName);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see BackEnd.Model.Table#addToDB(java.io.Serializable)
	 */
	@Override
	public <T extends Serializable>  ArrayList<Integer> addToDB(T addition) {
		Grade grade = (Grade) addition;
		String update = "INSERT INTO " + tableName +
				" VALUES ( " + IDGenerator.generateID() + ", " + 
				grade.getAssignID() + ", " +
				grade.getStudentID() + ", " +
				grade.getCourseID() + ", " +
				grade.getAssignmentGrade() + ");";
		ArrayList<Integer> result = new ArrayList<Integer>();
		result.add(execute.preformUpdate(update));
		return result;
	}

	/* (non-Javadoc)
	 * @see BackEnd.Model.Table#listFromResultSet(java.sql.ResultSet)
	 */
	@Override
	protected ArrayList<? extends Serializable> listFromResultSet(ResultSet results) {
		ArrayList<Grade> grades = new ArrayList<Grade>();
		try {
			while(results.next()) {
				grades.add(new Grade(results.getInt(1), results.getInt(2), results.getInt(3), 
						results.getInt(4), results.getInt(5)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return grades;
	}

}
