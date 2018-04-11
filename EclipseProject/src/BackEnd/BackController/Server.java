/**
 * 
 */
package BackEnd.BackController;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * This class is for connecting clients to a worker thread.
 * @author 	Antoine Bizon & Ross Bartlett
 * @version 1.0
 * @since	2018-04-03
 */
public class Server {
	/**
	 * The server socket.
	 */
	private ServerSocket serverSocket;
	/**
	 * The ExecutorServise used to start new worker threads.
	 */
	private ExecutorService pool;
	
	/**
	 * Constructs a Server object.
	 * @param portNumber
	 */
	public Server(int portNumber) {
		try {
			serverSocket = new ServerSocket(portNumber);
			pool=Executors.newCachedThreadPool();
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
	/**
	 * Waits for a client to connect and starts a new worker thread.
	 */
	public void makeConnection() {
		try 
		{
			while(true)
			{
				ServerSocketCommunicator worker = new ServerSocketCommunicator(serverSocket.accept());
				
				pool.execute(worker);
			}
		}catch(Exception e) {
			e.printStackTrace();
			pool.shutdown();
		}
		
	}
	
	public static void main(String[] args) {
		Server theServer = new Server(7800);
		System.out.println("the server is running...");
		theServer.makeConnection();
	}

}
