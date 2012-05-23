import java.awt.Color;


public class Main {

	// random Zahl zwischen 0 und 255
	public static int Farbe(int a) {
		double x = Math.random() * 255;
		a = (int) x;

		return a;
	}

	// Methode zur Betragserrechnung zwecks einfacher positiver Differenzen
	// zweier Distanzen
	public static double Betrag(double wert1, double wert2) {
		double betrag = (Math.sqrt((wert1 - wert2) * (wert1 - wert2)));

		return betrag;
	}

	// Initialisierung der Varbiablen, Objekte etc.
	public static int bombenanzahl = 20;
	public static int spielfelder = 12; // individuel einstellbar, beste
										// performance mit 12

	public static double spielfeldgröße = (double) 1 / spielfelder;

	public static Spielfeld2[][] feld = new Spielfeld2[spielfelder][spielfelder];
	public static Spieler sp1 = new Spieler(.5, .5, "wurst", 1);
	public static Color farbe[][] = new Color[spielfelder][spielfelder];

	public static boolean bombenmalen = false;
	public static boolean left = false;
	public static boolean right = false;
	public static boolean up = false;
	public static boolean down = false;
	public static boolean space = false;

	public static int bombencounter = 0;// zählt die anzahl der bomben
	public static Bombe[] bombe = new Bombe[bombenanzahl];
	public static double bombe_x;
	public static double bombe_y;
	public static boolean bombencheck;
	public static int x_feld = 0;
	public static int y_feld = 0;

	public static int explosionscounter = 0;// zählt die anzahl der explosionen
	public static Explosion[] explosion = new Explosion[bombenanzahl];

	/**
	 * Hauptprogramm
	 * 
	 * @throws InterruptedException
	 * 
	 **/

	public static void main(String[] args) {

		/*
		 * Initialisierungen
		 */

		Drawing.init();
		for (int i = 0; i < bombenanzahl; i++) {
			bombe[i] = new Bombe(.0, .0, false, 0, 0, "bombe");
			explosion[i] = new Explosion(.0, .0, 0, 0, false, "explosion");
		}

		// Ersteinteilung des Spielfeldes
		for (int i = 0; i < spielfelder; i++) {
			for (int i2 = 0; i2 < spielfelder; i2++) {
				feld[i][i2] = new Spielfeld2(spielfeldgröße * i + spielfeldgröße
						/ 2, spielfeldgröße * i2 + spielfeldgröße / 2,
						spielfeldgröße, false);
				farbe[i][i2] = new Color(Farbe(1), Farbe(1), Farbe(1));

			}
		}
		/*
		 * Spielschleife
		 */

		while (true) {

			// Steuerung, StdDraw wurde bearbeitet in Zeile 1363. KeyListener
			// des Frames wurde benötigt.
			// Wurde einer Pfeiltaste oder iwas gedrückt, boolean=true,
			// Abfrage hier: kommt "Spieler"-Radius an Rand, setzte Spieler auf
			// Position = Rand - Spieler-Radius
			if (left) {
				if (sp1.x < 0 + feld[0][0].size / 2.25 + 0.015)
					sp1.x = 0 + feld[0][0].size / 2.25;
				else
					sp1.x -= 0.015;
			}
			if (right) {
				if (sp1.x > 1 - feld[0][0].size / 2.25 - 0.015)
					sp1.x = 1 - feld[0][0].size / 2.25;
				else
					sp1.x += 0.015;
			}
			if (down) {
				if (sp1.y < 0 + feld[0][0].size / 2.25 + 0.015)
					sp1.y = 0 + feld[0][0].size / 2.25;
				else
					sp1.y -= 0.015;
			}
			if (up) {
				if (sp1.y > 1 - feld[0][0].size / 2.25 - 0.015)
					sp1.y = 1 - feld[0][0].size / 2.25;
				else
					sp1.y += 0.015;
			}

			/*
			 * wenn Leertaste gedrückt wird: 1)gecheckt, ob der bombencounter
			 * gleich 479 ist, wenn ja counter=0; 2)neue Bombe wird im Thread
			 * erstellt. vorher wird geprüft auf welchem feld sich der spieler
			 * sich befinden, damit die bombe dort plaziert wird 3)counter wird
			 * um eins erweitert 4)space boolean wird auf false gesetzt, damit
			 * der befehl nochmal aufgerufen werden kann
			 */
			if (space) {
				if (bombencounter == bombenanzahl - 1)
					bombencounter = 0;
				FeldCheck(sp1.x, sp1.y);
				if (feld[x_feld][y_feld].belegt == false) {
					bombe[bombencounter] = new Bombe(bombe_x, bombe_y, true,
							x_feld, y_feld, "bombe");
					bombe[bombencounter].start();
					feld[x_feld][y_feld].belegt = true;
					bombencounter++;
					System.out.println("fehler!");
					// bombencheck = false;
				}
				space = false;
			}

			StdDraw.setPenColor(StdDraw.BLACK);
			StdDraw.filledSquare(.5, .5, 1);

			Drawing.draw();

			StdDraw.show(10);
			StdDraw.clear();

		}
	}

	// FeldCheck-Methode: x-y-Werte werden eingelesen, und geprüft, ob auf
	// welchem Feld der spieler sich befindet. setzt feld auf besetzt
	private static void FeldCheck(double x, double y) {
		// System.out.println(spielfeldgröße);

		for (int i = 0; i < spielfelder; i++) {
			for (int i2 = 0; i2 < spielfelder; i2++) {
				Betrag(x, feld[i][i2].x);
				Betrag(y, feld[i][i2].y);
				if (Betrag(x, feld[i][i2].x) + spielfeldgröße / 2 < spielfeldgröße
						& Betrag(y, feld[i][i2].y) + spielfeldgröße / 2 < spielfeldgröße) {
					// System.out.println(feld[i][i2].x);
					// System.out.println(feld[i][i2].y);
					bombe_x = feld[i][i2].x;
					bombe_y = feld[i][i2].y;
					x_feld = i;
					y_feld = i2;

					return;

				}
			}
		}

	}
}