package gruppe38.Items;

import gruppe38.Main;
import gruppe38.Tests.BombenTest2;
import gruppe38.Tests.Feldwiedergabe;

/**
 * Bombenthread
 * @author Gruppe 38
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
//	int rad;

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
	
	/**
	 * Initialisierung der Bomben
	 * @param x_coor x
	 * @param y_coor y
	 * @param bool Existiert die Bombe
	 * @param fc Speilfeld
	 * @param bombenindexeingabe Bombennummer
	 * @param objekt_typ_eingabe Objekttyp
	 * @param spieler Besitzer
	 */

	public Bombe(double x_coor, double y_coor, boolean bool, Feldwiedergabe fc,
			int bombenindexeingabe, String objekt_typ_eingabe, int spielerNum/*,Spieler spieler*/) {
		setX(x_coor);
		setY(y_coor);
		this.spieler = spielerNum;
		setExistent(bool);

		x_field = fc.getX();
		y_field = fc.getY();
		setObjekt_typ(objekt_typ_eingabe);
		bombenindex = bombenindexeingabe;
//		rad = spieler.getExplosions_staerke();
		System.out.println(getX()+" "+getY());
	}
	/**
	 * Laesst die Bommbe 3 sek warten, bevor sie Explodiert
	 * @throws InterruptedException
	 */

	public void run() {

		try {
			Thread.sleep(3000); // warte 3 sekunden
		} catch (InterruptedException e) {

			// TODO Auto-generated catch block
			e.getCause();
		}

		// TEST: wenn explosionsthread hier ankommt, starte explosion

		if (!Main.getExplosion()[bombenindex].isAlive()) {
			Main.getExplosion()[bombenindex] = new Explosion(getX(), getY(),
					x_field, y_field, true, "explosion");
			Main.getExplosion()[bombenindex].start();

		}

		// erhoehe den explosionscounter um 1, f�r die naechste initialisierung
		// einer bombe, sofern eine explosion zugelassen ist. siehe zeile 59
		if (isExplosionscounter_check())
			Main.setExplosionscounter(Main.getExplosionscounter() + 1);

		if (Main.getExplosionscounter() == Main.getBombenanzahl() - 1) {
			Main.setExplosionscounter(0);
		}

		Main.getSp1().setBombenanzahlcounter(Main.getSp1().getBombenanzahlcounter() - 1);
		BombenTest2 test2 = new BombenTest2(x_field,y_field);		
		test2.run();
		
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
	
//	public int getRadius(){
//		return rad;
//	}
}
