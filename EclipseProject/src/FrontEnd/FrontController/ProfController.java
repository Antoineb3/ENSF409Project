package FrontEnd.FrontController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import FrontEnd.View.ProfGUI;


//TODO what relationship between actionListeners and ProfController ? ViewController?


public class ProfController extends ViewController{
    
	
   
    public ProfController(ProfGUI pg){
    		super(pg); // sets the frame to pg 

    		//set all the back to homepage buttons
        pg.getProfCoursePagePanel().setHomepageButtonListener(new CardChangerListener("PROFHOMEPAGE"));
        pg.getViewStudentsPanel().setHomepageButtonListener(new CardChangerListener("PROFHOMEPAGE"));
        pg.getProfAssignmentPanel().setHomepageButtonListener(new CardChangerListener("PROFHOMEPAGE"));
        
        //set all the back buttons
        pg.getProfAssignmentPanel().setBackButtonListener(new CardChangerListener("PROFCOURSEPAGE"));
        pg.getViewStudentsPanel().setBackButtonListener(new CardChangerListener("PROFCOURSEPAGE"));

        
        //set other navigator buttons
        pg.getProfCoursePagePanel().setViewStudentsButtonListener(new CardChangerListener("VIEWSTUDENTSPAGE"));
        
        pg.getProfCoursePagePanel().setNewAssignmentButtonListener(new NewAssignmentButtonListener(pg.getProfCoursePagePanel(), this));
        pg.getProfHomePagePanel().setCreateNewCourseButtonListener(new NewCourseButtonListener(pg.getProfHomePagePanel(), this));
        
       
    }
    
    /**
     * Commonly used inner class listener to go back to the homepage
     */
    class CardChangerListener implements ActionListener {
    		String card;
			public CardChangerListener(String c) {
				card = c;
			}
	    	@Override
	        public void actionPerformed(ActionEvent e) {
	            System.out.println("Card changer button pressed. going to: "+card);
	            if(card.equals("PROFASSIGNMENTPAGE")){
	            		//TODO update the file to show using pg.getProfAssignmentPanel().updateAssignmentFile(STRING);
	            }
	            ((ProfGUI) frame).setActiveCard(card);
	        }
	}

    //TODO in the listener that goes to a course page, use pg.getProfCoursePagePanel().setCourse(theCourse) before setActiveCard()
    //and before going to  viewStudents page

}
