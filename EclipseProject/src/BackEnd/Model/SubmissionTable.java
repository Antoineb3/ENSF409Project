package BackEnd.Model;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import SharedObjects.Grade;
import SharedObjects.Submission;

/**
 * This class implements the abstract methods in the Table class for the submission table.
 * @author 	Antoine Bizon
 * @version 1.0
 * @since	2018-03-30
 */
public class SubmissionTable extends Table {

	/**
	 * Constructs an object of class SubmissionTable.
	 * @param execute
	 * @param tableName
	 */
	public SubmissionTable(StatementExecutor execute, String tableName) {
		super(execute, tableName);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see BackEnd.Model.Table#addToDB(java.io.Serializable)
	 */
	@Override
	protected <T extends Serializable>  ArrayList<Integer> addToDB(T addition) {
		Submission submission = (Submission) addition;
		String update = "INSERT INTO " + tableName +
				" VALUES ( " + IDGenerator.generateID() + ", " + 
				submission.getAssignID() + ", " +
				submission.getStudentID() + ", '" +
				submission.getPath() + "', '" +
				submission.getTitle() + "', " +
				submission.getSubmissionGrade() + ", '" +
				submission.getComments() + "', '" +
				submission.getTimestamp()+ "');";
		ArrayList<Integer> result = new ArrayList<Integer>();
		result.add(execute.preformUpdate(update));
		return result;
	}

	/* (non-Javadoc)
	 * @see BackEnd.Model.Table#listFromResultSet(java.sql.ResultSet)
	 */
	@Override
	protected ArrayList<? extends Serializable> listFromResultSet(ResultSet results) {
		ArrayList<Submission> submissions = new ArrayList<Submission>();
		try {
			while(results.next()) {
				submissions.add(new Submission(results.getInt(1), results.getInt(2), results.getInt(3), results.getString(4),
						results.getString(5), results.getInt(6), results.getString(7), results.getString(8)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return submissions;
	}

}
