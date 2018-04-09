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

import FrontEnd.View.ProfAssignmentPage;
import FrontEnd.View.ProfHomepage;
import SharedObjects.FileContents;
import SharedObjects.FileMessage;

/**
 * Listener for the button to download an assignment
 * @author 	Antoine Bizon & Ross Bartlett
 */
public class DownloadButtonListener implements ActionListener {
	/**
	 * The panel with the button calling this listener
	 */
	private ProfAssignmentPage panel;
	/**
	 * the controller constructing this listener
	 */
	private ProfController controller;

	//ctor
	public DownloadButtonListener(ProfAssignmentPage p, ProfController c) {
		panel = p;
		controller=c;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		String fileName = panel.getAssignment().getTitle();

		//make a message to ask the DB to send a file with the given name
		ArrayList<String> params = new ArrayList<String>();
		params.add("TITLE"); 
		params.add("'"+fileName+"'");
		FileMessage msg = new FileMessage(3, 0, params, null, null); // 3, 0 is assingmentTableNum, searchOpNum
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
	public void saveFile(String fileName, String extention, byte[] fileContents) {
		File newFile = new File( fileName + "." + extention); 
		System.out.println("Saving download as: "+ fileName +"."+extention+" to: "+newFile.getAbsolutePath()+" ...");
		createFile(newFile, fileContents);
	}
	
	/**
	 * Helper method to create a file and fill it with contents
	 */
	private void createFile(File newFile, byte[] content) {
		try{
			if(! newFile.exists()) newFile.createNewFile();
			FileOutputStream writer = new FileOutputStream(newFile); 
			BufferedOutputStream bos = new BufferedOutputStream(writer); 
			bos.write(content);
			bos.close();
		} catch(IOException ex){ 
			ex.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error extracting download file.", "Error downloading assignment", JOptionPane.WARNING_MESSAGE);
			return;
		}
		//success
		JOptionPane.showMessageDialog(null, "File saved as: "+ newFile.getName() +" to: "+newFile.getAbsolutePath(), "Download Success", JOptionPane.INFORMATION_MESSAGE);
	}

}
