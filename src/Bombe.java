//Bombenthread

public class Bombe extends Thread {

	double x;
	double y;
	boolean existent;
	int x_field;
	int y_field;
	String objekt_typ;
	int counter = 0;
	int bombendauer = 1000;// dauer in millisekunden. drittel der zeit angeben
	

	public Bombe(double x_coor, double y_coor, boolean bool, int x_feld,
			int y_feld, String objekt_typ_eingabe) {
		x = x_coor;
		y = y_coor;
		existent = bool;
		x_field = x_feld;
		y_field = y_feld;
		objekt_typ = objekt_typ_eingabe;
	}

	public void run() {
		// System.out.println(existent);
		for (counter = 0; counter < bombendauer; counter++) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {

				// TODO Auto-generated catch block
				e.getCause();
			}

		}

		Main.explosion[Main.explosionscounter] = new Explosion(x, y, x_field,
				y_field, true, "explosion");
		Main.explosion[Main.explosionscounter].start();

		if (Main.explosionscounter == Main.bombenanzahl - 1) {
			Main.explosionscounter = 0;
		}

		Main.explosionscounter++;
		Main.feld[x_field][y_field].belegt = false;
		System.out.println(x_field + " " + y_field);
		existent = false;
		counter = 0;
		// System.out.println(existent);

	}
}
