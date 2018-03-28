package FrontEnd.FrontEndTesting;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProfController{
    ProfGUI profGUI;
    
    /**
     * Commonly used inner class listener to go back to the homepage
     */
    class HomepageButtonListener implements ActionListener {
    	@Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("homepage button pressed. going back to homepage..");
            profGUI.setActiveCard("PROFHOMEPAGE");
        }
	}

    public ProfController(ProfGUI pg){
        profGUI=pg;

        pg.getProfCoursePagePanel().setHomepageButtonListener(new HomepageButtonListener());
        pg.getViewStudentsPanel().setHomepageButtonListener(new HomepageButtonListener());
    }

    //TODO in the listener that goes to a course page, use pg.getProfCoursePagePanel().setCourse(theCourse) before setActiveCard()
    //and before going to  viewStudents page

}
