
package FrontEnd.FrontController;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JList;
import FrontEnd.View.StudentGUI;
import SharedObjects.Assignment;

/**
 * Listener for the assignmentList on the StudentCoursePage using MouseAdapter
 * @author 	Antoine Bizon & Ross Bartlett
 */
public class StudentCoursePageListListener extends MouseAdapter{
	/**
	 * the controller constructing this listener
	 */
	private StudentController controller;

	/**
	 * initializes the controller 
	 * @param c the controller
	 */
	public StudentCoursePageListListener(StudentController c) {
		controller=c;
	}

	/**
	 * On double click, Updates the selected assignmentPage's assignment, refreshes the assignment page, switches to it
	 */
	@Override
	public void mouseClicked(MouseEvent evt) {
		
		JList<Assignment> list = (JList<Assignment>) evt.getSource();
		int index = list.locationToIndex(evt.getPoint());
		if (index<0) return;
		Assignment selected = (Assignment) list.getSelectedValue();

		// if Double-click detected
		if (evt.getClickCount() == 2) {
			//set the assignment, refresh the assignmentPage
			StudentGUI sg = ((StudentGUI) controller.getFrame());
			sg.getStudentAssignmentPanel().setAssignment(selected);
			controller.refreshStudentAssignmentPage(sg);
			sg.setActiveCard("STUDASSIGNMENTPAGE"); // go to the assignmentPage

		} 
	}
	
}
