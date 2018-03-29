package FrontEnd.FrontController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import FrontEnd.View.ProfGUI;


//TODO what relationship between actionListeners and ProfController ? ViewController?


public class ProfController extends ViewController{
    
	
   
    public ProfController(ProfGUI pg){
    		super(pg); // sets the frame to pg 

        pg.getProfCoursePagePanel().setHomepageButtonListener(new HomepageButtonListener());
        pg.getViewStudentsPanel().setHomepageButtonListener(new HomepageButtonListener());
        
        
        pg.getProfCoursePagePanel().setNewAssignmentButtonListener(new NewAssignmentListener(pg.getProfCoursePagePanel(), this));
        
       
    }
    
    /**
     * Commonly used inner class listener to go back to the homepage
     */
    class HomepageButtonListener implements ActionListener {
    	@Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("homepage button pressed. going back to homepage..");
            ((ProfGUI) frame).setActiveCard("PROFHOMEPAGE");
        }
	}

    //TODO in the listener that goes to a course page, use pg.getProfCoursePagePanel().setCourse(theCourse) before setActiveCard()
    //and before going to  viewStudents page

}
