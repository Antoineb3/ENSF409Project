
package FrontEnd.FrontController;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.NoSuchElementException;

import SharedObjects.*;


/**
 * 
 * @author Ross
 */
public class ClientSocketCommunicator {

	private Socket socket;
	private ObjectInputStream socketIn;
	private ObjectOutputStream socketOut;

	/**
	 * Initializes the socket and its I/O streams
	 */
	public ClientSocketCommunicator(String serverName, int portNumber) {
		try {
			socket = new Socket(serverName, portNumber);
			socketOut = new ObjectOutputStream(socket.getOutputStream());
			socketIn = new ObjectInputStream(socket.getInputStream());
		} catch (IOException e) {
			System.err.println(e.getStackTrace());
		}
	}


	//NOTE this returns ArrayList instead of Message as shown on our class diagram
	/**
	 * sends a message to the socket and returns its response
	 * @param m the message to send
	 * @return the response
	 */
	private ArrayList<?> communicate(Message m) {
		writeToSocket(m);
		return getResponse();
	}

	/**
	 * helper method to  write a serialized object to socket
	 */
	private void writeToSocket(Message m) {
		try {
			socketOut.writeObject(m);
		} catch (IOException ioException) {
			System.err.println("Error writing to file. - cant send message");
		} catch (NoSuchElementException elementException) {
			System.err.println("Invalid element to write. - cant send message");
		}
	}

	/**
	 * helper method to read a serialized Message object from socket
	 */
	private ArrayList<?> getResponse() {
		try {
			return (ArrayList<?>) socketIn.readObject();
		} catch (EOFException e) {
			System.err.println("Error, EOF");
		} catch (ClassNotFoundException e) {
			System.out.println("error: class not found");
		} catch (IOException e) {
			System.out.println("error reading from file");
		}
		System.out.println("error getting response from server.");
		return null;
	}
}
