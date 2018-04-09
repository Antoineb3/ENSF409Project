/**
 * 
 */
package SharedObjects;

/**
 * @author Antoine
 *
 */
public class ProfEmail extends EmailMessage {
	private Course theCourse;
	private Professor theProf;
	
	
	/**
	 * @param subject
	 * @param contents
	 */
	public ProfEmail(String subject, String contents, Course course) {
		super(subject, contents);
		theCourse = course;
	}


	public Course getCourse() {
		return theCourse;
	}


	public Professor getTheProf() {
		return theProf;
	}
	
	

}
