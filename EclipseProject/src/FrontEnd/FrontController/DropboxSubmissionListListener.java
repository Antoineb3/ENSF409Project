
package FrontEnd.FrontController;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JList;

import FrontEnd.View.DropboxPage;
import SharedObjects.Submission;

/**
 * Listener for the 
 * @author 	Antoine Bizon & Ross Bartlett
 */
public class DropboxSubmissionListListener extends MouseAdapter{
	/**
	 * the controller constructing this listener
	 */
	private ProfController controller;
	/**
	 * the panel with this list 
	 */
	private DropboxPage panel;

	public DropboxSubmissionListListener(ProfController c) {
		controller=c;

	}

	/**
	 * On click, set the selectedSubmission on the dropbox page and update the grade field 
	 */
	@Override
	public void mouseClicked(MouseEvent evt) {
		
		JList<Submission> list = (JList<Submission>) evt.getSource();
		int index = list.locationToIndex(evt.getPoint());
		if (index<0) return;
		Submission selectedSub = (Submission) list.getSelectedValue();
		panel.setSelectedSubmission(selectedSub);
		
		//update the gradeField to the submissionGrade of the selected Submission
//		panel.setGradeField(selectedSub.getSubmissionGrade()+""); // TODO uncomment when grade is implemented
	}
	
}

