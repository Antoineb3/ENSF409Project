package FrontEnd.View;
import java.awt.*;
import java.awt.event.MouseAdapter;

import javax.swing.*;

import SharedObjects.Course;
import SharedObjects.Student;

/*
 * Ross Bartlett
 * ENSF 409 - Project
 * March 24 2018
 */

/**
 * Creates the StudentHomepage GUI panel
 * @author 	Ross Bartlett, Antoine Bizon
 */
public class StudentHomepage extends JPanel{

	/**
	 * The Student that logged in 
	 */
	private Student Student;

	/**
	 * The list of the Student's Courses
	 */
	private DefaultListModel<Course> listModel = new DefaultListModel<>(); 
	private JList<Course> courseList;

	/**
	 * JLabel at top of panel 
	 */
	private JLabel welcomeText= new JLabel();
	

	/**
	 * Constructor that creates the visible panel 
	 */
	public StudentHomepage() { 
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		setPreferredSize(new Dimension(800,600));
		fillContentPane();
	}


	/**
	 * Helper method to add components to the JFrame
	 */
	private void fillContentPane() {
		add(Box.createRigidArea(new Dimension(0,10))); //empty spacing

		setWelcomeText("testFirst", "testLast");//TODO remove? the cardChanger sets this when go to this page
		welcomeText.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(welcomeText);

		add(Box.createRigidArea(new Dimension(0,10))); //empty spacing

		add(GuiUtilities.centeredJLabel("Courses:"));
		setupCourseList();

	}

	/**
	 * setup and add the courseList to the panel
	 */
	private void setupCourseList(){
		courseList = new JList<>(listModel);
		courseList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		courseList.setLayoutOrientation(JList.VERTICAL);
		
		JScrollPane scroller = new JScrollPane(courseList);
		scroller.setMaximumSize(new Dimension(400,300));
		add(scroller);
	}


	/**
	 * Helper functions for the CONTROLLER to initialize the listeners
	 * @param e the mouseAdapter listener
	 */
	public void setListListener(MouseAdapter e) {
		courseList.addMouseListener(e);
	}
	
	/**
	 * sets the panels student and updates the welcome text
	 * @param s the student to set
	 */
	public void setStudent(Student s) {
		Student = s;
		setWelcomeText(s.getFirstName() , s.getLastName());//update the "welcome, name!" JLabel
	}

	/**
	 * Sets the welcome message at top of panel
	 * @param first Students first name
	 * @param last Students last name
	 */
	private void setWelcomeText(String first, String last) {
		String message = "Welcome, Student: ";
		message+= first+" "+last+"!";
		welcomeText.setText(message);
	}


	/**
	 * helper method to set and update the course list 
	 * @param c the list of results to display 
	 */
	public void updateCourseList(DefaultListModel<Course> c) { 
		listModel=c;
		courseList.setModel(listModel);
	}

	
	/**
	 * @return the courseList
	 */
	public JList<Course> getCourseList() {
		return courseList;
	}

	/**
	 * @return the Student
	 */
	public Student getStudent() {
		return Student;
	}




}
