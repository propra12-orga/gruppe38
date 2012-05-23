import java.awt.Color;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;

public class Server extends Thread {

	private ServerSocket serverSocket;

	public Server(int port) throws IOException {
		serverSocket = new ServerSocket(port);
		// serverSocket.setSoTimeout(10000);
	}

	public void run() {
		Main main = null;
		Drawing draw = null;
		while (true) {

			try {
				serverSocket.accept();

				FileInputStream fileIn2 = new FileInputStream("drawing.ser");
				ObjectInputStream in2 = new ObjectInputStream(fileIn2);
				draw = (Drawing) in2.readObject();

				FileInputStream fileIn = new FileInputStream("main.ser");
				ObjectInputStream in = new ObjectInputStream(fileIn);

				main = (Main) in.readObject();

				in2.close();
				in.close();
				fileIn.close();
				fileIn2.close();
			} catch (IOException i) {
				i.printStackTrace();
				return;
			} catch (ClassNotFoundException c) {
				System.out.println(".Employee class not found.");
				c.printStackTrace();
				return;
			}
			main.main();

		}

	}
	public static void main(String[] args) {

		try {
			Thread t = new Server(100);
			t.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/*****************************************************************************************************************************************************
	 * 
	 * @author Styler
	 * 
	 ****************************************************************************************************************************************************/

	public static class Drawing extends Main {
		public static double w1[] = new double[Main.bombenanzahl];
		public static double w2[] = new double[Main.bombenanzahl];
		public static double h1[] = new double[Main.bombenanzahl];
		public static double h2[] = new double[Main.bombenanzahl];

		static boolean malen = false;

		// initialisierung der Width und Height des Bildes der Explosionsgrafik
		public static void init() {
			for (int i = 0; i < bombenanzahl; i++) {
				w1[i] = spielfeldgröße / 2;
				w2[i] = spielfeldgröße / 2;
				h1[i] = spielfeldgröße / 2;
				h2[i] = spielfeldgröße / 2;
			}

			// initialiserung des Spielfeldes

			for (int i = 0; i < spielfelder; i++) {
				for (int i2 = 0; i2 < spielfelder; i2++) {
					if (i == spielfelder - spielfelder)
						feld[i][i2].beinhaltet = "spawn";
					else if (i == spielfelder)
						feld[i][i2].beinhaltet = "spawn";
					else if (i2 == spielfelder - spielfelder)
						feld[i][i2].beinhaltet = "spawn";
					else if (i2 == spielfelder)
						feld[i][i2].beinhaltet = "spawn";
					if (!feld[i][i2].beinhaltet.equals("spawn")) {

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
			// StdDraw.picture(.5, .5, "background.png");

			// spielfeld malen
			for (int i = 0; i < spielfelder; i++) {
				for (int i2 = 0; i2 < spielfelder; i2++) {

					StdDraw.setPenColor(Main.farbe[i][i2]);
					if (malen) {
						StdDraw.filledSquare(spielfeldgröße * i
								+ spielfeldgröße / 2, spielfeldgröße * i2
								+ spielfeldgröße / 2, spielfeldgröße / 2);
						if (feld[i][i2].beinhaltet.equals("feuer")) {
							StdDraw.picture(feld[i][i2].x, feld[i][i2].y,
									feld[i][i2].beinhaltet + ".gif",
									spielfeldgröße - spielfeldgröße / 10,
									spielfeldgröße - spielfeldgröße / 10);
						}
						if (feld[i][i2].beinhaltet.equals("explosiv")) {
							StdDraw.picture(feld[i][i2].x, feld[i][i2].y,
									feld[i][i2].beinhaltet + ".png",
									spielfeldgröße - spielfeldgröße / 10,
									spielfeldgröße - spielfeldgröße / 10);
						}
					}
				}
			}

			// bomben malen
			for (int i = 0; i < bombenanzahl; i++) {

				if (bombe[i].existent == true) {
					StdDraw.picture(bombe[i].x, bombe[i].y, "bombe.gif",
							spielfeldgröße - spielfeldgröße / 10,
							spielfeldgröße - spielfeldgröße / 10);

				}
				if (!explosion[i].isAlive()) {
					w1[i] = 0;
					h2[i] = 0;
				}
				if (!bombe[i].isAlive() & explosion[i].existent == true
						& explosion[i].isAlive()) {

					w1[i] += (double) 1 / spielfelder + (double) 1
							/ spielfelder * sp1.explosions_stärke / 4;
					h2[i] += (double) 1 / spielfelder + (double) 1
							/ spielfelder * sp1.explosions_stärke / 4;
					if (w1[i] > (double) 1 / spielfelder + (double) 1
							/ spielfelder * sp1.explosions_stärke * 2)
						w1[i] = (double) 1 / spielfelder + (double) 1
								/ spielfelder * sp1.explosions_stärke * 2;
					if (h2[i] > (double) 1 / spielfelder + (double) 1
							/ spielfelder * sp1.explosions_stärke * 2)
						h2[i] = (double) 1 / spielfelder + (double) 1
								/ spielfelder * sp1.explosions_stärke * 2;

					StdDraw.picture(bombe[i].x, bombe[i].y, "explosion.png",
							w1[i], h1[i]);
					StdDraw.picture(bombe[i].x, bombe[i].y, "explosion.png",
							w2[i], h2[i]);
					// System.out.println(w1[i] + " " + h2[i]);

					for (int i2 = 0; i2 < bombenanzahl; i2++) {
						// ABFRAGE OB ZERSTÖRBARE BOMBEN IN DER NÄHE SIND
						BombenTest test = new BombenTest(i, i2);
						test.run();

					}

					for (int i3 = 0; i3 < spielfelder; i3++) {
						for (int i4 = 0; i4 < spielfelder; i4++) {
							// ABFRAGE OB ZERSTÖRBARE ITEMS IN DER NÄHE SIND
							ItemTest test2 = new ItemTest(i, i3, i4);
							test2.run();

						}

					}

				}

			}
			StdDraw.setPenColor(StdDraw.BLACK);
			StdDraw.filledCircle(sp1.x, sp1.y, spielfeldgröße / 2.25);
			malen = true;

		}
	}

	/******************************************************************************************************************************
	 * 
	 * @author Styler
	 * 
	 *******************************************************************************************************************************/

	public static class Main {

		// random Zahl zwischen "von" und "bis"
		public static int randomnumber(int von, int bis) {
			int a = 0;
			double x = Math.random() * bis + von;
			if (x > bis)
				x = bis;

			a = (int) x;

			return a;
		}

		// Methode zur Betragserrechnung zwecks einfacher positiver Differenzen
		// zweier Distanzen
		public double Betrag(double wert1, double wert2) {
			double betrag = (Math.sqrt((wert1 - wert2) * (wert1 - wert2)));

			return betrag;
		}

		// FPS berechnen
		private void computeDelta() {
			delta = System.nanoTime() - last;
			last = System.nanoTime();
			fps = ((long) 1e9) / delta;

		}

		// Initialisierung der FPS
		private static long delta = 0;
		private static long last = 0;
		public static long fps = 0;

		// Initialisierung der Varbiablen, Objekte etc.
		public static int bombenanzahl = 200;
		public static int spielfelder = 12; // mindestens 12 felder

		public static double spielfeldgröße = (double) 1 / spielfelder;

		public static Spieler sp1 = new Spieler(.5, .5, "wurst", 1, 4);

		public static Spielfeld2[][] feld = new Spielfeld2[spielfelder][spielfelder];
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

		public static int explosionscounter = 0;// zählt die anzahl der
												// explosionen
		public static Explosion[] explosion = new Explosion[bombenanzahl];

		/**
		 * Hauptprogramm
		 * 
		 * @throws InterruptedException
		 * 
		 **/

		public void main() {

			Client client = new Client();
			client.start();

			/*
			 * Initialisierungen
			 */

			for (int i = 0; i < bombenanzahl; i++) {
				bombe[i] = new Bombe(.0, .0, false, 0, 0, 0, "bombe");
				explosion[i] = new Explosion(.0, .0, 0, 0, false, "explosion");
			}

			// Ersteinteilung des Spielfeldes
			for (int i = 0; i < spielfelder; i++) {
				for (int i2 = 0; i2 < spielfelder; i2++) {
					feld[i][i2] = new Spielfeld2(spielfeldgröße * i
							+ spielfeldgröße / 2, spielfeldgröße * i2
							+ spielfeldgröße / 2, spielfeldgröße, "nothing",
							false);
					farbe[i][i2] = new Color(randomnumber(0, 255),
							randomnumber(0, 255), randomnumber(0, 255));

				}
			}
			Drawing.init();
			/*
			 * Spielschleife
			 */

			// last = System.nanoTime();
			computeDelta();

			// Steuerung, StdDraw wurde bearbeitet in Zeile 1363.
			// KeyListener
			// des Frames wurde benötigt.
			// Wurde einer Pfeiltaste oder iwas gedrückt, boolean=true,
			// Abfrage hier: kommt "Spieler"-Radius an Rand, setzte Spieler
			// auf
			// Position = Rand - Spieler-Radius
			if (left) {
				if (sp1.x < 0 + feld[0][0].size / 2.25 + 0.015)
					sp1.x = 0 + feld[0][0].size / 2.25;
				else
					sp1.x -= 0.015 * sp1.speed / 4 / (spielfelder / 12);
			}
			if (right) {
				if (sp1.x > 1 - feld[0][0].size / 2.25 - 0.015)
					sp1.x = 1 - feld[0][0].size / 2.25;
				else
					sp1.x += 0.015 * sp1.speed / 4 / (spielfelder / 12);
			}
			if (down) {
				if (sp1.y < 0 + feld[0][0].size / 2.25 + 0.015)
					sp1.y = 0 + feld[0][0].size / 2.25;
				else
					sp1.y -= 0.015 * sp1.speed / 4 / (spielfelder / 12);
			}
			if (up) {
				if (sp1.y > 1 - feld[0][0].size / 2.25 - 0.015)
					sp1.y = 1 - feld[0][0].size / 2.25;
				else
					sp1.y += 0.015 * sp1.speed / 4 / (spielfelder / 12);
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
							x_feld, y_feld, bombencounter, "bombe");
					bombe[bombencounter].start();
					feld[x_feld][y_feld].belegt = true;
					bombencounter++;
					// System.out.println("Bombe gelegt!");
					// bombencheck = false;
				}
				space = false;
			}

			for (int i = 0; i < spielfelder; i++) {
				for (int i2 = 0; i2 < spielfelder; i2++) {
					FeldCheck(sp1.x, sp1.y);
					if (bombe_x == feld[i][i2].x & bombe_y == feld[i][i2].y
							& feld[i][i2].beinhaltet.equals("feuer")) {
						sp1.explosions_stärke++;
						if (sp1.explosions_stärke > spielfelder - 2)
							sp1.explosions_stärke = spielfelder - 2;
						feld[i][i2].belegt = false;
						feld[i][i2].beinhaltet = "nothing";
					}
					if (bombe_x == feld[i][i2].x & bombe_y == feld[i][i2].y
							& feld[i][i2].beinhaltet.equals("explosiv")) {
						feld[i][i2].belegt = false;
						feld[i][i2].beinhaltet = "nothing";
					}

				}
			}
			// System.out.println(Main.sp1.x);

			StdDraw.setPenColor(StdDraw.BLACK);
			StdDraw.filledSquare(.5, .5, 1);

			// FPS zeigen
			StdDraw.offscreen.setColor(Color.red);
			StdDraw.offscreen.drawString("FPS:" + Long.toString(fps), 20, 10);

			Drawing.draw();

			StdDraw.onscreen.drawImage(StdDraw.offscreenImage, 0, 0, null);;

			StdDraw.show(10);

			StdDraw.frame.repaint();

		}
		// FeldCheck-Methode: x-y-Werte werden eingelesen, und geprüft, ob auf
		// welchem Feld der spieler sich befindet. setzt feld auf besetzt
		private void FeldCheck(double x, double y) {
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
}
