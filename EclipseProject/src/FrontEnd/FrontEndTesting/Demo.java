package FrontEnd.FrontEndTesting;

import FrontEnd.FrontController.*;
import FrontEnd.View.*;

public class Demo{

    public static void main(String[] args) {
        ProfGUI pg = new ProfGUI();
        ProfController controller = new ProfController(pg);
        pg.setActiveCard("PROFCOURSEPAGE");
        
    }
}