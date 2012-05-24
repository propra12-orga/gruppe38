package gruppe38;

import java.awt.Color;

public class Drawing extends Main {
	public static double w1[] = new double[Main.bombenanzahl];
	public static double w2[] = new double[Main.bombenanzahl];
	public static double h1[] = new double[Main.bombenanzahl];
	public static double h2[] = new double[Main.bombenanzahl];

	static boolean malen = false;

	// initialisierung der Width und Height des Bildes der Explosionsgrafik
	public static void init() {
		for (int i = 0; i < bombenanzahl; i++) {
			w1[i] = spielfeldgroesse / 2;
			w2[i] = spielfeldgroesse / 2;
			h1[i] = spielfeldgroesse / 2;
			h2[i] = spielfeldgroesse / 2;
		}
		/*
		 * Reset des Spielfeldes
		 */
		for (int i = 0; i < spielfelder; i++) {
			for (int i2 = 0; i2 < spielfelder; i2++) {
				feld[i][i2].mauer = false;
				feld[i][i2].beinhaltet = "nothing";
				feld[i][i2].belegt = false;

			}
		}

		/*
		 * Initialisierung des Spielfeldes mit Powerups, Mauer und Spielerspawns
		 */

		for (int i = 0; i < spielfelder; i++) {
			for (int i2 = 0; i2 < spielfelder; i2++) {
				if (i != 0 & i2 != 0 & i != spielfelder & i2 != spielfelder
						& (i + 1) % 2 == 0 & (i2 + 1) % 2 == 0) {
					feld[i][i2].mauer = true;
					farbe[i][i2] = new Color(255, 255, 255);
				}

				if (i == spielfelder - spielfelder)
					feld[i][i2].beinhaltet = "spawn";
				else if (i == spielfelder)
					feld[i][i2].beinhaltet = "spawn";
				else if (i2 == spielfelder - spielfelder)
					feld[i][i2].beinhaltet = "spawn";
				else if (i2 == spielfelder)
					feld[i][i2].beinhaltet = "spawn";
				if (!feld[i][i2].beinhaltet.equals("spawn")
						& !feld[i][i2].mauer) {

					if (randomnumber(0, 5) == 1) {
						feld[i][i2].beinhaltet = "explosiv";
						feld[i][i2].belegt = true;
					}
					if (randomnumber(0, 5) == 3) {
						feld[i][i2].beinhaltet = "feuer";
						feld[i][i2].belegt = true;
					}
				}
			}
		}
		// initialisierung der Spielerposition
		sp1.x = feld[0][0].x;
		sp1.y = feld[0][0].y;
	}

	public static void draw() {

		/*
		 * Spielfeld mit den farben und der Powerups malen.
		 */
		for (int i = 0; i < spielfelder; i++) {
			for (int i2 = 0; i2 < spielfelder; i2++) {

				StdDraw.setPenColor(Main.farbe[i][i2]);
				if (malen) {
					StdDraw.filledSquare(spielfeldgroesse * i
							+ spielfeldgroesse / 2, spielfeldgroesse * i2
							+ spielfeldgroesse / 2, spielfeldgroesse / 2);
					// abfrage des Explosionspowerups
					if (feld[i][i2].beinhaltet.equals("feuer")) {
						StdDraw.picture(feld[i][i2].x, feld[i][i2].y,
								feld[i][i2].beinhaltet + ".gif",
								spielfeldgroesse - spielfeldgroesse / 10,
								spielfeldgroesse - spielfeldgroesse / 10);
					}
					// Abfrage des Bombenanzahlpowerups
					if (feld[i][i2].beinhaltet.equals("explosiv")) {
						StdDraw.picture(feld[i][i2].x, feld[i][i2].y,
								feld[i][i2].beinhaltet + ".png",
								spielfeldgroesse - spielfeldgroesse / 10,
								spielfeldgroesse - spielfeldgroesse / 10);
					}if (feld[i][i2].mauer) {
						StdDraw.picture(feld[i][i2].x, feld[i][i2].y,
								"mauer" + ".png",
								spielfeldgroesse  ,
								spielfeldgroesse );
					}
				}
			}
		}

		// bomben malen
		for (int i = 0; i < bombenanzahl; i++) {

			// wenn aufgerufen bombe existiert, male sie
			if (bombe[i].existent == true) {
				StdDraw.picture(bombe[i].x, bombe[i].y, "bombe.gif",
						spielfeldgroesse - spielfeldgroesse / 10,
						spielfeldgroesse - spielfeldgroesse / 10);

			}
			// wenn der explosionsthread nicht existiert, setze die dehnung des
			// explosionsbildes auf 0
			if (!explosion[i].isAlive()) {
				w1[i] = 0;
				h2[i] = 0;
			}

			// wenn eine explosion existiert, dehne das explosionsbild in x und
			// y richtung gleichermassen
			if (!bombe[i].isAlive() & explosion[i].existent == true
					& explosion[i].isAlive()) {

				w1[i] += (double) 1 / spielfelder + (double) 1 / spielfelder
						* sp1.explosions_staerke / 4;
				h2[i] += (double) 1 / spielfelder + (double) 1 / spielfelder
						* sp1.explosions_staerke / 4;
				if (w1[i] > (double) 1 / spielfelder + (double) 1 / spielfelder
						* sp1.explosions_staerke * 2)
					w1[i] = (double) 1 / spielfelder + (double) 1 / spielfelder
							* sp1.explosions_staerke * 2;
				if (h2[i] > (double) 1 / spielfelder + (double) 1 / spielfelder
						* sp1.explosions_staerke * 2)
					h2[i] = (double) 1 / spielfelder + (double) 1 / spielfelder
							* sp1.explosions_staerke * 2;

				StdDraw.picture(bombe[i].x, bombe[i].y, "explosion.png", w1[i],
						h1[i]);
				StdDraw.picture(bombe[i].x, bombe[i].y, "explosion.png", w2[i],
						h2[i]);

				for (int i2 = 0; i2 < bombenanzahl; i2++) {
					// ABFRAGE OB ZERST�RBARE BOMBEN IN DER N�HE SIND
					BombenTest test = new BombenTest(i, i2);
					test.run();

				}

				for (int i3 = 0; i3 < spielfelder; i3++) {
					for (int i4 = 0; i4 < spielfelder; i4++) {
						// ABFRAGE OB ZERST�RBARE ITEMS IN DER N�HE SIND
						ItemTest test2 = new ItemTest(i, i3, i4);
						test2.run();

					}

				}

			}

		}

		// malen des Spielers
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.filledCircle(sp1.x, sp1.y, sp1.radius);
		malen = true;

	}
}
