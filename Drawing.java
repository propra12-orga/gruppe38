package gruppe38;

public class Drawing extends Main {
	public static double w1[] = new double[Main.bombenanzahl];
	public static double w2[] = new double[Main.bombenanzahl];
	public static double h1[] = new double[Main.bombenanzahl];
	public static double h2[] = new double[Main.bombenanzahl];

	static boolean malen = false;

	// initialisierung der Width und Height des Bildes der Explosionsgrafik
	public static void init() {
		for (int i = 0; i < bombenanzahl; i++) {
			w1[i] = spielfeldgröße / 2;
			w2[i] = spielfeldgröße / 2;
			h1[i] = spielfeldgröße / 2;
			h2[i] = spielfeldgröße / 2;
		}

		// initialiserung des Spielfeldes

		for (int i = 0; i < spielfelder; i++) {
			for (int i2 = 0; i2 < spielfelder; i2++) {
				if (i == spielfelder - spielfelder)
					feld[i][i2].beinhaltet = "spawn";
				else if (i == spielfelder)
					feld[i][i2].beinhaltet = "spawn";
				else if (i2 == spielfelder - spielfelder)
					feld[i][i2].beinhaltet = "spawn";
				else if (i2 == spielfelder)
					feld[i][i2].beinhaltet = "spawn";
				if (!feld[i][i2].beinhaltet.equals("spawn")) {

					if (randomnumber(0, 5) == 1) {
						feld[i][i2].beinhaltet = "explosiv";
						feld[i][i2].belegt = true;
					}
					if (randomnumber(0, 5) == 3) {
						feld[i][i2].beinhaltet = "feuer";
						feld[i][i2].belegt = true;
					}
				}
			}
		}
		// initialisierung der Spielerposition
		sp1.x = feld[0][0].x;
		sp1.y = feld[0][0].y;
	}
	public static void draw() {
		StdDraw.picture(.5, .5, "background.png");

		// spielfeld malen
		for (int i = 0; i < spielfelder; i++) {
			for (int i2 = 0; i2 < spielfelder; i2++) {

				// StdDraw.setPenColor(Main.farbe[i][i2]);
				if (malen) {
					// StdDraw.filledSquare(spielfeldgröße * i + spielfeldgröße
					// / 2, spielfeldgröße * i2 + spielfeldgröße / 2,
					// spielfeldgröße / 2);
					if (feld[i][i2].beinhaltet.equals("feuer")) {
						StdDraw.picture(feld[i][i2].x, feld[i][i2].y,
								feld[i][i2].beinhaltet + ".gif", spielfeldgröße
										- spielfeldgröße / 10, spielfeldgröße
										- spielfeldgröße / 10);
					}
					if (feld[i][i2].beinhaltet.equals("explosiv")) {
						StdDraw.picture(feld[i][i2].x, feld[i][i2].y,
								feld[i][i2].beinhaltet + ".png", spielfeldgröße
										- spielfeldgröße / 10, spielfeldgröße
										- spielfeldgröße / 10);
					}
				}
			}
		}
		StdDraw.setPenColor(StdDraw.BLACK);

		// bomben malen
		for (int i = 0; i < bombenanzahl; i++) {

			if (bombe[i].existent == true) {
				StdDraw.picture(bombe[i].x, bombe[i].y, "bombe.gif",
						spielfeldgröße - spielfeldgröße / 10, spielfeldgröße
								- spielfeldgröße / 10);

			}
			if (!explosion[i].isAlive()) {
				w1[i] = 0;
				h2[i] = 0;
			}
			if (!bombe[i].isAlive() & explosion[i].existent == true
					& explosion[i].isAlive()) {

				w1[i] += (double) 1 / spielfelder + (double) 1 / spielfelder
						* sp1.explosions_stärke / 4;
				h2[i] += (double) 1 / spielfelder + (double) 1 / spielfelder
						* sp1.explosions_stärke / 4;
				if (w1[i] > (double) 1 / spielfelder + (double) 1 / spielfelder
						* sp1.explosions_stärke * 2)
					w1[i] = (double) 1 / spielfelder + (double) 1 / spielfelder
							* sp1.explosions_stärke * 2;
				if (h2[i] > (double) 1 / spielfelder + (double) 1 / spielfelder
						* sp1.explosions_stärke * 2)
					h2[i] = (double) 1 / spielfelder + (double) 1 / spielfelder
							* sp1.explosions_stärke * 2;

				StdDraw.picture(bombe[i].x, bombe[i].y, "explosion.png", w1[i],
						h1[i]);
				StdDraw.picture(bombe[i].x, bombe[i].y, "explosion.png", w2[i],
						h2[i]);
				// System.out.println(w1[i] + " " + h2[i]);

				for (int i2 = 0; i2 < bombenanzahl; i2++) {
					// ABFRAGE OB ZERSTÖRBARE BOMBEN IN DER NÄHE SIND
					BombenTest test = new BombenTest(i, i2);
					test.run();

				}

				for (int i3 = 0; i3 < spielfelder; i3++) {
					for (int i4 = 0; i4 < spielfelder; i4++) {
						// ABFRAGE OB ZERSTÖRBARE ITEMS IN DER NÄHE SIND
						ItemTest test2 = new ItemTest(i, i3, i4);
						test2.run();

					}

				}

			}

		}
		StdDraw.filledCircle(sp1.x, sp1.y, spielfeldgröße / 2.25);
		malen = true;

	}
}
