/**
 * 
 */
package SharedObjects;

import java.io.Serializable;

/**
 * @author Ross
 *
 */
public class Grade implements Serializable{
	static final long serialVersionUID = 20;	
	private int id;
	private int assignID;
	private int studentID;
	private int courseID;
	private int assignmentGrade;

	
	/**
	 * Constructor that inits all fields and receives int ID, for creating a temporary grade to return from the DB that already has an ID
	 */
	public Grade(int id, int assignID, int studentID, int courseID, int assignmentGrade) {
		this.id = id;
		this.assignID = assignID;
		this.studentID = studentID;
		this.courseID = courseID;
		this.assignmentGrade = assignmentGrade;
	}
	
	
	/**
	 * @return the id
	 */
	public int getID() {
		return id;
	}
	
	/**
	 * @return the assignID
	 */
	public int getAssignID() {
		return assignID;
	}
	/**
	 * @return the studentID
	 */
	public int getStudentID() {
		return studentID;
	}
	/**
	 * @return the courseID
	 */
	public int getCourseID() {
		return courseID;
	}
	/**
	 * @return the assignmentGrade
	 */
	public int getAssignmentGrade() {
		return assignmentGrade;
	}
	
}
