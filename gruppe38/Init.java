package gruppe38;

import gruppe38.Items.Bombe;
import gruppe38.Items.Explosion;
import gruppe38.Spieler.Spieler;
import gruppe38.Spielfeld.Spielfeld2;
import gruppe38.Tests.Feldwiedergabe;

import java.awt.Color;

public class Init extends Main {

	/**
	 * Initialisierungen
	 */

	public static void init() {

		bombencounter = 0;// zaehlt die anzahl der bomben
		sp1 = new Spieler(.5, .5, "wurst", 1, 4);
		explosionscounter = 0;

		// Init der Bomben beim Start-Menu
		for (int i = 0; i < 10; i++) {
			bild_x[i] = Math.random();
			bild_y[i] = Math.random();
			bild_vx[i] = Math.random() / 100;
			bild_vy[i] = Math.random() / 100;
		}

		// Initialisierungen der Powerups
		for (int i = 0; i < bombenanzahl; i++) {
			getBombe()[i] = new Bombe(.0, .0, false, new Feldwiedergabe(), 0,
					"bombe", 1);
			explosion[i] = new Explosion(.0, .0, 0, 0, false, "explosion");
		}

		// Ersteinteilung des Spielfeldes mit RandomFarbe
		for (int i = 0; i < spielfelder; i++) {
			for (int i2 = 0; i2 < spielfelder; i2++) {
				getFeld()[i][i2] = new Spielfeld2(spielfeldgroesse * i
						+ spielfeldgroesse / 2, spielfeldgroesse * i2
						+ spielfeldgroesse / 2, spielfeldgroesse, "nothing",
						false);
				farbe[i][i2] = new Color(randomnumber(0, 255), randomnumber(0,
						255), randomnumber(0, 255));

			}
		}

		for (int i = 0; i < bombenanzahl; i++) {
			w1[i] = spielfeldgroesse / 2;
			w2[i] = spielfeldgroesse / 2;
			h1[i] = spielfeldgroesse / 2;
			h2[i] = spielfeldgroesse / 2;
		}
		/*
		 * Reset des Spielfeldes
		 */
		for (int i = 0; i < spielfelder; i++) {
			for (int i2 = 0; i2 < spielfelder; i2++) {
				getFeld()[i][i2].mauer = false;
				getFeld()[i][i2].beinhaltet = "nothing";
				getFeld()[i][i2].belegt = false;

			}
		}

		/*
		 * Initialisierung des Spielfeldes mit Powerups, Mauer und Spielerspawns
		 */

		getFeld()[1][1].beinhaltet = "spawn";

		getFeld()[1][(spielfelder - 2)].beinhaltet = "spawn";

		getFeld()[(spielfelder - 2)][1].beinhaltet = "spawn";

		getFeld()[(spielfelder - 2)][(spielfelder - 2)].beinhaltet = "spawn";

		for (int i = 0; i < spielfelder; i++) {
			for (int i2 = 0; i2 < spielfelder; i2++) {
				// Mauern
				if (i == 0 || i == spielfelder - 1 || i2 == 0
						|| i2 == spielfelder - 1) {
					farbe[i][i2] = new Color(0, 0, 0);
					getFeld()[i][i2].mauer = true;
					getFeld()[i][i2].beinhaltet = "mauer";
				}

				if (i > 1 & i2 > 1 & i < (spielfelder - 2)
						& i2 < (spielfelder - 2) & i != 0 & i2 != 0
						& i != spielfelder & i2 != spielfelder & (i) % 2 == 0
						& (i2) % 2 == 0) {
					getFeld()[i][i2].mauer = true;
					getFeld()[i][i2].beinhaltet = "mauer";
					farbe[i][i2] = new Color(0, 0, 0);
				}

				// Spawns

				// Items Mauern
				if (getFeld()[i][i2].beinhaltet.equals("nothing")) {
					if (randomnumber(0, 2) == 1) {
						getFeld()[i][i2].beinhaltet = "mauer_destroyable";
						getFeld()[i][i2].belegt = true;
					}
				}
				if (getFeld()[i][i2].beinhaltet.equals("nothing")) {

					if (randomnumber(0, 5) == 1) {
						getFeld()[i][i2].beinhaltet = "explosiv";
						getFeld()[i][i2].belegt = true;
					}
				}
				if (getFeld()[i][i2].beinhaltet.equals("nothing")) {
					if (randomnumber(0, 5) == 3) {
						getFeld()[i][i2].beinhaltet = "feuer";
						getFeld()[i][i2].belegt = true;
					}
				}

			}
		}
		for (int i = 1; i < spielfelder - 1; i++) {
			for (int i2 = 1; i2 < spielfelder - 1; i2++) {
				if (getFeld()[i - 1][i2].beinhaltet.equals("spawn")
						|| getFeld()[i][i2 - 1].beinhaltet.equals("spawn")
						|| getFeld()[i + 1][i2].beinhaltet.equals("spawn")
						|| getFeld()[i][i2 + 1].beinhaltet.equals("spawn")) {
					getFeld()[i][i2].belegt = false;
					getFeld()[i][i2].beinhaltet = "nothing";
				}

			}
		}
		// initialisierung der Spielerposition
		sp1.setX(getFeld()[1][1].x);
		sp1.setY(getFeld()[1][1].y);

		for (int i = 0; i < bombenanzahl; i++) {
			if (explosion[i].isAlive())
				explosion[i].interrupt();
			if (getBombe()[i].isAlive())
				getBombe()[i].interrupt();
			// if()

		}

	}
}
