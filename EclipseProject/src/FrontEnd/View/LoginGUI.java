package FrontEnd.View;

import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;

/*
 * ENSF 409 - Project
 * March 24 2018
 */

/**
 * Creates the Login GUI 
 */
public class LoginGUI extends JFrame{
	/**
	 * The login button
	 */
    private JButton loginButton = new JButton("Login");
    /** 
	 * text fields
	 */
	private JTextField usernameField = new JTextField(20);		
	private JTextField passwordField = new JTextField(20);

	/**
	 * Constructor that creates the visible Frame 
	 */
	public LoginGUI() {
		setTitle("B&B Learning");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));
		setPreferredSize(new Dimension(400,190));

        fillContentPane();

        pack();
        setLocationRelativeTo(null); // center the JFrame
		setResizable(false);
		setVisible(true);
	}


	/**
	 * Helper method to add components to the JFrame
	 */
	private void fillContentPane() {
        usernameField.setMaximumSize( usernameField.getPreferredSize() );
        passwordField.setMaximumSize( passwordField.getPreferredSize() );
        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        add(Box.createRigidArea(new Dimension(0,10))); //empty spacing 
        add(GuiUtilities.centeredJLabel("Welcome! Please enter your login information."));
        add(Box.createRigidArea(new Dimension(0,10))); //empty spacing 
        add(GuiUtilities.centeredJLabel("Username:"));
        add(usernameField);
        add(GuiUtilities.centeredJLabel("Password:"));
        add(passwordField);
        add(Box.createRigidArea(new Dimension(0,10))); //empty spacing 
        add(loginButton);
    }
    

	/**
	 * Helper functions for the CONTROLLER to initialize the listener
	 */
	public void setLoginButtonListener(ActionListener e) {
        loginButton.addActionListener(e);

        //within the action listener:
        //TODO use getUsernameInput() and getPasswordInput()
        //TODO if entered incorrect credentials, show JOPtionPane, clear fields
	}
	
	/**
	 * helper method to clear the JTextFields
	 */
	public void clearLoginFields(){
		usernameField.setText("");	
		passwordField.setText("");	
    }
    
    public String getUsernameInput(){
        return usernameField.getText();
    }
    public String getPasswordInput(){
        return passwordField.getText();
    }



    //Main for testing
    public static void main(String[] args) {
        LoginGUI gui = new LoginGUI();
    }

}
