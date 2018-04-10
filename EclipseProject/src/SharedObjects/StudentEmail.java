/**
 * 
 */
package SharedObjects;

import java.io.Serializable;

/**
 * @author Antoine
 *
 */
public class StudentEmail extends EmailMessage implements Serializable{
	private static final long serialVersionUID = 102;
	private Course theCourse;
	private Student theStudent;
	
	/**
	 * @param subject
	 * @param contents
	 */
	public StudentEmail(String subject, String contents, Course course, Student student) {
		super(subject, contents);
		theCourse = course;
		theStudent = student;
	}


	public Course getCourse() {
		return theCourse;
	}


	public Student getStudent() {
		return theStudent;
	}
	
	

}
