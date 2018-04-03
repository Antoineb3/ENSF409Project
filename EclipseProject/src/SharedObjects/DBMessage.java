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
	private ArrayList<? extends Serializable> params;
	
	public DBMessage(int isFile, int isEmail, int table, int op, ArrayList<? extends Serializable> params) {
		super(isFile, isEmail);
		this.table = table;
		this.op = op;
		this.params = params;
	}

	public int getTable() {
		return table;
	}

	public int getOp() {
		return op;
	}
	
	public ArrayList<? extends Serializable> getParams(){
		return params;
	}
	
	

}
