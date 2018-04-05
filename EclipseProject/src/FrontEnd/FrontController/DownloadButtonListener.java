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

		System.out.println("TODO need to implmement downloading a file still");
		//TODO need a FileMessage containing a byte [] contents sent back to me 
//		if(response.size()<1) {
//			JOptionPane.showMessageDialog(null, "Error fetching assignment file.", "Error downloading assignment", JOptionPane.WARNING_MESSAGE);
//			return;
//		}
//		byte [] content;
//		String ext;
//		try {
//			content = (byte[]) ((FileMessage)response.get(0)).getContents();
//			ext =  (String) ((FileMessage)response.get(0)).getExt();
//		}
//		catch(Exception e1) {
//			JOptionPane.showMessageDialog(null, "Error extracting assignment file.", "Error downloading assignment", JOptionPane.WARNING_MESSAGE);
//			return;
//		}
		
//
//		File newFile = new File( fileName +"."+ext); 
//		createFile(newFile, content);
//		System.out.println("File saved as: "+ fileName +"."+ext+" to: "+newFile.getAbsolutePath());
//		JOptionPane.showMessageDialog(null, "File saved as: "+ fileName +"."+ext+" to: "+newFile.getAbsolutePath(), "Assignment Downloaded", JOptionPane.INFORMATION_MESSAGE);


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
		} catch(IOException ex){ ex.printStackTrace();
		}
	}

}
