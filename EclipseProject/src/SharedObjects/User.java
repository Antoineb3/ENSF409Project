/**
 * 
 */
package SharedObjects;

import java.io.Serializable;

/**
 * @author Ross
 *
 */
//class user should be abstract right?
public abstract class User implements Serializable{
	static final long serialVersionUID = 00;
	/**
	 * Static counter to set the ID to the next ID 
	 */
	private static int idCount;
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
	 * Constructor that inits all fields and increments idCount, used for creating new user
	 */
	public User(String password, String email, String firstName, String lastName) {
		this.id = idCount++;
		this.password = password;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	

	/**
	 * initialize the ID count to a specified value, used when first reading the DB table in order to get next id to create
	 */
	public static void setIdCount(int idCount) {
		User.idCount = idCount;
		System.out.println("User idCount set to "+idCount);
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
