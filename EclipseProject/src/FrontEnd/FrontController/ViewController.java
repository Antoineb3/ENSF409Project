package FrontEnd.FrontController;

import javax.swing.JFrame;

/**
 * Abstract controller that has a JFrame (GUI) and a communication line to the socket 
 * @author 	Ross Bartlett, Antoine Bizon
 */
public abstract class ViewController {
	private JFrame frame;
	private ClientSocketCommunicator communicator;
	
	public ViewController(JFrame f, ClientSocketCommunicator c) {
		this.frame = f;
		communicator = c;
	}


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


}
