package FrontEnd.View;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;

import SharedObjects.Course;
import SharedObjects.Student;

/*
 * Ross Bartlett
 * March 27 2018
 */

/**
 * Creates the ViewStudents page of the ProfGUI
 * @author 	Antoine Bizon & Ross Bartlett
 */
public class ViewStudentsPage extends JPanel{
	/**
	 * The course represented on the GUI
	 */
	private Course course;

	/**
	 * The buttons on the JPanel
	 */
	JButton homepageButton = new JButton("Back to Homepage");
	JButton backButton = new JButton("Back");

	JButton searchButton = new JButton("Search");
	JButton clearSearchButton = new JButton("Clear Search");

	JButton changeEnrollmentButton = new JButton("Change Enrollment Status");

	/**
	 *  tells if selected student is enrolled or not
	 */
	private JLabel enrollmentStatusMessage = new JLabel();  // TODO set this in list listener

	/**
	 * Components to get Search parameters
	 */
	JComboBox searchType;
	JTextField searchField;

	/**
	 * The list of search results
	 */
	DefaultListModel<Student> listModel = new DefaultListModel<>(); 
	JList<Student> resultsList;
	/**
	 * the current selection from the list
	 */
	Student selectedStudent; //TODO set this in list listener
	/**
	 * if the selected student is enrolled
	 */
	int selectedStudentEnrollmentID; // TODO set this in list listener



	/**
	 *  tells course name
	 */
	private JLabel courseNameText = new JLabel();

	/**
	 * Constructor that creates the visible Frame 
	 */
	public ViewStudentsPage() {
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		setPreferredSize(new Dimension(800,600));
		fillContentPane();
	}

	/**
	 * Helper method to add components to the panel
	 */
	private void fillContentPane() {
		add(Box.createRigidArea(new Dimension(0,10))); //empty spacing 

		setCourseNameText("testCourseName"); //TODO remove? the cardChanger sets this when go to this page
		courseNameText.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(courseNameText);

		setButtonPanel();

		add(GuiUtilities.horizontalLine());

		add(GuiUtilities.centeredJLabel("Search Students"));
		add(Box.createRigidArea(new Dimension(0,10))); //empty spacing 
		addSearchPrompts();

		add(GuiUtilities.horizontalLine());

		add(GuiUtilities.centeredJLabel("Results"));
		addResultsList();

		clearSearchFields();
		clearEnrollmentStatusMessage();
		enrollmentStatusMessage.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(enrollmentStatusMessage);

		changeEnrollmentButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(changeEnrollmentButton);

	}



	/**
	 * make a panel of the top buttons on the frame
	 */
	private void setButtonPanel(){
		JPanel buttonPanel = new JPanel();
		buttonPanel.setMaximumSize(new Dimension(800,50));
		buttonPanel.setLayout(new FlowLayout());
		buttonPanel.add(homepageButton);
		buttonPanel.add(backButton);
		add(buttonPanel);
	}


	/**
	 * Helper method to add components to the results panel
	 */
	private void addResultsList() {
		resultsList = new JList<>(listModel);//updates the resultsList to show all database contents
		resultsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		resultsList.setLayoutOrientation(JList.VERTICAL);

		JScrollPane scroller = new JScrollPane(resultsList);
		scroller.setMaximumSize(new Dimension(400,600));
		add(scroller);

	}

	/**
	 * Helper method to add components to the center panel
	 */
	private void addSearchPrompts() {
		add(GuiUtilities.centeredJLabel("Search by:"));
		String [] options = {"Student ID", "Last Name"};
		searchType = new JComboBox(options);
		searchType.setMaximumSize( searchType.getPreferredSize() ); // sets the width of the ComboBox
		searchType.setSelectedIndex(-1);
		add(searchType);

		add(GuiUtilities.centeredJLabel("Search key:"));
		searchField = new JTextField(10);
		searchField.setMaximumSize(new Dimension(120,20));
		add(searchField);

		setSearchButtonPanel();

	}

	/**
	 * make a panel of the search and clearSearch buttons
	 */
	private void setSearchButtonPanel(){
		JPanel searchButtonPanel = new JPanel();
		searchButtonPanel.setMaximumSize(new Dimension(800,50));
		searchButtonPanel.setLayout(new FlowLayout());
		searchButtonPanel.add(searchButton);
		searchButtonPanel.add(clearSearchButton);
		add(searchButtonPanel);
	}

	/**
	 * Helper functions for the CONTROLLER to initialize the listeners
	 */
	public void setHomepageButtonListener(ActionListener e) {
		homepageButton.addActionListener(e);
	}
	public void setBackButtonListener(ActionListener e) {
		backButton.addActionListener(e);
	}
	public void setClearSearchButtonListener(ActionListener e) {
		clearSearchButton.addActionListener(e);
	}
	public void setSearchButtonListener(ActionListener e) {
		searchButton.addActionListener(e);
	}
	public void setChangeEnrollmentButton(ActionListener e) {
		changeEnrollmentButton.addActionListener(e);
	}
	public void setListListener(ListSelectionListener e) {
		resultsList.addListSelectionListener(e);
	}




	/**
	 * helper method to set and update the results list 
	 * @param c the list of results to display 
	 */
	public void updateResultsList(DefaultListModel<Student> s) { //TODO change to type Student
		listModel=s;
		resultsList.setModel(listModel);
	}



	/**
	 * @return the resultsList
	 */
	public JList<Student> getResultsList() {
		return resultsList;
	}

	/**
	 * changes the JLabel that tells the course name
	 * @param name the course name
	 */
	private void setCourseNameText(String name) {
		String message = "Viewing students through Course: ";
		message+= name;
		courseNameText.setText(message);
	}

	/**
	 * Changes the frame to tell whether the selected student is enrolled or not in this course
	 * @param s the selected student
	 * @param enrolled true if they are enrolled in this course
	 */
	public void setEnrollmentStatusMessage(boolean enrolled){
		String message = selectedStudent.getFirstName()+" is currently ";
		message+= (enrolled==true)? "":"NOT ";
		message+= "enrolled in this course.";
		enrollmentStatusMessage.setText(message);
	}


	public void clearEnrollmentStatusMessage() {
		enrollmentStatusMessage.setText("Select a student to view their enrollment status.");
	}


	/**
	 * @return the course
	 */
	public Course getCourse() { 
		return course;
	}

	/**
	 * @param c the course to set
	 */
	public void setCourse(Course c) {
		course = c;
		setCourseNameText(c.getName());//update the course name JLabel
	}

	/**
	 * @return the selected index of the searchType ComboBox. 
	 * 0 for Student ID, 1 for last name
	 */
	public int getSearchType() {
		return searchType.getSelectedIndex();

	}

	/**
	 * @return the searchField text
	 */
	public String getSearchFieldText() {
		return searchField.getText();
	}

	/**
	 * Clear the searchField and searchType comboBox
	 */
	public void clearSearchFields() {
		searchField.setText("");
		searchType.setSelectedIndex(-1);
	}


	/**
	 * @return the selectedStudent
	 */
	public Student getSelectedStudent() {
		return selectedStudent;
	}
	/**
	 * @param selectedStudent the selectedStudent to set
	 */
	public void setSelectedStudent(Student selectedStudent) {
		this.selectedStudent = selectedStudent;
	}

	/**
	 * @return the value of selectedStudentEnrollmentID
	 */
	public int getSelectedStudentEnrollmentID() {
		return selectedStudentEnrollmentID;
	}

	/**
	 * @param selectedStudentEnrollmentID the STudentEnrollment of the selected student, -1 if not enrolled
	 */
	public void setSelectedStudentEnrollmentID(int selectedStudentEnrollmentID) {
		this.selectedStudentEnrollmentID = selectedStudentEnrollmentID;
		boolean enrolled = (selectedStudentEnrollmentID<0)? false:true;
		setEnrollmentStatusMessage(enrolled);
	}

}
