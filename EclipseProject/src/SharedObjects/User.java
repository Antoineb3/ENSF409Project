/**
 * 
 */
package SharedObjects;

import java.io.Serializable;

/**
 * This class corresponds to a row in the user table.
 * @author 	Ross Bartlett, Antoine Bizon
 */
//class user should be abstract right?
public abstract class User implements Serializable{
	static final long serialVersionUID = 00;
	protected int id;
	protected String password;
	protected String email;
	protected String firstName;
	protected String lastName;
	
	/**
	 * Constructor that inits all fields and receives int ID, for creating a temporary user to return from the DB that already has an ID
	 */
	public User(int id, String password, String email, String firstName, String lastName) {
		this.id = id;
		this.password = password;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
	}	
	
	
	/**
	 * @return the id
	 */
	public int getID() {
		return id;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}
	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}
	
	
	
	

}
