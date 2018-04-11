
package FrontEnd.FrontController;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JList;
import FrontEnd.View.StudentGUI;
import SharedObjects.Course;

/**
 * Listener for the CourseList on the StudentHomePage using MouseAdapter
 * @author 	Antoine Bizon & Ross Bartlett
 */
public class StudentHomepageListListener extends MouseAdapter{
	/**
	 * the controller constructing this listener
	 */
	private StudentController controller;
	/**
	 * Constructor that inits the controller
	 * @param c the controller to
	 */
	public StudentHomepageListListener(StudentController c) {
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
			StudentGUI sg = ((StudentGUI) controller.getFrame());
			sg.getStudentCoursePagePanel().setCourse(selected);
			controller.refreshStudentCoursePage(sg);
			sg.setActiveCard("STUDCOURSEPAGE"); // go to the coursePage
		} 
	}

}
