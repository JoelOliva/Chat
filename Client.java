import java.net.*;
import java.io.*;

public class Client {

	public static void main(String[] args) {

        if (args.length != 3) {
            System.err.println(
                "Usage: java EchoClient <username> <host name> <port number>");
            System.exit(1);
        }

		String userName = args[0];
        String hostName = args[1];
        int port = Integer.parseInt(args[2]);

        try (
			Socket socket = new Socket(hostName, port);
			BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
			PrintWriter serverOut = new PrintWriter(socket.getOutputStream(), true);
			BufferedReader serverIn = new BufferedReader(
				new InputStreamReader(socket.getInputStream()));
		) {
			Thread serverResponses = new Thread(new ServerResponses(serverIn));
			serverResponses.start();

			serverOut.println(userName);

			String message;
			while ((message = userInput.readLine()) != null) {
				serverOut.println(message);

				if (message.equals("!exit"))
					break;
			}

			System.out.println("Exiting chat...");
			serverResponses.join();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
