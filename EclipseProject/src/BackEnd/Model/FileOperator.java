/**
 * 
 */
package BackEnd.Model;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import SharedObjects.FileMessage;

/**
 * This class saves files to and loads files from the server side. 
 * @author 	Antoine Bizon & Ross Bartlett
 * @version 1.0
 * @since	2018-04-03
 */
public class FileOperator {
	private File returnFile;
	
	/**
	 * Constructs a file operator object.
	 */
	public FileOperator() {
		returnFile = null;
	}
	
	/**
	 * Saves a file to the local directory of the server side eclipse project.  
	 * @param fileName the name of the file.
	 * @param extention the extension of the file (Example, .pdf, .docx) 
	 * @param fileContents the byte contents of the file.
	 * @return the absolute path to the file.
	 */
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
	
	
	/**
	 * Creates a file message with the extension and byte contents of the file given the files path. 
	 * @param path the path to the file.
	 * @return the fileMessage containing the files information. 
	 */
	public FileMessage loadFile(String path) {
		returnFile = new File(path); 
		String ext = getFileExtension();
		byte[] content = getFileContents();
		FileMessage returnMessage = new FileMessage(-1, -1, null, content, ext);
		return returnMessage;
	}
	
	/**
	 * Helper method to extract the file extension from the file.
	 * example: file "foo.pdf" will have extension "pdf"
	 * @return the file extension 
	 */
	private String getFileExtension() {
		String s = "";
		int i = returnFile.getName().lastIndexOf('.');
		if (i >= 0) {
			s = returnFile.getName().substring(i+1);
		}
		return s;
	}
	
	/**
	 * Helper method to get the bytes of the returnFile
	 * @return array of bytes of the file content
	 */
	private byte[] getFileContents() {
		long length = returnFile.length();
		byte[] content = new byte[(int) length]; // Converting long to int 
		FileInputStream fis ;
		BufferedInputStream bos;
		try {
			fis = new FileInputStream(returnFile); 
			bos = new BufferedInputStream(fis); 
			bos.read(content, 0, (int)length);
			fis.close();
			bos.close();
		} catch (FileNotFoundException FNFe) { FNFe.printStackTrace();
		} catch(IOException ioe){ ioe.printStackTrace();
		}
		return content;
	}


}
