/**
 * 
 */
package SharedObjects;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Objects of this class are used to communicate messages for accessing sending emails from the client to the server.
 * @author 	Ross Bartlett, Antoine Bizon
 */
public abstract class EmailMessage extends Message implements Serializable {
	static final long serialVersionUID = 13;
	private String subject;
	private String contents;

	
	public EmailMessage(String subject, String contents) {
		super();
		this.subject = subject;
		this.contents = contents;
	}
	
	
	public String getSubject() {
		return subject;
	}


	public String getContents() {
		return contents;
	}
}
