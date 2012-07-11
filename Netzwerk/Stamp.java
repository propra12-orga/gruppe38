package gruppe38.Netzwerk;

import gruppe38.Main;
import gruppe38.Items.Bombe;
import gruppe38.Items.BombeSp2;
import gruppe38.Items.Explosion;
import gruppe38.Spieler.Spieler;
import gruppe38.Spielfeld.Spielfeld2;

public class Stamp {
	
	public Stamp(int spielfelder, Spielfeld2[][] feld, double spielfeldgroesse, int bombenanzahl,
			Bombe[] bombe, Explosion[] explosion, double[] W1, double[] W2,
			double[] H1, double[] H2, Spieler sp1, Spieler sp2, double[] hdraw){
		
		//Konstruktor
	}
	
	public void run(){
		//Stempel erstellen
		Stamp stempel = create();
		
	}
	
	public static Stamp create(){
			
			Stamp stmp = new Stamp(Main.getSpielfelder(), Main.getFeld(), Main.getSpielfeldgroesse(),
										Main.getBombenanzahl(), Main.getBombe(), 
										Main.getExplosion(), Main.getW1(), Main.getW2(), Main.getH1(), 
										Main.getH2(), Main.getSp1(), Main.getSp2(), Main.getHdraw());
			System.out.println("Aktuellen Stempel erstellt");
			
			return stmp;
	}

	public void send(Stamp stampToSend){
		
		
		
	}
}
