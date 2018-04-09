/**
 * 
 */
package SharedObjects;

/**
 * @author Antoine
 *
 */
public class StudentEmail extends EmailMessage {
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
