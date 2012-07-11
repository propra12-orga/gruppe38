package gruppe38.Netzwerk;

import gruppe38.Main;

public class CreateStamp extends Thread{
	
	public CreateStamp(){
		
	}

	public static Stamp create(){
		
		Stamp stempel = new Stamp(Main.getSpielfelder(), Main.getFeld(), Main.getSpielfeldgroesse(),
									Main.getBombenanzahl(), Main.getBombe(), 
									Main.getExplosion(), Main.getW1(), Main.getW2(), Main.getH1(), 
									Main.getH2(), Main.getSp1(), Main.getSp2(), Main.getHdraw());
		System.out.println("Aktuellen Stempel erstellt");
		
		return stempel;
	}
}
