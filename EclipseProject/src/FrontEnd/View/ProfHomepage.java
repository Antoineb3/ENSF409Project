package FrontEnd.View;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;

import javax.swing.*;

import SharedObjects.Course;
import SharedObjects.Professor;


/**
 * Creates the ProfHomepage GUI panel
 * @author 	Ross Bartlett, Antoine Bizon
 */
public class ProfHomepage extends JPanel{

	/**
	 * The prof that logged in 
	 */
	private Professor prof;

	/**
	 * The create new course button
	 */
	private JButton createNewCourseButton = new JButton("Create New Course");

	/**
	 * The list of the prof's Courses
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
	public ProfHomepage() { 
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		setPreferredSize(new Dimension(800,600));
		fillContentPane();
	}


	/**
	 * Helper method to add components to the JFrame
	 */
	private void fillContentPane() {
		add(Box.createRigidArea(new Dimension(0,10))); //empty spacing

		welcomeText.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(welcomeText);

		add(Box.createRigidArea(new Dimension(0,10))); //empty spacing

		add(GuiUtilities.centeredJLabel("Courses:"));
		setupCourseList();

		createNewCourseButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(createNewCourseButton);
	}

	/**
	 * make and add the courseList to the panel 
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
	 * @param e the actionListener
	 */
	public void setCreateNewCourseButtonListener(ActionListener e) {
		createNewCourseButton.addActionListener(e);
	}
	public void setListListener(MouseAdapter e) {
		courseList.addMouseListener(e);
	}
	
	
	/**
	 * sets the panels Prof and updates the welcom label
	 * @param p the prof to set
	 */
	public void setProf(Professor p) {
		prof = p;
		setWelcomeText(p.getFirstName() , p.getLastName());//update the "welcome, name!" JLabel
	}

	/**
	 * Sets the welcome message at top of panel
	 * @param first profs first name
	 * @param last profs last name
	 */
	private void setWelcomeText(String first, String last) {
		String message = "Welcome, Professor ";
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
	 * @return the prof
	 */
	public Professor getProf() {
		return prof;
	}



}
