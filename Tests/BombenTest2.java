package gruppe38.Tests;

import gruppe38.Drawing;
import gruppe38.Main;

/**
 * Hiermit wird Abgefragt, ob eine Bombe im explosionsradius befindet. Wenn ja, unterbreche den sleep im jeweiligen bomben-thread! Damit wir eine Explosion aufgerufen
 * @author Gruppe38
 */

public class BombenTest2 {
	int x = 0;
	int y = 0;
	int explosion_radius;
	int bomb_number;
	

	public BombenTest2(int i3, int i4) {
		x = i3;
		y = i4;
	}

	/**
	 *  ABFRAGE DER GEGENSTAENDE BEI EXPLOSION
	 */

	public void run() {


		// BOMBENTEST:
		// BOMBENTEST: f�r Bomben auf der X-Achse
		for (int i=x;i<x+explosion_radius;i++){
			if(!Main.getFeld()[x][y].beinhaltet.equals("nothing")&!Main.getFeld()[x][y].beinhaltet.equals("bombe")){
				break;
			}
			if(Main.getFeld()[x][y].belegt)Main.getBombe()[Main.getFeld()[x][y].bomben_number].interrupt();
		}

		
		}

	

}

