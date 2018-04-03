package FrontEnd.FrontController;

import javax.swing.*;

import FrontEnd.View.ProfCoursePage;
import SharedObjects.Assignment;
import SharedObjects.Course;
import SharedObjects.DBMessage;

import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;
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
	 * The textFields with which user will input the new assignment's due date
	 */
	private JTextField dueDateField = new JTextField();
	/**
	 * The user's inputs
	 */
	private String name, dueDate;
	
	private JFileChooser fc = new JFileChooser();
	
	private JButton chooseFileButton = new JButton("choose file");

	/**
	 * All the components to display on the frame
	 * Used for displaying multiple input textFields on frame
	 */
	private Object[] items = {"New Assignment", "Name:", assignmentNameField, "Due Date:", dueDateField, "File Upload:", chooseFileButton};
	
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
			dueDate = dueDateField.getText();
			
			//check that inputs are valid
			if(checkInputs()!=0) {
				clearInputs();
				return;
			}
			
			//construct the new assignment
						//TOOD change "path???" - ctor for assignment needs to receive path, but this path is meant to say the path on the server machine once it is saved there
			Assignment temp = new Assignment(-1, panel.getCourse().getID(), "path???", name, '0', dueDate); // '0' so the assignment starts as inactive // TODO change -1 to getHighestID in assignment table ?? 
		
			//make a message to tell the DB to add the Assignment
			ArrayList<Assignment> params = new ArrayList<>();
			params.add(temp);
			DBMessage msg = new DBMessage(0, 0, -1, -1, params); //TODO change -1, -1 to assignmentTableNum, addOpNum

			//send the message, get response
			ArrayList<?> response = controller.getCommunicator().communicate(msg); //TODO right now the addToDB() in Table.java is returning an int?
			checkResponse(response);
			//lastly, update the list
			controller.fillCoursePageAssignmentList(panel);
		}
		clearInputs();
		
	}
	
	/**
	 * Helper method to determine if add to DB was successfull
	 * @param response the arrayList returned by addToDB()
	 */
	private void checkResponse(ArrayList<?> response) {
		if(response.size()>1) {
			System.out.println("Unexpected error adding an Assignment: addToDB returned "+response.size()+" sized arrayList");
			JOptionPane.showMessageDialog(null, "Error: Assignment "+name+" may not have been created.", "Assignment Creation Error", JOptionPane.WARNING_MESSAGE);
		}
		else if (response.size()==0 || (Integer)response.get(0)!=1) {
			System.out.println("Error adding Assignment - addToDB returned empty arrayList or returned not 1");
			JOptionPane.showMessageDialog(null, "Error: Assignment "+name+" could not be created.", "Assignment Creation Error", JOptionPane.WARNING_MESSAGE);

		}	
		else {
			//success, DB added 1 row 
			System.out.println("Assignment "+name+" successfuly created.");
			JOptionPane.showMessageDialog(null,"Assignment "+name+" from file "+newFile.getName()+" successfuly created.", "Assignment Created", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	/**
	 * Helper method to check if the inputs are valid 
	 */
	private int checkInputs() {
		if(name.equals("") || dueDate.equals("")) {
			JOptionPane.showMessageDialog(null, "Error: Field can't be empty", "Error creating assignment", JOptionPane.WARNING_MESSAGE);
			return -1;
		}
		if(newFile==null) {
			JOptionPane.showMessageDialog(null, "Error: no assignment file chosen", "Error creating assignment", JOptionPane.WARNING_MESSAGE);
			return -2;
		}
		return 0;
	}

	/**
	 * Helper method to clear the nameField and the selected file
	 */
	private void clearInputs() {
		assignmentNameField.setText("");
		dueDateField.setText("");
		newFile=null;
	}


}

