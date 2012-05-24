package gruppe38;

/*
 * Hiermit wird abgefragt, ob eine bombe im explosionsradius befindet. wenn ja, unterbreche den sleep im jeweiligen bomben-thread! damit wir eine explosion aufgerufen
 */

public class BombenTest extends Thread {
	int i = 0;
	int i2 = 0;

	public BombenTest(int i3, int i4) {
		i = i3;
		i2 = i4;
	}

	public void run() {

		// ABFRAGE DER GEGENST�NDE BEI EXPLOSION

		// BOMBENTEST:
		// BOMBENTEST: f�r Bomben auf der X-Achse
		if (ExplosionsCheck.ExplosionsCheck(Main.bombe[i].x, Main.bombe[i2].x,
				Drawing.w1[i], Main.bombe[i2].existent,
				Main.bombe[i2].objekt_typ).equals("bombe")
				& Main.bombe[i2].y == Main.bombe[i].y
				& Main.bombe[i2].isAlive()) {
			Main.sp1.bombenanzahlcounter--;

			System.out.println("BombeNr.:" + Main.explosionscounter
					+ " Status:"
					+ Main.explosion[Main.explosionscounter].isAlive());

			if (Main.bombe[i2].isAlive()) {
				Main.bombe[i2].interrupt();
			}

			Main.bombe[i2].explosionscounter_check = false;
			Main.explosionscounter++;

		}

		// BOMBENTEST: f�r Bomben auf der Y-Achse
		if (ExplosionsCheck.ExplosionsCheck(Main.bombe[i].y, Main.bombe[i2].y,
				Drawing.h2[i], Main.bombe[i2].existent,
				Main.bombe[i2].objekt_typ).equals("bombe")
				& Main.bombe[i2].x == Main.bombe[i].x
				& Main.bombe[i2].isAlive()) {

			System.out.println("BombeNr.:" + Main.explosionscounter
					+ " Status:"
					+ Main.explosion[Main.explosionscounter].isAlive());

			if (Main.bombe[i2].isAlive()) {
				Main.bombe[i2].interrupt();
			}

			Main.bombe[i2].explosionscounter_check = false;
			Main.explosionscounter++;

		}

	}

}
