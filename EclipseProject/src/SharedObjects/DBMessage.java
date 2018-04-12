/**
 * 
 */
package SharedObjects;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Objects of this class are used to communicate messages for accessing the database from the client to the server.
 * @author 	Ross Bartlett, Antoine Bizon
 */
public class DBMessage extends Message implements Serializable{
	static final long serialVersionUID = 12;
	private int table;
	private int op;
	private ArrayList<? extends Serializable> params;
	
	public DBMessage(int table, int op, ArrayList<? extends Serializable> params) {
		super();
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
