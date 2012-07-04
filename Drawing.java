package gruppe38;

import gruppe38.Bilder.BilderInit;
import gruppe38.Sonstiges.StdDraw;
import gruppe38.Tests.BombenTest;
import gruppe38.Tests.ItemTest;

import java.awt.Color;

/**
 * Malen des Spielfeldes
 * 
 * @author Gruppe38
 * 
 */
public class Drawing extends Main {

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

					// Spawnfeld malen
					if (getFeld()[i][i2].beinhaltet.equals("spawn")) {
						StdDraw.setPenColor(new Color(119, 34, 8));
						StdDraw.filledSquare(getSpielfeldgroesse() * i
								+ getSpielfeldgroesse() / 2,
								getSpielfeldgroesse() * i2
										+ getSpielfeldgroesse() / 2,
								getSpielfeldgroesse() / 2);

						// StdDraw.filledSquare(getFeld()[i][i2].x
						// + getSpielfeldgroesse() / 2, getFeld()[i][i2].y
						// + getSpielfeldgroesse() / 2,
						// getSpielfeldgroesse() / 2);
					}

					// abfrage des Explosionspowerups
					if (getFeld()[i][i2].beinhaltet.equals("feuer")) {
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

						// StdDraw.picture(getFeld()[i][i2].x,
						// getFeld()[i][i2].y,
						// "../Bilder/" + getFeld()[i][i2].beinhaltet
						// + ".gif", getSpielfeldgroesse()
						// - getSpielfeldgroesse() / 10,
						// getSpielfeldgroesse() - getSpielfeldgroesse()
						// / 10);

					}
					// Abfrage des Bombenanzahlpowerups
					if (getFeld()[i][i2].beinhaltet.equals("explosiv")) {
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

						// StdDraw.picture(getFeld()[i][i2].x,
						// getFeld()[i][i2].y,
						// "../Bilder/" + getFeld()[i][i2].beinhaltet
						// + ".png", getSpielfeldgroesse()
						// - getSpielfeldgroesse() / 10,
						// getSpielfeldgroesse() - getSpielfeldgroesse()
						// / 10);
					}
					// Abfrage des Bombenanzahlpowerups
					if (getFeld()[i][i2].mauer) {
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
						// StdDraw.picture(getFeld()[i][i2].x,
						// getFeld()[i][i2].y,
						// "../Bilder/mauer.png", getSpielfeldgroesse(),
						// getSpielfeldgroesse());
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
						// StdDraw.picture(getFeld()[i][i2].x,
						// getFeld()[i][i2].y,
						// "../Bilder/" + getFeld()[i][i2].beinhaltet
						// + ".png", getSpielfeldgroesse(),
						// getSpielfeldgroesse());
					}
				}
			}
		}

		// bomben malen
		for (int i = 0; i < getBombenanzahl(); i++) {

			// wenn aufgerufen bombe existiert, male sie
			if (getBombe()[i].isExistent() == true) {
				StdDraw.picture(getBombe()[i].getX(), getBombe()[i].getY(),
						"../Bilder/bombe.gif", getSpielfeldgroesse()
								- getSpielfeldgroesse() / 10,
						getSpielfeldgroesse() - getSpielfeldgroesse() / 10);

			}

			if (getBombe2()[i].isExistent() == true) {
				StdDraw.picture(getBombe2()[i].getX(), getBombe2()[i].getY(),
						"../Bilder/bombe.gif", getSpielfeldgroesse()
								- getSpielfeldgroesse() / 10,
						getSpielfeldgroesse() - getSpielfeldgroesse() / 10);

			}
			// wenn der explosionsthread nicht existiert, setze die dehnung des
			// explosionsbildes auf 0
			if (!getExplosion()[i].isAlive()) {
				getW1()[i] = 0;
				getH2()[i] = 0;
			}

			// wenn eine explosion existiert, dehne das explosionsbild in x und
			// y richtung gleichermassen
			if (!getBombe()[i].isAlive()
					& getExplosion()[i].isExistent() == true
					& getExplosion()[i].isAlive()) {

				getW1()[i] += (double) 1 / getSpielfelder() + (double) 1
						/ getSpielfelder() * getSp1().getExplosions_staerke()
						/ 4;
				getH2()[i] += (double) 1 / getSpielfelder() + (double) 1
						/ getSpielfelder() * getSp1().getExplosions_staerke()
						/ 4;
				getHdraw()[i] = (double) 1 / getSpielfelder() + (double) 1
						/ getSpielfelder() / 2;

				if (getW1()[i] > (double) 1 / getSpielfelder() + (double) 1
						/ getSpielfelder() * getSp1().getExplosions_staerke()
						* 2)
					getW1()[i] = (double) 1 / getSpielfelder() + (double) 1
							/ getSpielfelder()
							* getSp1().getExplosions_staerke() * 2;
				if (getH2()[i] > (double) 1 / getSpielfelder() + (double) 1
						/ getSpielfelder() * getSp1().getExplosions_staerke()
						* 2)
					getH2()[i] = (double) 1 / getSpielfelder() + (double) 1
							/ getSpielfelder()
							* getSp1().getExplosions_staerke() * 2;

				StdDraw.picture(getBombe()[i].getX(), getBombe()[i].getY(),
						"../Bilder/explosion2.png", getW1()[i],
						getHdraw()[i] / 2);
				StdDraw.picture(getBombe()[i].getX(), getBombe()[i].getY(),
						"../Bilder/explosion2.png", getW1()[i],
						getHdraw()[i] / 2, 90);

			}

			if (!getBombe2()[i].isAlive()
					& getExplosion()[i].isExistent() == true
					& getExplosion()[i].isAlive()) {

				getW1()[i] += (double) 1 / getSpielfelder() + (double) 1
						/ getSpielfelder() * getSp2().getExplosions_staerke()
						/ 4;
				getH2()[i] += (double) 1 / getSpielfelder() + (double) 1
						/ getSpielfelder() * getSp2().getExplosions_staerke()
						/ 4;
				getHdraw()[i] = (double) 1 / getSpielfelder() + (double) 1
						/ getSpielfelder() / 2;

				if (getW1()[i] > (double) 1 / getSpielfelder() + (double) 1
						/ getSpielfelder() * getSp2().getExplosions_staerke()
						* 2)
					getW1()[i] = (double) 1 / getSpielfelder() + (double) 1
							/ getSpielfelder()
							* getSp2().getExplosions_staerke() * 2;
				if (getH2()[i] > (double) 1 / getSpielfelder() + (double) 1
						/ getSpielfelder() * getSp2().getExplosions_staerke()
						* 2)
					getH2()[i] = (double) 1 / getSpielfelder() + (double) 1
							/ getSpielfelder()
							* getSp2().getExplosions_staerke() * 2;

				StdDraw.picture(getBombe2()[i].getX(), getBombe2()[i].getY(),
						"../Bilder/explosion2.png", getW1()[i],
						getHdraw()[i] / 2);

				StdDraw.picture(getBombe2()[i].getX(), getBombe2()[i].getY(),
						"../Bilder/explosion2.png", getW1()[i],
						getHdraw()[i] / 2, 90);

			}

			for (int i2 = 0; i2 < getBombenanzahl(); i2++) {
				// ABFRAGE OB ZERST�RBARE BOMBEN IN DER N�HE SIND
				BombenTest test = new BombenTest(i, i2);
				test.run();
			}

			for (int i3 = 0; i3 < getSpielfelder(); i3++) {
				for (int i4 = 0; i4 < getSpielfelder(); i4++) {
					// ABFRAGE OB ZERST�RBARE ITEMS IN DER N�HE SIND
					ItemTest test2 = new ItemTest(i, i3, i4);
					test2.test();
				}
			}

		}

		// malen des Spielers1
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.filledCircle(getSp1().getX(), getSp1().getY(), getSp1()
				.getRadius());

		// malen des Spielers1
		StdDraw.setPenColor(StdDraw.YELLOW);
		StdDraw.filledCircle(getSp2().getX(), getSp2().getY(), getSp2()
				.getRadius());
		malen = true;

	}
	private static void init() {
		// TODO Auto-generated method stub
		pic.mauer = pic.loadPics("gruppe38/Bilder/mauer.png", 1)[0];
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
