package FrontEnd.FrontController;

import java.util.ArrayList;
import javax.swing.JFrame;

/**
 * 
 * @author 	Antoine Bizon & Ross Bartlett
 *
 */
public abstract class ViewController {
//	protected ArrayList<?> responseItems; //TODO dont need this?
	protected JFrame frame;
	protected ClientSocketCommunicator communicator;
	
	public ViewController(JFrame f, ClientSocketCommunicator c) {
		this.frame = f;
		communicator = c;
	}
//
//	/**
//	 * @return the responseItems
//	 */
//	public ArrayList<?> getResponseItems() {
//		return responseItems;
//	}
//
//	/**
//	 * @param responseItems the responseItems to set
//	 */
//	public void setResponseItems(ArrayList<?> responseItems) {
//		this.responseItems = responseItems;
//	}

	/**
	 * @return the frame
	 */
	public JFrame getFrame() {
		return frame;
	}

	/**
	 * @return the communicator
	 */
	public ClientSocketCommunicator getCommunicator() {
		return communicator;
	}

	/**
	 * @param csc the csc to set
	 */
	public void setCommunicator(ClientSocketCommunicator csc) {
		this.communicator = csc;
	}
	
	
	
	
	
	
	

}
