/**
 * 
 */
package SharedObjects;

import java.io.Serializable;

/**
 * @author Ross
 *
 */
public class StudentEnrollment implements Serializable{
	static final long serialVersionUID = 30;
	private int id;
	private int studentID;
	private int courseID;
	

	/**
	 * Constructor that inits all fields and receives int ID, for creating a temporary StudentEnrollment to return from the DB that already has an ID
	 */
	public StudentEnrollment(int id, int studentID, int courseID) {
		this.id = id;
		this.studentID = studentID;
		this.courseID = courseID;
	}
	
	
	/**
	 * @return the id
	 */
	public int getID() {
		return id;
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
	
	

}
