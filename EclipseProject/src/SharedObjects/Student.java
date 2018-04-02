/**
 * 
 */
package SharedObjects;

/**
 * @author Ross
 *
 */
public class Student extends User{

	static final long serialVersionUID = 02;
	
	
	/**
	 * Constructor that inits all fields and receives int ID, for creating a temporary student to return from the DB that already has an ID
	 */
	public Student(int id, String password, String email, String firstName, String lastName) {
		super(id, password, email, firstName, lastName);
	}

	
	
}
