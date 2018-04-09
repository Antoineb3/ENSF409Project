/**
 * 
 */
package FrontEnd.FrontController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import FrontEnd.View.EmailPage;
import FrontEnd.View.ProfGUI;
import FrontEnd.View.ViewStudentsPage;
import SharedObjects.DBMessage;
import SharedObjects.EmailMessage;

/**
 * @author Ross
 *
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


	@Override
	public void actionPerformed(ActionEvent e) {
		String subject = panel.getSubjectField().getText();
		String text = panel.getTextArea().getText();
		//check inputs
		if(subject.equals("") || text.equals("")) {
			JOptionPane.showMessageDialog(null, "Error, cannot send an email with empty field(s).", "Email error", JOptionPane.WARNING_MESSAGE);
			return;
		}
		EmailMessage emailMessage;
		if(controller.getFrame() instanceof ProfGUI) {
			
			//TODO maybe this would be easier on the back end, instead of sending message full of reciepients, on backend use instanceOf profEmail or studentEmail
//			String sender = ((ProfGUI) controller.frame).getProf().getEmail();
//			
//			//send message to get student ID's of enrolled students
//			ArrayList<String> params = new ArrayList<>();
//			params.add("COURSEID"); // the column in the table to search
//			params.add("'"+Integer.toString(((ProfGUI) controller.getFrame()).getProfCoursePagePanel().getCourse().getID())+"'"); // the search key
//			DBMessage msg = new DBMessage(2, 0, params); // 2, 10 is studentEnrollmentTableNum, searchRowOp
//			//send the message, get response
//			ArrayList<? extends Serializable> response = controller.communicator.communicate(msg);
//			
//			//send message to get the emails of those students 
//			ArrayList<String> params2 = new ArrayList<>();
//			params.add("COURSEID"); // the column in the table to search
//			params.add("'"+Integer.toString(((ProfGUI) controller.getFrame()).getProfCoursePagePanel().getCourse().getID())+"'"); // the search key
//
//			//send the message, get response
//			ArrayList<? extends Serializable> response2 = controller.communicator.communicate(msg2);
//			
//			
//			
//			
//			
//			emailMessage = new EmailMessage(subject, text, sender, receiver)
		}
	}
	
	
}
