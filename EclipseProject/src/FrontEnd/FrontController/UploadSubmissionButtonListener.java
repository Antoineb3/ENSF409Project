
package FrontEnd.FrontController;

import javax.swing.*;

import FrontEnd.View.StudentAssignmentPage;
import SharedObjects.Assignment;
import SharedObjects.Course;
import SharedObjects.DBMessage;
import SharedObjects.FileMessage;
import SharedObjects.Grade;
import SharedObjects.Student;
import SharedObjects.Submission;

import java.awt.Dimension;
import java.awt.event.*;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Listener class for the Upload Submission button 
 * @author 	Ross Bartlett, Antoine Bizon
 */
public class UploadSubmissionButtonListener implements ActionListener{
	/**
	 * The panel with the button calling this listener
	 */
	private StudentAssignmentPage panel;
	/**
	 * The student
	 */
	private Student student;
	/**
	 * the controller constructing this listener
	 */
	private StudentController controller;
	/**
	 * The textFields with which user will input the new submission name
	 */
	private JTextField submissionNameField = new JTextField();
	/**
	 * The textArea with which user will input the new submission name
	 */
	private JTextArea commentsArea = new JTextArea(5,20);
	JScrollPane commentsScroller = new JScrollPane(commentsArea);

	/**
	 * The user's inputs
	 */
	private String name, comments, ext;
	/**
	 * the file chooser
	 */
	private JFileChooser fc = new JFileChooser();
	/**
	 * the button to launch the file chooser
	 */
	private JButton chooseFileButton = new JButton("choose file");

	/**
	 * All the components to display on the frame
	 * Used for displaying multiple input textFields on frame
	 */
	private Object[] items = {"New Submission", "Name:", submissionNameField, "Comments:",commentsScroller, "File Upload:", chooseFileButton};
	
	/**
	 * the submission 
	 */
	private File newFile=null;

	/**
	 * Constructor that inits the calling JPanel and the listener for the file chooser
	 * @param p the calling JPanel
	 * @param student 
	 * @param c the controller to access the backend
	 */
	public UploadSubmissionButtonListener(StudentAssignmentPage p, Student s, StudentController c) {
		panel = p;
		controller=c;
		student=s;
		submissionNameField.setEditable(true);
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
	 * Displays a JOptionPane to upload a new submission
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		int option = JOptionPane.showConfirmDialog(null, items, "Upload a New Submission", JOptionPane.OK_CANCEL_OPTION);
		if (option == JOptionPane.OK_OPTION) {
			name = submissionNameField.getText();
			comments = commentsArea.getText();
			ext = getFileExtension();
			byte[] content = getFileContents();

			//check that inputs are valid
			if(checkInputs()!=0 || checkContents(content)!=0) {
				clearInputs();
				return;
			}
			
			if(comments.length()>=140) {
				JOptionPane.showMessageDialog(null, "Error: Comment field must be <140 chars.", "Submission Creation Error", JOptionPane.WARNING_MESSAGE);
				return;
			}
			

			String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm").format(new java.util.Date());
			
			//construct the new submission
			Submission submission = new Submission(-1, panel.getAssignment().getID(), student.getID(), null, name, -1, comments, timeStamp); // path is null, grade is -1
		
			//make a message to tell the DB to add the submission
			ArrayList<Submission> params = new ArrayList<>();
			params.add(submission);
			FileMessage msg = new FileMessage(4, 2, params, content, ext); // 4, 2 is submissionTableNum, addOpNum

			//send the message, get response
			ArrayList<? extends Serializable> response = controller.getCommunicator().communicate(msg);
			if(response==null) {
				System.out.println("null response");
			}
			System.out.println("size is " + response.size());
			if(checkResponse(response)&&newGradeNeeded()) {
				addNewGrade();
			}

		}
		clearInputs();
	}

	/**
	 * Helper method to add a new grade row to the database.
	 */
	private void addNewGrade() {
		Grade grade = new Grade(-1, panel.getAssignment().getID(), student.getID(), panel.getAssignment().getCourseID(), 0);
		ArrayList<Grade> params = new ArrayList<>();
		params.add(grade);
		DBMessage msg = new DBMessage(5, 2, params); // 5,2 is gradeTableNum, addOpNum
		ArrayList<? extends Serializable> response = controller.getCommunicator().communicate(msg);
		if(response==null) {
			System.out.println("null response");
		}
		checkGradeResponse(response);
	}

	/**
	 * @param response
	 */
	private void checkGradeResponse(ArrayList<? extends Serializable> response) {
		if(response.size()>1) {
			System.out.println("Unexpected error adding a Grade: addToDB returned "+response.size()+" sized arrayList");
			JOptionPane.showMessageDialog(null, "Error: Submission "+name+" may not have been created.", "Submission Creation Error", JOptionPane.WARNING_MESSAGE);
		}
		else if (response.size()==0 || (Integer)response.get(0)!=1) {
			System.out.println("Error adding Grade - addToDB returned empty arrayList or returned not 1");
			JOptionPane.showMessageDialog(null, "Error: Submission "+name+" could not be created.", "Submission Creation Error", JOptionPane.WARNING_MESSAGE);
		}			
		else System.out.println("new grade row added to grade table");
	}

	/**
	 * Helper method to check if a new row in the grade table needs to be added for the submission.
	 * @return true if it's the students first submission, false otherwise. 
	 */
	private boolean newGradeNeeded() {
		ArrayList<String> params = new ArrayList<>();
		params.add("ASSIGNID"); // the column in the table to search
		params.add("'"+panel.getAssignment().getID()+"'"); // the search key 
		params.add("STUDENTID");
		params.add("'"+student.getID()+"'");
				
		DBMessage msg = new DBMessage(4, 0, params); // 4, 0 is submissionTableNum, searchOpNum
		//response should be a list of submissions
		ArrayList<? extends Serializable> response = controller.getCommunicator().communicate(msg);
		if(response.size()==1)
			return true;
		else
			return false;
	}

	/**
	 * Helper method to determine if the bytes of the File were read successfully
	 * @param content the bytes of the file
	 * @return  -1 for invalid contents, else 0 
	 */
	private int checkContents(byte[] content) {
		if (content==null) {
			System.out.println("Error: File could not be converted into bytes[]");
			JOptionPane.showMessageDialog(null, "Error: Submission "+name+"could not be converted to bytes[].", "Submission Creation Error", JOptionPane.WARNING_MESSAGE);
			return -1;
		}
		return 0;
	}



	/**
	 * Helper method to get the bytes of the newFile
	 * @return array of bytes of the file content
	 */
	private byte[] getFileContents() {
		long length = newFile.length();
		byte[] content = new byte[(int) length]; // Converting long to int 
		FileInputStream fis ;
		BufferedInputStream bos;
		try {
			fis = new FileInputStream(newFile); 
			bos = new BufferedInputStream(fis); 
			bos.read(content, 0, (int)length);
			fis.close();
			bos.close();
		} catch (FileNotFoundException FNFe) { FNFe.printStackTrace();
		} catch(IOException ioe){ ioe.printStackTrace();
		}
		return content;
	}


	/**
	 * Helper method to extract the file extension from the file.
	 * example: file "foo.pdf" will have extension "pdf"
	 * @return the file extension 
	 */
	private String getFileExtension() {
		String s = "";
		int i = newFile.getName().lastIndexOf('.');
		if (i >= 0) {
			s = newFile.getName().substring(i+1);
		}
		return s;
	}

	/**
	 * Helper method to determine if add to DB was successful
	 * @param response the arrayList returned by addToDB()
	 * @return 
	 */
	private boolean checkResponse(ArrayList<? extends Serializable> response) {
		//DB will return the number of rows added/affected
		if(response.size()>1) {
			System.out.println("Unexpected error adding a Submission: addToDB returned "+response.size()+" sized arrayList");
			JOptionPane.showMessageDialog(null, "Error: Submission "+name+" may not have been created.", "Submission Creation Error", JOptionPane.WARNING_MESSAGE);
			return false;
		}
		else if (response.size()==0 || (Integer)response.get(0)!=1) {
			System.out.println("Error adding Submission - addToDB returned empty arrayList or returned not 1");
			JOptionPane.showMessageDialog(null, "Error: Submission "+name+" could not be created.", "Submission Creation Error", JOptionPane.WARNING_MESSAGE);
			return false;
		}	
		else {
			//success, DB added 1 row 
			System.out.println("Submission "+name+" successfuly uploaded.");
			JOptionPane.showMessageDialog(null,"Submission "+name+" from file "+newFile.getName()+" successfuly uploaded.", "Submission Uploaded", JOptionPane.INFORMATION_MESSAGE);
			return true;
		}
	}

	/**
	 * Helper method to check if the inputs are valid 
	 */
	private int checkInputs() {
		if(name.equals("")) {
			JOptionPane.showMessageDialog(null, "Error: Name Field can't be empty", "Error creating Submission", JOptionPane.WARNING_MESSAGE);
			return -1;
		}
		if(newFile==null) {
			JOptionPane.showMessageDialog(null, "Error: no Submission file chosen", "Error creating Submission", JOptionPane.WARNING_MESSAGE);
			return -2;
		}
		if(ext.equals("")) {
			JOptionPane.showMessageDialog(null, "Error: Submission file extension is empty", "Error creating Submission", JOptionPane.WARNING_MESSAGE);
			return -3;
		}
		return 0;
	}

	/**
	 * Helper method to clear the nameField and the selected file
	 */
	private void clearInputs() {
		submissionNameField.setText("");
		commentsArea.setText("");
		newFile=null;
	}


}

