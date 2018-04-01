package FrontEnd.FrontController;

import javax.swing.*;

import FrontEnd.View.ProfCoursePage;
import FrontEnd.View.ProfHomepage;
import SharedObjects.Course;

import java.awt.event.*;
import java.io.File;


/*
 * Listener class for the Create a New Course button
 */
public class NewCourseButtonListener implements ActionListener{

	/**
	 * The panel with the button calling this listener
	 */
	private ProfHomepage panel;
	/**
	 * the controller constructing this listener
	 */
	private ProfController controller;
	/**
	 * The textFields with which user will input the new course name
	 */
	private JTextField courseNameField = new JTextField();
	/**
	 * The user's input
	 */
	private String name;


	/**
	 * All the components to display on the frame 
	 * Used for displaying multiple components on JOPtionPane
	 */
	private Object[] items = {"New Course", "Name:", courseNameField};

	/**
	 * Constructor that inits the calling JPanel
	 * @param p the calling JPanel
	 * @param c the controller to access the backend
	 */
	public NewCourseButtonListener(ProfHomepage p, ProfController c) {
		panel = p;
		controller=c;
	}



	/**
	 * Displays a JOptionPane to add a new Course
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		int option = JOptionPane.showConfirmDialog(null, items, "Create a New Assignment", JOptionPane.OK_CANCEL_OPTION);
		if (option == JOptionPane.OK_OPTION) {
			name = courseNameField.getText();
			courseNameField.setText(""); //empty the field

			if(name.equals("")) {
				JOptionPane.showMessageDialog(null, "Error: course name can't be empty", "Error creating course", JOptionPane.WARNING_MESSAGE);
				return;
			}


			//construct a new object of type course using the name and the panel.getProf()
//			Course temp = new Course(panel.getProf().getID(), name, 'n') // 'n' for active = no

					//now add the course
					//controller.getModel().addCourse(temp);

					//panel.updateCourseist(controller.getModel.getAllProfsCourses());


					System.out.println("Course "+name+" successfuly created.");
			JOptionPane.showMessageDialog(null, "Course "+name+" successfuly created.", "Course Created", JOptionPane.INFORMATION_MESSAGE);

		}
		courseNameField.setText(""); //empty the field
	}






}
