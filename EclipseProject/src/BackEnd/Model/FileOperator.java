/**
 * 
 */
package BackEnd.Model;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import SharedObjects.Assignment;
import SharedObjects.FileMessage;

/**
 * @author 	Antoine Bizon & Ross Bartlett
 * @version 1.0
 * @since	2018-04-03
 */
public class FileOperator {
	private String directory;
	
	public FileOperator(String dir) {
		directory = dir;
	}
	
	public String saveFile(String fileName, String extention, byte[] fileContents) {
		File newFile = new File( fileName + "." + extention); 
		createFile(newFile, fileContents);
		return newFile.getAbsolutePath();
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
	
	public FileMessage loadFile(String path) {
		
		return null;
	}


}
