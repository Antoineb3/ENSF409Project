package FrontEnd.View;

import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;



/**
 * Creates the Login GUI 
 * @author 	Ross Bartlett, Antoine Bizon
 */
public class LoginGUI extends JFrame{
	/**
	 * The login button
	 */
    private JButton loginButton = new JButton("Login");
    /** 
	 * text fields
	 */
	private JTextField userIDField = new JTextField(20);		
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
		userIDField.setMaximumSize( userIDField.getPreferredSize() );
        passwordField.setMaximumSize( passwordField.getPreferredSize() );
        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        add(Box.createRigidArea(new Dimension(0,10))); //empty spacing 
        add(GuiUtilities.centeredJLabel("Welcome! Please enter your login information."));
        add(Box.createRigidArea(new Dimension(0,10))); //empty spacing 
        add(GuiUtilities.centeredJLabel("User ID:"));
        add(userIDField);
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
	}
	
	/**
	 * helper method to clear the JTextFields
	 */
	public void clearLoginFields(){
		userIDField.setText("");	
		passwordField.setText("");	
    }
    
	/**
	 * @return the text input in the userID field
	 */
    public String getUsernameInput(){
        return userIDField.getText();
    }
    /**
	 * @return the text input in the password field
	 */
    public String getPasswordInput(){
        return passwordField.getText();
    }


}
