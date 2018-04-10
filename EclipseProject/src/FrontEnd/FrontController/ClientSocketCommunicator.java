
package FrontEnd.FrontController;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.ArrayList;
import java.util.NoSuchElementException;

import SharedObjects.*;


/**
 * 
 * @author 	Antoine Bizon & Ross Bartlett
 */
public class ClientSocketCommunicator {

	private Socket socket;
	private ObjectInputStream socketIn;
	private ObjectOutputStream socketOut;

	/**
	 * Initializes the socket and its I/O streams
	 */
	public ClientSocketCommunicator(String serverName, int portNumber) {
		System.out.println("Connecting to socket...");
		try {
			socket = new Socket(serverName, portNumber);
			socketOut = new ObjectOutputStream(socket.getOutputStream());
			socketIn = new ObjectInputStream(socket.getInputStream());
			System.out.println("Client connected to socket.");
		} catch (IOException e) {
			System.err.println("IO Exception connecting to socket: "+e.getStackTrace());
		}
	}


	//NOTE this returns ArrayList instead of Message as shown on our class diagram. and it now has packge visibility instead of private
	/**
	 * sends a message to the socket and returns its response
	 * @param m the message to send
	 * @return the response
	 */
	ArrayList<? extends Serializable> communicate(Message m) {
		//do i need a while loop here? or can the ActionListeners just call this method any time to send a message 
		writeToSocket(m);
		return getResponse();
	}

	/**
	 * helper method to  write a serialized object to socket
	 */
	private void writeToSocket(Message m) {
		try {
			socketOut.writeObject(m);
			socketOut.flush();
		} catch (IOException ioException) {
			System.err.println("Error writing to file. - cant send message");
		} catch (NoSuchElementException elementException) {
			System.err.println("Invalid element to write. - cant send message");
		}
	}

	/**
	 * helper method to read a serialized Message object from socket
	 */
	private ArrayList<? extends Serializable> getResponse() {
		try {
			return (ArrayList<? extends Serializable>) socketIn.readObject();
		} catch (EOFException e) {
			System.out.println("Error, EOF");
		} catch (ClassNotFoundException e) {
			System.out.println("error: class not found");
		} catch (IOException e) {
			System.out.println("error reading from file");
		}
		System.out.println("ERROR getting response from server... in ClientSocketCommunicator");
		return null;
	}
}
