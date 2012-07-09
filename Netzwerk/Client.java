package gruppe38.Netzwerk;

import java.io.IOException; 
import java.net.*; 
import java.util.*;


public class Client {

	/**
	 * @param args
	 */
	private static boolean		run	= true;
	
	private static int			port			=	4788;
	private static String		send_string		=	"player_bomb";
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		InetAddress 	address		= InetAddress.getByAddress("127.0.0.1", new byte[]{127, 0, 0,1});
		DatagramSocket 	socket		= new DatagramSocket(port+2);
		byte[] 			send		= send_string.getBytes("UTF-8");
		
		//Senden
		DatagramPacket packet	= new DatagramPacket(send, send.length, address, port);
		socket.send(packet);
		while(run == true){
			StdDraw.onscreen.drawImage(StdDraw.offscreenImage, 0, 0, null);;
		}
		
		socket.close();
	}
	
	public static void setUp(boolean status){
		
	}
	
	public static void setDown(boolean status){
		
	}
	
	public static void setRight(boolean status){
		
	}
	
	public static void setLeft(boolean status){
		
	}
	
	public static void setSpace(boolean status){
		
	}

	public static void connect(boolean b) {
		// TODO Auto-generated method stub
		System.out.println("Befehl zum verbinden gesetzt!");
		System.out.println();
	}
}
