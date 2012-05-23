public class ExplosionsCheck {
	

	public static String ExplosionsCheck(double wert1, double wert2,
			double redundanzwert, boolean existent, String objekt_typ) {
		String s = "wurst";
		if (wert1 + redundanzwert > Main.Betrag(wert1, wert2) & existent) {

			if (objekt_typ.equals("bombe")) {
				s = "bombe";

			}
			if (objekt_typ.equals("noch nicht implementiert")) {
				s = "noch nicht implementiert";
			}

		}
		return s;
	}
}
