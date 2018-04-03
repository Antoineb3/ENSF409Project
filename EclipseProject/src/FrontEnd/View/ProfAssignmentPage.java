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
 * Creates the ProfAssignmentPage GUI panel -  the Assignment page as viewed from a Professor
 */
public class ProfAssignmentPage extends JPanel{
    /**
     * The Assignment represented on the GUI
     */
    private Assignment assignment;
	/**
	 * The button to return to the prof homepage
	 */
    private JButton homepageButton = new JButton("Back to Homepage");
    /**
	 * The button to return to the course page
	 */
    private JButton backButton = new JButton("Back to Course Page");
    /**
	 * The button to go to this assignment's dropbox
	 */
    private JButton dropbox = new JButton("Dropbox");
    /**
	 * The create new Assignment button
	 */
    private JButton changeActiveButton = new JButton("Change Active Status");

    /**
     *  tells if Assignment is active/inactive
     */
    private JLabel statusMessage = new JLabel();
   
    /**
	 * The textArea to display the assignment file 
	 */
    private JTextArea fileArea;
    

	/**
	 * Constructor that creates the visible panel 
	 */
	public ProfAssignmentPage() {
        
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		setPreferredSize(new Dimension(800,600));

        fillContentPane();
	}


	/**
	 * Helper method to add components to the panel
	 */
	private void fillContentPane() {
        add(Box.createRigidArea(new Dimension(0,10))); //empty spacing 
        add(GuiUtilities.centeredJLabel("Assignment Page: testAssignmentName"));//TODO change to Assignment.getName(): //TODO will have to update this JLabel everytime we go to a new Assignmentpage?
        add(Box.createRigidArea(new Dimension(0,10))); //empty spacing


        
        
        setButtonPanel();
        add(Box.createRigidArea(new Dimension(0,10))); //empty spacing
        
        
        add(GuiUtilities.centeredJLabel("Assignment File"));
        addFileArea();


        setActiveStatusText('0'); // start as saying inactive //TODO remove?
        add(Box.createRigidArea(new Dimension(0,50))); //empty spacing 

        statusMessage.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(statusMessage);
        changeActiveButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(changeActiveButton);
        
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
        buttonPanel.add(dropbox);
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
        scroller.setMaximumSize(new Dimension(500,600));
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
	public void setDropboxButtonListener(ActionListener e) {
        dropbox.addActionListener(e);
    }
	public void setChangeActiveButtonListener(ActionListener e) {
        changeActiveButton.addActionListener(e);
    }
    

    /**
	 * helper method to set / update the Assignment file text area
	 * @param contents the file contents
	 */
	public void updateAssignmentFile(String contents) { //TODO call this in controller whenever changing to assignment page?
		fileArea.setText(contents);
    }
	
	/**
     * Changes the frame to tell whether the course is active or not
     * @param status 1 means active, else means inactive
     */
    public void setActiveStatusText(int status){
        String message = "This course is currently ";
        message+= (status==1)? "ACTIVE":"INACTIVE";
        statusMessage.setText(message);
    }
    
    /**
     * @return the Assignment
     */
    public Assignment getAssignment() {
        return assignment;
    }

    /**
     * @param c the Assignment to set
     */
    public void setAssignment(Assignment c) {
        assignment = c;
    }
    

    //for testing
    public static void main(String[] args) {
        JFrame frame = new JFrame();

		frame.setTitle("B&B Learning");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new ProfAssignmentPage());
        frame.pack();
        frame.setLocationRelativeTo(null); // center the JFrame
		frame.setResizable(false);
		frame.setVisible(true);
    }

}
