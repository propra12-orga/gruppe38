package gruppe38.Netzwerk;

import java.io.IOException;

/**
 * Startet den Client
 * 
 * @author Gruppe38
 * 
 */
public class Client2 {

	/**
	 * @param args
	 */

	private static int port = 4788;
	private static String send_string = "github";

	public static void main(String[] args) throws IOException {
		Client cli2 = new Client(true);
		cli2.start();
	}

}
