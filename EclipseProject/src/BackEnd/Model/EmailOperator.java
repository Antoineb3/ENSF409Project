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
	
	/**
	 * 
	 * @param sender
	 * @param recipients
	 * @param subject
	 * @param contents
	 * @return
	 */
	public int sendEmail(User sender, ArrayList<User> recipients, String subject, String contents) {
		System.out.println("sending email");
		Session session = creatSessionObject(sender.getEmail(), sender.getPassword());
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(sender.getEmail()));
			addRecipients(message, recipients);
			message.setSubject(subject);
			message.setText(contents);
			Transport.send(message); // Send the Email Message
			} catch (MessagingException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
				return -1;
			}
		return 0;
	}


	/**
	 * @param email
	 * @param password
	 * @return
	 */
	private Session creatSessionObject(String email, String password) {
		try {
			System.out.println("Creating system properties");
			Properties properties = new Properties();
			properties.put("mail.smtp.auth", "true");
			properties.put("mail.smtp.starttls.enable", "true");
			properties.put("mail.smtp.host", "smtp.gmail.com");
			properties.put("mail.smtp.port", "587");
			System.out.println("System properties created");
	        return Session.getInstance(properties, new javax.mail.Authenticator() {
	            protected PasswordAuthentication getPasswordAuthentication() {
	                return new PasswordAuthentication(email, password);
	            }
	        });
		}catch(NullPointerException e) {e.printStackTrace();}
		System.out.println("returnig null");
		return null;
				
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

}
