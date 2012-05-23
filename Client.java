import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client extends Thread {

	public void run() {
		Socket zumServer;

		while (true) {
			try {

				// ///////////

				zumServer = new Socket("localhost", 100);

				Drawing draw = new Drawing();
				FileOutputStream fileOut2 = (FileOutputStream) zumServer
						.getOutputStream();
				ObjectOutputStream out2 = new ObjectOutputStream(fileOut2);

				Main main = new Main();
				FileOutputStream fileOut = (FileOutputStream) zumServer
						.getOutputStream();
				ObjectOutputStream out = new ObjectOutputStream(fileOut);

				out2.writeObject(draw);
				out.writeObject(main);

				out2.close();
				out.close();
				fileOut.close();
				fileOut2.close();

			} catch (IOException i) {
				i.printStackTrace();
			}
		}
	}
	public static void main(String[] args) {

		Client client = new Client();
		client.run();
	}

}
