package FrontEnd.FrontController;

import java.util.ArrayList;
import javax.swing.JFrame;


public abstract class ViewController {
	protected ArrayList<?> responseItems;
	protected JFrame frame;
	private ClientSocketCommunicator communicator;
	
	public ViewController(JFrame f) {
		this.frame = f;
//		csc = new ClientSocketCommunicator(serverName, portNumber)
	}

	/**
	 * @return the responseItems
	 */
	public ArrayList<?> getResponseItems() {
		return responseItems;
	}

	/**
	 * @param responseItems the responseItems to set
	 */
	public void setResponseItems(ArrayList<?> responseItems) {
		this.responseItems = responseItems;
	}

	/**
	 * @return the frame
	 */
	public JFrame getFrame() {
		return frame;
	}

	/**
	 * @param frame the frame to set
	 */
	public void setFrame(JFrame frame) {
		this.frame = frame;
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
