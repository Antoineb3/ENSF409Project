/**
 * 
 */
package BackEnd.BackController;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;

import BackEnd.Model.ModelExecutor;
import SharedObjects.Message;

/**
 * This class enables communication between the client and the back end controller.
 * @author 	Antoine Bizon, Ross Bartlett 
 * @version 1.0
 * @since	2018-04-03
 */
public class ServerSocketCommunicator implements Runnable{
	/**
	 * The input stream used to read objects from the client.
	 */
	private ObjectInputStream socketIn;
	/**
	 * The output stream used to send objects to the client.
	 */
	private ObjectOutputStream socketOut;
	
	/**
	 * Constructs a new ServerSocketCommunicator by constructing the ObjectInputStream and ObjectOutputStream fields.
	 * @param aSocket the socket that connects this to the client.
	 */
	public ServerSocketCommunicator(Socket aSocket) {
		try {
			socketIn = new ObjectInputStream(aSocket.getInputStream());
			socketOut = new ObjectOutputStream(aSocket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Communicates with the client by waiting for a message to be sent and sending back the response.
	 */
	void communicate() {
		try {
			MessageDecoder decoder = createMessageDecoder();
			while(true) {
				Message messageIn = (Message) socketIn.readObject();
				ArrayList<? extends Serializable> returnMessage = decoder.decodeMessage(messageIn);
				socketOut.writeObject(returnMessage);
				socketOut.reset();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SocketException e) {
			System.out.println("Connection to client lost");
		} catch (IOException e) {
			e.printStackTrace();
		}catch (Exception e) {
			System.err.println(e.getClass().getName());
		}
		finally {
			try {
				socketOut.close();
				socketIn.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}

		
	}

	/**
	 * Constructs the message decoder used by the ServerSocketCommunicator object.
	 * @return
	 */
	private MessageDecoder createMessageDecoder() {
		ModelExecutor execute = new ModelExecutor();
		DBController theDBController = new DBController(execute);
		FileController theFileController = new FileController(execute);
		EmailController theEmailController = new EmailController(execute);
		return new MessageDecoder(theDBController, theFileController, theEmailController);
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		System.out.println("New worker thread starting");
		communicate();
		System.out.println("Worker thread terminating");
	}

}
