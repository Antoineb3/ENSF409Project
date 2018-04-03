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
	private int active;
	
	
	/**
	 * Constructor that inits all fields and receives int ID, for creating a temporary course to return from the DB that already has an ID
	 */
	public Course(int id, int profID, String name, int active) {
		super();
		this.id = id;
		this.profID = profID;
		this.name = name;
		this.active = active;
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
	public int getActive() {
		return active;
	}
	/**
	 * @param active the active to set
	 */
	public void setActive(int active) {
		this.active = active;
	}
	
	

}
