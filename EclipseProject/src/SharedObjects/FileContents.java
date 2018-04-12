/**
 * 
 */
package SharedObjects;

import java.io.Serializable;

/**
 * This class contains all the fields necessary to reconstruct a file.
 * @author 	Ross Bartlett, Antoine Bizon
 */
public class FileContents implements Serializable{

	private static final long serialVersionUID = 100;
	private String title, extention;
	private byte[] contents;
	
	public FileContents(String title, String extention, byte[] contents) {
		this.title = title;
		this.extention = extention;
		this.contents = contents;
	}

	public String getTitle() {
		return title;
	}

	public String getExtention() {
		return extention;
	}

	public byte[] getContents() {
		return contents;
	}
	
	
	
	
}
