
package gruppe38;
  
import gruppe38.Items.Bombe;
import gruppe38.Items.BombeSp2;
import gruppe38.Items.Explosion;
import gruppe38.Sonstiges.StdDraw;
import gruppe38.Spieler.Spieler;
import gruppe38.Spieler.Steuerung;
import gruppe38.Spieler.SteuerungSp2;
import gruppe38.Spielfeld.Spielfeld2;
import gruppe38.Tests.Kollisionsabfrage;
import gruppe38.Sonstiges.StdAudio;

import java.awt.Color;

/**
 * Hauptprogramm und Spielschleife
 * @author Gruppe38
 *
 */
public class Main {

	// Initialisierung der FPS
	private static long delta = 0;
	private static long last = 0;
	public static long fps = 0;

	// Initialisierung der Varbiablen, Objekte etc.
	/**
	 * Maximale Bombenanzahl
	 */
	private static int bombenanzahl = 200;
	/**
	 * mindestens 12 Felder
	 */
	protected static int spielfelder = 15;

	private static double spielfeldgroesse = (double) 1 / getSpielfelder();

	private static Spieler sp1 = new Spieler(.5, .5, "wurst", 1, 4, "Spieler1");
	private static Spieler sp2 = new Spieler(.5, .5, "wurst", 1, 4, "Spieler2");

	private static Spielfeld2[][] feld = new Spielfeld2[getSpielfelder()][getSpielfelder()];
	private static Color farbe[][] = new Color[getSpielfelder()][getSpielfelder()];

	private static boolean bombenmalen = false;
	//Spieler1
	private static boolean left = false;
	private static boolean right = false;
	private static boolean up = false;
	private static boolean down = false;
	private static boolean space = false;
	//Spieler2
	private static boolean left2 = false;
	private static boolean right2 = false;
	private static boolean up2 = false;
	private static boolean down2 = false;
	private static boolean space2 = false;

	private static boolean spiel_start = false;
	private static boolean menu_start = true;

	private static int[] bild_drehen = new int[10];
	private static double[] bild_x = new double[10];
	private static double[] bild_y = new double[10];
	private static double[] bild_vx = new double[10];
	private static double[] bild_vy = new double[10];

	// initialisierung der Width und Height des Bildes der Explosionsgrafik,
	// auch zur Berechnung der Kollision ben�tigt
	private static double w1[] = new double[getBombenanzahl()];
	private static double w2[] = new double[getBombenanzahl()];
	private static double w3[] = new double[getBombenanzahl()];
	private static double w4[] = new double[getBombenanzahl()];
	private static double h1[] = new double[getBombenanzahl()];
	private static double h2[] = new double[getBombenanzahl()];
	private static double h3[] = new double[getBombenanzahl()];
	private static double h4[] = new double[getBombenanzahl()];
	private static double hdraw[] = new double[getBombenanzahl()];

	private static int bombencounter = 0;// z�hlt die anzahl der bomben
	private static Bombe[] bombe = new Bombe[getBombenanzahl()];
	private static BombeSp2[] bombe2 = new BombeSp2[getBombenanzahl()];
	private static double bombe_x;
	private static double bombe_y;
	private static boolean bombencheck;
	private static int x_feld = 0;
	private static int y_feld = 0;

	private static int explosionscounter = 0;// z�hlt die anzahl der
												// explosionen
	private static Explosion[] explosion = new Explosion[getBombenanzahl()];

	/**
	 *  random Zahl zwischen "von" und "bis"
	 * @param von
	 * @param bis
	 * @return Zufallszahl zwischen "von" und "bis"
	 */
	public static int randomnumber(int von, int bis) {
		int a = 0;
		double x = Math.random() * bis + von;
		if (x > bis)
			x = bis;

		a = (int) x;

		return a;
	}

	/**
	 * Methode zur Betragserrechnung, zwecks einfacher positiver Differenzen
	 * zweier Distanzen
	 * 
	 */ 
	public static double Betrag(double wert1, double wert2) {
		double betrag = (Math.sqrt((wert1 - wert2) * (wert1 - wert2)));

		return betrag;
	}

	/**
	 *  Methode zur FPS berechnung
	 */
	private static void computeDelta() {
		delta = System.nanoTime() - last;
		last = System.nanoTime();
		fps = ((long) 1e9) / delta;

	}

	/**
	 * Hauptprogramm
	 * @return 
	 * 
	 * 
	 * @throws InterruptedException
	 * 
	 **/

	public static void main(String[] args){
		//StdAudio.loop("http://www.masterpaddy.de/etc/background.wav");
		Init.init();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*
		 * Spielschleife
		 */

		while (true) {
			computeDelta();
			if (isSpiel_start()) {
				// FPS berechnen

				// Interaktion, bewegung etc
				Steuerung.aktionen();
				SteuerungSp2.aktionen();

				// Kollisionsabfragen
				Kollisionsabfrage.mauer(sp1);
				Kollisionsabfrage.item(sp1);
				Kollisionsabfrage.mauer(sp2);
				Kollisionsabfrage.item(sp2);

				/**
				 * Befehle f�r das Zeichnen des Spieles
				 * ============================================================
				 */

				// Hintergrund
				StdDraw.setPenColor(StdDraw.DARK_GRAY);
				StdDraw.filledSquare(.5, .5, 1);

				// den Draw-Befehl f�r alle Powerups, Bomben, Spieler aufrufen
				Drawing.draw();

				// Gib dem Frame StdDraw den Befehl das offscreenbild zu
				// zeichnen
				StdDraw.onscreen.drawImage(StdDraw.offscreenImage, 0, 0, null);;

				// zeichne Frame neu

			} 

			

			// warte 10 millisekunden
			StdDraw.show(10);

			// FPS anzeigen lassen
			StdDraw.offscreen.setColor(Color.BLACK);
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

	public static BombeSp2[] getBombe2() {
		return bombe2;
	}

	public static void setBombe2(BombeSp2[] bombe2) {
		Main.bombe2 = bombe2;
	}
	public static Spielfeld2[][] getFeld() {
		return feld;
	}

	public static void setFeld(Spielfeld2[][] feld) {
		Main.feld = feld;
	}

	public static double getSpielfeldgroesse() {
		return spielfeldgroesse;
	}

	public static void setSpielfeldgroesse(double spielfeldgroesse) {
		Main.spielfeldgroesse = spielfeldgroesse;
	}

	public static Spieler getSp1() {
		return sp1;
	}

	public static void setSp1(Spieler sp1) {
		Main.sp1 = sp1;
	}
	
	public static Spieler getSp2() {
		return sp2;
	}

	public static void setSp2(Spieler sp2) {
		Main.sp2 = sp2;
	}

	public static Color[][] getFarbe() {
		return farbe;
	}

	public static void setFarbe(Color farbe[][]) {
		Main.farbe = farbe;
	}

	public static boolean isBombenmalen() {
		return bombenmalen;
	}

	public static void setBombenmalen(boolean bombenmalen) {
		Main.bombenmalen = bombenmalen;
	}

	public static boolean isLeft() {
		return left;
	}

	public static void setLeft(boolean left) {
		Main.left = left;
	}

	public static boolean isRight() {
		return right;
	}

	public static void setRight(boolean right) {
		Main.right = right;
	}

	public static boolean isUp() {
		return up;
	}

	public static void setUp(boolean up) {
		Main.up = up;
	}

	public static boolean isDown() {
		return down;
	}

	public static void setDown(boolean down) {
		Main.down = down;
	}

	public static boolean isSpace() {
		return space;
	}

	public static void setSpace(boolean space) {
		Main.space = space;
	}

	public static boolean isLeft2() {
		return left2;
	}

	public static void setLeft2(boolean left) {
		Main.left2 = left;
	}

	public static boolean isRight2() {
		return right2;
	}

	public static void setRight2(boolean right) {
		Main.right2 = right;
	}

	public static boolean isUp2() {
		return up2;
	}

	public static void setUp2(boolean up) {
		Main.up2 = up;
	}

	public static boolean isDown2() {
		return down2;
	}

	public static void setDown2(boolean down) {
		Main.down2 = down;
	}
	
	public static void setSpace2(boolean space) {
		Main.space2 = space;
	}
	
	public static boolean isSpace2() {
		return space2;
	}

	public static boolean isMenu_start() {
		return menu_start;
	}

	public static void setMenu_start(boolean menu_start) {
		Main.menu_start = menu_start;
	}

	public static double[] getW1() {
		return w1;
	}

	public static void setW1(double w1[]) {
		Main.w1 = w1;
	}

	public static double[] getW2() {
		return w2;
	}

	public static void setW2(double w2[]) {
		Main.w2 = w2;
	}

	public static double[] getH1() {
		return h1;
	}

	public static void setH1(double h1[]) {
		Main.h1 = h1;
	}

	public static double[] getH2() {
		return h2;
	}

	public static void setH2(double h2[]) {
		Main.h2 = h2;
	}

	public static int getBombencounter() {
		return bombencounter;
	}

	public static void setBombencounter(int bombencounter) {
		Main.bombencounter = bombencounter;
	}

	public static double getBombe_x() {
		return bombe_x;
	}

	public static void setBombe_x(double bombe_x) {
		Main.bombe_x = bombe_x;
	}

	public static double getBombe_y() {
		return bombe_y;
	}

	public static void setBombe_y(double bombe_y) {
		Main.bombe_y = bombe_y;
	}

	public static boolean isBombencheck() {
		return bombencheck;
	}

	public static void setBombencheck(boolean bombencheck) {
		Main.bombencheck = bombencheck;
	}

	public static int getX_feld() {
		return x_feld;
	}

	public static void setX_feld(int x_feld) {
		Main.x_feld = x_feld;
	}

	public static int getY_feld() {
		return y_feld;
	}

	public static void setY_feld(int y_feld) {
		Main.y_feld = y_feld;
	}

	public static int getExplosionscounter() {
		return explosionscounter;
	}

	public static void setExplosionscounter(int explosionscounter) {
		Main.explosionscounter = explosionscounter;
	}

	public static Explosion[] getExplosion() {
		return explosion;
	}

	public static void setExplosion(Explosion[] explosion) {
		Main.explosion = explosion;
	}

	public static double[] getBild_x() {
		return bild_x;
	}

	public static void setBild_x(double[] bild_x) {
		Main.bild_x = bild_x;
	}

	public static double[] getBild_y() {
		return bild_y;
	}

	public static void setBild_y(double[] bild_y) {
		Main.bild_y = bild_y;
	}

	public static double[] getBild_vx() {
		return bild_vx;
	}

	public static void setBild_vx(double[] bild_vx) {
		Main.bild_vx = bild_vx;
	}

	public static double[] getBild_vy() {
		return bild_vy;
	}

	public static void setBild_vy(double[] bild_vy) {
		Main.bild_vy = bild_vy;
	}

	public static int getBombenanzahl() {
		return bombenanzahl;
	}

	public static void setBombenanzahl(int bombenanzahl) {
		Main.bombenanzahl = bombenanzahl;
	}

	public static int getSpielfelder() {
		return spielfelder;
	}

	public static void setSpielfelder(int spielfelder) {
		Main.spielfelder = spielfelder;
	}

	public static boolean isSpiel_start() {
		return spiel_start;
	}

	public static void setSpiel_start(boolean spiel_start) {
		Main.spiel_start = spiel_start;
	}

	public static double[] getW3() {
		return w3;
	}

	public static void setW3(double w3[]) {
		Main.w3 = w3;
	}

	public static double[] getW4() {
		return w4;
	}

	public static void setW4(double w4[]) {
		Main.w4 = w4;
	}

	public static double[] getH3() {
		return h3;
	}

	public static void setH3(double h3[]) {
		Main.h3 = h3;
	}

	public static double[] getH4() {
		return h4;
	}

	public static void setH4(double h4[]) {
		Main.h4 = h4;
	}

	public static double[] getHdraw() {
		return hdraw;
	}

	public static void setHdraw(double hdraw[]) {
		Main.hdraw = hdraw;
	}

}

