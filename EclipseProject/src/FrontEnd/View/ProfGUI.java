package FrontEnd.View;

import java.awt.*;
import javax.swing.*;

import SharedObjects.Professor;

/*
 * Ross Bartlett
 * ENSF 409 - project
 * March 24 2018
 */

/**
 * Creates the prof GUI 
 * @author 	Antoine Bizon & Ross Bartlett
 */
public class ProfGUI extends JFrame{
	/**
	 * The prof that logged in 
	 */
	private Professor prof;

	/**
	 * The JPanel on the JFrame that can switch between different pages/cards
	 */
	private JPanel cards; // a panel that uses CardLayout

	/**
	 * The different pages/panels that the JFrame will display
	 */
	private ProfHomepage profHomePagePanel;
	private ProfCoursePage profCoursePagePanel;
	private ViewStudentsPage viewStudentsPanel;
	private ProfAssignmentPage profAssignmentPanel;

	public ProfGUI(Professor p) {
		prof=p;
		setTitle("B&B Learning");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		cards = new JPanel(new CardLayout());

		profHomePagePanel = new ProfHomepage(); 
		profHomePagePanel.setProf(prof);
		cards.add(profHomePagePanel, "PROFHOMEPAGE"); //the cardName of the homePagePanel is PROFHOMEPAGE

		profCoursePagePanel = new ProfCoursePage();
		cards.add(profCoursePagePanel, "PROFCOURSEPAGE"); //the cardName of the homePagePanel is PROFCOURSEPAGE

		viewStudentsPanel = new ViewStudentsPage();
		cards.add(viewStudentsPanel, "VIEWSTUDENTSPAGE"); //the cardName is VIEWSTUDENTSPAGE

		profAssignmentPanel = new ProfAssignmentPage();
		cards.add(profAssignmentPanel, "PROFASSIGNMENTPAGE"); //the cardName is PROFASSIGNMENTPAGE

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
		//note: if cardname is passed in that doesnt exist, nothing happens
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
	/**
	 * @return the viewStudentsPanel
	 */
	public ViewStudentsPage getViewStudentsPanel() {
		return viewStudentsPanel;
	}
	/**
	 * @return the profAssignmentPanel
	 */
	public ProfAssignmentPage getProfAssignmentPanel() {
		return profAssignmentPanel;
	}


	/**
	 * @return the prof
	 */
	public Professor getProf() {
		return prof;
	}



}
