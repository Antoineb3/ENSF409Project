/**
 * 
 */
package SharedObjects;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author Ross
 *
 */
public class FileMessage extends DBMessage implements Serializable{
	static final long serialVersionUID = 11;
	
	private byte[] contents;
	private String ext;
	
	public FileMessage(int table, int op, ArrayList<?> params, byte[] contents, String ext) {
		super(1, 0, table, op, params); // isFile is 1, isEmail is 0
		this.contents = contents;
		this.ext = ext;
	}
	

}
