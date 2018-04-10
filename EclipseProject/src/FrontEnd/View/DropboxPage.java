package FrontEnd.View;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;

import javax.swing.*;

import SharedObjects.Assignment;
import SharedObjects.StudentEnrollment;
import SharedObjects.Submission;

/*
 * Ross Bartlett
 * ENSF 409 - Project
 * March 24 2018
 */

/**
 * Creates the Dropbox GUI panel 
 * @author 	Antoine Bizon & Ross Bartlett
 */
public class DropboxPage extends JPanel{
	
	/**
     * The Assignment of the dropbox
     */
    private Assignment assignment;
	/**
	 * The button to return to the prof homepage
	 */
    private JButton homepageButton = new JButton("Back to Homepage");
    /**
	 * The button to return to the assignment page
	 */
    private JButton backButton = new JButton("Back");
    /**
	 * The button to download this assignment's file
	 */
    private JButton downloadButton = new JButton("Download");
    /**
	 * The setGrade button
	 */
    private JButton setGradeButton = new JButton("Set Grade");
    /**
	 * The setGrade button
	 */
    private JButton setFinalGradeButton = new JButton("Use Selected Submission for Student's Final Grade");

    /**
     *  tells and can change the selected submisison's grade
     */
    private JTextField gradeField = new JTextField("000");

    /**
     *  tells the selected student's grade
     */
    private JTextField finalGradeField = new JTextField("000");
   
    /**
	 * The list of student enrollments
	 */
	private DefaultListModel<StudentEnrollment> listModel1 = new DefaultListModel<>(); 
	private JList<StudentEnrollment> studentEnrollmentList;
	/**
	 * The list of submissions of the selected student enrollment
	 */
	private DefaultListModel<Submission> listModel2 = new DefaultListModel<>(); 
	private JList<Submission> submissionList;
	/**
	 * JLabel at top of panel 
	 */
	private JLabel assignmentNameLabel= new JLabel();
	
	private Submission selectedSubmission;

	/**
	 * Constructor that creates the visible panel 
	 */
	public DropboxPage() {
        
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		setPreferredSize(new Dimension(800,600));

        fillContentPane();
	}


	/**
	 * Helper method to add components to the panel
	 */
	private void fillContentPane() {
        add(Box.createRigidArea(new Dimension(0,10))); //empty spacing 
        
        setAssignmentNameText("testAssignmentName");//TODO remove? the cardChanger sets this when go to this page
        assignmentNameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(assignmentNameLabel);
        
        setButtonPanel();
        add(Box.createRigidArea(new Dimension(0,10))); //empty spacing
        
        // student enrollment list 
        makeEnrollmentList();
		
		 // submissions list 
        makeSubmissionList();

        downloadButton.setAlignmentX(CENTER_ALIGNMENT);
        add(downloadButton);
        add(GuiUtilities.horizontalLine());
        
        add(GuiUtilities.centeredJLabel("Grade (%):"));
        gradeField.setMaximumSize( gradeField.getPreferredSize() );
        add(gradeField);
        setGradeButton.setAlignmentX(CENTER_ALIGNMENT);
        add(setGradeButton);
        
        add(GuiUtilities.centeredJLabel("Student's Final Grade (%):"));
        finalGradeField.setMaximumSize( finalGradeField.getPreferredSize() );
        finalGradeField.setEditable(false);
        add(finalGradeField);
        setFinalGradeButton.setAlignmentX(CENTER_ALIGNMENT);
        add(setFinalGradeButton);

    }


	private void makeEnrollmentList() {
		add(GuiUtilities.centeredJLabel("Enrolled Students:"));
        studentEnrollmentList = new JList<>(listModel1);
        studentEnrollmentList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        studentEnrollmentList.setLayoutOrientation(JList.VERTICAL);
		JScrollPane scroller1 = new JScrollPane(studentEnrollmentList);
		scroller1.setMaximumSize(new Dimension(400,300));
		scroller1.setAlignmentX(CENTER_ALIGNMENT);
		add(scroller1);
	}
	
	private void makeSubmissionList() {
		add(GuiUtilities.centeredJLabel("Submissions"));
        submissionList = new JList<>(listModel2);
        submissionList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        submissionList.setLayoutOrientation(JList.VERTICAL);
		JScrollPane scroller2 = new JScrollPane(submissionList);
		scroller2.setMaximumSize(new Dimension(400,300));
		scroller2.setAlignmentX(CENTER_ALIGNMENT);
		add(scroller2);
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
	public void setSetGradeButtonListener(ActionListener e) {
        setGradeButton.addActionListener(e);
    }
	public void setSetFinalGradeButtonListener(ActionListener e) {
		setFinalGradeButton.addActionListener(e);
    }
	public void setDownloadButtonListener(ActionListener e) {
        downloadButton.addActionListener(e);
    }
	public void setStudentEnrollmentListListener(MouseAdapter e) {
		studentEnrollmentList.addMouseListener(e);
	}
	public void setSubmissionListListener(MouseAdapter e) {
		submissionList.addMouseListener(e);
	}
    
   


	/**
     * Changes the assignment name 
   	 * @param name the new name
   	 */
    private void setAssignmentNameText(String name) {
   		String message = "Assignment Page: ";
        message+= name;
        assignmentNameLabel.setText(message);
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


	public void setFinalGradeField(String g) {
    		finalGradeField.setText(g);
    }

    /**
	 * @return the studentEnrollmentList
	 */
	public JList<StudentEnrollment> getStudentEnrollmentList() {
		return studentEnrollmentList;
	}
	/**
	 * helper method to set and update the studentenrollment list 
	 * @param s the list to display 
	 */
	public void updateStudentEnrollentList(DefaultListModel<StudentEnrollment> s) { //TODO change to type Student
		listModel1=s;
		studentEnrollmentList.setModel(listModel1);
	}


	/**
	 * @return the submissionList
	 */
	public JList<Submission> getSubmissionList() {
		return submissionList;
	}
	/**
	 * helper method to set and update the submission list 
	 * @param s the list of results to display 
	 */
	public void updateSubmissionList(DefaultListModel<Submission> s) { 
		listModel2=s;
		submissionList.setModel(listModel2);
	}
	
	public void clearSubmissionList() {
		listModel2.clear();
		submissionList.setModel(listModel2);
	}
	


    /**
     * @return the Assignment
     */
    public Assignment getAssignment() {
        return assignment;
    }

    /**
     * sets the panels Assignment and updates the name, dueDate JLabels
     * @param c the Assignment to set
     */
    public void setAssignment(Assignment a) {
        assignment = a;
		setAssignmentNameText(a.getTitle());//update the assignment name JLabel
    }


	/**
	 * @return the selectedSubmission
	 */
	public Submission getSelectedSubmission() {
		return selectedSubmission;
	}


	/**
	 * @param selectedSubmission the selectedSubmission to set
	 */
	public void setSelectedSubmission(Submission selectedSubmission) {
		this.selectedSubmission = selectedSubmission;
	}

}

