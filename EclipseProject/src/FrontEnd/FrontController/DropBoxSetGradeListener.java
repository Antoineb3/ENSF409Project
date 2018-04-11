/**
 * 
 */
package FrontEnd.FrontController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import FrontEnd.View.DropboxPage;
import SharedObjects.DBMessage;


/**
 * Listener to set the grade of a submission thru the Dropbox page 
 * @author Ross Bartlett and Antoine Bizon
 */
public class DropBoxSetGradeListener implements ActionListener {
	/**
	 * The panel with the button calling this listener
	 */
	private DropboxPage panel;
	/**
	 * the controller constructing this listener
	 */
	private ProfController controller;
	/**
	 * constructor to initialize the fields
	 * @param p the frame/panel
	 * @param c the controller
	 */
	public DropBoxSetGradeListener(DropboxPage p, ProfController c) {
		panel = p;
		controller=c;
	}

	/**
	 * Sets the submissionGrade of a submission
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String input = panel.getGradeField().getText();
		int grade = getGradeFromInput(input);
		if(grade==-1) return;
		if(panel.getSelectedSubmission()==null) {
			JOptionPane.showMessageDialog(null, "Error changing Submission grade.", "Submission Grade Change Error ", JOptionPane.WARNING_MESSAGE);
			System.out.println("error: selectedSubmission is null");
			panel.getGradeField().setText("");
			return;
		}
		//make a message to edit the DB
		ArrayList<String> params = new ArrayList<>();
		params.add("SUBMISSIONGRADE"); // the column to change
		params.add("'"+grade+"'"); // the new data key
		params.add("ID"); // the column in the table to search by (condition)
		params.add("'"+panel.getSelectedSubmission().getID()+"'"); // the conditionVal 
		DBMessage msg = new DBMessage(4, 1, params); // 4, 1 is submissionTableNum, editRowOp
		//send the message, get response
		ArrayList<? extends Serializable> response = controller.getCommunicator().communicate(msg);
		
		if(response.size()==1) { // success
			JOptionPane.showMessageDialog(null, "Submission grade changed to "+grade+".", "Submission Grade Change ", JOptionPane.INFORMATION_MESSAGE);
			panel.getSelectedSubmission().setSubmissionGrade(grade);
			panel.setGradeField(grade+""); 
		}
		else {
			JOptionPane.showMessageDialog(null, "Error changing Submission grade.", "Submission Grade Change Error ", JOptionPane.WARNING_MESSAGE);
			panel.setGradeField(panel.getSelectedSubmission().getSubmissionGrade()+""); // reset the grade field
		}
	}

	/**
	 * @return the grade or -1 if error converting the input string
	 */
	private int getGradeFromInput(String input) {
		int grade=-1;
		try {
			if(input.equals("")) 
				throw new Exception();
			 grade = Integer.parseInt(input);
			if (grade<0 || grade >100) 
				throw new Exception();
		}
		catch(Exception e1) {
			System.out.println(e1.getMessage());
			JOptionPane.showMessageDialog(null, "Invalid grade.","Submission Grade Change Error ", JOptionPane.WARNING_MESSAGE);
		}		
		return grade;
	}

}


