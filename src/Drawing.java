public class Drawing extends Main {
	public static double w1[] = new double[bombenanzahl];
	public static double w2[] = new double[bombenanzahl];
	public static double h1[] = new double[bombenanzahl];
	public static double h2[] = new double[bombenanzahl];
	

	static boolean malen = false;

	// initialisierung der Width und Height des Bildes der Explosionsgrafik
	public static void init() {
		for (int i = 0; i < bombenanzahl; i++) {
			w1[i] = spielfeldgröße / 2;
			w2[i] = spielfeldgröße / 2;
			h1[i] = spielfeldgröße / 2;
			h2[i] = spielfeldgröße / 2;
		}
	}

	public static void draw() {

		// spielfeld malen
		for (int i = 0; i < spielfelder; i++) {
			for (int i2 = 0; i2 < spielfelder; i2++) {

				StdDraw.setPenColor(Main.farbe[i][i2]);
				if (malen) {
					StdDraw.filledSquare(spielfeldgröße * i + spielfeldgröße
							/ 2, spielfeldgröße * i2 + spielfeldgröße / 2,
							spielfeldgröße / 2);
					StdDraw.setPenColor(StdDraw.BLACK);
				}
			}
		}
		// bomben malen
		int counter = 0;
		for (int i = 0; i < bombenanzahl; i++) {

			if (bombe[i].existent == true) {
				StdDraw.picture(bombe[i].x, bombe[i].y, "bombe.gif");

			}
			if (bombe[i].existent == false & explosion[i].existent == true) {

				StdDraw.picture(bombe[i].x, bombe[i].y, "explosion.png", w1[i],
						h1[i]);
				StdDraw.picture(bombe[i].x, bombe[i].y, "explosion.png", w2[i],
						h2[i]);
				w1[i] += spielfeldgröße / 4;
				h2[i] += spielfeldgröße / 4;
				// Test, falls Explosion noch den True-Status hat und setzt
				// Dehnung des Bildes auf 0
				if (w1[i] > spielfeldgröße / 4 * bombenanzahl
						* sp1.explosions_stärke) {
					w1[i] = 0.000001;
					h2[i] = 0.000001;
					explosion[i].existent = false;
					System.out.println(i);

				}

				// for (int i2 = 0; i2 < bombenanzahl; i2++) {
				// if (ExplosionsCheck.ExplosionsCheck(bombe[i].x,
				// bombe[i2].x, w1[i], bombe[i2].existent,
				// bombe[i2].objekt_typ).equals("bombe")) {
				// System.out.println("bombe");
				// counter++;
				//
				// }
				//
				// }

			}
			// for (int i3 = Main.bombencounter - counter; i3 <
			// Main.bombencounter; i3++) {
			// if (bombe[Main.bombencounter - i3].isAlive()) {
			// bombe[Main.bombencounter - i3].counter = bombe[Main.bombencounter
			// - i3].bombendauer - 100;
			// }
			// }

		}

		StdDraw.filledCircle(sp1.x, sp1.y, spielfeldgröße / 2.25);
		malen = true;
	}
}
