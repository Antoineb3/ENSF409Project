/**
 * 
 */
package FrontEnd.FrontController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import FrontEnd.View.ProfCoursePage;
import SharedObjects.Course;
import SharedObjects.DBMessage;

/**
 * Listener class for the Change Active Status button of a course
 * @author Ross
 */
public class ChangeCourseStatusListener implements ActionListener{
	/**
	 * The panel with the button calling this listener
	 */
	private ProfCoursePage panel;
	/**
	 * the controller constructing this listener
	 */
	private ProfController controller;

	public ChangeCourseStatusListener(ProfCoursePage p, ProfController c) {
		panel = p;
		controller=c;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Course course = panel.getCourse();
		int oldStatus = course.getActive();
		int newStatus = (oldStatus==0)? 1:0;
		//make a message to edit the DB
		ArrayList<String> params = new ArrayList<>();
		params.add("id"); // the column in the table to search by
		params.add(Integer.toString(course.getID())); // the search key
		params.add("active"); // the column to change
		params.add(Integer.toString(newStatus)); // the new data
		DBMessage msg = new DBMessage(0, 0, 1, 1, params); // 1, 1 is courseTableNum, editRowOp

		//send the message, get response
		ArrayList<? extends Serializable> response = controller.getCommunicator().communicate(msg);
		//get the new status of the course
		int status = ((Course) response.get(0)).getActive();
		if (status!=newStatus) {
			System.out.println("Error: course status did not change in db...");
			JOptionPane.showMessageDialog(null, "Error: Course status could not be changed.", "Course Status Change Error", JOptionPane.WARNING_MESSAGE);
		}
		else {
			JOptionPane.showMessageDialog(null, "Course status changed.", "Course Status Change ", JOptionPane.INFORMATION_MESSAGE);
			//update the JLabel
			panel.setActiveStatusText(status);
		}
	}


}


