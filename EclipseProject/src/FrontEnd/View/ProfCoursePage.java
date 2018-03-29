package FrontEnd.View;

import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;

/*
 * Ross Bartlett
 * ENSF 409 - Project
 * March 24 2018
 */

/**
 * Creates the ProfCoursePage GUI panel -  the course page as viewed from a Professor
 */
public class ProfCoursePage extends JPanel{
    /**
     * The course rerpesented on the GUI
     */
    private String course; // TODO chagne this to class Course
	/**
	 * The button to return to the prof homepage
	 */
    private JButton homepageButton = new JButton("Back to Homepage");
    /**
	 * The create new course button
	 */
    private JButton viewStudentsButton = new JButton("View Students");
    /**
	 * The create new course button
	 */
    private JButton emailButton = new JButton("Email");
    /**
	 * The create new course button
	 */
    private JButton newAssignmentButton = new JButton("Create New Assignment");
    /**
	 * The create new course button
	 */
    private JButton changeActiveButton = new JButton("Change Active Status");

    /**
     *  tells if course is active/inactive
     */
    private JLabel statusMessage = new JLabel();
   
    /**
	 * The list of the course's assignments
	 */
	// private DefaultListModel<Assignment> listModel = new DefaultListModel<>(); 
    // private JList<Assignment> assignmentList;
    private DefaultListModel<String> listModel = new DefaultListModel<>(); //TODO make class Assignment
    private JList<String> assignmentList;
    

	/**
	 * Constructor that creates the visible panel 
	 */
	public ProfCoursePage() {
        
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		setPreferredSize(new Dimension(800,600));

        // listModel=manager.getCourseAssignments(); //TODO updates the courseList to show all course's assignments

        fillContentPane();
	}


	/**
	 * Helper method to add components to the panel
	 */
	private void fillContentPane() {
        add(Box.createRigidArea(new Dimension(0,10))); //empty spacing 
        add(GuiUtilities.centeredJLabel("Course Page: testCourseName"));//TODO change to course.getName(): //TODO will have to update this JLabel everytime we go to a new coursepage?
        add(Box.createRigidArea(new Dimension(0,10))); //empty spacing

        setButtonPanel();

        add(GuiUtilities.centeredJLabel("Assignments:"));
        setupAssignmentsList();

        newAssignmentButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(newAssignmentButton);

        setActiveStatusText(0); // start as saying inactive //TODO remove?
        add(Box.createRigidArea(new Dimension(0,50))); //empty spacing 

        statusMessage.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(statusMessage);
        changeActiveButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(changeActiveButton);
        
    }

    /**
     * Changes the frame to tell whether the course is active or not
     * @param status 0 means inactive, else means active
     */
    public void setActiveStatusText(int status){
        String message = "This course is currently ";
        message+= (status==0)? "INACTIVE":"ACTIVE";
        statusMessage.setText(message);
    }

    /**
     * make a panel of the top buttons on the frame
     */
    public void setButtonPanel(){
        JPanel buttonPanel = new JPanel();
        buttonPanel.setMaximumSize(new Dimension(800,50));
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(homepageButton);
        buttonPanel.add(viewStudentsButton);
        buttonPanel.add(emailButton);
        add(buttonPanel);
    }

    /**
     * Generate a scrollable JList of assignemts 
     */
    private void setupAssignmentsList(){
        assignmentList = new JList<>(listModel);
		assignmentList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		assignmentList.setLayoutOrientation(JList.VERTICAL);

		// assignmentList.addListSelectionListener(new ListListener(this)); // TODO make ListListener, double click to view course page

		JScrollPane scroller = new JScrollPane(assignmentList);
        scroller.setMaximumSize(new Dimension(400,300));
		add(scroller);
    }
    
	/**
	 * Helper functions for the CONTROLLER to initialize the listeners
	 */
    //TODO in the listeners, use e.getSource.getCourse() 
	public void setHomepageButtonListener(ActionListener e) {
        homepageButton.addActionListener(e);
    }
	public void setViewStudentsButtonListener(ActionListener e) {
        viewStudentsButton.addActionListener(e);
    }
	public void setEmailButtonListener(ActionListener e) {
        emailButton.addActionListener(e);
    }
	public void setNewAssignmentButtonListener(ActionListener e) {
        newAssignmentButton.addActionListener(e);
    }
	public void setChangeActiveButtonListener(ActionListener e) {
        changeActiveButton.addActionListener(e);
    }
    

    /**
	 * helper method to set and update the course list 
	 * @param c the list of results to display 
	 */
	public void updateAssignmentList(DefaultListModel<String> c) { //TODO change to Assignment instead of String
		listModel=c;
		assignmentList.setModel(listModel);
    }
    
    /**
     * @return the course
     */
    public String getCourse() { //TODO chagne to type Course
        return course;
    }

    /**
     * @param c the course to set
     */
    public void setCourse(String c) {
        course = c;
    }
    

    public static void main(String[] args) {
        JFrame frame = new JFrame();

		frame.setTitle("B&B Learning");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new ProfCoursePage());
        frame.pack();
        frame.setLocationRelativeTo(null); // center the JFrame
		frame.setResizable(false);
		frame.setVisible(true);
    }

}
