package gruppe38.Tests;

import gruppe38.Main;

/**
 * Prueft das auf Inhalt und ob es von einer Bombe getroffen wurde
 * @author Gruppe38
 *
 */
public class ExplosionsCheck {
	
	/**
	 * Das Feld wird auf Inhalt ueberprueft. Wenn es von einer Bombe getroffen wird, wird der Inhalt "nothing"
	 * @param wert1		x
	 * @param wert2		y
	 * @param explosionsradius
	 * @param existent
	 * @param objekt_typ	Derzeitiger Inhalt des Feldes
	 * @return		Inhalt des Feldes nach der Explosion
	 */

	public static String ExplosionsCheck(double wert1, double wert2,
			double explosionsradius, boolean existent, String objekt_typ) {
		String s = "nothing";

		if (wert1 + explosionsradius / 2 > wert1 + Main.Betrag(wert1, wert2)
				& existent & wert1 - wert2 != 0) {

			if (objekt_typ.equals("bombe")) {
				s = "bombe";
			}
			if (objekt_typ.equals("feuer")) {
				s = "feuer";
			}
			if (objekt_typ.equals("explosiv")) {
				s = "explosiv";
			}
			if (objekt_typ.equals("mauer_destroyable")) {
				s = "mauer_destroyable";
			}

		}
		return s;
	}
}
