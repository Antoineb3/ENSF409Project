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

		boolean oldStatus = course.getActive();

		String newStatusBitString = (oldStatus==false)? "b'1'":"b'0'";
		//make a message to edit the DB
		ArrayList<String> params = new ArrayList<>();
		params.add("ACTIVE"); // the column to change
		params.add(newStatusBitString); // the new data
		params.add("ID"); // the condition condition
		params.add("'"+Integer.toString(course.getID())+"'"); // the condition val
		DBMessage msg = new DBMessage(1, 1, params); // 1, 1 is courseTableNum, editRowOp

		//send the message, get response
		ArrayList<? extends Serializable> response = controller.getCommunicator().communicate(msg);
		
		if (response.size()!=1) {
			JOptionPane.showMessageDialog(null, "Error: Course status could not be changed.", "Course Status Change Error", JOptionPane.WARNING_MESSAGE);
		}
		else {
			JOptionPane.showMessageDialog(null, "Course status changed.", "Course Status Change ", JOptionPane.INFORMATION_MESSAGE);

			//update the panel's JLabel and status of its stored course
			panel.setActiveStatusText(!oldStatus);
			panel.getCourse().setActive(!oldStatus);
			System.out.println("Course "+panel.getCourse().getName()+" has been set to active="+panel.getCourse().getActive());
		}
	}


}


