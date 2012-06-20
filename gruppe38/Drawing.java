package gruppe38;

import gruppe38.Sonstiges.StdDraw;
import gruppe38.Tests.BombenTest;
import gruppe38.Tests.ItemTest;

import java.awt.Color;

/**
 * Malt das gesamte Spielfeld, mit Playern, Items und Waenden
 * 
 * @author Gruppe38
 *
 */

public class Drawing extends Main {

	static boolean malen = false;

	/**
	 * Spielfeld mit den farben und der Powerups malen.
	 */
	
	public static void draw() {

		
		for (int i = 0; i < spielfelder; i++) {
			for (int i2 = 0; i2 < spielfelder; i2++) {

				if (malen) {

					// Spawnfeld malen
					if (getFeld()[i][i2].beinhaltet.equals("spawn")) {
						StdDraw.setPenColor(new Color(119, 34, 8));
						StdDraw.filledSquare(spielfeldgroesse * i
								+ spielfeldgroesse / 2, spielfeldgroesse * i2
								+ spielfeldgroesse / 2, spielfeldgroesse / 2);
					}

					// abfrage des Explosionspowerups
					if (getFeld()[i][i2].beinhaltet.equals("feuer")) {
						StdDraw.picture(getFeld()[i][i2].x, getFeld()[i][i2].y,
								"../Bilder/" + getFeld()[i][i2].beinhaltet
										+ ".gif", spielfeldgroesse
										- spielfeldgroesse / 10,
								spielfeldgroesse - spielfeldgroesse / 10);
					}
					// Abfrage des Bombenanzahlpowerups
					if (getFeld()[i][i2].beinhaltet.equals("explosiv")) {
						StdDraw.picture(getFeld()[i][i2].x, getFeld()[i][i2].y,
								"../Bilder/" + getFeld()[i][i2].beinhaltet
										+ ".png", spielfeldgroesse
										- spielfeldgroesse / 10,
								spielfeldgroesse - spielfeldgroesse / 10);
					}
					// Abfrage des Bombenanzahlpowerups
					if (getFeld()[i][i2].mauer) {
						StdDraw.picture(getFeld()[i][i2].x, getFeld()[i][i2].y,
								"../Bilder/mauer.png", spielfeldgroesse,
								spielfeldgroesse);
					}
					// Abfrage der kaputtbaren Mauern
					if (getFeld()[i][i2].beinhaltet.equals("mauer_destroyable")) {
						StdDraw.picture(getFeld()[i][i2].x, getFeld()[i][i2].y,
								"../Bilder/" + getFeld()[i][i2].beinhaltet
										+ ".png", spielfeldgroesse,
								spielfeldgroesse);
					}
				}
			}
		}

		// bomben malen
		for (int i = 0; i < bombenanzahl; i++) {

			// wenn aufgerufen bombe existiert, male sie
			if (getBombe()[i].isExistent() == true) {
				StdDraw.picture(getBombe()[i].getX(), getBombe()[i].getY(),
						"../Bilder/bombe.gif", spielfeldgroesse
								- spielfeldgroesse / 10, spielfeldgroesse
								- spielfeldgroesse / 10);

			}
			// wenn der explosionsthread nicht existiert, setze die dehnung des
			// explosionsbildes auf 0
			if (!explosion[i].isAlive()) {
				w1[i] = 0;
				h2[i] = 0;
			}

			// wenn eine explosion existiert, dehne das explosionsbild in x und
			// y richtung gleichermassen
			if (!getBombe()[i].isAlive() & explosion[i].isExistent() == true
					& explosion[i].isAlive()) {

				w1[i] += (double) 1 / spielfelder + (double) 1 / spielfelder
						* sp1.getExplosions_staerke() / 4;
				h2[i] += (double) 1 / spielfelder + (double) 1 / spielfelder
						* sp1.getExplosions_staerke() / 4;

				if (w1[i] > (double) 1 / spielfelder + (double) 1 / spielfelder
						* sp1.getExplosions_staerke() * 2)
					w1[i] = (double) 1 / spielfelder + (double) 1 / spielfelder
							* sp1.getExplosions_staerke() * 2;
				if (h2[i] > (double) 1 / spielfelder + (double) 1 / spielfelder
						* sp1.getExplosions_staerke() * 2)
					h2[i] = (double) 1 / spielfelder + (double) 1 / spielfelder
							* sp1.getExplosions_staerke() * 2;

				StdDraw.picture(getBombe()[i].getX(), getBombe()[i].getY(),
						"../Bilder/explosion2.png", w1[i], h1[i]);
				StdDraw.picture(getBombe()[i].getX(), getBombe()[i].getY(),
						"../Bilder/explosion2.png", w1[i], h1[i], 90);

			}
			for (int i2 = 0; i2 < bombenanzahl; i2++) {
				// ABFRAGE OB ZERST�RBARE BOMBEN IN DER N�HE SIND
				BombenTest test = new BombenTest(i, i2);
				test.test();
			}
			for (int i3 = 0; i3 < spielfelder; i3++) {
				for (int i4 = 0; i4 < spielfelder; i4++) {
					// ABFRAGE OB ZERST�RBARE ITEMS IN DER N�HE SIND
					ItemTest test2 = new ItemTest(i, i3, i4);
					test2.test();
				}
			}

		}

		// malen des Spielers
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.filledCircle(sp1.getX(), sp1.getY(), sp1.getRadius());
		malen = true;

	}
}
