
package FrontEnd.FrontController;

import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import FrontEnd.View.ProfGUI;
import FrontEnd.View.ProfHomepage;
import SharedObjects.Course;

/**
 * The ListListener of the CourseList on the ProfHomePage to select a course to view 
 */
public class ProfHomepageListListener implements ListSelectionListener {
	/**
	 * The panel with the button calling this listener
	 */
	private ProfHomepage panel;
	/**
	 * the controller constructing this listener
	 */
	private ProfController controller;

	public ProfHomepageListListener(ProfHomepage p, ProfController c) {
		panel = p;
		controller=c;
	}

	/**
	 * Updates the selected coursePage's course, refreshes the coursePage, switches to it
	 */
	@Override
	public void valueChanged(ListSelectionEvent e) {
		//get the selected course
		JList<Course> list = panel.getCourseList();
		int index = list.getSelectedIndex();
		if (index<0) return;
		Course selected = (Course)  list.getSelectedValue();
		
		//TODO only do the following on double click: 
		//set the course, refresh the coursepage
		ProfGUI pg = ((ProfGUI) controller.getFrame());
		pg.getProfCoursePagePanel().setCourse(selected);
		controller.refreshProfCoursePage(pg);
		pg.setActiveCard("PROFCOURSEPAGE"); // go to the coursePage
	}
}

