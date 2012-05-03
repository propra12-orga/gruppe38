package gruppe38;

//Bombenthread

public class Bombe extends Thread {

	double x;
	double y;
	boolean existent;
	int x_field;
	int y_field;
	String objekt_typ;
	boolean explosionscounter_check = true;
	int bombenindex;

	public Bombe(double x_coor, double y_coor, boolean bool, int x_feld,
			int y_feld, int bombenindexeingabe, String objekt_typ_eingabe) {
		x = x_coor;
		y = y_coor;
		existent = bool;
		x_field = x_feld;
		y_field = y_feld;
		objekt_typ = objekt_typ_eingabe;
		bombenindex = bombenindexeingabe;
	}

	public void run() {

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {

			// TODO Auto-generated catch block
			e.getCause();
		}

		// TEST: wenn explosionsthread nicht existiert, starte explosion

		if (!Main.explosion[bombenindex].isAlive()) {
			Main.explosion[bombenindex] = new Explosion(x, y, x_field, y_field,
					true, "explosion");
			Main.explosion[bombenindex].start();

		}

		if (explosionscounter_check)
			Main.explosionscounter++;

		if (Main.explosionscounter == Main.bombenanzahl - 1) {
			Main.explosionscounter = 0;
		}

		explosionscounter_check = true;
		Main.feld[x_field][y_field].belegt = false;
		existent = false;

		return;

	}
}
