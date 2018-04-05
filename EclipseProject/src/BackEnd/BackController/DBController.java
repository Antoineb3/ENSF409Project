package BackEnd.BackController;

import java.io.Serializable;
import java.util.ArrayList;

import BackEnd.Model.ModelExecutor;
import BackEnd.Model.Table;
import SharedObjects.DBMessage;
import SharedObjects.Message;

/**
 * This class implements the abstract methods in ModelController to control the DB class.
 * @author 	Antoine Bizon
 * @version 1.0
 * @since	2018-04-03
 */
class DBController extends ModelController {
	private DBMessage dbMessage;
	
	/**
	 * Constructs s DBController object by calling the classes super constructor. 
	 * @param theModel
	 */
	DBController(ModelExecutor theModel) {
		super(theModel);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see BackEnd.BackController.ModelController#executeMessage(SharedObjects.Message)
	 */
	@Override
	ArrayList<? extends Serializable> executeMessage(Message theMessage) {
		dbMessage = (DBMessage) theMessage;
		Table theTable = theModel.getDatabase().getTableAt(dbMessage.getTable());
		ArrayList<? extends Serializable> returnMessage = preformOperation(theTable);
		return returnMessage;
	}

	/**
	 * Decides what operation needs to be performed on the table and returns the appropriate ArrayList.
	 * @param theTable
	 * @return
	 */
	private ArrayList<? extends Serializable> preformOperation(Table theTable) {
		//search(parameter, key)
		if(dbMessage.getOp() == 0) {
			return theTable.search((String)dbMessage.getParams().get(0), (String)dbMessage.getParams().get(1));
		}
		//edit(parameter, key, condition, value)
		else if(dbMessage.getOp() == 1) {
			return theTable.editRow((String)dbMessage.getParams().get(0), (String)dbMessage.getParams().get(1),
									(String)dbMessage.getParams().get(2), (String)dbMessage.getParams().get(3));
		}
		//add(? extends Serializable)
		else if(dbMessage.getOp() == 2) {
			return theTable.addToDB(dbMessage.getParams().get(0));
		}
		//Remove(parameter, key)
		else if(dbMessage.getOp() == 3) {
			return theTable.remove((String)dbMessage.getParams().get(0), (String)dbMessage.getParams().get(1));
		}
		
		return new ArrayList<Integer>();
	}

}
