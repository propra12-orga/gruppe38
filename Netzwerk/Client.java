package gruppe38.Netzwerk;

import gruppe38.Main;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 * Der Client
 * 
 * @author tom
 * 
 */

public class Client extends Thread {

	/**
	 * @param args
	 */
	private static boolean run = true;
	private static boolean already_connected = false;
	private static String[] dummy;

	private static int port = 4788;
	private static int ip1 = 0;
	private static int ip2 = 0;
	private static int ip3 = 0;
	private static int ip4 = 0;
	private static String server_ip = "";
	private static String password = "github";

	// private static DatagramPacket packet_receive;
	private static byte[] packet_data;
	private static Stamp packet_stamp;
	final int buffer_size = 1024 * 4;
	byte[] buffer = new byte[buffer_size];

	private static boolean remote;

	/*
	 * public static void main(String[] args) throws IOException { // TODO
	 * Auto-generated method stub
	 * 
	 * runClient(false);
	 * 
	 * }
	 */
	public Client(boolean r) {
		setRemote(r);
	}

	public static void setUp(boolean status) throws IOException {
		if (status == true) {
			System.out.println("Client hat Einhabe hoch erkannt!");
			sendData("player_up_on", Main.address);
		} else {
			sendData("player_up_off", Main.address);
		}
	}

	public static void setDown(boolean status) throws IOException {
		if (status == true) {
			sendData("player_down_on", Main.address);
		} else {
			sendData("player_down_off", Main.address);
		}
	}

	public static void setRight(boolean status) throws IOException {
		if (status == true) {
			sendData("player_right_on", Main.address);
		} else {
			sendData("player_right_off", Main.address);
		}
	}

	public static void setLeft(boolean status) throws IOException {
		if (status == true) {
			sendData("player_left_on", Main.address);
		} else {
			sendData("player_left_off", Main.address);
		}
	}

	public static void setSpace(boolean status) throws IOException {
		if (status == true) {
			sendData("player_bomb", Main.address);
		}
	}

	/**
	 * Verbindet den Client mit dem Server
	 * 
	 * @throws IOException
	 */

	public static void connect() throws IOException {
		// TODO Auto-generated method stub
		// Prüfe ob schon mit einem Server verbunden wurde
		if (already_connected == false) {
			System.out.println("Befehl zum verbinden gesetzt!");
			/*
			 * ip1 = ip_1; ip2 = ip_2; ip3 = ip_3; ip4 = ip_4;
			 * 
			 * server_ip = ip1 + "." + ip2 + "." + ip3 + "." + ip4; Main.address
			 * = InetAddress.getByAddress(server_ip, new byte[]{(byte) ip1,
			 * (byte) ip2, (byte) ip3,(byte) ip4}); Main.socketcli = new
			 * DatagramSocket(port+2);
			 */
			sendData(password, Main.address);
			already_connected = true;
		}

	}

	/**
	 * Sendet die Daten
	 * 
	 * @param dataToSend
	 * @param addressToSend
	 * @throws IOException
	 */

	public static void sendData(String dataToSend, InetAddress addressToSend)
			throws IOException {

		Main.sendcli = dataToSend.getBytes("UTF-8");
		Main.packetcli = new DatagramPacket(Main.sendcli, Main.sendcli.length,
				addressToSend, port);
		Main.socketcli.send(Main.packetcli);

	}

	/**
	 * Registriert den Spieler
	 * 
	 * @throws IOException
	 */
	public static void registerLocalPlayer() throws IOException {
		ip1 = 127;
		ip2 = 0;
		ip3 = 0;
		ip4 = 1;

		server_ip = ip1 + "." + ip2 + "." + ip3 + "." + ip4;
		Main.address = InetAddress.getByAddress(server_ip, new byte[] {
				(byte) ip1, (byte) ip2, (byte) ip3, (byte) ip4 });
		Main.socketcli = new DatagramSocket(port + 2);

		sendData(password, Main.address);
		already_connected = true;
		System.out
				.println("Der Lokale Spieler wurde als Spieler1 im Netzwerk angemeldet!");
		Main.socketcli.close();
		// Client c = new Client();
		// Client.run(true);
	}

	public static void establishConnection() throws IOException {
		ip1 = Main.getIP()[1];
		ip2 = Main.getIP()[2];
		ip3 = Main.getIP()[3];
		ip4 = Main.getIP()[4];
		server_ip = ip1 + "." + ip2 + "." + ip3 + "." + ip4;
		Main.address = InetAddress.getByAddress(server_ip, new byte[] {
				(byte) ip1, (byte) ip2, (byte) ip3, (byte) ip4 });
		Main.socketcli = new DatagramSocket(Server.port + 2);
	}

	/**
	 * Starten des Clients
	 */
	public void run() {
		try {
			System.out.println("Thread zum Empfangen der Packete gestartet.");
			if (this.remote == true) {
				while (run == true) {
					DatagramPacket packet_receive = new DatagramPacket(buffer,
							buffer.length);
					try {

						System.out.println("Versuche Packet zu lesen");
						Main.socketcli.receive(packet_receive);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println("Packet Empfangen..theoretisch");
					packet_data = packet_receive.getData();
					packet_stamp = (Stamp) Stamp.getFromByteArray(packet_data);
					Stamp.load(packet_stamp);
				}

			}
			if (Main.netzwerk_localPlayer == true) {
				server_ip = ip1 + "." + ip2 + "." + ip3 + "." + ip4;
				try {
					Main.address = InetAddress.getByAddress(server_ip,
							new byte[] { (byte) ip1, (byte) ip2, (byte) ip3,
									(byte) ip4 });
				} catch (UnknownHostException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					Main.socketcli = new DatagramSocket(port + 2);
				} catch (SocketException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				Main.socketcli.close();
			}
		} catch (NullPointerException e) {

		}
		return;
	}

	public static void setRemote(boolean i) {

		remote = i;

	}
}
