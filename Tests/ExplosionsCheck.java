package gruppe38.Tests;

import gruppe38.Main;

public class ExplosionsCheck {

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
