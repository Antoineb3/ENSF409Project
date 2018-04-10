/**
 * 
 */
package BackEnd.Model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import SharedObjects.User;

/**
 * @author 	Antoine Bizon & Ross Bartlett
 *
 */
public class EmailOperator {
	private Properties properties;
	
	/**
	 * 
	 * @param sender
	 * @param recipients
	 * @param subject
	 * @param contents
	 * @return
	 */
	public int sendEmail(User sender, ArrayList<User> recipients, String subject, String contents) {
		createProperties();
		Session session = Session.getInstance(properties,
				new javax.mail.Authenticator(){
				 protected PasswordAuthentication getPasswordAuthentication() {
				 return new PasswordAuthentication(sender.getEmail(), sender.getPassword());
				 }
				});
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(sender.getEmail()));
			addRecipients(message, recipients);
			message.setSubject(subject);
			message.setText(contents);
			Transport.send(message); // Send the Email Message
			} catch (MessagingException e) {
			e.printStackTrace();
			}
		return 0;
	}


	/**
	 * @param message
	 * @param recipients
	 */
	private void addRecipients(Message message, ArrayList<User> recipientList) throws MessagingException{
		Iterator<User> recipients = recipientList.iterator();
		while(recipients.hasNext()) {
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipients.next().getEmail()));
		}
	}


	/**
	 * 
	 */
	private void createProperties() {
		properties.put("mail.smtp.starttls.enable", "true"); // Using TLS
		properties.put("mail.smtp.auth", "true"); // Authenticate
		properties.put("mail.smtp.host", "smtp.gmail.com"); // Using Gmail Account
		properties.put("mail.smtp.port", "587"); // TLS uses port 587
		
	}
}
