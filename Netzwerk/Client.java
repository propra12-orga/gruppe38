package gruppe38.Netzwerk;

import java.io.IOException; 
import java.io.UnsupportedEncodingException;
import java.net.*; 
import java.util.*;


public class Client {

	/**
	 * @param args
	 */
	private static boolean		run					= true;
	private static boolean		already_connected	= false;
	
	private static int			port			=	4788;
	private static int			ip1				=	0;
	private	static int			ip2				= 	0;
	private static int			ip3				= 	0;
	private static int			ip4				= 	0;
	private static String		server_ip		=	"";
	private static String		password		=	"github";
	
	private static	InetAddress			address;
	private	static 	DatagramSocket 		socket;
	private static	DatagramPacket 		packet;
	private static	byte[]				send;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		
		while(run == true){
			StdDraw.onscreen.drawImage(StdDraw.offscreenImage, 0, 0, null);;
		}
		
		socket.close();
	}
	
	public static void setUp(boolean status) throws IOException{
		if(status == true){
			System.out.println("Client hat Einhabe hoch erkannt!");
			sendData("player_up", address);
		}
	}
	
	public static void setDown(boolean status) throws IOException{
		if(status == true){
			sendData("player_down", address);
		}
	}
	
	public static void setRight(boolean status) throws IOException{
		if(status == true){
			sendData("player_right", address);
		}
	}
		
	public static void setLeft(boolean status) throws IOException{
		if(status == true){
			sendData("player_left", address);
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
				System.out.println("Server-IP: " + server_ip);
				address		= InetAddress.getByAddress(server_ip, new byte[]{(byte) ip1, (byte) ip2, (byte) ip3,(byte) ip4});
				socket		= new DatagramSocket(port+2);
				
				sendData(password, address);
				already_connected = true;
			}
		
	}
	
	public static void sendData(String dataToSend, InetAddress addressToSend) throws IOException{
		
		send	= dataToSend.getBytes("UTF-8");
		packet	= new DatagramPacket(send, send.length, addressToSend, port);
		socket.send(packet);
		
	}
}
