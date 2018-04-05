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
 * @author 	Antoine Bizon
 * @version 1.0
 * @since	2018-04-03
 */
public class FileOperator {
	private String directory;
	
	public FileOperator(String dir) {
		directory = dir;
	}
	
	public String saveFile(FileMessage fileContents) {
		if(fileContents.getParams().get(0) instanceof Assignment) {
			String fileName = ((Assignment)fileContents.getParams().get(0)).getTitle();
			File newFile = new File( fileName + "." + fileContents.getExt()); 
			createFile(newFile, fileContents.getContents());
			return newFile.getAbsolutePath();
		}
		else if(true) {
			//TODO for submission
		}
		
		return null;
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
