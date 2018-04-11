/**
 * 
 */
package FrontEnd.FrontController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JPanel;


import FrontEnd.View.DropboxPage;
import FrontEnd.View.ProfAssignmentPage;
import FrontEnd.View.StudentAssignmentPage;
import SharedObjects.FileContents;
import SharedObjects.FileMessage;

/**
 * Listener for the button to download an assignment/submission
 * @author 	Antoine Bizon & Ross Bartlett
 */
public class DownloadButtonListener implements ActionListener {
	/**
	 * The panel with the button calling this listener
	 */
	private JPanel panel;
	/**
	 * the controller constructing this listener
	 */
	private ViewController controller;

	/**
	 * constructor to initialize the listener
	 * @param p the frame/panel
	 * @param c the controller
	 */
	public DownloadButtonListener(JPanel p, ViewController c) {
		panel = p;
		controller=c;
	}

	/**
	 * queries the file from the DB and saves it to the local machine
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String fileName="";
		int tableNum=-1;
		if(panel instanceof ProfAssignmentPage) {
			 fileName = ((ProfAssignmentPage) panel).getAssignment().getTitle();
			 tableNum=3; // use assignmentTable
		}
		else if (panel instanceof StudentAssignmentPage) {
			 fileName = ((StudentAssignmentPage) panel).getAssignment().getTitle();
			 tableNum=3;// use assignmentTable
		}
		else if (panel instanceof DropboxPage) {
			 fileName = ((DropboxPage) panel).getSelectedSubmission().getTitle();
			 tableNum=4;// use submissionTable
		}
		else {
			System.err.println("error getting panel type in download button listener..");
			return;
		}
		//make a message to ask the DB to load a file with the given name
		ArrayList<String> params = new ArrayList<String>();
		params.add("TITLE"); 
		params.add("'"+fileName+"'");
		FileMessage msg = new FileMessage(tableNum, 0, params, null, null); // 0 is searchOpNum
		//send the message, get response
		ArrayList<? extends Serializable> response = controller.getCommunicator().communicate(msg);

		//response will be an arrayList containing a FileContents object
		if(response.size()<1) {
			JOptionPane.showMessageDialog(null, "Error getting File response from server", "Download Error", JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		FileContents fileContents = (FileContents) response.get(0);
		
		saveFile(fileContents.getTitle(), fileContents.getExtention(), fileContents.getContents());
	}
	
	/**
	 * Saves a file to the local directory  
	 * @param fileName the name of the file.
	 * @param extention the extension of the file (Example, .pdf, .docx) 
	 * @param fileContents the byte contents of the file.
	 */
	private void saveFile(String fileName, String extention, byte[] fileContents) {
		File newFile = new File( fileName + "." + extention); 
		System.out.println("Saving download as: "+ fileName +"."+extention+" to: "+newFile.getAbsolutePath()+" ...");
		createFile(newFile, fileContents);
	}
	
	/**
	 * Helper method to create a file and fill it with contents
	 */
	private void createFile(File newFile, byte[] content) {
		try{
			newFile.createNewFile();
			FileOutputStream writer = new FileOutputStream(newFile); 
			BufferedOutputStream bos = new BufferedOutputStream(writer); 
			bos.write(content);
			//success
			JOptionPane.showMessageDialog(null, "File saved as: "+ newFile.getName() +" to: "+newFile.getAbsolutePath(), "Download Success", JOptionPane.INFORMATION_MESSAGE);
			bos.close();
		} catch(IOException ex){ 
			ex.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error extracting download file.", "Error downloading assignment", JOptionPane.WARNING_MESSAGE);
		}
		
	}

}
