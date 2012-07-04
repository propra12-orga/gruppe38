package gruppe38.Editor;

import gruppe38.Spielfeld.Spielfeld2;

import java.awt.Color;

/**
 * @author Gruppe38
 * 
 *         Initialisierungen der Mauern, Bomben, Items, etc.
 */
public class InitEditor extends Editor {

	/**
	 * Initialisierungen der Mauern, Bomben, Items, etc.
	 */

	public static void init() {

		// Ersteinteilung des Spielfeldes mit RandomFarbe
		for (int i = 0; i < getSpielfelder(); i++) {
			for (int i2 = 0; i2 < getSpielfelder(); i2++) {
				getFeld()[i][i2] = new Spielfeld2(getSpielfeldgroesse() * i
						+ getSpielfeldgroesse() / 2, getSpielfeldgroesse() * i2
						+ getSpielfeldgroesse() / 2, getSpielfeldgroesse(),
						"nothing", false);
				getFarbe()[i][i2] = new Color(randomnumber(0, 255),
						randomnumber(0, 255), randomnumber(0, 255));
			}
		}

		/*
		 * Reset des Spielfeldes
		 */
		for (int i = 0; i < getSpielfelder(); i++) {
			for (int i2 = 0; i2 < getSpielfelder(); i2++) {
				getFeld()[i][i2].mauer = false;
				getFeld()[i][i2].beinhaltet = "nothing";
				getFeld()[i][i2].belegt = false;

			}
		}

	}
	public static void random() {

		// Ersteinteilung des Spielfeldes mit RandomFarbe
		for (int i = 0; i < getSpielfelder(); i++) {
			for (int i2 = 0; i2 < getSpielfelder(); i2++) {
				getFeld()[i][i2] = new Spielfeld2(getSpielfeldgroesse() * i
						+ getSpielfeldgroesse() / 2, getSpielfeldgroesse() * i2
						+ getSpielfeldgroesse() / 2, getSpielfeldgroesse(),
						"nothing", false);
				getFarbe()[i][i2] = new Color(randomnumber(0, 255),
						randomnumber(0, 255), randomnumber(0, 255));

			}
		}

		/*
		 * Reset des Spielfeldes
		 */
		for (int i = 0; i < getSpielfelder(); i++) {
			for (int i2 = 0; i2 < getSpielfelder(); i2++) {
				getFeld()[i][i2].mauer = false;
				getFeld()[i][i2].beinhaltet = "nothing";
				getFeld()[i][i2].belegt = false;

			}
		}

		/*
		 * Initialisierung des Spielfeldes mit Powerups, Mauer und Spielerspawns
		 */

		getFeld()[1][1].beinhaltet = "spawn";

		getFeld()[1][(getSpielfelder() - 2)].beinhaltet = "spawn";

		getFeld()[(getSpielfelder() - 2)][1].beinhaltet = "spawn";

		getFeld()[(getSpielfelder() - 2)][(getSpielfelder() - 2)].beinhaltet = "spawn";

		for (int i = 0; i < getSpielfelder(); i++) {
			for (int i2 = 0; i2 < getSpielfelder(); i2++) {

				// Mauern
				if (i == 0 || i == getSpielfelder() - 1 || i2 == 0
						|| i2 == getSpielfelder() - 1) {
					getFarbe()[i][i2] = new Color(0, 0, 0);
					getFeld()[i][i2].mauer = true;
					getFeld()[i][i2].beinhaltet = "mauer";
				}

				if (i > 1 & i2 > 1 & i < (getSpielfelder() - 2)
						& i2 < (getSpielfelder() - 2) & i != 0 & i2 != 0
						& i != getSpielfelder() & i2 != getSpielfelder()
						& (i) % 2 == 0 & (i2) % 2 == 0) {
					getFeld()[i][i2].mauer = true;
					getFeld()[i][i2].beinhaltet = "mauer";
					getFarbe()[i][i2] = new Color(0, 0, 0);
				}

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
			clearSpawn();
		}

	}
	public static void clearSpawn() {
		for (int i = 1; i < getSpielfelder() - 1; i++) {
			for (int i2 = 1; i2 < getSpielfelder() - 1; i2++) {
				if (getFeld()[i - 1][i2].beinhaltet.equals("spawn")
						|| getFeld()[i][i2 - 1].beinhaltet.equals("spawn")
						|| getFeld()[i + 1][i2].beinhaltet.equals("spawn")
						|| getFeld()[i][i2 + 1].beinhaltet.equals("spawn")) {
					getFeld()[i][i2].belegt = false;
					getFeld()[i][i2].beinhaltet = "nothing";
				}

			}
		}
	}
}
