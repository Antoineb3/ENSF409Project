/**
 * 
 */
package SharedObjects;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author 	Antoine Bizon & Ross Bartlett
 *
 */
public class EmailMessage extends Message implements Serializable {
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
