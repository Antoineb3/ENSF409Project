package FrontEnd.FrontController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;

import FrontEnd.View.ProfAssignmentPage;
import FrontEnd.View.ProfCoursePage;
import FrontEnd.View.ProfGUI;
import FrontEnd.View.ProfHomepage;
import SharedObjects.*;


//TODO what relationship between actionListeners and ProfController ? ViewController?


//TODO in the listener that goes to a course page, use pg.getProfCoursePagePanel().setCourse(theCourse) before setActiveCard()
//and before going to  viewStudents page

public class ProfController extends ViewController{


	public ProfController(ProfGUI pg, ClientSocketCommunicator c){
		super(pg, c); 

		//set all the back to homepage buttons
		pg.getProfCoursePagePanel().setHomepageButtonListener(new CardChangerListener("PROFHOMEPAGE"));
		pg.getViewStudentsPanel().setHomepageButtonListener(new CardChangerListener("PROFHOMEPAGE"));
		pg.getProfAssignmentPanel().setHomepageButtonListener(new CardChangerListener("PROFHOMEPAGE"));

		//set all the back buttons
		pg.getProfAssignmentPanel().setBackButtonListener(new CardChangerListener("PROFCOURSEPAGE"));
		pg.getViewStudentsPanel().setBackButtonListener(new CardChangerListener("PROFCOURSEPAGE"));


		//set other navigator buttons
		pg.getProfCoursePagePanel().setViewStudentsButtonListener(new CardChangerListener("VIEWSTUDENTSPAGE"));

		//set new item listeners 
		pg.getProfCoursePagePanel().setNewAssignmentButtonListener(new NewAssignmentButtonListener(pg.getProfCoursePagePanel(), this));
		pg.getProfHomePagePanel().setCreateNewCourseButtonListener(new NewCourseButtonListener(pg.getProfHomePagePanel(), this));


		//update the courseList on the homepage
//		fillHomePageCourseList(pg.getProfHomePagePanel()); //TODO uncomment this when connections are ready
	}



	/**
	 * Inner class listener to switch the active card on the ProfGUI
	 */
	class CardChangerListener implements ActionListener {
		String card;
		public CardChangerListener(String c) {
			card = c;
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			ProfGUI pg = ((ProfGUI) frame); 
			System.out.println("Card changer button pressed. going to: "+card);
			if(card.equals("PROFASSIGNMENTPAGE")){
				//TODO the mechanism to assign the assignmentPage's prof will be in the coursePage's assignmentList listener, which then calls this CardChanger
				//TODO update the file to show using pg.getProfAssignmentPanel().updateAssignmentFile(STRING);
				//and the assignment name JLabel
				updateAssignmentPageStatusLabel(pg.getProfAssignmentPanel()); // refresh the JLabel that says this assignment is active/inactive
			}
			else if(card.equals("PROFCOURSEPAGE")){
				//TODO the mechanism to assign the coursePages's course will be in the homepage's courseList listener, which then calls this CardChanger
				//TODO update the course name JLabel 
				fillCoursePageAssignmentList(pg.getProfCoursePagePanel()); // update/refresh the assignment list
				updateCoursePageStatusLabel(pg.getProfCoursePagePanel()); //update the JLabel that says this Course is active/inactive
			}
			else if(card.equals("PROFHOMEPAGE")){
				fillHomePageCourseList(((ProfGUI) frame).getProfHomePagePanel()); // update/refresh the course list
			}
			pg.setActiveCard(card);
		}
	}


	//helper method to fill the homepage courseList using the DB table. package scope so that CreateNewCourseButton listener can use it 
	void fillHomePageCourseList(ProfHomepage homepagePanel) {

		//make a message to query all the courses for this prof
		ArrayList<String> params = new ArrayList<>();
		params.add("prof_id"); // the column in the table to search
		params.add(Integer.toString(homepagePanel.getProf().getID())); // the search key // will have to convert this string to int in the model?
		DBMessage msg = new DBMessage(0, 0, -1, -1, params); //TODO change -1, -1 to courseTableNum, searchOpNum

		//send the message, get response
		ArrayList<?> response = communicator.communicate(msg);

		// convert the returned arraylist to a listmodel
		DefaultListModel<Course> listModel = new DefaultListModel<>();
		for(Object co : response) {
			listModel.addElement((Course)co);
		}
		// then do the update: 
		homepagePanel.updateCourseList(listModel);
	}

	//helper method to fill the coursePage's assignmentList using the DB table. package scope so that CreateNewAssignmentButton listener can use it 
	void fillCoursePageAssignmentList(ProfCoursePage coursePage) {

		//make a message to query all the assignments in a course
		ArrayList<String> params = new ArrayList<>();
		params.add("course_id"); // the column in the table to search
		params.add(Integer.toString(coursePage.getCourse().getID())); // the search key // will have to convert this string to int in the model?
		DBMessage msg = new DBMessage(0, 0, -1, -1, params); //TODO change -1, -1 to assignemntTableNum, searchOpNum

		//send the message, get response
		ArrayList<?> response = communicator.communicate(msg);

		// convert the returned arraylist to a listmodel 
		DefaultListModel<Assignment> listModel = new DefaultListModel<>();
		for(Object co : response) {
			listModel.addElement((Assignment)co);
		}
		// then do the update: 
		coursePage.updateAssignmentList(listModel);
	}
	
	/**
	 * Helper method to refresh the JLabel on the CoursePage that says whether or not the course is active
	 * @param coursePage the course page
	 */
	void updateCoursePageStatusLabel(ProfCoursePage coursePage) {

		//make a message to query the course status (active/inactive)
		ArrayList<String> params = new ArrayList<>();
		params.add("id"); // the column in the table to search
		params.add(Integer.toString(coursePage.getCourse().getID())); // the search key // TODO will have to convert this string to int in the model?
		DBMessage msg = new DBMessage(0, 0, 1, 0, params); // 1, 0 is courseTableNum, searchOpNum

		//send the message, get response
		ArrayList<?> response = communicator.communicate(msg);
		if(response.size()>1) {
			System.out.println("Unexpected error in updateCoursePageStatusLabel(): search for a course by ID returned "+response.size()+" courses");
		}
		else if (response.size()==0) {
			System.out.println("Error in updateCoursePageStatusLabel(): course not found");
			return;
		}
		//get the status of the course
		int status = ((Course) response.get(0)).getActive();
		// then do the update: 
		coursePage.setActiveStatusText(status);
	}
	

	/**
	 * Helper method to refresh the JLabel on the AssignmentPage that says whether or not the course is active
	 * @param assignmentPage the assignment page
	 */
	void updateAssignmentPageStatusLabel(ProfAssignmentPage assignmentPage) {

		//make a message to query the course status (active/inactive)
		ArrayList<String> params = new ArrayList<>();
		params.add("id"); // the column in the table to search
		params.add(Integer.toString(assignmentPage.getAssignment().getID())); // the search key // TODO will have to convert this string to int in the model?
		DBMessage msg = new DBMessage(0, 0, -1, -1, params); //TODO change -1, -1 to assignmentTableNum, searchOpNum

		//send the message, get response
		ArrayList<?> response = communicator.communicate(msg);
		if(response.size()>1) {
			System.out.println("Unexpected error in updateAssignmentPageStatusLabel(): search for an assignment by ID returned "+response.size()+" assignments");
		}
		else if (response.size()==0) {
			System.out.println("Error in updateAssignmentPageStatusLabel(): assignment not found");
			return;
		}
		//get the status of the assignment
		int status = ((Assignment) response.get(0)).getActive();
		// then do the update: 
		assignmentPage.setActiveStatusText(status);
	}

}
