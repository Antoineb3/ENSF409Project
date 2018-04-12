/**
 * 
 */
package SharedObjects;

/**
 * This class corresponds to a row user row of type professor table.
 * @author 	Ross Bartlett, Antoine Bizon
 */
public class Professor extends User{
	static final long serialVersionUID = 01;
	
	
	/**
	 * Constructor that inits all fields and receives int ID, for creating a temporary Professor to return from the DB that already has an ID
	 */
	public Professor(int id, String password, String email, String firstName, String lastName) {
		super(id, password, email, firstName, lastName);
	}


	@Override
	public String toString() {
		return "Professor [id=" + id + ", password=" + password + ", email=" + email + ", firstName=" + firstName
				+ ", lastName=" + lastName + "]";
	}
	
	

}
