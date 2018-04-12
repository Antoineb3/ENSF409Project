/**
 * 
 */
package FrontEnd.FrontController;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JList;
import FrontEnd.View.ProfGUI;
import SharedObjects.Assignment;

/**
 * Listener for the assignmentList on the ProfCoursePage using MouseAdapter 
 * @author 	Ross Bartlett, Antoine Bizon
 */
public class ProfCoursePageListListener extends MouseAdapter{
	/**
	 * the controller constructing this listener
	 */
	private ProfController controller;
	/**
	 * constructor to initialize the controller
	 * @param c the controller
	 */
	public ProfCoursePageListListener(ProfController c) {
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
			ProfGUI pg = ((ProfGUI) controller.getFrame());
			pg.getProfAssignmentPanel().setAssignment(selected);
			controller.refreshProfAssignmentPage(pg);
			pg.getDropboxPage().setAssignment(selected);
			pg.setActiveCard("PROFASSIGNMENTPAGE"); // go to the assignmentPage

		} 
	}
	
}
