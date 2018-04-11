
package FrontEnd.View;

import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;

import SharedObjects.Assignment;
/*
 * Ross Bartlett
 * ENSF 409 - Project
 * March 24 2018
 */

/**
 * Creates the StudentAssignmentPage GUI panel -  the Assignment page as viewed from a Student
 * @author 	Antoine Bizon & Ross Bartlett
 */
public class StudentAssignmentPage extends JPanel{
    /**
     * The Assignment represented on the GUI
     */
    private Assignment assignment;
	/**
	 * The button to return to the Student homepage
	 */
    private JButton homepageButton = new JButton("Back to Homepage");
    /**
	 * The button to return to the course page
	 */
    private JButton backButton = new JButton("Back to Course Page");
    /**
	 * The button to download this assignment's file
	 */
    private JButton downloadButton = new JButton("Download");
    /**
	 * The create new Assignment button
	 */
    private JButton uploadSubmissionButton = new JButton("Upload Submission");

    /**
     *  tells when the dueDate is
     */
    private JLabel dueDateMessage = new JLabel();
    /**
     *  tells the assignment name
     */
    private JLabel assignmentNameMessage = new JLabel();
   
    /**
	 * The textArea to display the assignment file 
	 */
    private JTextArea fileArea;
    

	/**
	 * Constructor that creates the visible panel 
	 */
	public StudentAssignmentPage() {
        
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
        assignmentNameMessage.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(assignmentNameMessage);
        

        setButtonPanel();
        add(Box.createRigidArea(new Dimension(0,10))); //empty spacing
        
        add(GuiUtilities.centeredJLabel("Assignment File"));
        addFileArea();

        downloadButton.setAlignmentX(CENTER_ALIGNMENT);
        add(downloadButton);
        add(Box.createRigidArea(new Dimension(0,10))); //empty spacing 
        
        uploadSubmissionButton.setAlignmentX(CENTER_ALIGNMENT);
        add(uploadSubmissionButton);
        add(GuiUtilities.horizontalLine());
        add(Box.createRigidArea(new Dimension(0,10))); //empty spacing 

        setDueDateText("testDueDate");
        dueDateMessage.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(dueDateMessage);
        add(Box.createRigidArea(new Dimension(0,20))); //empty spacing 
        
   
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
        buttonPanel.add(Box.createHorizontalStrut(200)); // empty horizontal spacing between buttons
        add(buttonPanel);
        add(GuiUtilities.horizontalLine());
    }

    /**
     * Generate a scrollable text area to display the file 
     */
    private void addFileArea(){

		// assignmentList.addListSelectionListener(new ListListener(this)); // TODO make ListListener, double click to view Assignment page
    		fileArea = new JTextArea();
    		fileArea.setEditable(false);
		JScrollPane scroller = new JScrollPane(fileArea);
        scroller.setMaximumSize(new Dimension(500,350));
		add(scroller);
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
	public void setUploadSubmissionButtonListener(ActionListener e) {
        uploadSubmissionButton.addActionListener(e);
    }
	public void setDownloadButtonListener(ActionListener e) {
        downloadButton.addActionListener(e);
    }
    

    /**
	 * helper method to set / update the Assignment file text area
	 * for bonus points
	 * @param contents the file contents
	 */
	public void updateAssignmentDisplay(String contents) { //TODO call this in controller whenever changing to assignment page?
		fileArea.setText(contents);
    }
	
    /**
     * Changes JLabel that tells the dueDate
     * @param date the dueDate
     */
    private void setDueDateText(String date){
        String message = "This assignment's due date is: ";
        message+= date;
        dueDateMessage.setText(message);
    }
    /**
     * Changes the assignment name 
   	 * @param name the new name
   	 */
    private void setAssignmentNameText(String name) {
   		String message = "Assignment Page: ";
        message+= name;
        assignmentNameMessage.setText(message);
   	}
    
    /**
     * @return the Assignment
     */
    public Assignment getAssignment() {
        return assignment;
    }

    /**
     * sets the panels Assignment and updates the name, dueDate JLabels
     * @param a the Assignment to set
     */
    public void setAssignment(Assignment a) {
        assignment = a;
		setAssignmentNameText(a.getTitle());//update the assignment name JLabel
		setDueDateText(a.getDueDate()); // update the dueDate JLabel
    }
    
}
