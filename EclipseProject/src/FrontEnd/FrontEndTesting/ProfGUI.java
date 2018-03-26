package FrontEnd.FrontEndTesting;

import java.awt.*;
import javax.swing.*;

/*
 * Ross Bartlett
 * ENSF 409 - project
 * March 24 2018
 */

/**
 * Creates the prof GUI 
 */
public class ProfGUI extends JFrame{
    /**
     * The prof that logged in 
     */
    private String prof; //TODO make this class Professor

    /**
     * The JPanel on the JFrame that can switch between different pages/cards
     */
    private JPanel cards; // a panel that uses CardLayout

    /**
     * The different pages/panels that the JFrame will display
     */
    private ProfHomepage profHomePagePanel;
    private ProfCoursePage profCoursePagePanel;

    public ProfGUI() {
        
		setTitle("B&B Learning");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        cards = new JPanel(new CardLayout());

        profHomePagePanel = new ProfHomepage("testProfName"); //TODO use prof.getName()
        cards.add(profHomePagePanel, "PROFHOMEPAGE"); //the cardName of the homePagePanel is PROFHOMEPAGE

        profCoursePagePanel = new ProfCoursePage();// TODO set the CoursePage's course every time you go to it 
        cards.add(profCoursePagePanel, "PROFCOURSEPAGE"); //the cardName of the homePagePanel is PROFCOURSEPAGE


        add(cards);
		pack();
        setResizable(false);
        setLocationRelativeTo(null); // center the JFram
		setVisible(true);
    }

    /**
     * Changes the card/page shown on the JFrame
     */
    public void setActiveCard(String CARDNAME){
        CardLayout cl = (CardLayout)(cards.getLayout());
        cl.show(cards, CARDNAME);
        //TODO will need to update a card's list items when we switch to it?
    }

    /**
     * @return the profHomePagePanel
     */
    public ProfHomepage getProfHomePagePanel() {
        return profHomePagePanel;
    }

    /**
     * @return the profCoursePagePanel
     */
    public ProfCoursePage getProfCoursePagePanel() {
        return profCoursePagePanel;
    }



    public static void main(String[] args) {
        ProfGUI gui = new ProfGUI();
    }
    
}
