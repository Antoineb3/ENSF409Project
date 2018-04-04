
package FrontEnd.FrontController;


import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


import FrontEnd.View.ProfCoursePage;
import FrontEnd.View.ProfGUI;
import SharedObjects.Assignment;

/**
 * The ListListener of the assingmentList on the ProfCoursePage to select an assignment to view 
 */
public class ProfCoursePageAssignmentListListener implements ListSelectionListener {
	/**
	 * The panel with the button calling this listener
	 */
	private ProfCoursePage panel;
	/**
	 * the controller constructing this listener
	 */
	private ProfController controller;

	public ProfCoursePageAssignmentListListener(ProfCoursePage p, ProfController c) {
		panel = p;
		controller=c;
		
	}

	/**
	 * Updates the selected assignmentPage's assignment, refreshes the assignment page, switches to it
	 */
	@Override
	public void valueChanged(ListSelectionEvent e) {
		//get the selected course
		JList<Assignment> list = panel.getAssignmentList();
		int index = list.getSelectedIndex();
		if (index<0) return;
		Assignment selected = (Assignment) list.getSelectedValue();
		
		//TODO: do the rest if double click or make a GoToPage Button
		//set the assignment, refresh the assignmentPage
		ProfGUI pg = ((ProfGUI) controller.getFrame());
		pg.getProfAssignmentPanel().setAssignment(selected);
		controller.refreshProfAssignmentPage(pg);
		pg.setActiveCard("PROFASSIGNMENTPAGE"); // go to the assignmentPage
		
	}
}

