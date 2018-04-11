
package FrontEnd.FrontController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import FrontEnd.View.EmailPage;
import FrontEnd.View.ProfGUI;
import FrontEnd.View.StudentGUI;
import FrontEnd.View.ViewStudentsPage;
import SharedObjects.DBMessage;
import SharedObjects.EmailMessage;
import SharedObjects.ProfEmail;
import SharedObjects.StudentEmail;

/**
 * Listener for the send email button
 * @author 	Antoine Bizon & Ross Bartlett
 */
public class SendEmailListener implements ActionListener{

	/**
	 * the panel with this button
	 */
	private EmailPage panel;
	/**
	 * the controller constructing this listener
	 */
	private ViewController controller;

	public SendEmailListener( EmailPage p, ViewController c) {
		controller=c;
		panel=p;
	}


	/**
	 * tells the DB to send an email with given subject/contents
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String subject = panel.getSubjectField().getText();
		String text = panel.getTextArea().getText();
		//check inputs
		if(subject.equals("") || text.equals("")) {
			JOptionPane.showMessageDialog(null, "Error, cannot send an email with empty field(s).", "Email error", JOptionPane.WARNING_MESSAGE);
			return;
		}
		EmailMessage email;
		if(controller.getFrame() instanceof ProfGUI) 
			email = new ProfEmail(subject, text, ((ProfGUI) controller.getFrame()).getProfCoursePagePanel().getCourse(), ((ProfGUI) controller.getFrame()).getProf());
		else if(controller.getFrame() instanceof StudentGUI) 
			email = new StudentEmail(subject, text, ((StudentGUI) controller.getFrame()).getStudentCoursePagePanel().getCourse(), ((StudentGUI) controller.getFrame()).getStudent());
		else {
			System.err.println("error getting user type in SendEmailListener...");
			return;
		}
		//send email
		ArrayList<? extends Serializable> response = controller.getCommunicator().communicate(email);
		if(response==null || (int)response.get(0)!=0) {
			JOptionPane.showMessageDialog(null, "Error sending email.", "Email Error", JOptionPane.WARNING_MESSAGE);
			return;
		}
		JOptionPane.showMessageDialog(null, "Email sent.", "Send Email", JOptionPane.INFORMATION_MESSAGE);
	}
	
}

