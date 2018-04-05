package FrontEnd.FrontController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

import FrontEnd.View.ProfAssignmentPage;
import FrontEnd.View.ProfCoursePage;
import FrontEnd.View.ProfGUI;
import FrontEnd.View.ProfHomepage;
import FrontEnd.View.ViewStudentsPage;
import SharedObjects.*;


//TODO what relationship between actionListeners and ProfController ? ViewController?



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

		//set the changeActiveStatus listeners
		pg.getProfCoursePagePanel().setChangeActiveButtonListener(new ChangeCourseStatusListener(pg.getProfCoursePagePanel(), this));
		pg.getProfAssignmentPanel().setChangeActiveButtonListener(new ChangeAssignmentStatusListener(pg.getProfAssignmentPanel(), this));

		//search button on ViewStudent page
		pg.getViewStudentsPanel().setSearchButtonListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String key = pg.getViewStudentsPanel().getSearchFieldText();
				int searchParam = pg.getViewStudentsPanel().getSearchType();
				if(key.equals("") || searchParam==-1) {
					JOptionPane.showMessageDialog(null, "Error: Search field(s) cannot be empty.", "Search Error", JOptionPane.WARNING_MESSAGE);
					return;
				}
				//make a message to query the matching Students in the UserTable
				ArrayList<String> params = new ArrayList<>();
				if (searchParam==0) params.add("ID"); // search by ID
				else if (searchParam==1) params.add("LASTNAME"); // search by Last Name
				params.add("'"+key+"'"); // the search key  
				DBMessage msg = new DBMessage(0, 0, params); // 0, 0 is userTableNum, searchOpNum
				//send the message, get response
				ArrayList<? extends Serializable> response = communicator.communicate(msg);
				// convert the returned arraylist to a listmodel 
				DefaultListModel<Student> listModel = new DefaultListModel<>();
				for(Object s : response) {
					listModel.addElement((Student)s);
				}
				// do the update: 
				pg.getViewStudentsPanel().updateResultsList(listModel);
			}
		});

		//clear search button on ViewStudent page
		pg.getViewStudentsPanel().setClearSearchButtonListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				pg.getViewStudentsPanel().clearSearchFields();
				fillViewStudentsList(pg.getViewStudentsPanel()); // fill the results list with all students
			}
		});

		//change enrollment button
		pg.getViewStudentsPanel().setChangeEnrollmentButton(new ChangeStudentEnrollmentListener(pg.getViewStudentsPanel(), this));

		//download assignment button
		pg.getProfAssignmentPanel().setDownloadButtonListener(new DownloadButtonListener(pg.getProfAssignmentPanel(), this));
		//set list listeners
		pg.getViewStudentsPanel().setListListener(new ViewStudentsListListener(pg.getViewStudentsPanel(), this));
		
		
//		pg.getProfHomePagePanel().setListListener(new ProfHomepageListListener(pg.getProfHomePagePanel(), this ));
		pg.getProfHomePagePanel().setListListener(new CLICK_ProfHomepageListListener(this));

//		pg.getProfCoursePagePanel().setListListener(new ProfCoursePageAssignmentListListener(pg.getProfCoursePagePanel(), this));
		pg.getProfCoursePagePanel().setListListener(new CLICK_ProfCoursePageListListener(this));
		
		
		
		
		//update the courseList on the homepage , as this is the first active card 
		fillHomePageCourseList(pg.getProfHomePagePanel()); //TODO uncomment this when connections are ready
	}



	/**
	 * Inner class listener to switch the active card on the ProfGUI using either the BackToHomepage or Back buttons
	 */
	class CardChangerListener implements ActionListener {
		String card;
		public CardChangerListener(String c) {
			card = c;
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			ProfGUI pg = ((ProfGUI) frame); // from super
//			System.out.println("Card changer button pressed. going to: "+card);
			if(card.equals("PROFASSIGNMENTPAGE")){
				refreshProfAssignmentPage(pg);
			}
			else if(card.equals("PROFCOURSEPAGE")){
				refreshProfCoursePage(pg);
			}
			else if(card.equals("PROFHOMEPAGE")){
				//note: homepage's welcomeText with the prof's name never needs to be refreshed
				fillHomePageCourseList(pg.getProfHomePagePanel()); // update/refresh the course list
			}
			else if(card.equals("VIEWSTUDENTSPAGE")){
				pg.getViewStudentsPanel().setCourse(pg.getProfCoursePagePanel().getCourse()); // update the course as the most recent course of the CorusePage
				fillViewStudentsList(pg.getViewStudentsPanel()); // the results list starts as full of all students
			}
			pg.setActiveCard(card);
		}

	}





	/**
	 * refreshes the asssignmentList and the JLabel on the ProfCoursePage
	 * @param pg the ProfGUI
	 */
	void refreshProfCoursePage(ProfGUI pg) {
		fillCoursePageAssignmentList(pg.getProfCoursePagePanel()); // update/refresh the assignment list
		updateCoursePageStatusLabel(pg.getProfCoursePagePanel()); //update the JLabel that says this Course is active/inactive
	}

	/**
	 * refreshes the assignmentPage status label and BONUS: the assignment file shower
	 * @param pg
	 */
	void refreshProfAssignmentPage(ProfGUI pg) {
		//TODO BONUS: update the file shower using pg.getProfAssignmentPanel().updateAssignmentDisplay(STRING);. call this in ProfCoursePageAssignmentListListener
		updateAssignmentPageStatusLabel(pg.getProfAssignmentPanel()); // refresh the JLabel that says this assignment is active/inactive
	}

	/**	
	 * helper method to fill the homepage courseList using the DB table. 
	 * package scope so that CreateNewCourseButton listener can use it helper method to fill the coursePage's assignmentList using the DB table. 
	 * @param coursePage the course page
	 */
	void fillHomePageCourseList(ProfHomepage homepagePanel) {
		//make a message to query all the courses for this prof
		ArrayList<String> params = new ArrayList<>();
		params.add("PROFID"); // the column in the table to search
		params.add("'"+Integer.toString(homepagePanel.getProf().getID())+"'"); // the search key // will have to convert this string to int in the model?
		DBMessage msg = new DBMessage(1, 0, params); // 1, 0 is courseTableNum, searchOpNum

		//send the message, get response
		ArrayList<? extends Serializable> response = communicator.communicate(msg);

		// convert the returned arraylist to a listmodel
		DefaultListModel<Course> listModel = new DefaultListModel<>();
		for(Object co : response) {
			listModel.addElement((Course)co);
		}
		// then do the update: 
		homepagePanel.updateCourseList(listModel);
	}



	/**
	 * helper method to fill the coursePage's assignmentList using the DB table. 
	 * package scope so that CreateNewAssignmentButton listener can use it 
	 * @param coursePage the course page
	 */
	void fillCoursePageAssignmentList(ProfCoursePage coursePage) {

		//make a message to query all the assignments in a course
		ArrayList<String> params = new ArrayList<>();
		params.add("COURSEID"); // the column in the table to search
		params.add("'"+Integer.toString(coursePage.getCourse().getID())+"'"); // the search key // will have to convert this string to int in the model?
		DBMessage msg = new DBMessage(3, 0, params); // 3, 0 is assignemntTableNum, searchOpNum

		//send the message, get response
		ArrayList<? extends Serializable> response = communicator.communicate(msg);

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
		params.add("ID"); // the column in the table to search
		params.add("'"+Integer.toString(coursePage.getCourse().getID())+"'"); // the search key 
		DBMessage msg = new DBMessage(1, 0, params); // 1, 0 is courseTableNum, searchOpNum

		//send the message, get response
		ArrayList<? extends Serializable> response = communicator.communicate(msg);
		if(response.size()>1) {
			System.out.println("Unexpected error in updateCoursePageStatusLabel(): search for a course by ID returned "+response.size()+" courses");
		}
		else if (response.size()==0) {
			System.out.println("Error in updateCoursePageStatusLabel(): course not found");
			return;
		}
		//get the status of the course
		boolean status = ((Course) response.get(0)).getActive();
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
		params.add("ID"); // the column in the table to search
		params.add("'"+Integer.toString(assignmentPage.getAssignment().getID())+"'"); // the search key 
		DBMessage msg = new DBMessage(3, 0, params); //TODO change -1, -1 to assignmentTableNum, searchOpNum

		//send the message, get response
		ArrayList<? extends Serializable> response = communicator.communicate(msg);
		if(response.size()>1) {
			System.out.println("Unexpected error in updateAssignmentPageStatusLabel(): search for an assignment by ID returned "+response.size()+" assignments");
		}
		else if (response.size()==0) {
			System.out.println("Error in updateAssignmentPageStatusLabel(): assignment not found");
			return;
		}
		//get the status of the assignment
		boolean status = ((Assignment) response.get(0)).getActive();
		// then do the update: 
		assignmentPage.setActiveStatusText(status);
	}

	/**
	 * helper method to fill the ViewStudentsPage's result list with all students in the DB table. 
	 * @param coursePage the course page
	 */
	void fillViewStudentsList(ViewStudentsPage page) {

		//make a message to query all the Students in the UserTable
		ArrayList<String> params = new ArrayList<>();
		params.add("TYPE"); // the column in the table to search
		params.add("'S'"); // the search key  
		DBMessage msg = new DBMessage(0, 0, params); // 0, 0 is userTableNum, searchOpNum
		//send the message, get response
		ArrayList<? extends Serializable> response = communicator.communicate(msg);
		// convert the returned arraylist to a listmodel 
		DefaultListModel<Student> listModel = new DefaultListModel<>();
		for(Object s : response) {
			listModel.addElement((Student)s);
		}
		// do the update: 
		page.updateResultsList(listModel);
	}

}
