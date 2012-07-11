package gruppe38.Items;

import gruppe38.Main;
import gruppe38.Spieler.Spieler;
import gruppe38.Tests.SpielerTest;

/**
 * Explosionsthread
 * 
 * @author Gruppe38
 * 
 */

public class Explosion extends Thread {

	public String bombe = "explosion.png";
	double x = 1;
	double y = 1;
	private boolean existent = false;
	int x_feld;
	int y_feld;
	String objekt_typ;
	Spieler sp;

	/**
	 * Erzeugt die Explosion
	 * 
	 * @param x_coor
	 *            x-wert des Zentrums
	 * @param y_coor
	 *            y-wert des Zentrums
	 * @param x_field
	 *            x-wert des Feldes
	 * @param y_field
	 *            y-wert des Feldes
	 * @param bool
	 *            Existiert
	 * @param objekt_typ_eingabe
	 *            Objekttyp
	 */
	public Explosion(double x_coor, double y_coor, int x_field, int y_field,
			boolean bool, String objekt_typ_eingabe, Spieler spieler) {
		x = x_coor;
		y = y_coor;
		setExistent(bool);
		x_feld = x_field;
		y_feld = y_field;
		objekt_typ = objekt_typ_eingabe;
		sp = spieler;
	}

	/**
	 * wartet 0,3 sek bis zur Explosion
	 * 
	 * @throws InterruptedException
	 */
	public void run() {
		Main.soundlib.playSound("bombe");
		try {
			Thread.sleep(300);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		setExistent(false);
		// Testen, ob ein Spieler getroffen wird
		// for (int i = 0; i < Main.getBombenanzahl(); i++) {
		// if (Main.getBombe()[i].isExistent()) {
		SpielerTest killSp1 = new SpielerTest(this, sp, Main.getSp1());
		SpielerTest killSp2 = new SpielerTest(this, sp, Main.getSp2());
		// Spieler2Test killSp3 = new Spieler2Test(Main.getBombe2()[i],
		// Main.getSp1());
		// Spieler2Test killSp4 = new Spieler2Test(Main.getBombe2()[i],
		// Main.getSp2());

		killSp1.test();
		killSp2.test();
		// killSp3.test();
		// killSp4.test();
		// }
		// }

	}

	public boolean isExistent() {
		return existent;
	}

	public void setExistent(boolean existent) {
		this.existent = existent;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

}
