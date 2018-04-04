/**
 * 
 */
package BackEnd.BackController;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Antoine
 *
 */
public class Server {
	private ServerSocket serverSocket;
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
	 * 
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
		System.out.println("the srever is running...");
		theServer.makeConnection();
	}

}
