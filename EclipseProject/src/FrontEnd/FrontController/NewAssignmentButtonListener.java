package FrontEnd.FrontController;

import javax.swing.*;

import FrontEnd.View.ProfCoursePage;

import java.awt.event.*;
import java.io.File;
/**
 * Listener class for the Create a New Assignment button
 */
public class NewAssignmentButtonListener implements ActionListener{
	/**
	 * The panel with the button calling this listener
	 */
	private ProfCoursePage panel;
	/**
	 * the controller constructing this listener
	 */
	private ProfController controller;
	/**
	 * The textFields with which user will input the new assignment name
	 */
	private JTextField assignmentNameField = new JTextField();
	/**
	 * The user's input
	 */
	private String name;
	
	private JFileChooser fc = new JFileChooser();
	
	private JButton chooseFileButton = new JButton("choose file");

	/**
	 * All the components to display on the frame
	 * Used for displaying multiple input textFields on frame
	 */
	private Object[] items = {"New Assignment", "Name:", assignmentNameField, "File Upload:", chooseFileButton};
	
	private File newFile=null;

	/**
	 * Constructor that inits the calling JPanel and the listener for the file chooser
	 * @param p the calling JPanel
	 * @param c the controller to access the backend
	 */
	public NewAssignmentButtonListener(ProfCoursePage p, ProfController c) {
		panel = p;
		controller=c;
		/**
		 * opens a FileChooser
		 */
		chooseFileButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					int returnVal = fc.showOpenDialog(null); 
		            if (returnVal == JFileChooser.APPROVE_OPTION) { 
		            		newFile = fc.getSelectedFile();
		            		if(newFile.isFile()) {
		            			System.out.println("Successfuly chose file: "+newFile.getName());
		            		}
		            		else {
		            			System.out.println("Error choosing file: not a file");
		            			newFile=null;
		            		}
		            }
		        }
		});
		
	}
		
		

	/**
	 * Displays a JOptionPane to add a new Assignment to a Course
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		int option = JOptionPane.showConfirmDialog(null, items, "Create a New Assignment", JOptionPane.OK_CANCEL_OPTION);
		if (option == JOptionPane.OK_OPTION) {
			name = assignmentNameField.getText();
			
			if(name.equals("")) {
				JOptionPane.showMessageDialog(null, "Error: assignment name can't be empty", "Error creating assignment", JOptionPane.WARNING_MESSAGE);
				clearInputs();
				return;
			}
			
			if(newFile==null) {
				JOptionPane.showMessageDialog(null, "Error: no assignment file chosen", "Error creating assignment", JOptionPane.WARNING_MESSAGE);
				clearInputs();
				return;
			}
			
			//construct a new object of type Assignment using the name and the newFile and the panel.getCourse()
			//Assignment temp = new Assignment(...)
			
			//now add the assignment
			//controller.getModel().addAssignment(temp);
			
			//panel.updateAssignmentList(controller.getModel.getAllAssignmentsInCourse());
			
		
			System.out.println("Assignment "+name+"from file "+newFile.getName()+" successfuly created.");
			JOptionPane.showMessageDialog(null, "Assignment "+name+" from file "+newFile.getName()+" successfuly created.", "Assignment Created", JOptionPane.INFORMATION_MESSAGE);

		
		}
		clearInputs();
		
	}

	/**
	 * Helper method to clear the nameField and the selected file
	 */
	private void clearInputs() {
		assignmentNameField.setText("");
		newFile=null;
	}


}

