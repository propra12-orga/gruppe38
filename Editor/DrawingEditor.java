package gruppe38.Editor;

import gruppe38.Bilder.BilderInit;

/**
 * Malen des Spielfeldes
 * 
 * @author Gruppe38
 * 
 */
public class DrawingEditor extends Editor {

	static boolean malen = false;
	static BilderInit pic = new BilderInit();

	/**
	 * Malt das Speilfeld mit Items,Bomben, Mauern und Spielern
	 */

	public static void draw() {
		init();
		/*
		 * Spielfeld mit den farben und der Powerups malen.
		 */

		for (int i = 0; i < getSpielfelder(); i++) {
			for (int i2 = 0; i2 < getSpielfelder(); i2++) {

				if (malen) {
					if (getFeld()[i][i2].beinhaltet.equals("nothing")) {
						StdDraw.setPenColor(getFarbe()[i][i2]);
						StdDraw.offscreen
								.fillRect(
										(int) ((double) StdDraw.height
												* getFeld()[i][i2].x - getSpielfeldgroesse()
												/ 2 * StdDraw.width),
										(int) ((double) StdDraw.width
												* (1.0 - getFeld()[i][i2].y) - getSpielfeldgroesse()
												/ 2 * StdDraw.width),
										(int) (getSpielfeldgroesse() * (double) StdDraw.height),
										(int) (getSpielfeldgroesse() * (double) StdDraw.width));
					}

					// Spawnfeld malen
					if (getFeld()[i][i2].beinhaltet.equals("spawn")) {
						StdDraw.offscreen
								.drawImage(
										pic.spawn,
										(int) ((double) StdDraw.height
												* getFeld()[i][i2].x - getSpielfeldgroesse()
												/ 2 * StdDraw.width),
										(int) ((double) StdDraw.width
												* (1.0 - getFeld()[i][i2].y) - getSpielfeldgroesse()
												/ 2 * StdDraw.width),
										(int) (getSpielfeldgroesse() * (double) StdDraw.height),
										(int) (getSpielfeldgroesse() * (double) StdDraw.width),
										null);

					}

					// abfrage des Explosionspowerups
					if (getFeld()[i][i2].beinhaltet.equals("bombe_energie")) {
						StdDraw.offscreen
								.drawImage(
										pic.feuer,
										(int) ((double) StdDraw.height
												* getFeld()[i][i2].x - getSpielfeldgroesse()
												/ 2 * StdDraw.width),
										(int) ((double) StdDraw.width
												* (1.0 - getFeld()[i][i2].y) - getSpielfeldgroesse()
												/ 2 * StdDraw.width),
										(int) (getSpielfeldgroesse() * (double) StdDraw.height),
										(int) (getSpielfeldgroesse() * (double) StdDraw.width),
										null);

					}
					// Abfrage des Bombenanzahlpowerups
					if (getFeld()[i][i2].beinhaltet.equals("bombe_extra")) {
						StdDraw.offscreen
								.drawImage(
										pic.explosiv,
										(int) (((double) StdDraw.height * getFeld()[i][i2].x) - getSpielfeldgroesse()
												/ 2 * StdDraw.width),
										(int) (((double) StdDraw.width * (1.0 - getFeld()[i][i2].y)) - getSpielfeldgroesse()
												/ 2 * StdDraw.width),
										(int) (getSpielfeldgroesse() * (double) StdDraw.height),
										(int) (getSpielfeldgroesse() * (double) StdDraw.width),
										null);
					}
					// Abfrage des Bombenanzahlpowerups
					if (getFeld()[i][i2].beinhaltet.equals("mauer")) {
						StdDraw.offscreen
								.drawImage(
										pic.cake,
										(int) (((double) StdDraw.height * getFeld()[i][i2].x) - getSpielfeldgroesse()
												/ 2 * StdDraw.width),
										(int) (((double) StdDraw.width * (1.0 - getFeld()[i][i2].y)) - getSpielfeldgroesse()
												/ 2 * StdDraw.width),
										(int) (getSpielfeldgroesse() * (double) StdDraw.height),
										(int) (getSpielfeldgroesse() * (double) StdDraw.width),
										null);
					}
					// Abfrage der kaputtbaren Mauern
					if (getFeld()[i][i2].beinhaltet.equals("mauer_destroyable")) {
						StdDraw.offscreen
								.drawImage(
										pic.armor,
										(int) (((double) StdDraw.height * getFeld()[i][i2].x) - getSpielfeldgroesse()
												/ 2 * StdDraw.width),
										(int) (((double) StdDraw.width * (1.0 - getFeld()[i][i2].y)) - getSpielfeldgroesse()
												/ 2 * StdDraw.width),
										(int) (getSpielfeldgroesse() * (double) StdDraw.height),
										(int) (getSpielfeldgroesse() * (double) StdDraw.width),
										null);
					}
				}
			}
		}

		malen = true;

	}
	static void init() {
		// TODO Auto-generated method stub
		pic.mauer = pic.loadPics("gruppe38/Bilder/mauer.png", 1)[0];
		pic.spawn = pic.loadPics("gruppe38/Bilder/spawn.png", 1)[0];
		// pic.bombe = pic.loadPics("background.jpg", 1)[0];
		// pic.explosion = pic.loadPics("gruppe38/Bilder/feuer.gif", 1)[0];
		// pic.bombe_extra = pic
		// .loadPics("src/gruppe38/Bilder/bombe_extra.png", 1)[0];
		// pic.bombe_energie = pic.loadPics(
		// "src/gruppe38/Bilder/bombe_energie.png", 1)[0];
		// pic.mauer_destroyable = pic.loadPics("pics/background.jpg", 1)[0];
		pic.armor = pic.loadPics("gruppe38/Bilder/SB_Armor.png", 1)[0];
		pic.cake = pic.loadPics("gruppe38/Bilder/SB_Cake.gif", 1)[0];
		pic.explosiv = pic.loadPics("gruppe38/Bilder/bombe_extra.png", 1)[0];
		pic.feuer = pic.loadPics("gruppe38/Bilder/bombe_energie.png", 1)[0];
	}
}
