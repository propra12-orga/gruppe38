package gruppe38.Tests;

import gruppe38.Drawing;
import gruppe38.Main;

/*
 * Hiermit wird abgefragt, ob eine bombe im explosionsradius befindet. wenn ja, unterbreche den sleep im jeweiligen bomben-thread! damit wir eine explosion aufgerufen
 */

public class BombenTest {
	int i = 0;
	int i2 = 0;

	public BombenTest(int i3, int i4) {
		i = i3;
		i2 = i4;
	}

	public void test() {

		// ABFRAGE DER GEGENST�NDE BEI EXPLOSION

		// BOMBENTEST:
		// BOMBENTEST: f�r Bomben auf der X-Achse
		if (ExplosionsCheck.ExplosionsCheck(Main.getBombe()[i].getX(),
				Main.getBombe()[i2].getX(), Drawing.w1[i],
				Main.getBombe()[i2].isExistent(),
				Main.getBombe()[i2].getObjekt_typ()).equals("bombe")
				& Main.getBombe()[i2].getY() == Main.getBombe()[i].getY()
				& Main.getBombe()[i2].isAlive()
				|| ExplosionsCheck.ExplosionsCheck(Main.getBombe()[i].getY(),
						Main.getBombe()[i2].getY(), Drawing.h2[i],
						Main.getBombe()[i2].isExistent(),
						Main.getBombe()[i2].getObjekt_typ()).equals("bombe")
				& Main.getBombe()[i2].getX() == Main.getBombe()[i].getX()
				& Main.getBombe()[i2].isAlive()) {

			System.out.println("BombeNr.:" + Main.explosionscounter
					+ " Status:"
					+ Main.explosion[Main.explosionscounter].isAlive());

			if (Main.getBombe()[i2].isAlive()) {
				Main.getBombe()[i2].interrupt();
			}

			Main.getBombe()[i2].setExplosionscounter_check(false);
			Main.explosionscounter++;

		}

		// // BOMBENTEST: f�r Bomben auf der Y-Achse
		// if (ExplosionsCheck.ExplosionsCheck(Main.getBombe()[i].getY(),
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
