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
	private int id;
	private int assignID;
	private int studentID;
	private String path;
	private String title;
	private int submissionGrade;
	private String comments;
	private String timestamp;

}
