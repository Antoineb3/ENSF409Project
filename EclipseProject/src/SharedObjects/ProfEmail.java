/**
 * 
 */
package SharedObjects;

import java.io.Serializable;

/**
 * Objects of this class are used to communicate messages for send prof emails from the client to the server.
 * @author 	Ross Bartlett, Antoine Bizon
 */
public class ProfEmail extends EmailMessage implements Serializable {
	private static final long serialVersionUID = 101;
	private Course theCourse;
	private Professor theProf;
	
	
	/**
	 * @param subject
	 * @param contents
	 * @param course
	 * @param p
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
