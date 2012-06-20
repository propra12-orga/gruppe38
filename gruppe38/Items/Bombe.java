package gruppe38.Items;

import gruppe38.Main;
import gruppe38.Tests.Feldwiedergabe;

/**
 * Definiert alles Eigenschaften einer Bombe
 * 
 * @author gruppe38
 *
 *
 */

public class Bombe extends Thread {

	private double x;
	private double y;
	private boolean existent;
	int x_field;
	int y_field;
	private String objekt_typ;
	private boolean explosionscounter_check = true;
	int bombenindex;
	int spieler;

	// public Bombe(double x_coor, double y_coor, boolean bool, int x_feld,
	// int y_feld, int bombenindexeingabe, String objekt_typ_eingabe,
	// int spieler) {
	// setX(x_coor);
	// setY(y_coor);
	// this.spieler = spieler;
	// setExistent(bool);
	// x_field = x_feld;
	// y_field = y_feld;
	// setObjekt_typ(objekt_typ_eingabe);
	// bombenindex = bombenindexeingabe;
	// }

	public Bombe(double x_coor, double y_coor, boolean bool, Feldwiedergabe fc,
			int bombenindexeingabe, String objekt_typ_eingabe, int spieler) {
		setX(x_coor);
		setY(y_coor);
		this.spieler = spieler;
		setExistent(bool);

		x_field = fc.getX();
		y_field = fc.getY();
		setObjekt_typ(objekt_typ_eingabe);
		bombenindex = bombenindexeingabe;
	}

	/**
	 * Laesst die Bombe 3 Sekunden warten, bevor sie Explodiert
	 * 
	 * @throws InterrutedExeption
	 */
	
	public void run() {

		try {
			Thread.sleep(3000); // warte 3 sekunden
		} catch (InterruptedException e) {

			// TODO Auto-generated catch block
			e.getCause();
		}

		// TEST: wenn explosionsthread hier ankommt, starte explosion

		if (!Main.explosion[bombenindex].isAlive()) {
			Main.explosion[bombenindex] = new Explosion(getX(), getY(),
					x_field, y_field, true, "explosion");
			Main.explosion[bombenindex].start();

		}

		// erhoehe den explosionscounter um 1, fÜr die naechste initialisierung
		// einer bombe, sofern eine explosion zugelassen ist. siehe zeile 59
		if (isExplosionscounter_check())
			Main.explosionscounter++;

		if (Main.explosionscounter == Main.bombenanzahl - 1) {
			Main.explosionscounter = 0;
		}

		Main.sp1.setBombenanzahlcounter(Main.sp1.getBombenanzahlcounter() - 1);
		setExplosionscounter_check(true);
		Main.getFeld()[x_field][y_field].belegt = false;
		Main.getFeld()[x_field][y_field].beinhaltet = "nothing";
		setExistent(false);

		return;

	}

	public boolean isExistent() {
		return existent;
	}

	public void setExistent(boolean existent) {
		this.existent = existent;
	}

	public boolean isExplosionscounter_check() {
		return explosionscounter_check;
	}

	public void setExplosionscounter_check(boolean explosionscounter_check) {
		this.explosionscounter_check = explosionscounter_check;
	}

	public String getObjekt_typ() {
		return objekt_typ;
	}

	public void setObjekt_typ(String objekt_typ) {
		this.objekt_typ = objekt_typ;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}
}
