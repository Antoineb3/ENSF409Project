/**
 * 
 */
package SharedObjects;

import java.io.Serializable;

/**
 * @author Ross
 *
 */
public class Submission implements Serializable{
	static final long serialVersionUID = 40;
	/**
	 * Static counter to set the ID to the next ID 
	 */
	private static int idCount;
	private int id;
	private int assignID;
	private int studentID;
	private String path;
	private String title;
	private int submissionGrade;
	private String comments;
	private String timestamp;
	
	/**
	 * Constructor that inits all fields and increments idCount, used for creating new Submission
	 */
	public Submission(int assignID, int studentID, String path, String title, int submissionGrade,
			String comments, String timestamp) {
		this.id = idCount++;
		this.assignID = assignID;
		this.studentID = studentID;
		this.path = path;
		this.title = title;
		this.submissionGrade = submissionGrade;
		this.comments = comments;
		this.timestamp = timestamp;
	}
	/**
	 * Constructor that inits all fields and receives int ID, for creating a temporary Submission to return from the DB that already has an ID
	 */
	public Submission(int id, int assignID, int studentID, String path, String title, int submissionGrade,
			String comments, String timestamp) {
		this.id = id;
		this.assignID = assignID;
		this.studentID = studentID;
		this.path = path;
		this.title = title;
		this.submissionGrade = submissionGrade;
		this.comments = comments;
		this.timestamp = timestamp;
	}
	
	
	/**
	 * initialize the ID count to a specified value, used when first reading the DB table in order to get next id to create
	 */
	public static void setIdCount(int idCount) {
		Submission.idCount = idCount;
		System.out.println("Submission idCount set to "+idCount);
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
	 * @return the path
	 */
	public String getPath() {
		return path;
	}
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @return the submissionGrade
	 */
	public int getSubmissionGrade() {
		return submissionGrade;
	}
	/**
	 * @return the comments
	 */
	public String getComments() {
		return comments;
	}
	/**
	 * @return the timestamp
	 */
	public String getTimestamp() {
		return timestamp;
	}
	
	

}
