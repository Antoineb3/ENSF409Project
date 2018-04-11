
package FrontEnd.FrontController;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JList;
import FrontEnd.View.ProfGUI;
import SharedObjects.Course;

/**
 * Listener for the CourseList on the ProfHomePage using MouseAdapter instead of 
 * @author 	Antoine Bizon & Ross Bartlett
 */
public class ProfHomepageListListener extends MouseAdapter{
	/**
	 * the controller constructing this listener
	 */
	private ProfController controller;
	/**
	 * constructor to initialize the controller
	 * @param c the controller
	 */
	public ProfHomepageListListener(ProfController c) {
		controller=c;
	}

	/**
	 * On double click, Updates the selected assignmentPage's assignment, refreshes the assignment page, switches to it
	 */
	@Override
	public void mouseClicked(MouseEvent evt) {

		JList<Course> list = (JList<Course>) evt.getSource();
		int index = list.locationToIndex(evt.getPoint());
		if (index<0) return;
		Course selected = (Course) list.getSelectedValue();

		// if Double-click detected
		if (evt.getClickCount() == 2) {
			//set the course, refresh the course Page
			ProfGUI pg = ((ProfGUI) controller.getFrame());
			pg.getProfCoursePagePanel().setCourse(selected);
			controller.refreshProfCoursePage(pg);
			pg.setActiveCard("PROFCOURSEPAGE"); // go to the coursePage

		} 
	}



}
