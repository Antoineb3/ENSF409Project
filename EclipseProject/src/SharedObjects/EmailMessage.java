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
	private String sender;
	private ArrayList<String> receivers;
	
	public EmailMessage(String subject, String contents, String sender, ArrayList<String> r) {
		super();
		this.subject = subject;
		this.contents = contents;
		this.sender = sender;
		this.receivers = r;
	}
	
	

}
