package gruppe38.Tests;

import gruppe38.Drawing;
import gruppe38.Main;

/**
 * Hiermit wird Abgefragt, ob eine Bombe im explosionsradius befindet. Wenn ja, unterbreche den sleep im jeweiligen bomben-thread! Damit wir eine Explosion aufgerufen
 * @author Gruppe38
 */

public class BombenTest {
	int i = 0;
	int i2 = 0;

	public BombenTest(int i3, int i4) {
		i = i3;
		i2 = i4;
	}

	/**
	 *  ABFRAGE DER GEGENSTAENDE BEI EXPLOSION
	 */

	public void run() {


		// BOMBENTEST:
		// BOMBENTEST: f�r Bomben auf der X-Achse

		if (ExplosionsCheck.ExplosionsCheck(Main.getBombe()[i].getX(),
				Main.getBombe()[i2].getX(), Drawing.getW1()[i],
				Main.getBombe()[i2].isExistent(),
				Main.getBombe()[i2].getObjekt_typ()).equals("bombe")
				& Main.getBombe()[i2].getY() == Main.getBombe()[i].getY()
				& Main.getBombe()[i2].isAlive()
				|| ExplosionsCheck.ExplosionsCheck(Main.getBombe()[i].getY(),
						Main.getBombe()[i2].getY(), Drawing.getH2()[i],
						Main.getBombe()[i2].isExistent(),
						Main.getBombe()[i2].getObjekt_typ()).equals("bombe")
				& Main.getBombe()[i2].getX() == Main.getBombe()[i].getX()
				& Main.getBombe()[i2].isAlive()) {

			System.out.println("BombeNr.:"
					+ Main.getExplosionscounter()
					+ " Status:"
					+ Main.getExplosion()[Main.getExplosionscounter()]
							.isAlive());

			if (Main.getBombe()[i2].isAlive()) {
				Main.getBombe()[i2].interrupt();
			}

			Main.getBombe()[i2].setExplosionscounter_check(false);
			Main.setExplosionscounter(Main.getExplosionscounter() + 1);


		}
		if (ExplosionsCheck.ExplosionsCheck(Main.getBombe2()[i].getX(),
				Main.getBombe2()[i2].getX(), Drawing.getW1()[i],
				Main.getBombe2()[i2].isExistent(),
				Main.getBombe2()[i2].getObjekt_typ()).equals("bombe")
				& Main.getBombe2()[i2].getY() == Main.getBombe2()[i].getY()
				& Main.getBombe2()[i2].isAlive()
				|| ExplosionsCheck.ExplosionsCheck(Main.getBombe2()[i].getY(),
						Main.getBombe2()[i2].getY(), Drawing.getH2()[i],
						Main.getBombe2()[i2].isExistent(),
						Main.getBombe2()[i2].getObjekt_typ()).equals("bombe")
				& Main.getBombe2()[i2].getX() == Main.getBombe2()[i].getX()
				& Main.getBombe2()[i2].isAlive()) {

			System.out.println("Bombe2Nr.:"
					+ Main.getExplosionscounter()
					+ " Status:"
					+ Main.getExplosion()[Main.getExplosionscounter()]
							.isAlive());

			if (Main.getBombe2()[i2].isAlive()) {
				Main.getBombe2()[i2].interrupt();
			}

			Main.getBombe2()[i2].setExplosionscounter_check(false);
			Main.setExplosionscounter(Main.getExplosionscounter() + 1);
		}

	// // BOMBENTEST: f�r Bomben auf der Y-Achse
	// if
	// (ExplosionsCheck.ExplosionsCheck(Main.getBombe()[i].getY(),
	// Main.getBombe()[i2].getY(), Drawing.h2[i],
	// Main.getBombe()[i2].isExistent(),
	// Main.getBombe()[i2].getObjekt_typ()).equals("bombe")
	// & Main.getBombe()[i2].getX() == Main.getBombe()[i].getX()
	// & Main.getBombe()[i2].isAlive()) {
	//
	// System.out.println("BombeNr.:" + Main.explosionscounter
	// + " Status:"
	// + Main.explosion[Main.explosionscounter].isAlive());
	//
	// if (Main.getBombe()[i2].isAlive()) {
	// Main.getBombe()[i2].interrupt();
	// }
	//
	// Main.getBombe()[i2].setExplosionscounter_check(false);
	// Main.explosionscounter++;
	//
	// }

}
}
