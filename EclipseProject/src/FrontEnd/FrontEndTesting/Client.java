package FrontEnd.FrontEndTesting;

import FrontEnd.FrontController.*;
import FrontEnd.View.*;

/**
 * FrontEnd client to run the application
 * @author 	Ross Bartlett, Antoine Bizon
 */
public class Client{

	public static void main(String[] args) {
		ClientSocketCommunicator c = new ClientSocketCommunicator("localhost", 7800);

		LoginGUI loginGUI = new LoginGUI();
		LoginController controller = new LoginController(loginGUI, c);

	}
}