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
		boolean oldStatus = assignment.getActive();
		String newStatusBitString = (oldStatus==false)? "b'1'":"b'0'";
		//make a message to edit the DB
		ArrayList<String> params = new ArrayList<>();
		params.add("ACTIVE"); // the column to change
		params.add(newStatusBitString); // the new data key
		params.add("ID"); // the column in the table to search by (condition)
		params.add("'"+Integer.toString(assignment.getID())+"'"); // the conditionVal 
		DBMessage msg = new DBMessage(0, 0, 3, 1, params); // 3, 1 is assignmentTableNum, editRowOp

		//send the message, get response
		ArrayList<? extends Serializable> response = controller.getCommunicator().communicate(msg);
		//get the new status of the assignment
		boolean status = ((Assignment) response.get(0)).getActive();
		if (status==oldStatus) {
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


