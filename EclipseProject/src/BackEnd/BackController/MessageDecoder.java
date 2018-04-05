package BackEnd.BackController;

import java.io.Serializable;
import java.util.ArrayList;

import SharedObjects.DBMessage;
import SharedObjects.EmailMessage;
import SharedObjects.FileMessage;
import SharedObjects.Message;

/**
 * @author 	Antoine Bizon & Ross Bartlett
 *
 */
class MessageDecoder {
	private ModelController[] controllers;
	
	
	MessageDecoder(DBController dbController, FileController fileController,EmailController emailController) {
		controllers = new ModelController[3];
		controllers[0] = fileController;
		controllers[1] = emailController;
		controllers[2] = dbController;
	}
	
	
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
