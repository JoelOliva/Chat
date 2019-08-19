import java.io.*;

public class ServerResponses implements Runnable {

	private BufferedReader serverIn;

	public ServerResponses(BufferedReader serverIn) {
		this.serverIn = serverIn;
	}

	public void run() {
		String response;

		try {
			while ((response = serverIn.readLine()) != null) {
				if (response.equals("!exit"))
					return;

				System.out.println(response);
			}

		} catch (IOException e) {
			System.out.println("Error occured while reading server response");
		}
	}
}
