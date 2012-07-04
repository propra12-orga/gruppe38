package gruppe38;

import gruppe38.Items.Ausgang;
import gruppe38.Items.Bombe;
import gruppe38.Items.BombeSp2;
import gruppe38.Items.Explosion;
import gruppe38.Items.FeuerItem;
import gruppe38.Items.RadiusItem;
import gruppe38.Spieler.Spieler;
import gruppe38.Spielfeld.Spielfeld2;
import gruppe38.Tests.Feldwiedergabe;

import java.awt.Color;

/**
 * @author Gruppe38
 * 
 * Initialisierungen der Mauern, Bomben, Items, etc.
 */
public class Init extends Main {

	/**
	 * Initialisierungen der Mauern, Bomben, Items, etc.
	 */

	public static void init() {

		setBombencounter(0);// z�hlt die anzahl der bomben
		setSp1(new Spieler(.5, .5, "wurst", 1, 4));
		setSp2(new Spieler(.5, .5, "wurst2", 1, 4));
		setExplosionscounter(0);
		int ausgangX=Main.randomnumber(5, 10);
		int ausgangY=Main.randomnumber(5, 10);

		// Init der Bomben beim Start-Menu
		for (int i = 0; i < 10; i++) {
			getBild_x()[i] = Math.random();
			getBild_y()[i] = Math.random();
			getBild_vx()[i] = Math.random() / 100;
			getBild_vy()[i] = Math.random() / 100;
		}

		// Initialisierungen der Powerups
		for (int i = 0; i < getBombenanzahl(); i++) {
			getBombe()[i] = new Bombe(.0, .0, false, new Feldwiedergabe(), 0,
					"bombe", 1);
			getBombe2()[i] = new BombeSp2(.0, .0, false, new Feldwiedergabe(), 0, "bombe2", 1);
			getExplosion()[i] = new Explosion(.0, .0, 0, 0, false, "explosion");
		}

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

		for (int i = 0; i < getBombenanzahl(); i++) {
			getW1()[i] = getSpielfeldgroesse() / 2;
			getW2()[i] = getSpielfeldgroesse() / 2;
			getH1()[i] = getSpielfeldgroesse() / 2;
			getH2()[i] = getSpielfeldgroesse() / 2;
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
						getFeld()[i][i2].beinhaltet = RadiusItem.getName();
						getFeld()[i][i2].belegt = true;
					}
				}
				if (getFeld()[i][i2].beinhaltet.equals("nothing")) {
					if (randomnumber(0, 5) == 3) {
						getFeld()[i][i2].beinhaltet = FeuerItem.getName();
						getFeld()[i][i2].belegt = true;
					}
				}
				//Ausgang
				if (getFeld()[ausgangX][ausgangY].beinhaltet.equals("mauer")){
				getFeld()[ausgangX+1][ausgangY].beinhaltet = "exit";
				getFeld()[ausgangX+1][ausgangY].belegt = true;
				}
				else{
					getFeld()[ausgangX+1][ausgangY].beinhaltet = "exit";
					getFeld()[ausgangX+1][ausgangY].belegt = true;
				}
					
			}
		}
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
		// initialisierung der Spielerposition
		getSp1().setX(getFeld()[1][1].x);
		getSp1().setY(getFeld()[1][1].y);

		getSp2().setX(getFeld()[spielfelder-2][spielfelder-2].x);
		getSp2().setY(getFeld()[spielfelder-2][spielfelder-2].y);
		
		for (int i = 0; i < getBombenanzahl(); i++) {
			if (getExplosion()[i].isAlive())
				getExplosion()[i].interrupt();
			if (getBombe()[i].isAlive())
				getBombe()[i].interrupt();
			// if()

		}

	}
}
