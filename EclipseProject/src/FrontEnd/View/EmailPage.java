/**
 * 
 */
package FrontEnd.View;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * @author Ross
 *
 */
public class EmailPage extends JPanel{
	/**
	 * button to return to homepage
	 */
	private JButton homepageButton = new JButton("Back to Homepage");
    /**
	 * The back button
	 */
    private JButton backButton = new JButton("Back");
    /**
	 * The send button
	 */
    private JButton sendButton = new JButton("Send");
    /**
     * The subjext field
     */
    private JTextField subjectField = new JTextField(30);
    /**
     * The text are to type the email message
     */
    private JTextArea textArea = new JTextArea(20,30);
	JScrollPane scroller = new JScrollPane(textArea);
    
	private JButton clearFieldsButton = new JButton("Clear Fields");
    
    /**
	 * Constructor that creates the visible panel 
	 */
	public EmailPage() {
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		setPreferredSize(new Dimension(800,600));
        fillContentPane();
        subjectField.setEditable(true);
        subjectField.setMaximumSize(subjectField.getPreferredSize());
        textArea.setEditable(true);
        scroller.setMaximumSize(new Dimension(400,300));
        setClearFieldsButtonListener();
	}

	/**
	 * 
	 */
	private void fillContentPane() {
		setButtonPanel();
		add(GuiUtilities.centeredJLabel("Subject:"));
		add(subjectField);
		add(GuiUtilities.centeredJLabel("Text:"));
		add(scroller);		
		
		clearFieldsButton.setAlignmentX(CENTER_ALIGNMENT);
		add(clearFieldsButton);
		
		sendButton.setAlignmentX(CENTER_ALIGNMENT);
		add(sendButton);
		
	}
	
	/**
     * make a panel of the top buttons on the frame
     */
    private void setButtonPanel(){
        JPanel buttonPanel = new JPanel();
        buttonPanel.setMaximumSize(new Dimension(800,40));
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(homepageButton);
        buttonPanel.add(backButton);
        add(buttonPanel);
        add(GuiUtilities.horizontalLine());

    }
	
    public void setHomepageButtonListener(ActionListener e) {
        homepageButton.addActionListener(e);
    }
	public void setBackButtonListener(ActionListener e) {
        backButton.addActionListener(e);
    }
	public void setSendButtonListener(ActionListener e) {
        sendButton.addActionListener(e);
    }
	
	/**
	 * Anonymous listener to clear the input fields
	 */
	private void setClearFieldsButtonListener() {
		clearFieldsButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				clearFields();
			}
		});
	}
	
	/**
	 * Clears the input fields. also used by ProfController 
	 */
	public void clearFields() {
		subjectField.setText("");
		textArea.setText("");
	}

	/**
	 * @return the subjectField
	 */
	public JTextField getSubjectField() {
		return subjectField;
	}

	/**
	 * @return the textArea
	 */
	public JTextArea getTextArea() {
		return textArea;
	}
	
	
	
	

	
}
