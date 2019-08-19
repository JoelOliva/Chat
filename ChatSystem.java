import java.net.*;
import java.io.*;
import java.util.ArrayList;

public class ChatSystem {

	private ArrayList<PrintWriter> clientStreams;

	public ChatSystem() {
		clientStreams = new ArrayList<>();
	}

	public void addClientStream(PrintWriter clientStream) {
		clientStreams.add(clientStream);
	}

	public void sendAll(String message) {
		for (PrintWriter clientStream : clientStreams) {
			clientStream.println("\n### " + message + " ###\n");
		}
	}

	public void sendAll(String userName, String message) {
		for (PrintWriter clientStream : clientStreams) {
			clientStream.println(userName + ": " + message);
		}
	}
}
