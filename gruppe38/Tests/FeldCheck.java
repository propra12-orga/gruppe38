package gruppe38.Tests;

import gruppe38.Main;
/**
 * Pruefen des Spielers auf dem Spielfeld
 * @author Gruppe38
 *
 */
public class FeldCheck extends Main {

	/**
	 * hier werden die x-y-Werte eingelesen und geprueft, auf welchem Feld der Spieler sich befindet
	 */

	public static Feldwiedergabe check(double x, double y) {

		Feldwiedergabe fw = new Feldwiedergabe();

		for (int i = 0; i < getSpielfelder(); i++) {
			for (int i2 = 0; i2 < getSpielfelder(); i2++) {
				Betrag(x, getFeld()[i][i2].x);
				Betrag(y, getFeld()[i][i2].y);
				if (Betrag(x, getFeld()[i][i2].x) + getSpielfeldgroesse() / 2 < getSpielfeldgroesse()
						& Betrag(y, getFeld()[i][i2].y) + getSpielfeldgroesse() / 2 < getSpielfeldgroesse()) {
					setBombe_x(getFeld()[i][i2].x);
					setBombe_y(getFeld()[i][i2].y);
					fw.setX(i);
					fw.setY(i2);

				}
			}
		}
		return fw;

	}

}
