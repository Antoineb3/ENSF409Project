
package FrontEnd.FrontController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import FrontEnd.View.ViewStudentsPage;
import SharedObjects.Course;
import SharedObjects.DBMessage;
import SharedObjects.Student;
import SharedObjects.StudentEnrollment;

/**
 * Listener class for the Change Enrollment Status button of a student on the ViewStudentsPage
 * @author 	Antoine Bizon & Ross Bartlett
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
	/**
	 * init the page and the controller	
	 * @param p the page
	 * @param c the controller
	 */
	public ChangeStudentEnrollmentListener(ViewStudentsPage p, ProfController c) {
		panel = p;
		controller=c;
	}

	/**
	 * changes the studen'ts enrollment status in the course 
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		Course course = panel.getCourse();
		Student student = panel.getSelectedStudent();
		int oldEnrollmentID = panel.getSelectedStudentEnrollmentID(); //  if oldEnrollmentID is -1, student is not enrolled in this course
		if (oldEnrollmentID<0) {
			enroll(student, course);
		}
		else {
			unenroll(oldEnrollmentID, student, course);
		}
	}

	/**
	 * Un-Enrolls a student in a course by removing their row form the studentEnrollment table in the DB 
	 * @param selectedStudentEnrollmentID the current enrollment ID of the student, -1 if unenrolled
	 * @param student
	 * @param course
	 */
	private void unenroll(int selectedStudentEnrollmentID, Student student, Course course) {
		//make a message to the DB
		ArrayList<String> params = new ArrayList<>();
		params.add("ID"); // the column to change
		params.add("'"+Integer.toString(selectedStudentEnrollmentID)+"'"); // the conditionVal
		DBMessage msg = new DBMessage(2, 3, params); // 2, 3 is studentEnrollmentTableNum, removeRowOp
		
		//send the message, get response
		ArrayList<? extends Serializable> response = controller.getCommunicator().communicate(msg);
		System.out.println(response.get(0));
		if((int)response.get(0)!=1) { // number of affected rows
			JOptionPane.showMessageDialog(null, "Error un-enrolling "+student.getFirstName()+".", "Enrollment Status Change ERROR ", JOptionPane.WARNING_MESSAGE);
			return;
		}
		JOptionPane.showMessageDialog(null, student.getFirstName()+" has been successfuly un-enrolled from "+course.getName(), "Enrollment Status Change ", JOptionPane.INFORMATION_MESSAGE);
		//update the panel field
		panel.setSelectedStudentEnrollmentID(-1);

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
		DBMessage msg = new DBMessage(2, 2, params); // 2,2 is studentEnrollmentTableNum, addOpNum
		//send the message, get response
		ArrayList<? extends Serializable> response = controller.getCommunicator().communicate(msg);
		if((int)response.get(0)!=1) { // number of affected rows
			JOptionPane.showMessageDialog(null, "Error enrolling "+student.getFirstName()+".", "Enrollment Status Change ERROR 1", JOptionPane.WARNING_MESSAGE);
			System.out.println("(int)response.get(0) is: "+(int)response.get(0));
			return;
		}
		
		//make a message to get the enrollmentID just made
		ArrayList<String> params2 = new ArrayList<>();
		params2.add("STUDENTID");	
		params2.add("'"+Integer.toString(student.getID())+"'");
		DBMessage msg2 = new DBMessage(2, 0, params2); // 2,0 is studentEnrollmentTableNum, searchOpNum
		ArrayList<? extends Serializable> response2 = controller.getCommunicator().communicate(msg2);
		//response contains all the StudentEnrollments of that student
		int enrollmentID=-1;
		for (Serializable enrollment : response2) {
			if(((StudentEnrollment) enrollment).getCourseID()==course.getID()) {
				enrollmentID = ((StudentEnrollment) enrollment).getID();
			}
		}
		System.out.println("enrollment ID: "+enrollmentID);
		if(enrollmentID>=0) 
			JOptionPane.showMessageDialog(null, student.getFirstName()+" has been successfuly enrolled in "+course.getName(), "Enrollment Status Change ", JOptionPane.INFORMATION_MESSAGE);
		else 
			JOptionPane.showMessageDialog(null, "Error enrolling "+student.getFirstName()+".", "Enrollment Status Change ERROR 2", JOptionPane.WARNING_MESSAGE);
		//update the panel field
		panel.setSelectedStudentEnrollmentID(enrollmentID);
	}


}


