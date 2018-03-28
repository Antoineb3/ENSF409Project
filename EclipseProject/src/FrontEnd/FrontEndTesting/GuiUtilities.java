package FrontEnd.FrontEndTesting;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

/**
 * Contains helper methods used my many of the JPanel classes to improve code-reuse
 * class and methods are package-scope
 */
abstract class GuiUtilities {
	
	/**
	 * Creates a horizontal JSperator 
	 * @return the JSeperator
	 */
	static JSeparator horizontalLine() {
		JSeparator seperator = new JSeparator(SwingConstants.HORIZONTAL);
        seperator.setMaximumSize( new Dimension(Integer.MAX_VALUE, 1) );
        return seperator;
	}
	
	/**
     * Helper method to create a new centered JLabel 
     * @param s the text of the JLabel
     * @return the JLabel
     */
    static JLabel centeredJLabel(String s){
        JLabel label = new JLabel(s);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        return label;
    }


}
