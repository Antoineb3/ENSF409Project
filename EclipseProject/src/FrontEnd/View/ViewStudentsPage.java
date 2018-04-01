package FrontEnd.View;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;

import SharedObjects.Course;

/*
 * Ross Bartlett
 * March 27 2018
 */

/**
 * Creates the VIewStudents page of the ProfGUI
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
	
	JButton enrollButton = new JButton("Enroll");
	JButton unEnrollButton = new JButton("UnEnroll");

	/**
	 * Components to get Search parameters
	 */
	JComboBox searchType;
	JTextField searchField;

	/**
	 * The list of search results
	 */
	DefaultListModel<String> listModel = new DefaultListModel<>(); //TODO change to type Student
	JList<String> resultsList;
	/**
	 * the current selection from the list
	 */
	String selectedStudent; //TODO change this to type Student


	/**
	 * Constructor that creates the visible Frame 
	 */
	public ViewStudentsPage() {
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		setPreferredSize(new Dimension(800,600));

        // listModel=manager.getStudents(); //TODO updates the resultsList to show all students

        fillContentPane();
	}
	
	/**
	 * Helper method to add components to the panel
	 */
	private void fillContentPane() {
        add(Box.createRigidArea(new Dimension(0,10))); //empty spacing 
        add(GuiUtilities.centeredJLabel("Viewing students through course: testCourseName"));//TODO change to course.getName(): //TODO will have to update this JLabel everytime we go to a new coursepage?
        add(Box.createRigidArea(new Dimension(0,10))); //empty spacing

        setButtonPanel();
        
        add(GuiUtilities.horizontalLine());
        
        add(GuiUtilities.centeredJLabel("Search Students"));
        add(Box.createRigidArea(new Dimension(0,10))); //empty spacing 
        addSearchPrompts();

        add(GuiUtilities.horizontalLine());
        
        add(GuiUtilities.centeredJLabel("Results"));
        addResultsList();
        
        enrollButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(enrollButton);
        unEnrollButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(unEnrollButton);
        
    }

	
	
	/**
     * make a panel of the top buttons on the frame
     */
    public void setButtonPanel(){
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
		
		searchButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(searchButton);
		clearSearchButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(clearSearchButton);

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
	public void setEnrollButtonListener(ActionListener e) {
		enrollButton.addActionListener(e);
	}
	public void setUnEnrollButtonListener(ActionListener e) {
		unEnrollButton.addActionListener(e);
	}
	public void setListListener(ListSelectionListener e) {
		resultsList.addListSelectionListener(e);
	}
	
	
	

	/**
	 * helper method to set and update the results list 
	 * @param c the list of results to display 
	 */
	public void updateResultsList(DefaultListModel<String> s) { //TODO change to type Student
		listModel=s;
		resultsList.setModel(listModel);
	}
	

}
