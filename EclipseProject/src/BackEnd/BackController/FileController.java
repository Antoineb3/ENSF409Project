/**
 * 
 */
package BackEnd.BackController;

import java.io.Serializable;
import java.util.ArrayList;

import BackEnd.Model.ModelExecutor;
import BackEnd.Model.Table;
import SharedObjects.Assignment;
import SharedObjects.DBMessage;
import SharedObjects.FileMessage;
import SharedObjects.Message;

/**
 * @author Antoine
 *
 */
class FileController extends ModelController {
	private FileMessage fileMessage;
	
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
		fileMessage = (FileMessage) theMessage;
		if(fileMessage.getOp() == 2) {
			return saveFile();
		}
		else if(fileMessage.getOp() == 2) {
			loadFile();
		}
		
		return null;
	}

	/**
	 * 
	 */
	private ArrayList<? extends Serializable> loadFile() {
		ArrayList<FileMessage> returnMessage = new ArrayList<FileMessage>();
		if(fileMessage.getTable() == 3) {
			Assignment loadedAssign = (Assignment) theModel.getDatabase().getTableAt(3).search
					((String)fileMessage.getParams().get(0), (String)fileMessage.getParams().get(1)).get(0);
			
			returnMessage.add(theModel.getFileOperator().loadFile(loadedAssign.getPath()));
		}
		else if (fileMessage.getTable() == 4 ) {
			//TODO file controller for submissions
		}
		return returnMessage;
	}

	/**
	 * 
	 */
	private ArrayList<? extends Serializable> saveFile() {
		String path;
		if(fileMessage.getTable() == 3) {
			Assignment newAssignment = (Assignment) fileMessage.getParams().get(0);
			path = theModel.getFileOperator().saveFile(newAssignment.getTitle(), fileMessage.getExt(),
														fileMessage.getContents());
			newAssignment.setPath(path);
			return theModel.getDatabase().getTableAt(3).addToDB(newAssignment);
		}
		else if (fileMessage.getTable() == 4 ) {
			//TODO file controller for submissions
		}
		return new ArrayList<Integer>();
	}

}
