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
 * Creates the ProfHomepage GUI panel
 */
public class ProfHomepage extends JPanel{
    /**
     * The prof that logged in 
     */
    private String prof; //TODO make this class Professor
	/**
	 * The create new course button
	 */
    private JButton createNewCourseButton = new JButton("Create New Course");
   
    /**
	 * The list of the prof's Courses
	 */
	// private DefaultListModel<Course> listModel = new DefaultListModel<>(); 
    // private JList<Course> courseList;
    private DefaultListModel<String> listModel = new DefaultListModel<>(); //TODO make class Course
    private JList<String> courseList;
    

	/**
	 * Constructor that creates the visible panel 
	 */
	public ProfHomepage(String p) { //TODO make this arg type Professor 
        prof = p;
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		setPreferredSize(new Dimension(800,600));

        // listModel=manager.getProfsCourses(); //TODO updates the courseList to show all the profs Courses

        fillContentPane();
	}


	/**
	 * Helper method to add components to the JFrame
	 */
	private void fillContentPane() {
        add(Box.createRigidArea(new Dimension(0,10))); //empty spacing 
        add(GuiUtilities.centeredJLabel("Welcome, "+prof+"!"));//TODO prof.getName():
        add(Box.createRigidArea(new Dimension(0,10))); //empty spacing

        add(GuiUtilities.centeredJLabel("Courses:"));
        setupCourseList();

        createNewCourseButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(createNewCourseButton);
    }


    private void setupCourseList(){
        courseList = new JList<>(listModel);
		courseList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		courseList.setLayoutOrientation(JList.VERTICAL);

		// courseList.addListSelectionListener(new ListListener(this)); // TODO make ListListener, double click to view course page

		JScrollPane scroller = new JScrollPane(courseList);
        scroller.setMaximumSize(new Dimension(400,300));
		add(scroller);
    }
    
    
	/**
	 * Helper functions for the CONTROLLER to initialize the listener
	 */
	public void setCreateNewCourseButtonListener(ActionListener e) {
        createNewCourseButton.addActionListener(e);
    }
    

    /**
	 * helper method to set and update the course list 
	 * @param c the list of results to display 
	 */
	public void updateCourseList(DefaultListModel<String> c) { //TODO change to Course instead of String
		listModel=c;
		courseList.setModel(listModel);
	}


    public static void main(String[] args) {
        JFrame frame = new JFrame();

		frame.setTitle("B&B Learning");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new ProfHomepage("profName"));
        frame.pack();
        frame.setLocationRelativeTo(null); // center the JFrame
		frame.setResizable(false);
		frame.setVisible(true);
    }

}
