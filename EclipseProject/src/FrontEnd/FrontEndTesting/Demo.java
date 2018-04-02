package FrontEnd.FrontEndTesting;

import FrontEnd.FrontController.*;
import FrontEnd.View.*;

public class Demo{

    public static void main(String[] args) {
        
        ClientSocketCommunicator c = new ClientSocketCommunicator("localhost", 9898);
        
        ProfGUI pg = new ProfGUI();
        
        ProfController controller = new ProfController(pg, c);
        
        pg.setActiveCard("PROFCOURSEPAGE"); //change this arg to set the startup page
        
    }
}