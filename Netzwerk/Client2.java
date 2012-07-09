package gruppe38.Netzwerk;

import java.io.IOException; 
import java.net.*; 
import java.util.*;

public class Client2 {

	/**
	 * @param args
	 */
	
	private static int			port			=	4788;
	private static String		send_string		=	"github";
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		InetAddress 	address		= InetAddress.getByAddress("127.0.0.1", new byte[]{127,0,0,1});
		DatagramSocket 	socket		= new DatagramSocket(port+2);
		byte[] 			send		= send_string.getBytes("UTF-8");
		
		//Senden
		DatagramPacket packet	= new DatagramPacket(send, send.length, address, port);
		socket.send(packet);
		
		socket.close();
	}

}
