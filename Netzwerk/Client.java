package gruppe38.Netzwerk;

import gruppe38.Main;

import java.io.IOException; 
import java.io.UnsupportedEncodingException;
import java.net.*; 
import java.util.*;


public class Client extends Thread{

	/**
	 * @param args
	 */
	private static boolean		run					= true;
	private static boolean		already_connected	= false;
	private static String[]		dummy;
	
	private static int			port			=	4788;
	private static int			ip1				=	0;
	private	static int			ip2				= 	0;
	private static int			ip3				= 	0;
	private static int			ip4				= 	0;
	private static String		server_ip		=	"";
	private static String		password		=	"github";
	
	private static	InetAddress			address;
	public static 	DatagramSocket 		socketcli;
	private static	DatagramPacket 		packetcli;
	private static	byte[]				send;
	
	private static boolean remote;
	
	/*
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		runClient(false);

	}
	*/
	public Client(boolean r){
		setRemote(r);
	}
	
	public static void setUp(boolean status) throws IOException{
		if(status == true){
			System.out.println("Client hat Einhabe hoch erkannt!");
			sendData("player_up_on", address);
		}else{
			sendData("player_up_off", address);
		}
	}
	
	public static void setDown(boolean status) throws IOException{
		if(status == true){
			sendData("player_down_on", address);
		}else{
			sendData("player_down_off", address);
		}
	}
	
	public static void setRight(boolean status) throws IOException{
		if(status == true){
			sendData("player_right_on", address);
		}else{
			sendData("player_right_off", address);
		}
	}
		
	public static void setLeft(boolean status) throws IOException{
		if(status == true){
			sendData("player_left_on", address);
		}else{
			sendData("player_left_off", address);
		}
	}
	
	public static void setSpace(boolean status) throws IOException{
		if(status == true){
			sendData("player_bomb", address);
		}
	}

	public static void connect(int ip_1, int ip_2, int ip_3, int ip_4) throws IOException {
		// TODO Auto-generated method stub
		//Prüfe ob schon mit einem Server verbunden wurde
			if(already_connected == false){
				System.out.println("Befehl zum verbinden gesetzt!");
				ip1 = ip_1;
				ip2 = ip_2;
				ip3 = ip_3;
				ip4 = ip_4;
				
				server_ip	= ip1 + "." + ip2 + "." + ip3 + "." + ip4;
				address		= InetAddress.getByAddress(server_ip, new byte[]{(byte) ip1, (byte) ip2, (byte) ip3,(byte) ip4});
				socketcli		= new DatagramSocket(port+2);
				
				sendData(password, address);
				already_connected = true;
			}
		
	}
	
	public static void sendData(String dataToSend, InetAddress addressToSend) throws IOException{
		
		send	= dataToSend.getBytes("UTF-8");
		packetcli	= new DatagramPacket(send, send.length, addressToSend, port);
		socketcli.send(packetcli);
		
	}
	
	public static void registerLocalPlayer() throws IOException{
		ip1 = 127;
		ip2 = 0;
		ip3 = 0;
		ip4 = 1;
		
		server_ip	= ip1 + "." + ip2 + "." + ip3 + "." + ip4;
		address		= InetAddress.getByAddress(server_ip, new byte[]{(byte) ip1, (byte) ip2, (byte) ip3,(byte) ip4});
		socketcli		= new DatagramSocket(port+2);
		
		sendData(password, address);
		already_connected = true;
		System.out.println("Der Lokale Spieler wurde als Spieler1 im Netzwerk angemeldet!");
		socketcli.close();
		//Client c = new Client();
		//Client.run(true);
	}
	
	public static void establishConnection() throws IOException{
		server_ip	= ip1 + "." + ip2 + "." + ip3 + "." + ip4;
		address		= InetAddress.getByAddress(server_ip, new byte[]{(byte) ip1, (byte) ip2, (byte) ip3,(byte) ip4});
		socketcli		= new DatagramSocket(port+2);
	}
	
	public void run(){
		try{
			if(Client.remote == true){
				while(run == true){
					//System.out.println("Client-Thread läuft");
					StdDraw.onscreen.drawImage(StdDraw.offscreenImage, 0, 0, null);;
				}
				
			}
			server_ip	= ip1 + "." + ip2 + "." + ip3 + "." + ip4;
			try {
				address		= InetAddress.getByAddress(server_ip, new byte[]{(byte) ip1, (byte) ip2, (byte) ip3,(byte) ip4});
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				socketcli		= new DatagramSocket(port+2);
			} catch (SocketException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			socketcli.close();
		}catch(NullPointerException e){
			
		}
		return;
	}
	
	public static void setRemote(boolean i){
		
		remote = i;
		
	}
}
