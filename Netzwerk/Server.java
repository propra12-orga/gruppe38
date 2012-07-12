package gruppe38.Netzwerk;

import gruppe38.Main;

import java.net.*; 
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


public class Server extends Thread{

	/**
	 * @param args
	 */
	
	public static DatagramSocket socket;
	public static DatagramPacket packet = new DatagramPacket(new byte[1024], 1024);
	private static String[]	dummy;
	
	//Fuer Server
	
	public static 	boolean 	player1_check 	= false;
	public static	boolean		player2_check 	= false;
	
	private static 	InetAddress			address1;
	public	static	InetAddress			address2;
	
	public static	boolean		startserver			= false;
	public static 	int			port			= 4788;
	
	private			String		game_name		= "Bomberman - Gruppe38 - Netzwerk";
	private static 	String		password		= "github";
	byte[] data_byte;
	String data;
	
	
	public void run() {
		// TODO Auto-generated method stub
		try{
			startServer();
			
			while(Main.runserver == true){
				System.out.println("Serverthread läuft");
				socket.receive( packet );
				
				data_byte		= packet.getData();
				data			= new String(data_byte, 0, packet.getLength(), "UTF-8");
				System.out.println("Daten gelesen");
				System.out.println("Daten: " + data);
				if(data.contains("close")){
					close_programm();
					System.out.println("Befehl zum Schließen gesetzt!");
				}
				
				if(startserver == false){
					//Abfragen solang noch nicht beide Spieler angemeldet sind
					if(player1_check == false || player2_check == false){
						
						//Spieler1
						if(player1_check == false){
							if(data.contains(password)){
								//Speichere Addresse des ersten Spielers und melde ihn an
								setPlayer1(packet.getAddress());
								player1_check = true;
								System.out.println("Spieler1 angemeldet!");
								System.out.print("Seine IP lautet: ");
								System.out.println(packet.getAddress());
							}
						}
						//Spieler2
						else if(player2_check == false){
							if(data.contains(password)){
								//Speichere Addresse des zweiten Spielers und melde ihn an
								setPlayer2(packet.getAddress());
								player2_check = true;
								System.out.println("Spieler2 angemeldet!");
								System.out.print("Seine IP lautet: ");
								System.out.println(packet.getAddress());
								startserver = true;
							}
						}
					}else{
						//Starte Spiel
						//Main.startserver = true;
					}
					
				}
				if(startserver == true){
					//Wenn nun beide Spieler angemeldet sind und das Spiel gestartet ist
					//Frage ankommende Daten ab und fuehre die gewuenschten Aktionen aus
					System.out.println(packet.getAddress());
					if(data.contains("player_up_on")){
						
						if(address1.equals(packet.getAddress())){
							
							player1_up(true);
							System.out.println("Spieler 1 hoch");
							
						}else if(address2.equals(packet.getAddress())){
							
							player2_up(true);
							System.out.println("Spieler 2 hoch");
							
						}
					}
					else if(data.contains("player_down_on")){
						System.out.println("Befehl vom Client runter empfangen");
						if(address1.equals(packet.getAddress())){
							
							player1_down(true);
							System.out.println("Spieler 1 runter");
							
						}else if(address2.equals(packet.getAddress())){
							
							player2_down(true);
							System.out.println("Spieler 2 runter");
							
						}
					}
					else if(data.contains("player_left_on")){
						if(address1.equals(packet.getAddress())){
							
							player1_left(true);
							System.out.println("Spieler 1 links");
							
						}else if(address2.equals(packet.getAddress())){
							
							player2_left(true);
							System.out.println("Spieler 2 links");
							
						}
					}
					else if(data.contains("player_right_on")){
						if(address1.equals(packet.getAddress())){
							
							player1_right(true);
							System.out.println("Spieler 1 rechts");
							
						}else if(address2.equals(packet.getAddress())){
							
							player2_right(true);
							System.out.println("Spieler 2 rechts");
							
						}
					}
					 if(data.contains("player_bomb")){
						 System.out.println("Eingabe erkannt");
						if(address1.equals(packet.getAddress())){
							
							player1_bomb(true);
							System.out.println("Spieler 1 Bombe legen");
							
						}else if(address2.equals(packet.getAddress())){
							
							player2_bomb(true);
							System.out.println("Spieler 2 Bombe legen");
							
						}
					}
					 if(data.contains("player_up_off")){
							
							if(address1.equals(packet.getAddress())){
								
								player1_up(false);
								System.out.println("Spieler 1 hoch off");
								
							}else if(address2.equals(packet.getAddress())){
								
								player2_up(false);
								System.out.println("Spieler 2 hoch off");
								
							}
						}
						else if(data.contains("player_down_off")){
							if(address1.equals(packet.getAddress())){
								
								player1_down(false);
								System.out.println("Spieler 1 runter off");
								
							}else if(address2.equals(packet.getAddress())){
								
								player2_down(false);
								System.out.println("Spieler 2 runter off");
								
							}
						}
						else if(data.contains("player_left_off")){
							if(address1.equals(packet.getAddress())){
								
								player1_left(false);
								System.out.println("Spieler 1 links off");
								
							}else if(address2.equals(packet.getAddress())){
								
								player2_left(false);
								System.out.println("Spieler 2 links off");
								
							}
						}
						else if(data.contains("player_right_off")){
							if(address1.equals(packet.getAddress())){
								
								player1_right(false);
								System.out.println("Spieler 1 rechts off");
								
							}else if(address2.equals(packet.getAddress())){
								
								player2_right(false);
								System.out.println("Spieler 2 rechts off");
								
							}
						}
						 
					
				}
				
			}

		socket.close();
		System.out.println("Server-Thread terminiert");
		}catch(Exception e){
			
		}
		return;
	}


	private static void player2_bomb(boolean i) {
		// TODO Auto-generated method stub
		System.out.println("Spieler2 legt Bombe!");
		Main.setSpace2(i);
	}


	private static void player2_right(boolean i) {
		// TODO Auto-generated method stub
		Main.setRight2(i);
	}



	private static void player2_left(boolean i) {
		// TODO Auto-generated method stub
		Main.setLeft2(i);
	}



	private static void player2_down(boolean i) {
		// TODO Auto-generated method stub
		Main.setDown2(i);
	}


	private static void player2_up(boolean i) {
		// TODO Auto-generated method stub
		Main.setUp2(i);
	}


	private static void player1_bomb(boolean i) {
		// TODO Auto-generated method stub
		Main.setSpace(i);
		System.out.println("Spieler1 legt Bombe!");
	}


	private static void player1_right(boolean i) {
		// TODO Auto-generated method stub
		Main.setRight(i);
	}


	private static void player1_left(boolean i) {
		// TODO Auto-generated method stub
		Main.setLeft(i);
	}


	private static void player1_down(boolean i) {
		// TODO Auto-generated method stub
		Main.setDown(i);
	}


	private static void player1_up(boolean i) {
		// TODO Auto-generated method stub
		Main.setUp(i);
	}



	private static void close_programm() {
		// TODO Auto-generated method stub
		Main.runserver = false;
	}

	private static void setPlayer1(InetAddress address) {
		// TODO Auto-generated method stub
		address1	= address;
	}

	private static void setPlayer2(InetAddress address) {
		// TODO Auto-generated method stub
		address2	= address;
	}
	
	public static void startServer() throws SocketException{
		socket = new DatagramSocket(port);
	}

	public static int getPort(){
		return port;
	}

	
}
