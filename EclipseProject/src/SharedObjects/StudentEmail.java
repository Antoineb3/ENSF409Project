/**
 * 
 */
package SharedObjects;

import java.io.Serializable;

/**
 * Objects of this class are used to communicate messages for send student emails from the client to the server.
 * @author 	Ross Bartlett, Antoine Bizon
 */
public class StudentEmail extends EmailMessage implements Serializable{
	private static final long serialVersionUID = 102;
	private Course theCourse;
	private Student theStudent;
	
	/**
	 * Constructs a StudentEmail object.
	 */
	public StudentEmail(String subject, String contents, Course course, Student student) {
		super(subject, contents);
		theCourse = course;
		theStudent = student;
	}

	/**
	 * @return theCourse
	 */
	public Course getCourse() {
		return theCourse;
	}

	/**
	 * @return theStudent
	 */
	public Student getStudent() {
		return theStudent;
	}
	
	

}
