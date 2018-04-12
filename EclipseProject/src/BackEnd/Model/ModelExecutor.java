package BackEnd.Model;

/**
 * This is the class that the Model Controller communicates with, it contains all the operators for the model. 
 * @author 	Antoine Bizon, Ross Bartlett 
 * @version 1.0
 * @since	2018-04-03
 */
public class ModelExecutor {
	/**
	 * The database object.
	 */
	private DB theDatabase;
	/**
	 * The file operator. 
	 */
	private FileOperator theFileOperator;
	/**
	 * The email operator.
	 */
	private EmailOperator theEmailOperator;
	
	/**
	 * Constructs a ModelExecutor object. 
	 */
	public ModelExecutor() {
		theDatabase = new DB();
		theFileOperator = new FileOperator();
		theEmailOperator = new EmailOperator();
	}

	/**
	 * Returns the database.
	 * @return theDatabase
	 */
	public DB getDatabase() {
		return theDatabase;
	}

	/**
	 * Returns the file operator.
	 * @return theFileOperator
	 */
	public FileOperator getFileOperator() {
		return theFileOperator;
	}

	/**
	 * Returns the email operator.
	 * @return theEmailOperator
	 */
	public EmailOperator getEmailOperator() {
		return theEmailOperator;
	}
	
}
