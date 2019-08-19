import java.io.*;

public class ClientResponses implements Runnable {

	private BufferedReader serverIn;

	public ClientResponses(BufferedReader serverIn) {
		this.serverIn = serverIn;
	}

	public void run() {
		String response;

		try {
			while ((response = serverIn.readLine()) != null) {
				System.out.println(response);

				if (response.equals("Bye"))
					return;
			}

		} catch (IOException e) {
			System.out.println("Error occured while reading server response");
		}
	}
}
