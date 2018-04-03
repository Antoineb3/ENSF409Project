/**
 * 
 */
package BackEnd.BackController;

import java.io.Serializable;
import java.util.ArrayList;

import BackEnd.Model.ModelExecutor;
import SharedObjects.Message;

/**
 * @author Antoine
 *
 */
class FileController extends ModelController {

	/**
	 * @param theModel
	 */
	FileController(ModelExecutor theModel) {
		super(theModel);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see BackEnd.BackController.ModelController#executeMessage(SharedObjects.Message)
	 */
	@Override
	ArrayList<? extends Serializable> executeMessage(Message theMessage) {
		// TODO Auto-generated method stub
		return null;
	}

}
