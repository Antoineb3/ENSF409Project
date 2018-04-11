package BackEnd.BackController;

import java.io.Serializable;
import java.util.ArrayList;

import SharedObjects.DBMessage;
import SharedObjects.EmailMessage;
import SharedObjects.FileMessage;
import SharedObjects.Message;

/**
 * Calls the appropriate controllers executeMessage method based on the message type.
 * @author 	Antoine Bizon & Ross Bartlett
 * @version 1.0
 * @since	2018-04-11
 */
class MessageDecoder {
	/**
	 * An array containing all the controllers.
	 */
	private ModelController[] controllers;
	
	/**
	 * Constructs a MessageDecoder object.
	 * @param dbController the database controller.
	 * @param fileController the file controller.
	 * @param emailController the email controller.
	 */
	MessageDecoder(DBController dbController, FileController fileController,EmailController emailController) {
		controllers = new ModelController[3];
		controllers[0] = fileController;
		controllers[1] = emailController;
		controllers[2] = dbController;
	}
	
	/**
	 * Calls the appropriate executeMessage method.
	 * @param theMessage the message being sent form the user to the server. 
	 * @return The return value of the executeMessage method called.
	 */
	ArrayList<? extends Serializable> decodeMessage(Message theMessage){
		if(theMessage instanceof FileMessage) {
			return controllers[0].executeMessage(theMessage);
		}
		else if(theMessage instanceof EmailMessage) {
			return controllers[1].executeMessage(theMessage);
		}
		else if(theMessage instanceof DBMessage) {
			return controllers[2].executeMessage(theMessage);
		}
		return null;
	}
}
