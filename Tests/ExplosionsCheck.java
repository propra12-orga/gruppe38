package gruppe38.Tests;

import gruppe38.Main;

/**
 * Abfrage, ob ein Objekt von einer Explosion getroffen wird
 * @author Gruppe38
 *
 */
public class ExplosionsCheck {
/**
 * Belegt alle betroffenen Felder mit "nothing", sonst bleiben sie gleich
 * @param wert1
 * @param wert2
 * @param explosionsradius
 * @param existent
 * @param objekt_typ
 * @return Feldinhalt
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
			if (objekt_typ.equals("ausgang")){
				s = "ausgang";
			}

		}
		return s;
	}
}
