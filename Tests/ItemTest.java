package gruppe38.Tests;

import gruppe38.Drawing;
import gruppe38.Main;

/*
 * Selbe Prinzip wie mit der Bombenkettenreaktion! nur auf items bezogen. diese werden dann einfach "zerstört", d.h. das feld wird leer gemacht
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

	public void test() {
		// GEGENSTANDSTEST: fï¿½r explosiv auf der Y-Achse

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

		// GEGENSTANDSTEST: fï¿½r explosiv auf der Y-Achse
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

	}

}
