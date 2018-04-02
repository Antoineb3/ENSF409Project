/**
 * 
 */
package SharedObjects;

/**
 * @author Ross
 *
 */
public class Professor extends User{
	static final long serialVersionUID = 01;
	
	
	/**
	 * Constructor that inits all fields and receives int ID, for creating a temporary Professor to return from the DB that already has an ID
	 */
	public Professor(int id, String password, String email, String firstName, String lastName) {
		super(id, password, email, firstName, lastName);
	}

}
