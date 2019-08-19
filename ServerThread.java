import java.net.*;
import java.io.*;

public class ServerThread implements Runnable {

	private Socket socket;
	private ChatSystem chatSystem;

	public ServerThread(ChatSystem chatSystem, Socket socket) {
		this.socket = socket;
		this.chatSystem = chatSystem;
	}

	public void run() {

		try (
			PrintWriter clientOut = new PrintWriter(socket.getOutputStream(), true);
			BufferedReader clientIn = new BufferedReader(
				new InputStreamReader(socket.getInputStream()));
		) {
			String userName = clientIn.readLine();
			chatSystem.addClientStream(clientOut);
			chatSystem.sendAll(userName + " has entered the chat");

			String clientMessage;
			while ((clientMessage = clientIn.readLine()) != null) {
				if (clientMessage.equals("!exit")) {
					chatSystem.sendAll(userName + " has left the chat");
					break;
				}

				chatSystem.sendAll(userName, clientMessage);
			}

			clientOut.println("!exit");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
