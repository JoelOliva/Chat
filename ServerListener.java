import java.net.*;
import java.io.*;

public class ServerListener implements Runnable {

	private ServerSocket serverSocket;

	public ServerListener(ServerSocket serverSocket) {
		this.serverSocket = serverSocket;
	}

	public void run() {
		try {
			while (true) {
				Socket socket = serverSocket.accept();
				new Thread(new ServerThread(socket)).start();
			}
		} catch (IOException e) {
			System.out.println("Could not connect to client");
		}
	}
}
