/**
 * 
 */
package SharedObjects;

import java.io.Serializable;

/**
 * @author Ross
 *
 */
public class FileMessage extends Message implements Serializable{
	static final long serialVersionUID = 11;
	
	private byte[] contents;
	private String ext;
	
	public FileMessage(int isFile, int isEmail, byte[] contents, String ext) {
		super(isFile, isEmail);
		this.contents = contents;
		this.ext = ext;
	}
	

}
