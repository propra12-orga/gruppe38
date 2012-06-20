package gruppe38.Tests;

import gruppe38.Drawing;
import gruppe38.Main;

/**
 * Items werden zerstört, wenn sie vom Spieler oder einer Explosion getroffen wurden
 * @author Gruppe38
 *
 */

public class ItemTest {
	int i;
	int i3;
	int i4;
	public ItemTest(int i5, int i7, int i8) {
		i = i5;
		i3 = i7;
		i4 = i8;

	}
	/**
	 * Selbe Prinzip wie mit der Bombenkettenreaktion! Nur auf Items bezogen. Diese werden dann einfach "zerstoert", d.h. das Feld wird leer gemacht
	 */
	public void test() {
		// GEGENSTANDSTEST: fuer explosiv auf der Y-Achse

		if (ExplosionsCheck.ExplosionsCheck(Main.getBombe()[i].getY(),
				Main.getFeld()[i3][i4].y, Drawing.getH2()[i],
				Main.getFeld()[i3][i4].belegt,
				Main.getFeld()[i3][i4].beinhaltet).equals("explosiv")
				& Main.getBombe()[i].getX() == Main.getFeld()[i3][i4].x
				|| ExplosionsCheck.ExplosionsCheck(Main.getBombe()[i].getY(),
						Main.getFeld()[i3][i4].y, Drawing.getH2()[i],
						Main.getFeld()[i3][i4].belegt,
						Main.getFeld()[i3][i4].beinhaltet).equals("feuer")
				& Main.getBombe()[i].getX() == Main.getFeld()[i3][i4].x) {
			Main.getFeld()[i3][i4].beinhaltet = "nothing";
			Main.getFeld()[i3][i4].belegt = false;
		}
		//bOMBE2
		if (ExplosionsCheck.ExplosionsCheck(Main.getBombe2()[i].getY(),
				Main.getFeld()[i3][i4].y, Drawing.getH2()[i],
				Main.getFeld()[i3][i4].belegt,
				Main.getFeld()[i3][i4].beinhaltet).equals("explosiv")
				& Main.getBombe2()[i].getX() == Main.getFeld()[i3][i4].x
				|| ExplosionsCheck.ExplosionsCheck(Main.getBombe2()[i].getY(),
						Main.getFeld()[i3][i4].y, Drawing.getH2()[i],
						Main.getFeld()[i3][i4].belegt,
						Main.getFeld()[i3][i4].beinhaltet).equals("feuer")
				& Main.getBombe2()[i].getX() == Main.getFeld()[i3][i4].x) {
			Main.getFeld()[i3][i4].beinhaltet = "nothing";
			Main.getFeld()[i3][i4].belegt = false;
		}
		// if (ExplosionsCheck.ExplosionsCheck(Main.getBombe()[i].getY(),
		// Main.getFeld()[i3][i4].y, Drawing.h2[i],
		// Main.getFeld()[i3][i4].belegt,
		// Main.getFeld()[i3][i4].beinhaltet).equals("feuer")
		// & Main.getBombe()[i].getX() == Main.getFeld()[i3][i4].x) {
		// Main.getFeld()[i3][i4].beinhaltet = "nothing";
		// Main.getFeld()[i3][i4].belegt = false;
		// }
		if (ExplosionsCheck.ExplosionsCheck(Main.getBombe()[i].getY(),
				Main.getFeld()[i3][i4].y, Drawing.getH2()[i],
				Main.getFeld()[i3][i4].belegt,
				Main.getFeld()[i3][i4].beinhaltet).equals("mauer_destroyable")
				& Main.getBombe()[i].getX() == Main.getFeld()[i3][i4].x) {
			Main.getFeld()[i3][i4].beinhaltet = "nothing";
			Main.getFeld()[i3][i4].belegt = false;

			// int random = Main.randomnumber(0, 5);
			// if (random == 1) {
			// Main.getFeld()[i3][i4].beinhaltet = "explosiv";
			// Main.getFeld()[i3][i4].belegt = true;
			// }
			// if (random == 3) {
			// Main.getFeld()[i3][i4].beinhaltet = "feuer";
			// Main.getFeld()[i3][i4].belegt = true;
			// }

		}
		//Bombe2
		if (ExplosionsCheck.ExplosionsCheck(Main.getBombe2()[i].getY(),
				Main.getFeld()[i3][i4].y, Drawing.getH2()[i],
				Main.getFeld()[i3][i4].belegt,
				Main.getFeld()[i3][i4].beinhaltet).equals("mauer_destroyable")
				& Main.getBombe2()[i].getX() == Main.getFeld()[i3][i4].x) {
			Main.getFeld()[i3][i4].beinhaltet = "nothing";
			Main.getFeld()[i3][i4].belegt = false;

			// int random = Main.randomnumber(0, 5);
			// if (random == 1) {
			// Main.getFeld()[i3][i4].beinhaltet = "explosiv";
			// Main.getFeld()[i3][i4].belegt = true;
			// }
			// if (random == 3) {
			// Main.getFeld()[i3][i4].beinhaltet = "feuer";
			// Main.getFeld()[i3][i4].belegt = true;
			// }

		}

		// GEGENSTANDSTEST: f�r explosiv auf der Y-Achse
		if (ExplosionsCheck.ExplosionsCheck(Main.getBombe()[i].getX(),
				Main.getFeld()[i3][i4].x, Drawing.getW1()[i],
				Main.getFeld()[i3][i4].belegt,
				Main.getFeld()[i3][i4].beinhaltet).equals("explosiv")
				& Main.getBombe()[i].getY() == Main.getFeld()[i3][i4].y
				|| ExplosionsCheck.ExplosionsCheck(Main.getBombe()[i].getX(),
						Main.getFeld()[i3][i4].x, Drawing.getW1()[i],
						Main.getFeld()[i3][i4].belegt,
						Main.getFeld()[i3][i4].beinhaltet).equals("feuer")
				& Main.getBombe()[i].getY() == Main.getFeld()[i3][i4].y) {
			Main.getFeld()[i3][i4].beinhaltet = "nothing";
			Main.getFeld()[i3][i4].belegt = false;
		}
		// if (ExplosionsCheck.ExplosionsCheck(Main.getBombe()[i].getX(),
		// Main.getFeld()[i3][i4].x, Drawing.w1[i],
		// Main.getFeld()[i3][i4].belegt,
		// Main.getFeld()[i3][i4].beinhaltet).equals("feuer")
		// & Main.getBombe()[i].getY() == Main.getFeld()[i3][i4].y) {
		// Main.getFeld()[i3][i4].beinhaltet = "nothing";
		// Main.getFeld()[i3][i4].belegt = false;
		// }
		//Bombe2
		if (ExplosionsCheck.ExplosionsCheck(Main.getBombe2()[i].getX(),
				Main.getFeld()[i3][i4].x, Drawing.getW1()[i],
				Main.getFeld()[i3][i4].belegt,
				Main.getFeld()[i3][i4].beinhaltet).equals("explosiv")
				& Main.getBombe2()[i].getY() == Main.getFeld()[i3][i4].y
				|| ExplosionsCheck.ExplosionsCheck(Main.getBombe2()[i].getX(),
						Main.getFeld()[i3][i4].x, Drawing.getW1()[i],
						Main.getFeld()[i3][i4].belegt,
						Main.getFeld()[i3][i4].beinhaltet).equals("feuer")
				& Main.getBombe2()[i].getY() == Main.getFeld()[i3][i4].y) {
			Main.getFeld()[i3][i4].beinhaltet = "nothing";
			Main.getFeld()[i3][i4].belegt = false;
		}
		// if (ExplosionsCheck.ExplosionsCheck(Main.getBombe()[i].getX(),
		// Main.getFeld()[i3][i4].x, Drawing.w1[i],
		// Main.getFeld()[i3][i4].belegt,
		// Main.getFeld()[i3][i4].beinhaltet).equals("feuer")
		// & Main.getBombe()[i].getY() == Main.getFeld()[i3][i4].y) {
		// Main.getFeld()[i3][i4].beinhaltet = "nothing";
		// Main.getFeld()[i3][i4].belegt = false;
		// }

		if (ExplosionsCheck.ExplosionsCheck(Main.getBombe()[i].getX(),
				Main.getFeld()[i3][i4].x, Drawing.getW1()[i],
				Main.getFeld()[i3][i4].belegt,
				Main.getFeld()[i3][i4].beinhaltet).equals("mauer_destroyable")
				& Main.getBombe()[i].getY() == Main.getFeld()[i3][i4].y) {
			Main.getFeld()[i3][i4].beinhaltet = "nothing";
			Main.getFeld()[i3][i4].belegt = false;

			// int random = Main.randomnumber(0, 5);
			// if (random == 1) {
			// Main.getFeld()[i3][i4].beinhaltet = "explosiv";
			// Main.getFeld()[i3][i4].belegt = true;
			// }
			// if (random == 3) {
			// Main.getFeld()[i3][i4].beinhaltet = "feuer";
			// Main.getFeld()[i3][i4].belegt = true;
			// }

		}
		
		//Bombe2
		if (ExplosionsCheck.ExplosionsCheck(Main.getBombe2()[i].getX(),
				Main.getFeld()[i3][i4].x, Drawing.getW1()[i],
				Main.getFeld()[i3][i4].belegt,
				Main.getFeld()[i3][i4].beinhaltet).equals("mauer_destroyable")
				& Main.getBombe2()[i].getY() == Main.getFeld()[i3][i4].y) {
			Main.getFeld()[i3][i4].beinhaltet = "nothing";
			Main.getFeld()[i3][i4].belegt = false;

			// int random = Main.randomnumber(0, 5);
			// if (random == 1) {
			// Main.getFeld()[i3][i4].beinhaltet = "explosiv";
			// Main.getFeld()[i3][i4].belegt = true;
			// }
			// if (random == 3) {
			// Main.getFeld()[i3][i4].beinhaltet = "feuer";
			// Main.getFeld()[i3][i4].belegt = true;
			// }

		}

	}

}
