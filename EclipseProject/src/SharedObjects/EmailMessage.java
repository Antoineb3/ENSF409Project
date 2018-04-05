/**
 * 
 */
package SharedObjects;

import java.io.Serializable;

/**
 * @author Ross
 *
 */
public class EmailMessage extends Message implements Serializable {
	static final long serialVersionUID = 13;
	private String subject;
	private String contents;
	private String sender;
	private String receiver;
	
	public EmailMessage(String subject, String contents, String sender, String receiver) {
		super();
		this.subject = subject;
		this.contents = contents;
		this.sender = sender;
		this.receiver = receiver;
	}
	
	

}
