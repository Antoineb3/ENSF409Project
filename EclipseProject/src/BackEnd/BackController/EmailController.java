/**
 * 
 */
package BackEnd.BackController;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

import BackEnd.Model.ModelExecutor;
import SharedObjects.Message;
import SharedObjects.ProfEmail;
import SharedObjects.StudentEmail;
import SharedObjects.StudentEnrollment;
import SharedObjects.User;

/**
 * This class implements the abstract methods in ModelController to control the EmialOperator class.
 * @author 	Antoine Bizon, Ross Bartlett 
 * @version 1.0
 * @since	2018-04-11
 */
class EmailController extends ModelController {

	/**
	 * Constructs a EmailController object by calling the classes super constructor. 
	 * @param theModel the ModelExecutor object used by the controller.
	 */
	EmailController(ModelExecutor theModel) {
		super(theModel);
	}

	/* (non-Javadoc)
	 * @see BackEnd.BackController.ModelController#executeMessage(SharedObjects.Message)
	 */
	@Override
	ArrayList<? extends Serializable> executeMessage(Message theMessage) {
		ArrayList<Integer> sucess = new ArrayList<Integer>();
		if(theMessage instanceof ProfEmail) {
			sucess.add(sendProfEmail((ProfEmail) theMessage));
		}
		else if(theMessage instanceof StudentEmail) {
			sucess.add(sendStudentEmail((StudentEmail) theMessage));
		}
		else {
			sucess.add(-1);
		}
		System.out.println(sucess.get(0));
		return sucess;
	}
	

	/**
	 * Sends a prof email by retrieving all necessary information from the database sending the information 
	 * as parameters to the sendEmail method in the EmailOperator class.
	 * @param profEmail the prof email message with information on needed to send an email.
	 * @return 0 if the email was sent successfully, -1 otherwise. 
	 */
	private int sendProfEmail(ProfEmail profEmail){
		ArrayList <StudentEnrollment> students =(ArrayList<StudentEnrollment>) theModel.getDatabase().getTableAt(2)
				.search("COURSEID", "'" + profEmail.getCourse().getID()+ "'");
		Iterator<StudentEnrollment> enrolments = students.iterator();
		ArrayList <User> recipients = new ArrayList <User>();
		while(enrolments.hasNext()) {
			recipients.add((User) theModel.getDatabase().getTableAt(0).
					search("ID","'"+ enrolments.next().getStudentID() +"'").get(0));
		}
		return theModel.getEmailOperator().
				sendEmail((User)profEmail.getTheProf(), recipients, profEmail.getSubject(), profEmail.getContents());
	}
	
	/**
	 * Sends a student email by retrieving all necessary information from the database sending the information 
	 * as parameters to the sendEmail method in the EmailOperator class.
	 * @param studentEmail the student email message with information on needed to send an email.
	 * @return 0 if the email was sent successfully, -1 otherwise. 
	 */
	private Integer sendStudentEmail(StudentEmail studentEmail) {
		ArrayList <User> recipients = new ArrayList <User>();
		recipients.add((User) theModel.getDatabase().getTableAt(0).
				search("ID","'"+ studentEmail.getCourse().getProfID() +"'").get(0));
		return theModel.getEmailOperator().sendEmail
				((User)studentEmail.getStudent(), recipients, studentEmail.getSubject(), studentEmail.getContents());
	}

}
