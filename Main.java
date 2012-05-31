package gruppe38;

import gruppe38.Items.Bombe;
import gruppe38.Items.Explosion;
import gruppe38.Sonstiges.StdDraw;
import gruppe38.Spieler.Spieler;
import gruppe38.Spieler.Steuerung;
import gruppe38.Spielfeld.Spielfeld2;
import gruppe38.Tests.Kollisionsabfrage;

import java.awt.Color;

public class Main {

	// Initialisierung der FPS
	private static long delta = 0;
	private static long last = 0;
	public static long fps = 0;

	// Initialisierung der Varbiablen, Objekte etc.
	public static int bombenanzahl = 200;
	public static int spielfelder = 15; // mindestens 12 felder

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

	// initialisierung der Width und Height des Bildes der Explosionsgrafik
	public static double w1[] = new double[bombenanzahl];
	public static double w2[] = new double[bombenanzahl];
	public static double h1[] = new double[bombenanzahl];
	public static double h2[] = new double[bombenanzahl];

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

		Init.init();

		/*
		 * Spielschleife
		 */

		while (true) {
			computeDelta();
			if (spiel_start) {
				// FPS berechnen

				// Interaktion, bewegung etc
				Steuerung.aktionen();

				// Kollisionsabfragen
				Kollisionsabfrage.mauer();
				Kollisionsabfrage.item();

				/**
				 * Befehle für das Zeichnen des Spieles
				 * ============================================================
				 */

				// Hintergrund
				StdDraw.setPenColor(StdDraw.GREEN);
				StdDraw.filledSquare(.5, .5, 1);

				// den Draw-Befehl für alle Powerups, Bomben, Spieler aufrufen
				Drawing.draw();

				// Gib dem Frame StdDraw den Befehl das offscreenbild zu
				// zeichnen
				StdDraw.onscreen.drawImage(StdDraw.offscreenImage, 0, 0, null);;

				// zeichne Frame neu

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

					StdDraw.picture(bild_x[i], bild_y[i],
							"../Bilder/bombe.gif", bild_drehen[i]);
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

			}

			// warte 10 millisekunden
			StdDraw.show(10);

			// FPS anzeigen lassen
			StdDraw.offscreen.setColor(Color.red);
			StdDraw.offscreen.drawString("FPS:" + Long.toString(fps), 20, 10);

			// Gib dem Frame StdDraw den Befehl das offscreenbild zu
			// zeichnen
			StdDraw.onscreen.drawImage(StdDraw.offscreenImage, 0, 0, null);;

			// zeichne Frame neu
			StdDraw.frame.repaint();

		}
	}
	public static Bombe[] getBombe() {
		return bombe;
	}

	public static void setBombe(Bombe[] bombe) {
		Main.bombe = bombe;
	}

	public static Spielfeld2[][] getFeld() {
		return feld;
	}

	public static void setFeld(Spielfeld2[][] feld) {
		Main.feld = feld;
	}

}
