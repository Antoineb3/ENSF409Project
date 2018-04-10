package FrontEnd.View;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;

import javax.swing.*;

import SharedObjects.Assignment;
import SharedObjects.Course;


/*
 * Ross Bartlett
 * ENSF 409 - Project
 * March 24 2018
 */

/**
 * Creates the Dropbox GUI panel 
 * @author 	Antoine Bizon & Ross Bartlett
 */
public class GradePage extends JPanel{
	

	/**
     * The course of the grade page
     */
    private Course course;
	/**
	 * The button to return to the prof homepage
	 */
    private JButton homepageButton = new JButton("Back to Homepage");
    /**
	 * The button to return to the assignment page
	 */
    private JButton backButton = new JButton("Back");

    /**
     *  tellsthe selected assignment's final grade
     */
    private JTextField gradeField = new JTextField("000");

    /**
     *  tells the students average for the course
     */
    private JTextField averageField = new JTextField("00.000");
   
    /**
	 * The list of student enrollments
	 */
	private DefaultListModel<Assignment> listModel = new DefaultListModel<>(); 
	private JList<Assignment> assignmentList;
	/**
	 * JLabel at top of panel 
	 */
	private JLabel courseNameLabel= new JLabel();
	
	/**
	 * Constructor that creates the visible panel 
	 */
	public GradePage() {
        
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		setPreferredSize(new Dimension(800,600));

        gradeField.setEditable(false);
        averageField.setEditable(false);
        fillContentPane();
	}


	/**
	 * Helper method to add components to the panel
	 */
	private void fillContentPane() {
        add(Box.createRigidArea(new Dimension(0,10))); //empty spacing 
        
        setCourseNameText("testCourseName");//TODO remove? the cardChanger sets this when go to this page
        courseNameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(courseNameLabel);
        
        setButtonPanel();
        add(Box.createRigidArea(new Dimension(0,10))); //empty spacing
        
        // assingment list 
        makeAssignmentList();

        
        add(GuiUtilities.centeredJLabel("Final grade for the selected assignment (%):"));
        gradeField.setMaximumSize( gradeField.getPreferredSize() );
        add(gradeField);
        
        add(GuiUtilities.horizontalLine());
        
        add(GuiUtilities.centeredJLabel("Average (%):"));
        averageField.setMaximumSize( averageField.getPreferredSize() );
        add(averageField);
    }


	private void makeAssignmentList() {
		add(GuiUtilities.centeredJLabel("Assignments:"));
		assignmentList = new JList<>(listModel);
		assignmentList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		assignmentList.setLayoutOrientation(JList.VERTICAL);
		JScrollPane scroller = new JScrollPane(assignmentList);
		scroller.setMaximumSize(new Dimension(400,300));
		scroller.setAlignmentX(CENTER_ALIGNMENT);
		add(scroller);
	}


	/**
     * make a panel of the top buttons on the frame
     */
    public void setButtonPanel(){
        JPanel buttonPanel = new JPanel();
        buttonPanel.setMaximumSize(new Dimension(800,40));
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(homepageButton);
        buttonPanel.add(backButton);
        add(buttonPanel);
        add(GuiUtilities.horizontalLine());
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
	public void setAssignmentListListener(MouseAdapter e) {
		assignmentList.addMouseListener(e);
	}
    
   


	/**
     * Changes the course name 
   	 * @param name the new name
   	 */
    private void setCourseNameText(String name) {
   		String message = "Grades for Course: ";
        message+= name;
        courseNameLabel.setText(message);
   	}
    
    public void setGradeField(String g) {
    		gradeField.setText(g);
    }
    /**
	 * @return the gradeField
	 */
	public JTextField getGradeField() {
		return gradeField;
	}


    /**
	 * @return the assignmentList
	 */
	public JList<Assignment> getStudentEnrollmentList() {
		return assignmentList;
	}
	/**
	 * helper method to set and update the Assignment list 
	 * @param s the list to display 
	 */
	public void updateAssignmentList(DefaultListModel<Assignment> s) { //TODO change to type Student
		listModel=s;
		assignmentList.setModel(listModel);
	}


	/**
	 * @return the course
	 */
	public Course getCourse() {
		return course;
	}


	/**
	 * @param course the course to set
	 */
	public void setCourse(Course course) {
		this.course = course;
		setCourseNameText(course.getName());
	}

}

