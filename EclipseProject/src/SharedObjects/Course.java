/**
 * 
 */
package SharedObjects;

import java.io.Serializable;

/**
 * @author Ross
 *
 */
public class Course implements Serializable{
	static final long serialVersionUID = 60;
	private int id;
	private int profID;
	private String name;
	private char active;

}
