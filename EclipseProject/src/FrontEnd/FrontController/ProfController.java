package FrontEnd.FrontController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;

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

		pg.getProfCoursePagePanel().setNewAssignmentButtonListener(new NewAssignmentButtonListener(pg.getProfCoursePagePanel(), this));
		pg.getProfHomePagePanel().setCreateNewCourseButtonListener(new NewCourseButtonListener(pg.getProfHomePagePanel(), this));



		fillHomePageCourseList(pg.getProfHomePagePanel());
		fillCoursePageAssignmentList(pg.getProfCoursePagePanel());
	}



	/**
	 * Commonly used inner class listener to go back to the homepage
	 */
	class CardChangerListener implements ActionListener {
		String card;
		public CardChangerListener(String c) {
			card = c;
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Card changer button pressed. going to: "+card);
			if(card.equals("PROFASSIGNMENTPAGE")){
				//TODO update the file to show using pg.getProfAssignmentPanel().updateAssignmentFile(STRING);
			}
			((ProfGUI) frame).setActiveCard(card);
		}
	}


	//helper method to fill the homepage courseList using the DB table. package scope so that CreateNewCourseButton listener can use it 
	void fillHomePageCourseList(ProfHomepage homepagePanel) {

		//make a message to query all the courses for this prof
		ArrayList<String> params = new ArrayList<>();
		params.add("prof_id"); // the column in the table to search
		params.add(Integer.toString(homepagePanel.getProf().getID())); // the search key // will have to convert this string to int in the model?
		DBMessage msg = new DBMessage(0, 0, -1, -1, params); //TODO change -1, -1 to courseTableNum, searchOpNum,

		//send the message, get response
		ArrayList<?> response = communicator.communicate(msg);

		// convert the returned arraylist to a listmodel, 
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
		DBMessage msg = new DBMessage(0, 0, -1, -1, params); //TODO change -1, -1 to assignemntTableNum, searchOpNum,

		//send the message, get response
		ArrayList<?> response = communicator.communicate(msg);

		// convert the returned arraylist to a listmodel, 
		DefaultListModel<Assignment> listModel = new DefaultListModel<>();
		for(Object co : response) {
			listModel.addElement((Assignment)co);
		}
		// then do the update: 
		coursePage.updateAssignmentList(listModel);
	}

}
