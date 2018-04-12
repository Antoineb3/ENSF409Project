package FrontEnd.View;

import java.awt.*;
import javax.swing.*;

import SharedObjects.Student;


/**
 * Creates the student GUI 
 * @author 	Ross Bartlett, Antoine Bizon
 */
public class StudentGUI extends JFrame{
	/**
	 * The student that logged in 
	 */
	private Student student;

	/**
	 * The JPanel on the JFrame that can switch between different pages/cards
	 */
	private JPanel cards; // a panel that uses CardLayout

	/**
	 * The different pages/panels that the JFrame will display
	 */
	private StudentHomepage studentHomePagePanel;
	private StudentCoursePage studentCoursePagePanel;
	private StudentAssignmentPage studentAssignmentPanel;
	private EmailPage emailPage;
	private GradePage gradePage;

	/**
	 * Contructor that creates each page and adds it to the list of cards 
	 * @param s the student 
	 */
	public StudentGUI(Student s) {
		student=s;
		setTitle("B&B Learning");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		cards = new JPanel(new CardLayout());

		studentHomePagePanel = new StudentHomepage(); 
		studentHomePagePanel.setStudent(student);
		cards.add(studentHomePagePanel, "STUDHOMEPAGE"); //the cardName of the homePagePanel is STUDHOMEPAGE

		studentCoursePagePanel = new StudentCoursePage();// TODO set the CoursePage's course every time you go to it 
		cards.add(studentCoursePagePanel, "STUDCOURSEPAGE"); //the cardName of the homePagePanel is STUDCOURSEPAGE

		studentAssignmentPanel = new StudentAssignmentPage();// TODO set the assignment page's file every time you go to it 
		cards.add(studentAssignmentPanel, "STUDASSIGNMENTPAGE"); //the cardName is STUDASSIGNMENTPAGE

		emailPage = new EmailPage();
		cards.add(emailPage, "EMAILPAGE"); //the cardName is EMAILPAGE
		
		gradePage = new GradePage();
		cards.add(gradePage, "GRADEPAGE"); //the cardName is GRADEPAGE
		
		
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
	 * @return the gradePage
	 */
	public GradePage getGradePage() {
		return gradePage;
	}

	/**
	 * @return the studentHomePagePanel
	 */
	public StudentHomepage getStudentHomePagePanel() {
		return studentHomePagePanel;
	}

	/**
	 * @return the studentCoursePagePanel
	 */
	public StudentCoursePage getStudentCoursePagePanel() {
		return studentCoursePagePanel;
	}
	/**
	 * @return the studentAssignmentPanel
	 */
	public StudentAssignmentPage getStudentAssignmentPanel() {
		return studentAssignmentPanel;
	}


	/**
	 * @return the emailPage
	 */
	public EmailPage getEmailPage() {
		return emailPage;
	}

	/**
	 * @return the student
	 */
	public Student getStudent() {
		return student;
	}



}
