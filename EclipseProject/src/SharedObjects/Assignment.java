/**
 * 
 */
package SharedObjects;

import java.io.Serializable;

/**
 * @author Ross
 *
 */
public class Assignment implements Serializable{
	static final long serialVersionUID = 50;
	private int id;
	private int courseID;
	private String path;
	private String title;
	private char active;
	private String dueDate;

}
