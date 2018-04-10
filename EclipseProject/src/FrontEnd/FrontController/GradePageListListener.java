package FrontEnd.FrontController;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.JList;
import javax.swing.JOptionPane;

import FrontEnd.View.GradePage;
import FrontEnd.View.StudentGUI;
import SharedObjects.Assignment;
import SharedObjects.DBMessage;
import SharedObjects.Grade;

/**
 * Listener for the assignmentList on the grade page
 * @author 	Antoine Bizon & Ross Bartlett
 */
public class GradePageListListener extends MouseAdapter{
	/**
	 * the grade page 
	 */
	private GradePage panel;
	/**
	 * the controller constructing this listener
	 */
	private StudentController controller;

	public GradePageListListener(GradePage p, StudentController c) {
		controller=c;
		panel=p;
	}

	/**
	 * On click, updates the final grade label
	 */
	@Override
	public void mouseClicked(MouseEvent evt) {

		JList<Assignment> list = (JList<Assignment>) evt.getSource();
		int index = list.locationToIndex(evt.getPoint());
		if (index<0) return;
		Assignment selected = (Assignment) list.getSelectedValue();
		
		//get the student's final grade for that assignment
		ArrayList<String> params = new ArrayList<>();
		params.add("ASSIGNID"); // the column in the table to search
		params.add("'"+selected.getID()+"'"); // the search key 
		params.add("STUDENTID");
		params.add("'"+((StudentGUI) controller.getFrame()).getStudent().getID()+"'");
		DBMessage msg = new DBMessage(5, 0, params); // 5, 0 is gradeTableNum, searchOpNum
	
		//send the message, get response grade 
		ArrayList<? extends Serializable> response = controller.getCommunicator().communicate(msg);
		if(response==null || response.size()<1) {
			System.err.println("error getting assignment grade in GradePageLIistLIstener");
			return;
		}
		panel.setGradeField( ((Grade) response.get(0)).getAssignmentGrade()+"");

	}

}
