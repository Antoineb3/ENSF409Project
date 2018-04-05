/**
 * 
 */
package SharedObjects;

import java.io.Serializable;

/**
 * @author 	Antoine Bizon & Ross Bartlett
 *
 */
public class Course implements Serializable{
	static final long serialVersionUID = 60;
	private int id;
	private int profID;
	private String name;
	private boolean active;
	
	
	/**
	 * Constructor that inits all fields and receives int ID, for creating a temporary course to return from the DB that already has an ID
	 */
	public Course(int id, int profID, String name, boolean active) {
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
	public boolean getActive() {
		return active;
	}
	/**
	 * @param active the active to set
	 */
	public void setActive(boolean active) {
		this.active = active;
	}



	@Override
	public String toString() {
		return name +"      active = "+active;
	}
	
	

}
