package gruppe38.Spieler;

import gruppe38.Main;
import gruppe38.Items.Bombe;
import gruppe38.Tests.FeldCheck;
import gruppe38.Tests.Feldwiedergabe;

public class Steuerung extends Main {

	public static void aktionen() {

		/*
		 * Steuerung, StdDraw wurde bearbeitet in Zeile 1363. KeyListener des
		 * Frames wurde ben�tigt. Wurde einer Pfeiltaste oder iwas gedr�ckt,
		 * boolean=true, Abfrage hier: kommt "Spieler"-Radius an Rand, setzte
		 * Spieler auf: Position = Rand - Spieler-Radius
		 */

		if (left) {
			if (sp1.getX() < 0 + getFeld()[0][0].size / 2.25 + 0.015)
				sp1.setX(0 + getFeld()[0][0].size / 2.25);
			else
				sp1.setX(sp1.getX() - 0.015 * sp1.getSpeed() / 4
						/ (spielfelder / 12));
		}
		if (right) {
			if (sp1.getX() > 1 - getFeld()[0][0].size / 2.25 - 0.015)
				sp1.setX(1 - getFeld()[0][0].size / 2.25);
			else
				sp1.setX(sp1.getX() + 0.015 * sp1.getSpeed() / 4
						/ (spielfelder / 12));
		}
		if (down) {
			if (sp1.getY() < 0 + getFeld()[0][0].size / 2.25 + 0.015)
				sp1.setY(0 + getFeld()[0][0].size / 2.25);
			else
				sp1.setY(sp1.getY() - 0.015 * sp1.getSpeed() / 4
						/ (spielfelder / 12));
		}
		if (up) {
			if (sp1.getY() > 1 - getFeld()[0][0].size / 2.25 - 0.015)
				sp1.setY(1 - getFeld()[0][0].size / 2.25);
			else
				sp1.setY(sp1.getY() + 0.015 * sp1.getSpeed() / 4
						/ (spielfelder / 12));
		}

		/*
		 * wenn Leertaste gedr�ckt wird:
		 * 
		 * 1)gecheckt, ob der bombencounter gleich 479 ist, wenn ja counter=0;
		 * 
		 * 2)neue Bombe wird im Thread erstellt. vorher wird gepr�ft auf
		 * welchem feld sich der spieler sich befinden, damit die bombe dort
		 * plaziert wird
		 * 
		 * 3)counter wird um eins erweitert
		 * 
		 * 4)space boolean wird auf false gesetzt, damit der befehl nochmal
		 * aufgerufen werden kann
		 */
		if (space) {

			if (bombencounter == bombenanzahl - 1)
				bombencounter = 0;
			// FeldCheck.check(sp1.getX(), sp1.getY());

			// bombennummer des spieler1 wird erhoeht
			Feldwiedergabe fw = new Feldwiedergabe();
			fw = FeldCheck.check(sp1.getX(), sp1.getY());

			if (getFeld()[fw.getX()][fw.getY()].belegt == false
					& sp1.getBombenanzahlcounter() < sp1.getMaxbombenanzahl()) {
				getBombe()[bombencounter] = new Bombe(bombe_x, bombe_y, true,
						FeldCheck.check(sp1.getX(), sp1.getY()), bombencounter,
						"bombe", 1);
				getBombe()[bombencounter].start();
				getFeld()[x_feld][y_feld].beinhaltet = "bombe";
				getFeld()[x_feld][y_feld].belegt = true;
				bombencounter++;
				sp1.setBombenanzahlcounter(sp1.getBombenanzahlcounter() + 1);

			}
			space = false;
		}

	}
}
