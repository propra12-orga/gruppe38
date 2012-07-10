package gruppe38.Spieler;

import gruppe38.Main;
import gruppe38.Items.Bombe;
import gruppe38.Tests.FeldCheck;
import gruppe38.Tests.Feldwiedergabe;

/**
 * Steuerung der Spielfigur2
 * 
 * @author Gruppe38
 * 
 */

public class SteuerungSp2 extends Main {

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

		if (isLeft2()) {
			if (getSp2().getX() < 0 + getFeld()[0][0].size / 2.25 + 0.015)
				getSp2().setX(0 + getFeld()[0][0].size / 2.25);
			else
				getSp2().setX(
						getSp2().getX() - 0.015 * getSp2().getSpeed() / 4
								/ (getSpielfelder() / 12));
		}
		if (isRight2()) {
			if (getSp2().getX() > 1 - getFeld()[0][0].size / 2.25 - 0.015)
				getSp2().setX(1 - getFeld()[0][0].size / 2.25);
			else
				getSp2().setX(
						getSp2().getX() + 0.015 * getSp2().getSpeed() / 4
								/ (getSpielfelder() / 12));
		}
		if (isDown2()) {
			if (getSp2().getY() < 0 + getFeld()[0][0].size / 2.25 + 0.015)
				getSp2().setY(0 + getFeld()[0][0].size / 2.25);
			else
				getSp2().setY(
						getSp2().getY() - 0.015 * getSp2().getSpeed() / 4
								/ (getSpielfelder() / 12));
		}
		if (isUp2()) {
			if (getSp2().getY() > 1 - getFeld()[0][0].size / 2.25 - 0.015)
				getSp2().setY(1 - getFeld()[0][0].size / 2.25);
			else
				getSp2().setY(
						getSp2().getY() + 0.015 * getSp2().getSpeed() / 4
								/ (getSpielfelder() / 12));
		}

		/*
		 * wenn Tab gedr�ckt wird:
		 * 
		 * 1)gecheckt, ob der bombencounter gleich 479 ist, wenn ja counter=0;
		 * 
		 * 2)neue Bombe wird im Thread erstellt. vorher wird gepr�ft auf welchem
		 * feld sich der spieler sich befinden, damit die bombe dort plaziert
		 * wird
		 * 
		 * 3)counter wird um eins erweitert
		 * 
		 * 4)space boolean wird auf false gesetzt, damit der befehl nochmal
		 * aufgerufen werden kann
		 */
		if (isSpace2()) {

			if (getBombencounter() == getBombenanzahl() - 1)
				setBombencounter(0);
			// FeldCheck.check(Sp2.getX(), Sp2.getY());

			// bombennummer des spieler2 wird erhoeht
			Feldwiedergabe fw = new Feldwiedergabe();
			fw = FeldCheck.check(getSp2().getX(), getSp2().getY());

			double xWert = getFeld()[fw.getX()][fw.getY()].x;
			double yWert = getFeld()[fw.getX()][fw.getY()].y;

			if (getFeld()[fw.getX()][fw.getY()].belegt == false
					& getSp2().getBombenanzahlcounter() < getSp2()
							.getMaxbombenanzahl()) {
				getBombe()[getBombencounter()] = new Bombe(xWert, yWert, true,
						FeldCheck.check(getSp2().getX(), getSp2().getY()),
						getBombencounter(), "bombe", getSp2());
				getBombe()[getBombencounter()].start();
				getFeld()[getX_feld()][getY_feld()].beinhaltet = "bombe";
				getFeld()[getX_feld()][getY_feld()].belegt = true;
				setBombencounter(getBombencounter() + 1);
				getSp2().setBombenanzahlcounter(
						getSp2().getBombenanzahlcounter() + 1);

			}
			setSpace2(false);
		}

	}
}
