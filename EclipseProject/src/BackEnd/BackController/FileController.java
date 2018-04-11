/**
 * 
 */
package BackEnd.BackController;

import java.io.Serializable;
import java.util.ArrayList;

import BackEnd.Model.ModelExecutor;
import SharedObjects.Assignment;
import SharedObjects.FileContents;
import SharedObjects.FileMessage;
import SharedObjects.Message;
import SharedObjects.Submission;

/**
 * This class implements the abstract methods in ModelController to control the FileOperator class.
 * @author 	Antoine Bizon & Ross Bartlett
 * @version 1.0
 * @since	2018-04-11
 */
class FileController extends ModelController {
	/**
	 * The file message sent to the controller.
	 */
	private FileMessage fileMessage;
	
	/**
	 * Constructs a FileController object by calling the classes super constructor. 
	 * @param theModel the ModelExecutor object used by the controller.
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
		else if(fileMessage.getOp() == 0) {
			return loadFile();
		}
		
		return new ArrayList<Integer>();
	}

	/**
	 * Loads a file from the database by calling the FileOperator's load file method.
	 * @return an ArrayList containing a FileContents object
	 */
	private ArrayList<? extends Serializable> loadFile() {
		ArrayList<FileContents> returnMessage = new ArrayList<FileContents>();
		if(fileMessage.getTable() == 3) {
			Assignment loadedAssign = (Assignment) theModel.getDatabase().getTableAt(3).search
					((String)fileMessage.getParams().get(0), (String)fileMessage.getParams().get(1)).get(0);
			
			returnMessage.add(theModel.getFileOperator().loadFile(loadedAssign.getPath()));
		}
		else if (fileMessage.getTable() == 4 ) {
			Submission loadedSubmission = (Submission) theModel.getDatabase().getTableAt(4).search
					((String)fileMessage.getParams().get(0), (String)fileMessage.getParams().get(1)).get(0);
			
			returnMessage.add(theModel.getFileOperator().loadFile(loadedSubmission.getPath()));
		}
		return returnMessage;
	}

	/**
	 * Saves a file from the database by calling the FileOperator's save file method.
	 * @return an ArrayList containing 1 if the file was saved, 0 otherwise.
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
			Submission newSubmission = (Submission) fileMessage.getParams().get(0);
			path = theModel.getFileOperator().saveFile(newSubmission.getTitle(), fileMessage.getExt(),
					fileMessage.getContents());
			newSubmission.setPath(path);
			return theModel.getDatabase().getTableAt(4).addToDB(newSubmission);
		}
		return new ArrayList<Integer>();
	}

}
