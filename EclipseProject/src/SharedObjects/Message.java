/**
 * 
 */
package SharedObjects;

import java.io.Serializable;

/**
 * @author Ross
 *
 */
public abstract class Message implements Serializable{
	static final long serialVersionUID = 10;
	
	protected int isFile;
	protected int isEmail;
	
	public Message(int isFile, int isEmail) {
		super();
		this.isFile = isFile;
		this.isEmail = isEmail;
	}
	
	
}
