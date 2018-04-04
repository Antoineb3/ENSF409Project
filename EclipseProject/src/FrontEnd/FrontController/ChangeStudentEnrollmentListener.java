
package FrontEnd.FrontController;

import java.awt.AWTEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import FrontEnd.View.ProfCoursePage;
import FrontEnd.View.ViewStudentsPage;
import SharedObjects.Assignment;
import SharedObjects.Course;
import SharedObjects.DBMessage;
import SharedObjects.FileMessage;
import SharedObjects.Student;
import SharedObjects.StudentEnrollment;

/**
 * Listener class for the Change Enrollment Status button of a student on the ViewStudentsPage
 * @author Ross
 */
public class ChangeStudentEnrollmentListener implements ActionListener{
	/**
	 * The panel with the button calling this listener
	 */
	private ViewStudentsPage panel;
	/**
	 * the controller constructing this listener
	 */
	private ProfController controller;

	public ChangeStudentEnrollmentListener(ViewStudentsPage p, ProfController c) {
		panel = p;
		controller=c;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Course course = panel.getCourse();
		Student student = panel.getSelectedStudent();
		int oldEnrollmentID = panel.getSelectedStudentEnrollmentID(); //  if oldEnrollmentID is -1, student is not enrolled in this course
		if (oldEnrollmentID<0) {
			//enrolling the student
			enroll(student, course);
		}
		else {
			//un-enrolling the student
			unenroll(oldEnrollmentID, student, course);
		}

	}

	/**
	 * Un-Enrolls a student in a course by editing a row from the StudentEnrollmentTable to have studentID = -1
	 * @param student
	 * @param course
	 */
	private void unenroll(int selectedStudentEnrollmentID,   Student student, Course course) {
		// to unenroll a student, set their student id in their enrollment row in the enrollment table to -1

		//make a message to edit the DB
		ArrayList<String> params = new ArrayList<>();
		params.add("ID"); // the column in the table to search by
		params.add("'"+Integer.toString(selectedStudentEnrollmentID)+"'"); // the search key
		params.add("STUDENT_ID"); // the column to change
		params.add("'"+Integer.toString(-1)+"'"); // the new data 
		DBMessage msg = new DBMessage(0, 0, 2, 1, params); // 2, 1 is studentEnrollmentTableNum, editRowOp
		//send the message, get response
		ArrayList<? extends Serializable> response = controller.getCommunicator().communicate(msg);
		int enrollmentID = ((StudentEnrollment) response.get(0)).getID();
		if(enrollmentID<0) 
			JOptionPane.showMessageDialog(null, student.getFirstName()+" has been successfuly un-enrolled from "+course.getName(), "Enrollment Status Change ", JOptionPane.INFORMATION_MESSAGE);
		else 
			JOptionPane.showMessageDialog(null, "Error un-enrolling "+student.getFirstName()+".", "Enrollment Status Change ERROR ", JOptionPane.WARNING_MESSAGE);
		//update the panel fields
		panel.setSelectedStudentEnrollmentID(enrollmentID);

	}

	/**
	 * Enrolls a student in a course by adding a row to the StudentEnrollmentTable
	 * @param student
	 * @param course
	 */
	private void enroll(Student student, Course course) {
		//construct new student enrollment
		//make a message to tell the DB to add the studentEnrollment
		StudentEnrollment studentEnrollment = new StudentEnrollment(-1, student.getID(), course.getID());

		ArrayList<StudentEnrollment> params = new ArrayList<>();
		params.add(studentEnrollment);	
		DBMessage msg = new DBMessage(0, 0, 2, 2, params); // 2,2 is studentEnrollmentTableNum, addOpNum
		//send the message, get response
		ArrayList<? extends Serializable> response = controller.getCommunicator().communicate(msg);
		int enrollmentID = ((StudentEnrollment) response.get(0)).getID();
		if(enrollmentID>=0) 
			JOptionPane.showMessageDialog(null, student.getFirstName()+" has been successfuly enrolled in "+course.getName(), "Enrollment Status Change ", JOptionPane.INFORMATION_MESSAGE);
		else 
			JOptionPane.showMessageDialog(null, "Error enrolling "+student.getFirstName()+".", "Enrollment Status Change ERROR ", JOptionPane.WARNING_MESSAGE);
		//update the panel fields
		panel.setSelectedStudentEnrollmentID(enrollmentID);
	}


}


