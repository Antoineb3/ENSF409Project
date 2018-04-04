package FrontEnd.FrontController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import FrontEnd.View.LoginGUI;
import FrontEnd.View.ProfGUI;
import SharedObjects.DBMessage;
import SharedObjects.Professor;
import SharedObjects.Student;
import SharedObjects.User;



public class LoginController extends ViewController{

	public LoginController(LoginGUI gui, ClientSocketCommunicator c){
		super(gui, c); 
		gui.setLoginButtonListener(new LoginButtonListener());
	}

	/**
	 * Inner class listener for the Login button
	 */
	class LoginButtonListener implements ActionListener {
		/**
		 * The user's login inputs
		 */
		String userID, password;
		@Override
		public void actionPerformed(ActionEvent e) {
			LoginGUI gui = ((LoginGUI) frame);
			//get the login credential inputs
			if( getInputs(gui) == -1) return;
			
			
			//make a message to query the DB user table for the username 
			ArrayList<String> params = new ArrayList<>();
			params.add("id"); // the column in the table to search
			params.add(userID); // the search key // TODO will have to convert this string to int in the model?
			DBMessage msg = new DBMessage(0, 0, 0, 0, params); // 0, 0 is userTableNum, searchOpNum

			//send the message, get response
			ArrayList<? extends Serializable> response = communicator.communicate(msg);
			if (response.size()==0) {
				JOptionPane.showMessageDialog(null, "User ID not found.", "Login Error", JOptionPane.WARNING_MESSAGE);
				gui.clearLoginFields();
				return;
			}
			
			User temp = (User) response.get(0); // the returned user in the table
			tryLogin(gui, temp);
		}
		
		/**
		 * helper method to get the login inputs and check if valid
		 * @param the login gui
		 * @return -1 if invalid, else 0
		 */
		private int getInputs(LoginGUI gui ) {
			userID = gui.getUsernameInput();
			password = gui.getPasswordInput();
			if(userID.equals("") || password.equals("")) {
				JOptionPane.showMessageDialog(null, "Error: Login field(s) cannot be empty.", "Login Error", JOptionPane.WARNING_MESSAGE);
				gui.clearLoginFields();
				return -1;
			}
			else return 0;
		}

		/**
		 * helper method to verify correct password, start the prof/student gui
		 * @param temp the User returned from the DB
		 */
		public void tryLogin(LoginGUI gui, User temp) {
			if (temp.getPassword().equals(password)){
				//logged in 
				ClientSocketCommunicator csc = ((ClientSocketCommunicator) communicator); 
				if(temp instanceof Professor) {
					ProfGUI pg = new ProfGUI((Professor)temp);
			        ProfController controller = new ProfController(pg, csc);	
			        gui.dispose(); //close the LoginGUI
				}
				else if(temp instanceof Student) {
//					StudentGUI sg = new StudentGUI((Student)temp);
//			        StudentController controller = new StudentController(sg, csc);
					gui.dispose(); //close the LoginGUI
				}
			}
			else {
				JOptionPane.showMessageDialog(null, "Incorrect Password. Please try again.", "Login Error", JOptionPane.WARNING_MESSAGE);
				gui.clearLoginFields();
			}
			
		}
	}//end of inner class LoginButtonListener

	
	
	
	
	
}


