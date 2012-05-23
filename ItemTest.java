package gruppe38;
/*
 * Selbe Prinzip wie mit der Bombenkettenreaktion! nur auf items bezogen. diese werden dann einfach "zerstört", d.h. das feld leer gemacht
 */

public class ItemTest extends Thread {
	int i;
	int i3;
	int i4;
	public ItemTest(int i5, int i7, int i8) {
		i = i5;
		i3 = i7;
		i4 = i8;

	}

	public void run() {
		if (ExplosionsCheck.ExplosionsCheck(Main.bombe[i].x,
				Main.feld[i3][i4].x, Drawing.w1[i], Main.feld[i3][i4].belegt,
				Main.feld[i3][i4].beinhaltet).equals("feuer")
				& Main.bombe[i].y == Main.feld[i3][i4].y) {
			Main.feld[i3][i4].beinhaltet = "nothing";
			Main.feld[i3][i4].belegt = false;
		}

		// GEGENSTANDSTEST: fï¿½r explosiv auf der Y-Achse
		// System.out.println(Main.bombe[i].x + " " + Main.feld[i3][i4].x + " "
		// + Main.feld[i3][i4].belegt);

		if (ExplosionsCheck.ExplosionsCheck(Main.bombe[i].y,
				Main.feld[i3][i4].y, Drawing.h2[i], Main.feld[i3][i4].belegt,
				Main.feld[i3][i4].beinhaltet).equals("feuer")
				& Main.bombe[i].x == Main.feld[i3][i4].x) {
			Main.feld[i3][i4].beinhaltet = "nothing";
			Main.feld[i3][i4].belegt = false;
		}
		if (ExplosionsCheck.ExplosionsCheck(Main.bombe[i].x,
				Main.feld[i3][i4].x, Drawing.w1[i], Main.feld[i3][i4].belegt,
				Main.feld[i3][i4].beinhaltet).equals("explosiv")
				& Main.bombe[i].y == Main.feld[i3][i4].y) {
			Main.feld[i3][i4].beinhaltet = "nothing";
			Main.feld[i3][i4].belegt = false;
		}

		// GEGENSTANDSTEST: fï¿½r explosiv auf der Y-Achse
		// System.out.println(Main.bombe[i].x + " " + Main.feld[i3][i4].x + " "
		// + Main.feld[i3][i4].belegt);

		if (ExplosionsCheck.ExplosionsCheck(Main.bombe[i].y,
				Main.feld[i3][i4].y, Drawing.h2[i], Main.feld[i3][i4].belegt,
				Main.feld[i3][i4].beinhaltet).equals("explosiv")
				& Main.bombe[i].x == Main.feld[i3][i4].x) {
			Main.feld[i3][i4].beinhaltet = "nothing";
			Main.feld[i3][i4].belegt = false;
		}

	}

}
