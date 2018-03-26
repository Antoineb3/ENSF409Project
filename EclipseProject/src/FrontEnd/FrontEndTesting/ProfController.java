package FrontEnd.FrontEndTesting;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProfController{
    ProfGUI profGUI;

    public ProfController(ProfGUI pg){
        profGUI=pg;
        
        pg.getProfCoursePagePanel().setHomepageButtonListener(new ActionListener(){
        
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("homepage button pressed. going back to homepage..");
                profGUI.setActiveCard("PROFHOMEPAGE");
            }
        });
    }

    //TODO in the listener that goes to a course page, use pg.getProfCoursePagePanel().setCourse(theCourse) before setActiveCard()

}
