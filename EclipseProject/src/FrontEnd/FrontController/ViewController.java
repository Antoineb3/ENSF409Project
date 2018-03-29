package FrontEnd.FrontController;

import java.util.ArrayList;
import javax.swing.JFrame;


public abstract class ViewController {
	protected ArrayList<?> responseItems;
	protected JFrame frame;
//	private ClientSocketCommunicator csc;
	
	public ViewController(JFrame f) {
		this.frame = f;
	}
	
	
	
	
	

}
