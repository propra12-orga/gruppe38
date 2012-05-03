package gruppe38;

public class ExplosionsCheck {
	// wenn wert1 + redundanzwert

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
			// System.out.println("Redund.+wert1:" + (wert1 + explosionsradius)
			// + " Betrag(wert1,wert2)+wert1:"
			// + (wert1 + Main.Betrag(wert1, wert2) + " String:" + s));

		}
		return s;
	}
}
