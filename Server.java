import java.net.*;
import java.io.*;

public class Server {

	public static void main(String[] args) {

		if (args.length != 1) {
			System.out.println("Must specify port number an argument");
			System.exit(0);
		}

		int port = Integer.parseInt(args[0]);

		try (ServerSocket serverSocket = new ServerSocket(port)) {

			ChatSystem chatSystem = new ChatSystem();
			while (true) {
				Socket socket = serverSocket.accept();
				new Thread(new ServerThread(chatSystem, socket)).start();
			}
		} catch (IOException e) {
			System.out.println("Could not listen on port");
		}
	}
}
