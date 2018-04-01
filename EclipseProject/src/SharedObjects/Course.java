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
	/**
	 * Static counter to set the ID to the next ID 
	 */
	private static int idCount;
	private int id;
	private int profID;
	private String name;
	private char active;
	
	/**
	 * Constructor that inits all fields and increments idCount, used for creating new courses
	 */
	public Course( int profID, String name, char active) {
		this.id = idCount++;
		this.profID = profID;
		this.name = name;
		this.active = active;
	}
	
	/**
	 * Constructor that inits all fields and receives int ID, for creating a temporary course to return from the DB that already has an ID
	 */
	public Course(int id, int profID, String name, char active) {
		super();
		this.id = id;
		this.profID = profID;
		this.name = name;
		this.active = active;
	}
	
	/**
	 * initialize the ID count to a specified value, used when first reading the DB table in order to get next id to create
	 */
	public static void setIdCount(int idCount) {
		Course.idCount = idCount;
		System.out.println("Course idCount set to "+idCount);
	}
	
	
	/**
	 * @return the id
	 */
	public int getID() {
		return id;
	}
	/**
	 * @return the profID
	 */
	public int getProfID() {
		return profID;
	}
	/**
	 * @param profID the profID to set
	 */
	public void setProfID(int profID) {
		this.profID = profID;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the active
	 */
	public char getActive() {
		return active;
	}
	/**
	 * @param active the active to set
	 */
	public void setActive(char active) {
		this.active = active;
	}
	
	

}
