
package FrontEnd.FrontController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import FrontEnd.View.DropboxPage;
import SharedObjects.DBMessage;
import SharedObjects.Grade;


/**
 * Listener to set the final grade of an assignment thru the Dropbox page 
 * @author 	Ross Bartlett, Antoine Bizon
 */
public class DropboxSetFinalGradeListener implements ActionListener {
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
	public DropboxSetFinalGradeListener(DropboxPage p, ProfController c) {
		panel = p;
		controller=c;
	}

	/**
	 * Sets the final grade of an assignment 
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		
		//first send a message to get the GradeID that matches this assignment and student (2 param search)
		ArrayList<String> params = new ArrayList<>();
		params.add("ASSIGNID"); // the column in the table to search
		params.add("'"+panel.getAssignment().getID()+"'"); // the search key 
		params.add("STUDENTID"); 
		params.add("'"+panel.getSelectedSubmission().getStudentID()+"'");
		DBMessage msg = new DBMessage(5, 0, params); // 5, 0 is gradeTableNum, searchRowOp
		//send the message, get response
		ArrayList<? extends Serializable> response = controller.getCommunicator().communicate(msg);
		if(response==null || response.size()<1) {
			System.err.println("error: couldnt get gradeID in SetFinalGradeListener");
			JOptionPane.showMessageDialog(null, "Error setting final grade.","Set Final Grade Error", JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		int gradeID = (int)((Grade) response.get(0)).getID();
		int finalGradeToSet = panel.getSelectedSubmission().getSubmissionGrade();
		
		//make a message to edit the GradeDB
		ArrayList<String> params2 = new ArrayList<>();
		params2.add("ASSIGNMENTGRADE"); // the column to change
		params2.add("'"+finalGradeToSet+"'"); // the new data key
		params2.add("ID"); // the column in the table to search by (condition)
		params2.add("'"+gradeID+"'"); // the conditionVal 
		DBMessage msg2 = new DBMessage(5, 1, params2); // 5, 1 is gradeTableNum, editRowOp
		//send the message, get response
		ArrayList<? extends Serializable> response2 = controller.getCommunicator().communicate(msg2);
		
		if(response2.size()==1 && (int) response2.get(0)==1) { // success
			JOptionPane.showMessageDialog(null, "Student's Final Grade set to "+finalGradeToSet+".", "Final Grade Change ", JOptionPane.INFORMATION_MESSAGE);
			panel.setFinalGradeField(finalGradeToSet+""); 
		}
		else {
			JOptionPane.showMessageDialog(null, "Error setting final grade.", "Final Grade Change Error ", JOptionPane.WARNING_MESSAGE);
		}
	}


}


