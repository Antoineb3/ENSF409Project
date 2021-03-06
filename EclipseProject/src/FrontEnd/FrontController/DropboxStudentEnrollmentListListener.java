package FrontEnd.FrontController;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JList;

import FrontEnd.View.DropboxPage;
import SharedObjects.DBMessage;
import SharedObjects.Grade;
import SharedObjects.StudentEnrollment;
import SharedObjects.Submission;

/**
 * Listener for the student enrollment list on the dropbox page 
 * @author 	Ross Bartlett, Antoine Bizon
 */
public class DropboxStudentEnrollmentListListener extends MouseAdapter{
	/**
	 * the controller constructing this listener
	 */
	private ProfController controller;
	/**
	 * the panel with this list 
	 */
	private DropboxPage panel;
	/**
	 * constructor to initialize the fields
	 * @param p the frame/panel
	 * @param c the controller
	 */
	public DropboxStudentEnrollmentListListener(DropboxPage p, ProfController c) {
		controller=c;
		panel=p;
	}

	/**
	 * On click, Updates the submission list to show the selected student enrollment's submissions
	 */
	@Override
	public void mouseClicked(MouseEvent evt) {
		
		JList<StudentEnrollment> list = (JList<StudentEnrollment>) evt.getSource();
		int index = list.locationToIndex(evt.getPoint());
		if (index<0) return;
		panel.getGradeField().setText("");
		panel.setSelectedSubmission(null);
		StudentEnrollment selectedStudentEnrollment = (StudentEnrollment) list.getSelectedValue();
		
		//query all the submissions of the selected student for that assignment
		ArrayList<String> params = new ArrayList<>();
		params.add("ASSIGNID"); // the column in the table to search
		params.add("'"+panel.getAssignment().getID()+"'"); // the search key 
		params.add("STUDENTID");
		params.add("'"+selectedStudentEnrollment.getStudentID()+"'");
				
		DBMessage msg = new DBMessage(4, 0, params); // 4, 0 is submissionTableNum, searchOpNum
		//response should be a list of submissions
		ArrayList<? extends Serializable> response = controller.getCommunicator().communicate(msg);
		DefaultListModel<Submission> submissionListModel = new DefaultListModel<>();
		for(Object sub : response) {
			submissionListModel.addElement((Submission)sub);
		}
		panel.updateSubmissionList(submissionListModel);
		
		//update the finalGradeField to the finalGrade of the selected student
		updateFinalGradeField(selectedStudentEnrollment); 	}
	/**
	 * updates the texField to show the final grade  of the selected student 
	 * @param selectedStudentEnrollment
	 */
	private void updateFinalGradeField(StudentEnrollment selectedStudentEnrollment) {
		//2 param search
		ArrayList<String> params2 = new ArrayList<>();
		params2.add("ASSIGNID"); // the column in the table to search
		params2.add("'"+panel.getAssignment().getID()+"'"); // the search key 
		params2.add("STUDENTID"); 
		params2.add("'"+selectedStudentEnrollment.getStudentID()+"'");
		DBMessage msg2 = new DBMessage(5, 0, params2); // 5, 0 is gradeTableNum, searchOpNum
		//response should be a grade
		ArrayList<? extends Serializable> response2 = controller.getCommunicator().communicate(msg2);
		if(response2==null) {
			System.err.println("response2 is null getting Grade in DropboxStudentEnrollmentListLIstener");
			return;
		}
		if(response2.size()==0) {
			//no submissions yet 
			System.out.println("response2 size is 0 in DropboxStudentEnrollmentListListener so student made no submissions yet ");
			panel.setFinalGradeField("000");
			return;
		}
		try {
			panel.setFinalGradeField(""+((Grade) response2.get(0)).getAssignmentGrade());
		}catch (Exception e) {
			System.err.println("error getting final grade in DropboxStudentEnrollmentListLIstener");
			return;
		}
	}
	
}
