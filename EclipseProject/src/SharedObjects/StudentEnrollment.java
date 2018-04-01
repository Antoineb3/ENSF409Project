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
	/**
	 * Static counter to set the ID to the next ID 
	 */
	private static int idCount;
	private int id;
	private int studentID;
	private int courseID;
	
	/**
	 * Constructor that inits all fields and increments idCount, used for creating new StudentEnrollment
	 */
	public StudentEnrollment( int studentID, int courseID) {
		this.id = idCount++;
		this.studentID = studentID;
		this.courseID = courseID;
	}
	/**
	 * Constructor that inits all fields and receives int ID, for creating a temporary StudentEnrollment to return from the DB that already has an ID
	 */
	public StudentEnrollment(int id, int studentID, int courseID) {
		this.id = id;
		this.studentID = studentID;
		this.courseID = courseID;
	}
	
	/**
	 * initialize the ID count to a specified value, used when first reading the DB table in order to get next id to create
	 */
	public static void setIdCount(int idCount) {
		StudentEnrollment.idCount = idCount;
		System.out.println("StudentEnrollment idCount set to "+idCount);
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
