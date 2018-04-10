package FrontEnd.FrontController;

import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.DefaultListModel;

import com.sun.javafx.font.PGFont;

import FrontEnd.FrontController.ProfController.CardChangerListener;
import FrontEnd.View.GradePage;
import FrontEnd.View.StudentCoursePage;
//import FrontEnd.View.StudentAssignmentPage;
//import FrontEnd.View.StudentCoursePage;
import FrontEnd.View.StudentGUI;
import FrontEnd.View.StudentHomepage;
import SharedObjects.*;


/**
 * 
 * @author 	Antoine Bizon & Ross Bartlett
 *
 */

public class StudentController extends ViewController{


	public StudentController(StudentGUI sg, ClientSocketCommunicator c){
		super(sg, c); 

		//set all the back to homepage buttons
		sg.getStudentCoursePagePanel().setHomepageButtonListener(new CardChangerListener("STUDHOMEPAGE"));
		sg.getStudentAssignmentPanel().setHomepageButtonListener(new CardChangerListener("STUDHOMEPAGE"));
		sg.getEmailPage().setHomepageButtonListener(new CardChangerListener("STUDHOMEPAGE"));
		sg.getGradePage().setHomepageButtonListener(new CardChangerListener("STUDHOMEPAGE"));
		//set all the back buttons
		sg.getStudentAssignmentPanel().setBackButtonListener(new CardChangerListener("STUDCOURSEPAGE"));
		sg.getEmailPage().setBackButtonListener(new CardChangerListener("STUDCOURSEPAGE"));
		sg.getGradePage().setBackButtonListener(new CardChangerListener("STUDCOURSEPAGE"));


		//set other navigator buttons
		sg.getStudentCoursePagePanel().setEmailButtonListener(new CardChangerListener("EMAILPAGE"));
		sg.getStudentCoursePagePanel().setViewGradesButtonListener(new CardChangerListener("GRADEPAGE"));

		//set upload submission listener
		sg.getStudentAssignmentPanel().setUploadSubmissionButtonListener(new UploadSubmissionButtonListener(sg.getStudentAssignmentPanel(), sg.getStudent(),this));


		//set list listeners
		sg.getStudentHomePagePanel().setListListener(new StudentHomepageListListener(this));
		sg.getStudentCoursePagePanel().setListListener(new StudentCoursePageListListener(this));
		sg.getGradePage().setAssignmentListListener(new GradePageListListener(sg.getGradePage(),this));

		//download assignment button
		sg.getStudentAssignmentPanel().setDownloadButtonListener(new DownloadButtonListener(sg.getStudentAssignmentPanel(), this));
		//send email button 
		sg.getEmailPage().setSendButtonListener(new SendEmailListener(sg.getEmailPage(), this));

		//update the courseList on the homepage , as this is the first active card 
		fillHomePageCourseList(sg.getStudentHomePagePanel()); //TODO uncomment this when connections are ready
	}



	/**
	 * Inner class listener to switch the active card on the StudentGUI using either the BackToHomepage or Back buttons
	 */
	class CardChangerListener implements ActionListener {
		String card;
		public CardChangerListener(String c) {
			card = c;
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			StudentGUI sg = ((StudentGUI) getFrame()); // from super
			//			System.out.println("Card changer button pressed. going to: "+card);
			if(card.equals("STUDASSIGNMENTPAGE")){
				refreshStudentAssignmentPage(sg);
			}
			else if(card.equals("STUDCOURSEPAGE")){
				refreshStudentCoursePage(sg);
			}
			else if(card.equals("STUDHOMEPAGE")){
				//note: homepage's welcomeText with the student's name never needs to be refreshed
				fillHomePageCourseList(sg.getStudentHomePagePanel()); // update/refresh the course list
			}
			else if (card.equals("GRADEPAGE")) {
				sg.getGradePage().setCourse(sg.getStudentCoursePagePanel().getCourse());
				refreshGradePage(sg.getGradePage());
			}
			sg.setActiveCard(card);
		}

	}




	/**
	 * refreshes the asssignmentList and the JLabel on the StudentCoursePage
	 * @param sg the StudentGUI
	 */
	void refreshStudentCoursePage(StudentGUI sg) {
		fillCoursePageAssignmentList(sg.getStudentCoursePagePanel()); // update/refresh the assignment list
	}

	/**
	 * updates the assignment list on the grade page
	 * @param panel the GradePage
	 */
	public void refreshGradePage(GradePage panel) {
		panel.setGradeField("   ");
		//fill the list with active assignments of that course 
		DefaultListModel<Assignment> listModel = new DefaultListModel<>();
		//make a message to query all the assignments in this course
		ArrayList<String> params = new ArrayList<>();
		params.add("COURSEID"); // the column in the table to search
		params.add("'"+panel.getCourse().getID()+"'"); // the search key 
		DBMessage msg = new DBMessage(3, 0, params); // 3, 0 is assignmentTableNum, searchOpNum

		//send the message, get response of all student enrollment this student is associated with  
		ArrayList<? extends Serializable> response = getCommunicator().communicate(msg);

		//for each assignment of this course
		for(Object a : response) {
			if(((Assignment) a).getActive() == true) // only show active assignments
				listModel.addElement((Assignment)a);
			
		}

		// then do the update: 
		panel.updateAssignmentList(listModel);

		//now update the averageGrade field
		panel.setAverageField(calcGradeAvg(this,response));
		
	}

	/**
	 * @return the grade avg using all the assignments in the list on the grade page, or -1 if error
	 */
	private double calcGradeAvg(StudentController controller, ArrayList<? extends Serializable> list ) {
		int sum=0 ,count = 0;
		for(Object a : list) {
			count++;
			//send message to get each assignments final grade
			ArrayList<String> params = new ArrayList<>();
			params.add("ASSIGNID"); // the column in the table to search
			params.add("'"+((Assignment) a).getID()+"'"); // the search key 
			params.add("STUDENTID");
			params.add("'"+ ((StudentGUI) controller.getFrame()).getStudent().getID()+"'");
			DBMessage msg = new DBMessage(5, 0, params); // 5, 0 is gradeTableNum, searchOpNum
		
			//send the message, get response grade 
			ArrayList<? extends Serializable> response = controller.getCommunicator().communicate(msg);
			if(response==null || response.size()<1) {
				System.err.println("error getting assignment grade in calcGradeAvg");
				return -1;
			}
			sum += ((Grade) response.get(0)).getAssignmentGrade();
		}
		return sum/(double)count;
	}

	/**
	 * TODO BONUS: refreshes the assignment file shower
	 * @param sg
	 */
	void refreshStudentAssignmentPage(StudentGUI sg) {
		//TODO BONUS: update the file shower using sg.getStudentAssignmentPanel().updateAssignmentDisplay(STRING);. call this in StudentCoursePageAssignmentListListener
	}

	/**	
	 * helper method to fill the homepage courseList with ACTIVE courses that the student is enrolled in using the DB table. 
	 * @param homepagePanel the homepagePanel
	 */
	private void fillHomePageCourseList(StudentHomepage homepagePanel) {
		DefaultListModel<Course> listModel = new DefaultListModel<>();
		//make a message to query all the courses this student is enrolled in 
		ArrayList<String> params = new ArrayList<>();
		params.add("STUDENTID"); // the column in the table to search
		params.add("'"+homepagePanel.getStudent().getID()+"'"); // the search key 
		DBMessage msg = new DBMessage(2, 0, params); // 2, 0 is studentEnrollmentTableNum, searchOpNum

		//send the message, get response of all student enrollment this student is associated with  
		ArrayList<? extends Serializable> response = getCommunicator().communicate(msg);

		//for each studentEnrollment of this student
		for(Object se : response) {

			//get the course associated with that studentEnrollment
			ArrayList<String> params2 = new ArrayList<>();
			params2.add("ID"); // the column in the table to search
			params2.add("'"+Integer.toString(((StudentEnrollment) se).getCourseID())+"'"); // the search key 
			DBMessage msg2 = new DBMessage(1, 0, params2); // 1, 0 is courseTableNum, searchOpNum

			//send the message, get response containing that course
			ArrayList<? extends Serializable> response2 = getCommunicator().communicate(msg2);
			for(Object co : response2) { // should just be one course returned 
				if(((Course) co).getActive() == true) // only show active courses that the student is enrolled in 
					listModel.addElement((Course)co);
			}
		}
		// then do the update: 
		homepagePanel.updateCourseList(listModel);
	}


	/**
	 * helper method to fill the coursePage's assignmentList using the DB table. 
	 * package scope so that CreateNewAssignmentButton listener can use it 
	 * @param coursePage the course page
	 */
	void fillCoursePageAssignmentList(StudentCoursePage coursePage) {

		//make a message to query all the assignments in a course
		ArrayList<String> params = new ArrayList<>();
		params.add("COURSEID"); // the column in the table to search
		params.add("'"+Integer.toString(coursePage.getCourse().getID())+"'"); // the search key 
		DBMessage msg = new DBMessage(3, 0, params); // 3, 0 is assignemntTableNum, searchOpNum

		//send the message, get response
		ArrayList<? extends Serializable> response = getCommunicator().communicate(msg);

		// convert the returned arraylist to a listmodel 
		DefaultListModel<Assignment> listModel = new DefaultListModel<>();
		for(Object co : response) {
			if(((Assignment) co).getActive() == true) // only show the active assignments 
				listModel.addElement((Assignment)co);
		}
		// then do the update: 
		coursePage.updateAssignmentList(listModel);
	}


}
