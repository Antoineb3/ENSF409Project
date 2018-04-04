/**
 * 
 */
package FrontEnd.FrontController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import FrontEnd.View.ProfAssignmentPage;
import SharedObjects.Assignment;
import SharedObjects.DBMessage;

/**
 * Listener class for the Change Active Status button of an assignment 
 * @author Ross
 */
public class ChangeAssignmentStatusListener implements ActionListener{
	/**
	 * The panel with the button calling this listener
	 */
	private ProfAssignmentPage panel;
	/**
	 * the controller constructing this listener
	 */
	private ProfController controller;

	public ChangeAssignmentStatusListener(ProfAssignmentPage p, ProfController c) {
		panel = p;
		controller=c;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Assignment assignment = panel.getAssignment();
		int oldStatus = assignment.getActive();
		int newStatus = (oldStatus==0)? 1:0;
		//make a message to edit the DB
		ArrayList<String> params = new ArrayList<>();
		params.add("id"); // the column in the table to search by
		params.add(Integer.toString(assignment.getID())); // the search key
		params.add("active"); // the column to change
		params.add(Integer.toString(newStatus)); // the new data
		DBMessage msg = new DBMessage(0, 0, 3, 1, params); // 3, 1 is courseTableNum, editRowOp

		//send the message, get response
		ArrayList<? extends Serializable> response = controller.getCommunicator().communicate(msg);
		//get the new status of the assignment
		int status = ((Assignment) response.get(0)).getActive();
		if (status!=newStatus) {
			System.out.println("Error: assignment status did not change in db...");
			JOptionPane.showMessageDialog(null, "Error: Assignment status could not be changed.", "Assignment Status Change Error", JOptionPane.WARNING_MESSAGE);
		}
		else {
			JOptionPane.showMessageDialog(null, "Assignment status changed.", "Assignment Status Change ", JOptionPane.INFORMATION_MESSAGE);
			//update the JLabel
			panel.setActiveStatusText(status);
		}
	}


}


