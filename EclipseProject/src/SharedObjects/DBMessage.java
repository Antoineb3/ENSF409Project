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
public class DBMessage extends Message implements Serializable{
	static final long serialVersionUID = 12;
	private int table;
	private int op;
	private ArrayList<?> params;
	
	public DBMessage(int isFile, int isEmail, int table, int op, ArrayList<?> params) {
		super(isFile, isEmail);
		this.table = table;
		this.op = op;
		this.params = params;
	}
	
	

}
