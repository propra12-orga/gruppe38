package gruppe38.Tests;

import gruppe38.Main;

public class FeldCheck extends Main {

	/*
	 * FeldCheck-Methode: hier werden die x-y-Werte eingelesen und gepr�ft,
	 * auf welchem Feld der Spieler sich befindet
	 */

	public static Feldwiedergabe check(double x, double y) {

		Feldwiedergabe fw = new Feldwiedergabe();

		for (int i = 0; i < spielfelder; i++) {
			for (int i2 = 0; i2 < spielfelder; i2++) {
				Betrag(x, getFeld()[i][i2].x);
				Betrag(y, getFeld()[i][i2].y);
				if (Betrag(x, getFeld()[i][i2].x) + spielfeldgroesse / 2 < spielfeldgroesse
						& Betrag(y, getFeld()[i][i2].y) + spielfeldgroesse / 2 < spielfeldgroesse) {
					bombe_x = getFeld()[i][i2].x;
					bombe_y = getFeld()[i][i2].y;
					fw.setX(i);
					fw.setY(i2);

				}
			}
		}
		return fw;

	}

}
