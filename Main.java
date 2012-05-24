package gruppe38;

import java.awt.Color;

public class Main {

	// Initialisierung der FPS
	private static long delta = 0;
	private static long last = 0;
	public static long fps = 0;

	// Initialisierung der Varbiablen, Objekte etc.
	public static int bombenanzahl = 200;
	public static int spielfelder = 13; // mindestens 12 felder

	public static double spielfeldgroesse = (double) 1 / spielfelder;

	public static Spieler sp1 = new Spieler(.5, .5, "wurst", 1, 4);

	public static Spielfeld2[][] feld = new Spielfeld2[spielfelder][spielfelder];
	public static Color farbe[][] = new Color[spielfelder][spielfelder];

	public static boolean bombenmalen = false;
	public static boolean left = false;
	public static boolean right = false;
	public static boolean up = false;
	public static boolean down = false;
	public static boolean space = false;

	public static boolean spiel_start = false;
	public static boolean menu_start = true;

	public static int[] bild_drehen = new int[10];
	public static double[] bild_x = new double[10];
	public static double[] bild_y = new double[10];
	public static double[] bild_vx = new double[10];
	public static double[] bild_vy = new double[10];

	public static int bombencounter = 0;// zï¿½hlt die anzahl der bomben
	public static Bombe[] bombe = new Bombe[bombenanzahl];
	public static double bombe_x;
	public static double bombe_y;
	public static boolean bombencheck;
	public static int x_feld = 0;
	public static int y_feld = 0;

	public static int explosionscounter = 0;// zï¿½hlt die anzahl der
											// explosionen
	public static Explosion[] explosion = new Explosion[bombenanzahl];

	// random Zahl zwischen "von" und "bis"
	public static int randomnumber(int von, int bis) {
		int a = 0;
		double x = Math.random() * bis + von;
		if (x > bis)
			x = bis;

		a = (int) x;

		return a;
	}

	// Methode zur Betragserrechnung, zwecks einfacher positiver Differenzen
	// zweier Distanzen
	public static double Betrag(double wert1, double wert2) {
		double betrag = (Math.sqrt((wert1 - wert2) * (wert1 - wert2)));

		return betrag;
	}

	// Methode zur FPS berechnung
	private static void computeDelta() {
		delta = System.nanoTime() - last;
		last = System.nanoTime();
		fps = ((long) 1e9) / delta;

	}

	/**
	 * Hauptprogramm
	 * 
	 * @return
	 * 
	 * @throws InterruptedException
	 * 
	 **/

	public static void main(String[] args) {
		// Client fürs Netzwerk
		// Client client = new Client();
		// client.start();

		/*
		 * Initialisierungen
		 */
		// Bomben beim Menu
		for (int i = 0; i < 10; i++) {
			bild_x[i] = Math.random();
			bild_y[i] = Math.random();
			bild_vx[i] = Math.random() / 100;
			bild_vy[i] = Math.random() / 100;
		}

		// Initialisierungen der Powerups
		for (int i = 0; i < bombenanzahl; i++) {
			bombe[i] = new Bombe(.0, .0, false, 0, 0, 0, "bombe", 1);
			explosion[i] = new Explosion(.0, .0, 0, 0, false, "explosion");
		}

		// Ersteinteilung des Spielfeldes mit RandomFarbe
		for (int i = 0; i < spielfelder; i++) {
			for (int i2 = 0; i2 < spielfelder; i2++) {
				feld[i][i2] = new Spielfeld2(spielfeldgroesse * i
						+ spielfeldgroesse / 2, spielfeldgroesse * i2
						+ spielfeldgroesse / 2, spielfeldgroesse, "nothing",
						false);
				farbe[i][i2] = new Color(randomnumber(0, 255), randomnumber(0,
						255), randomnumber(0, 255));

			}
		}
		Drawing.init();
		/*
		 * Spielschleife
		 */

		while (true) {
			if (spiel_start) {
				// last = System.nanoTime();
				computeDelta();

				// Steuerung, StdDraw wurde bearbeitet in Zeile 1363.
				// KeyListener
				// des Frames wurde benï¿½tigt.
				// Wurde einer Pfeiltaste oder iwas gedrï¿½ckt, boolean=true,
				// Abfrage hier: kommt "Spieler"-Radius an Rand, setzte Spieler
				// auf:
				// Position = Rand - Spieler-Radius
				if (left) {
					if (sp1.x < 0 + feld[0][0].size / 2.25 + 0.015)
						sp1.x = 0 + feld[0][0].size / 2.25;
					else
						sp1.x -=  sp1.speed*0.01 / 4 / (spielfelder / 12);
				}
				if (right) {
					if (sp1.x > 1 - feld[0][0].size / 2.25 - 0.015)
						sp1.x = 1 - feld[0][0].size / 2.25;
					else
						sp1.x += sp1.speed*0.01 / 4 / (spielfelder / 12);
				}
				if (down) {
					if (sp1.y < 0 + feld[0][0].size / 2.25 + 0.015)
						sp1.y = 0 + feld[0][0].size / 2.25;
					else
						sp1.y -=  sp1.speed*0.01 / 4 / (spielfelder / 12);
				}
				if (up) {
					if (sp1.y > 1 - feld[0][0].size / 2.25 - 0.015)
						sp1.y = 1 - feld[0][0].size / 2.25;
					else
						sp1.y +=  sp1.speed*0.01 / 4 / (spielfelder / 12);
				}

				/*
				 * kollisionsabfrage mit den Mauern
				 * 
				 * Hier wird jedes Feld einzeln geprüft, ob es eine Mauer ist
				 * und ob es in der Nähe des Spielers ist. Naeheres steht bei
				 * jeder Abfrage
				 */
				for (int i = 0; i < spielfelder; i++) {
					for (int i2 = 0; i2 < spielfelder; i2++) {
						/*
						 * oben nach unten wird abgefragt:
						 * 
						 * 
						 * 1)ist spielermitte in y-Richtung kleiner als die
						 * mitte des abgefragten spielfeldes plus halbe
						 * spielfeldgroesse plus spielerradius
						 * 
						 * 2)liegt spielermitte wirklich überhalb des
						 * abgefragten spielfeldes
						 * 
						 * 3)&4) einschraenkung der spielerposition durch die
						 * breite des spielfeldes in x-Richtungen
						 */
						if (feld[i][i2].mauer
								& sp1.y < feld[i][i2].y + spielfeldgroesse / 2
										+ sp1.radius

								& sp1.y > feld[i][i2].y

								& sp1.x > feld[i][i2].x - spielfeldgroesse / 2
								& sp1.x < feld[i][i2].x + spielfeldgroesse / 2) {
							System.out.println("Oben nach unten");
							sp1.y = feld[i][i2].y + spielfeldgroesse / 2
									+ sp1.radius;
						}
						/*
						 * unten nach oben wird abgefragt:
						 * 
						 * 
						 * 1)ist spielermitte y-Richtung größer als die mitte
						 * des abgefragten spielfeldes minus halbe
						 * spielfeldgroesse minus spielerradius
						 * 
						 * 2)liegt spielermitte wirklich unterhalb des
						 * abgefragten spielfeldes
						 * 
						 * 3)&4) einschraenkung der spielerposition durch die
						 * breite des spielfeldes in x-Richtungen
						 */
						if (feld[i][i2].mauer
								& sp1.y > feld[i][i2].y - spielfeldgroesse / 2
										- sp1.radius

								& sp1.y < feld[i][i2].y

								& sp1.x > feld[i][i2].x - spielfeldgroesse / 2
								& sp1.x < feld[i][i2].x + spielfeldgroesse / 2) {
							System.out.println("Unten nach oben");
							sp1.y = feld[i][i2].y - spielfeldgroesse / 2
									- sp1.radius;

						}
						/*
						 * rechts nach links wird abgefragt:
						 * 
						 * 
						 * 1)ist spielermitte in x-Richtung kleiner als die
						 * mitte des abgefragten spielfeldes plus halbe
						 * spielfeldgroesse plus spielerradius
						 * 
						 * 2)liegt spielermitte wirklich rechts des abgefragten
						 * spielfeldes
						 * 
						 * 3)&4) einschraenkung der spielerposition durch die
						 * breite des spielfeldes in y-Richtungen
						 */
						if (feld[i][i2].mauer
								& sp1.x < feld[i][i2].x + spielfeldgroesse / 2
										+ sp1.radius

								& sp1.x > feld[i][i2].x

								& sp1.y > feld[i][i2].y - spielfeldgroesse / 2
								& sp1.y < feld[i][i2].y + spielfeldgroesse / 2) {
							System.out.println("Rechts nach links");
							sp1.x = feld[i][i2].x + spielfeldgroesse / 2
									+ sp1.radius;

						}
						/*
						 * links nach rechts wird abgefragt:
						 * 
						 * 1)ist spielermitte in x-Richtung größer als die mitte
						 * des abgefragten spielfeldes plus halbe
						 * spielfeldgroesse plus spielerradius
						 * 
						 * 2)liegt spielermitte wirklich links des abgefragten
						 * spielfeldes
						 * 
						 * 3)&4) einschraenkung der spielerposition durch die
						 * breite des spielfeldes in y-Richtungen
						 */
						if (feld[i][i2].mauer
								& sp1.x > feld[i][i2].x - spielfeldgroesse / 2
										- sp1.radius

								& sp1.x < feld[i][i2].x

								& sp1.y > feld[i][i2].y - spielfeldgroesse / 2
								& sp1.y < feld[i][i2].y + spielfeldgroesse / 2) {
							System.out.println("Links nach rechts");
							sp1.x = feld[i][i2].x - spielfeldgroesse / 2
									- sp1.radius;

						}

					}
				}

				/*
				 * wenn Leertaste gedrï¿½ckt wird:
				 * 
				 * 1)gecheckt, ob der bombencounter gleich 479 ist, wenn ja
				 * counter=0;
				 * 
				 * 2)neue Bombe wird im Thread erstellt. vorher wird geprï¿½ft
				 * auf welchem feld sich der spieler sich befinden, damit die
				 * bombe dort plaziert wird
				 * 
				 * 3)counter wird um eins erweitert
				 * 
				 * 4)space boolean wird auf false gesetzt, damit der befehl
				 * nochmal aufgerufen werden kann
				 */
				if (space) {
					if (bombencounter == bombenanzahl - 1)
						bombencounter = 0;
					FeldCheck(sp1.x, sp1.y);
					// bombennummer des spieler1 wird erhoeht
					
					if (feld[x_feld][y_feld].belegt == false
							& sp1.bombenanzahlcounter <= sp1.bombenanzahl) {
						sp1.bombenanzahlcounter++;
						bombe[bombencounter] = new Bombe(bombe_x, bombe_y,
								true, x_feld, y_feld, bombencounter, "bombe", 1);
						bombe[bombencounter].start();
						feld[x_feld][y_feld].belegt = true;
						bombencounter++;
					}
					space = false;
				}
				

				/*
				 * hier wird geprueft ob der Spieler ein Item berührt. Ist dies
				 * der Fall, wird das Item entfernt, indem das Feld die belegung
				 * auf "false" und das feld mit "nothing"(also nichts) gesetzt
				 * wird
				 */
				for (int i = 0; i < spielfelder; i++) {
					for (int i2 = 0; i2 < spielfelder; i2++) {
						FeldCheck(sp1.x, sp1.y);
						// check fuer die explosionsstaerke
						if (bombe_x == feld[i][i2].x & bombe_y == feld[i][i2].y
								& feld[i][i2].beinhaltet.equals("feuer")) {

							sp1.explosions_staerke++;
							// explosionsstaerke des spielers darf die anzahl
							// der spielfelder-2 nicht überschreiten
							if (sp1.explosions_staerke > spielfelder - 2)
								sp1.explosions_staerke = spielfelder - 2;

							feld[i][i2].belegt = false;
							feld[i][i2].beinhaltet = "nothing";
						}
						// check für die bombenanzahl des spielers
						if (bombe_x == feld[i][i2].x & bombe_y == feld[i][i2].y
								& feld[i][i2].beinhaltet.equals("explosiv")) {

							sp1.bombenanzahl++;
							// der spieler darf maximal 8 bomben legen können.
							// hier wird gecheckt, ob er mehr hat, wenn ja, wird
							// der zaehler auf 8 gesetzt
							if (sp1.bombenanzahl > 8)
								sp1.bombenanzahl = 8;
							feld[i][i2].belegt = false;
							feld[i][i2].beinhaltet = "nothing";
						}

					}
				}

				/**
				 * Befehle für das Zeichnen des Spieles
				 * ============================================================
				 */
				// Schwarzer Hintergrund
				StdDraw.setPenColor(StdDraw.BLACK);
				StdDraw.filledSquare(.5, .5, 1);

				// FPS anzeigen lassen
				StdDraw.offscreen.setColor(Color.red);
				StdDraw.offscreen.drawString("FPS:" + Long.toString(fps), 20,
						10);

				// den Draw-Befehl für alle Powerups, Bomben, Spieler aufrufen
				Drawing.draw();

				// Gib dem Frame StdDraw den Befehl das offscreenbild zu
				// zeichnen
				StdDraw.onscreen.drawImage(StdDraw.offscreenImage, 0, 0, null);;

				// warte 10 millisekunden
				StdDraw.show(10);

				// zeichne Frame neu
				StdDraw.frame.repaint();

			} else {
				/**
				 * Anfangsbild mit den Bomben die rumspringen. Wird zu Beginn
				 * aufgerufen.
				 */

				// Malt ein weißes Viereck zum uebermalen
				StdDraw.setPenColor(StdDraw.WHITE);
				StdDraw.filledSquare(.5, .5, 1);

				// Hier werden die Springenden Bomben berechnet und gemalt
				for (int i = 0; i < 10; i++) {

					StdDraw.picture(bild_x[i], bild_y[i], "bombe.gif",
							bild_drehen[i]);
					bild_x[i] += bild_vx[i];
					bild_y[i] += bild_vy[i];
					bild_drehen[i] += Math.random();
					if (bild_x[i] < 0)
						bild_vx[i] = -bild_vx[i];
					if (bild_x[i] > 1)
						bild_vx[i] = -bild_vx[i];
					if (bild_y[i] < 0)
						bild_vy[i] = -bild_vy[i];
					if (bild_y[i] > 1)
						bild_vy[i] = -bild_vy[i];

				}

				// Gib dem Frame StdDraw den Befehl das offscreenbild zu
				// zeichnen
				StdDraw.onscreen.drawImage(StdDraw.offscreenImage, 0, 0, null);;

				// warte 10 millisekunden
				StdDraw.show(10);

				// zeichne Frame neu
				StdDraw.frame.repaint();

			}

		}
	}
	// FeldCheck-Methode: hier werden die x-y-Werte eingelesen und geprï¿½ft,
	// auf
	// welchem Feld der spieler sich befindet.
	private static void FeldCheck(double x, double y) {

		for (int i = 0; i < spielfelder; i++) {
			for (int i2 = 0; i2 < spielfelder; i2++) {
				Betrag(x, feld[i][i2].x);
				Betrag(y, feld[i][i2].y);
				if (Betrag(x, feld[i][i2].x) + spielfeldgroesse / 2 < spielfeldgroesse
						& Betrag(y, feld[i][i2].y) + spielfeldgroesse / 2 < spielfeldgroesse) {
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