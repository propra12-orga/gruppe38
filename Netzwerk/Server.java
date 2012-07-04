import java.io.IOException; 
import java.net.*; 


public class Server {

	/**
	 * @param args
	 */
	private static	boolean		run				= true;
	private static 	boolean 	player1_check 	= false;
	private static	boolean		player2_check 	= false;
	
	private static 	InetAddress			address1;
	private	static	InetAddress			address2;
	
	private static	boolean		start			= false;
	private static 	int			port			= 4788;
	private			String		game_name		= "Bomberman - Gruppe38 - Netzwerk";
	private static 	String		password		= "github";
	
	
	
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		DatagramSocket socket = new DatagramSocket(port);
		
		while(run == true){
			
			DatagramPacket packet = new DatagramPacket( new byte[1024], 1024);
			socket.receive( packet );
			
			byte[] data_byte		= packet.getData();
			String data				= new String(data_byte, 0, packet.getLength(), "UTF-8");
			
			System.out.println(data);
			if(data.contains("close")){
				close_programm();
				System.out.println("Befehl zum Schließen gesetzt!");
			}
			
			if(start == false){
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
						}
					}
				}else{
					//Starte Spiel
					start = true;
				}
				
			}
			if(start == true){
				//Wenn nun beide Spieler angemeldet sind und das Spiel gestartet ist
				//Frage ankommende Daten ab und fuehre die gewuenschten Aktionen aus
				if(data.contains("player_up")){
					if(address1.equals(packet.getAddress())){
						
						player1_up();
						
					}else if(address2.equals(packet.getAddress())){
						
						player2_up();
						
					}
				}
				else if(data.contains("player_down")){
					if(address1.equals(packet.getAddress())){
						
						player1_down();
						
					}else if(address2.equals(packet.getAddress())){
						
						player2_down();
						
					}
				}
				else if(data.contains("player_left")){
					if(address1.equals(packet.getAddress())){
						
						player1_left();
						
					}else if(address2.equals(packet.getAddress())){
						
						player2_left();
						
					}
				}
				else if(data.contains("player_right")){
					if(packet.getAddress() == address1){
						
						player1_right();
						
					}else if(address2.equals(packet.getAddress())){
						
						player2_right();
						
					}
				}
				 if(data.contains("player_bomb")){
					 System.out.println("Eingabe erkannt");
					if(address1.equals(packet.getAddress())){
						
						player1_bomb();
						System.out.println("Befehl ausgeführt");
						
					}else if(address2.equals(packet.getAddress())){
						
						player2_bomb();
						
					}
				}
				
				
			}
			
		}
		socket.close();
		System.out.println("Programm geschlossen!");
		
	}


	private static void player2_bomb() {
		// TODO Auto-generated method stub
		System.out.println("Spieler2 legt Bombe!");
	}


	private static void player2_right() {
		// TODO Auto-generated method stub
		
	}



	private static void player2_left() {
		// TODO Auto-generated method stub
		
	}



	private static void player2_down() {
		// TODO Auto-generated method stub
		
	}


	private static void player2_up() {
		// TODO Auto-generated method stub
		
	}


	private static void player1_bomb() {
		// TODO Auto-generated method stub
		System.out.println("Spieler1 legt Bombe!");
	}


	private static void player1_right() {
		// TODO Auto-generated method stub
		
	}


	private static void player1_left() {
		// TODO Auto-generated method stub
		
	}


	private static void player1_down() {
		// TODO Auto-generated method stub
		
	}


	private static void player1_up() {
		// TODO Auto-generated method stub
		
	}






	private static void close_programm() {
		// TODO Auto-generated method stub
		run = false;
	}

	private static void setPlayer1(InetAddress address) {
		// TODO Auto-generated method stub
		address1	= address;
	}

	private static void setPlayer2(InetAddress address) {
		// TODO Auto-generated method stub
		address2	= address;
	}


}
