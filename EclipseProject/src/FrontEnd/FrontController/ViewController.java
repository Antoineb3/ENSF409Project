package FrontEnd.FrontController;

import javax.swing.JFrame;

/**
 * 
 * @author 	Antoine Bizon & Ross Bartlett
 *
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
