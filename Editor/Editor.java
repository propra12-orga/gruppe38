package gruppe38.Editor;

import gruppe38.Spielfeld.Spielfeld2;

import java.awt.Color;

public class Editor extends Thread{

	public static String item = "nothing";
	private static int spielfelder = 15;

	private static double spielfeldgroesse = (double) 1 / getSpielfelder();

	private static int x_feld = 0;
	private static int y_feld = 0;

	private static Spielfeld2[][] feld = new Spielfeld2[getSpielfelder()][getSpielfelder()];
	private static Color farbe[][] = new Color[getSpielfelder()][getSpielfelder()];

	public static double Betrag(double wert1, double wert2) {
		double betrag = (Math.sqrt((wert1 - wert2) * (wert1 - wert2)));

		return betrag;
	}

	public static int randomnumber(int von, int bis) {
		int a = 0;
		double x = Math.random() * bis + von;
		if (x > bis)
			x = bis;

		a = (int) x;

		return a;
	}

	public void run() {
		InitEditor.init();

		/*
		 * Spielschleife
		 */
		while (true) {

			DrawingEditor.draw();

			// Hintergrund
			StdDraw.setPenColor(StdDraw.DARK_GRAY);
			StdDraw.filledSquare(.5, .5, 1);

			// // den Draw-Befehl f�r alle Powerups, Bomben, Spieler aufrufen
			// Drawing.draw();

			// Gib dem Frame StdDraw den Befehl das offscreenbild zu
			// zeichnen
			StdDraw.onscreen.drawImage(StdDraw.offscreenImage, 0, 0, null);;

			// zeichne Frame neu

			// warte 10 millisekunden
			StdDraw.show(10);

			// FPS anzeigen lassen
			StdDraw.offscreen.setColor(Color.BLACK);

			// Gib dem Frame StdDraw den Befehl das offscreenbild zu
			// zeichnen
			StdDraw.onscreen.drawImage(StdDraw.offscreenImage, 0, 0, null);;

			// zeichne Frame neu
			StdDraw.frame.repaint();

		}
	}

	public static int getSpielfelder() {
		return spielfelder;
	}

	public static void setSpielfelder(int spielfelder) {
		Editor.spielfelder = spielfelder;
	}

	public static double getSpielfeldgroesse() {
		return spielfeldgroesse;
	}

	public static void setSpielfeldgroesse(double spielfeldgroesse) {
		Editor.spielfeldgroesse = spielfeldgroesse;
	}

	public static Color[][] getFarbe() {
		return farbe;
	}

	public static void setFarbe(Color farbe[][]) {
		Editor.farbe = farbe;
	}

	public static Spielfeld2[][] getFeld() {
		return feld;
	}

	public static void setFeld(Spielfeld2[][] feld) {
		Editor.feld = feld;
	}

	public static int getX_feld() {
		return x_feld;
	}

	public static void setX_feld(int x_feld) {
		Editor.x_feld = x_feld;
	}

	public static int getY_feld() {
		return y_feld;
	}

	public static void setY_feld(int y_feld) {
		Editor.y_feld = y_feld;
	}

}
