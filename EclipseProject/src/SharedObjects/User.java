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
	
	protected int id;
	protected String password;
	protected String email;
	protected String firstName;
	protected String lastName;

}
