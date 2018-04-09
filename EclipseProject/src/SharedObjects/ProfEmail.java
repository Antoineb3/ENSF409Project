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
	public ProfEmail(String subject, String contents, Course course, Professor p) {
		super(subject, contents);
		theCourse = course;
		theProf=p;
	}


	public Course getCourse() {
		return theCourse;
	}


	public Professor getTheProf() {
		return theProf;
	}
	
	

}
