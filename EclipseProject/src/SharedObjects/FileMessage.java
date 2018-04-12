/**
 * 
 */
package SharedObjects;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Objects of this class are used to communicate messages for saving and loading from the client to the server.
 * @author 	Ross Bartlett, Antoine Bizon
 */
public class FileMessage extends DBMessage implements Serializable{
	static final long serialVersionUID = 11;
	
	private byte[] contents;
	private String ext;
	
	public FileMessage(int table, int op, ArrayList<? extends Serializable> params, byte[] contents, String ext) {
		super(table, op, params); // isFile is 1, isEmail is 0
		this.contents = contents;
		this.ext = ext;
	}

	/**
	 * @return the contents
	 */
	public byte[] getContents() {
		return contents;
	}

	/**
	 * @return the ext
	 */
	public String getExt() {
		return ext;
	}
	
	
	

}
