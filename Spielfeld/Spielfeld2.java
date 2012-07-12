package gruppe38.Spielfeld;

import java.io.Serializable;

import gruppe38.Sonstiges.StdDraw;

/**
 * Das Spielfeld
 * @author Gruppe38
 */

public class Spielfeld2 implements Serializable{

	public double size = 0;
	public boolean belegt = false;
	public double x;
	public double y;
	public boolean mauer = false;
	public int bomben_number;
	public String beinhaltet;

	/**
	 * Initialisierung des Spielfeldes
	 * @param d x
	 * @param e y
	 * @param spielfeldgroesse
	 * @param s Inhalt
	 * @param b Belegung Ja/Nein
	 */
	public Spielfeld2(double d, double e, double spielfeldgroesse, String s,
			boolean b) {
		x = d;
		y = e;
		size = spielfeldgroesse;
		belegt = b;
		beinhaltet = s;
	}

	public void show() {
		StdDraw.show();
	}

	public void clear() {
		StdDraw.clear();
	}

}
