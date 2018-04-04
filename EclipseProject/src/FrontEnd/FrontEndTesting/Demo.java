package FrontEnd.FrontEndTesting;

import FrontEnd.FrontController.*;
import FrontEnd.View.*;
import SharedObjects.Professor;

public class Demo{

	public static void main(String[] args) {

		ClientSocketCommunicator c = new ClientSocketCommunicator("localhost", 9898);

		Professor tempProf = new Professor(-1, "password", "email","firstName","LastName");
		ProfGUI pg = new ProfGUI(tempProf);
		ProfController controller = new ProfController(pg, c);

		pg.setActiveCard("VIEWSTUDENTSPAGE"); //change this arg to set the startup page

	}
}