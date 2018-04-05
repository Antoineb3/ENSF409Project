/**
 * 
 */
package SharedObjects;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author 	Antoine Bizon & Ross Bartlett
 *
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
