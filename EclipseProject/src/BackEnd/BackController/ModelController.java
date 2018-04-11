package BackEnd.BackController;

import java.io.Serializable;
import java.util.ArrayList;

import BackEnd.Model.ModelExecutor;
import SharedObjects.Message;

/**
 * This abstract class outlines the fields needed by each kind of model controller.
 * @author 	Antoine Bizon & Ross Bartlett
 * @version 1.0
 * @since	2018-04-03
 */
abstract class ModelController {
	/**
	 * The model executor used by all controller classes.
	 */
	protected ModelExecutor theModel;
	
	/**
	 * Calls the appropriate methods in the Model and returns an ArrayList.
	 * @param theMessage 
	 * @return
	 */
	abstract ArrayList<? extends Serializable> executeMessage(Message theMessage);

	/**
	 * Constructs a ModelControler object by setting the theModel field to the given ModelExecutor object. 
	 * @param theModel the model that a ModelControler object has.
	 */
	protected ModelController(ModelExecutor theModel) {
		this.theModel = theModel;
	}
}
