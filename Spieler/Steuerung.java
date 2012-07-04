package gruppe38.Spieler;

import gruppe38.Main;
import gruppe38.Items.Bombe;
import gruppe38.Tests.FeldCheck;
import gruppe38.Tests.Feldwiedergabe;
/**
 * Steuerung der Spielfigur
 * @author Gruppe38
 *
 */

public class Steuerung extends Main {

	/**
	 * Bei einem Tastendruck werden die entsprechenden Parameter veraendert
	 */
	public static void aktionen() {

		/*
		 * Steuerung, StdDraw wurde bearbeitet in Zeile 1363. KeyListener des
		 * Frames wurde ben�tigt. Wurde einer Pfeiltaste oder iwas gedr�ckt,
		 * boolean=true, Abfrage hier: kommt "Spieler"-Radius an Rand, setzte
		 * Spieler auf: Position = Rand - Spieler-Radius
		 */

		if (isLeft()) {
			if (getSp1().getX() < 0 + getFeld()[0][0].size / 2.25 + 0.015)
				getSp1().setX(0 + getFeld()[0][0].size / 2.25);
			else
				getSp1().setX(getSp1().getX() - 0.015 * getSp1().getSpeed() / 4
						/ (getSpielfelder() / 12));
		}
		if (isRight()) {
			if (getSp1().getX() > 1 - getFeld()[0][0].size / 2.25 - 0.015)
				getSp1().setX(1 - getFeld()[0][0].size / 2.25);
			else
				getSp1().setX(getSp1().getX() + 0.015 * getSp1().getSpeed() / 4
						/ (getSpielfelder() / 12));
		}
		if (isDown()) {
			if (getSp1().getY() < 0 + getFeld()[0][0].size / 2.25 + 0.015)
				getSp1().setY(0 + getFeld()[0][0].size / 2.25);
			else
				getSp1().setY(getSp1().getY() - 0.015 * getSp1().getSpeed() / 4
						/ (getSpielfelder() / 12));
		}
		if (isUp()) {
			if (getSp1().getY() > 1 - getFeld()[0][0].size / 2.25 - 0.015)
				getSp1().setY(1 - getFeld()[0][0].size / 2.25);
			else
				getSp1().setY(getSp1().getY() + 0.015 * getSp1().getSpeed() / 4
						/ (getSpielfelder() / 12));
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
		if (isSpace()) {

			if (getBombencounter() == getBombenanzahl() - 1)
				setBombencounter(0);
			// FeldCheck.check(sp1.getX(), sp1.getY());

			// bombennummer des spieler1 wird erhoeht
			Feldwiedergabe fw = new Feldwiedergabe();
			fw = FeldCheck.check(getSp1().getX(), getSp1().getY());

			if (getFeld()[fw.getX()][fw.getY()].belegt == false
					& getSp1().getBombenanzahlcounter() < getSp1().getMaxbombenanzahl()) {
				getBombe()[getBombencounter()] = new Bombe(getSp1().getX(),getSp1().getY(), true,
						FeldCheck.check(getSp1().getX(), getSp1().getY()), getBombencounter(),
						"bombe", 1);
				getBombe()[getBombencounter()].start();
				getFeld()[getX_feld()][getY_feld()].beinhaltet = "bombe";
				getFeld()[getX_feld()][getY_feld()].belegt = true;
				setBombencounter(getBombencounter() + 1);
				getSp1().setBombenanzahlcounter(getSp1().getBombenanzahlcounter() + 1);
				getFeld()[getX_feld()][getY_feld()].bomben_number=getBombencounter();

			}
			setSpace(false);
		}

	}
}
