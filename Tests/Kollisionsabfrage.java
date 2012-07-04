package gruppe38.Tests;

import gruppe38.Main;
import gruppe38.Spieler.Spieler;

/**
 * Kollisionsabfrage mit Mauern, Bomben und Items
 * @author Gruppe38
 *
 */
public class Kollisionsabfrage extends Main {
/**
 * Pruefe mit Mauern und Bomben
 */
	public static void mauer(Spieler sp) {
		Spieler spieler = sp;
		/*
		 * kollisionsabfrage mit den Mauern und Bomben
		 * 
		 * Hier wird jedes Feld einzeln gepr�ft, ob es eine Mauer oder Bombe ist
		 * und ob es in der N�he des Spielers ist. Naeheres steht bei jeder
		 * Abfrage
		 */

		for (int i = 0; i < getSpielfelder(); i++) {
			for (int i2 = 0; i2 < getSpielfelder(); i2++) {
				/*
				 * oben nach unten wird abgefragt:
				 * 
				 * 
				 * 1)ist spielermitte in y-Richtung kleiner als die mitte des
				 * abgefragten spielfeldes plus halbe spielfeldgroesse plus
				 * spielerradius
				 * 
				 * 2)liegt spielermitte wirklich �berhalb des abgefragten
				 * spielfeldes
				 * 
				 * 3)&4) einschraenkung der spielerposition durch die breite des
				 * spielfeldes in x-Richtungen
				 */
				if (getFeld()[i][i2].mauer
						& spieler.getY() < getFeld()[i][i2].y + getSpielfeldgroesse() / 2
								+ spieler.getRadius()

						& spieler.getY() > getFeld()[i][i2].y

						& spieler.getX() > getFeld()[i][i2].x - getSpielfeldgroesse() / 2
						& spieler.getX() < getFeld()[i][i2].x + getSpielfeldgroesse() / 2

						|| getFeld()[i][i2].beinhaltet.equals("bombe")
						& spieler.getY() < getFeld()[i][i2].y + getSpielfeldgroesse() / 2
								+ spieler.getRadius()

						& spieler.getY() > getFeld()[i][i2].y

						& spieler.getX() > getFeld()[i][i2].x - getSpielfeldgroesse() / 2
						& spieler.getX() < getFeld()[i][i2].x + getSpielfeldgroesse() / 2
						|| getFeld()[i][i2].beinhaltet.equals("mauer_destroyable")
						& spieler.getY() < getFeld()[i][i2].y + getSpielfeldgroesse() / 2
								+ spieler.getRadius()

						& spieler.getY() > getFeld()[i][i2].y

						& spieler.getX() > getFeld()[i][i2].x - getSpielfeldgroesse() / 2
						& spieler.getX() < getFeld()[i][i2].x + getSpielfeldgroesse() / 2) {
					System.out.println("Oben nach unten");
					spieler.setY(getFeld()[i][i2].y + getSpielfeldgroesse() / 2
							+ spieler.getRadius());
				}
				/*
				 * unten nach oben wird abgefragt:
				 * 
				 * 
				 * 1)ist spielermitte y-Richtung gr��er als die mitte des
				 * abgefragten spielfeldes minus halbe spielfeldgroesse minus
				 * spielerradius
				 * 
				 * 2)liegt spielermitte wirklich unterhalb des abgefragten
				 * spielfeldes
				 * 
				 * 3)&4) einschraenkung der spielerposition durch die breite des
				 * spielfeldes in x-Richtungen
				 */
				if (getFeld()[i][i2].mauer
						& spieler.getY() > getFeld()[i][i2].y - getSpielfeldgroesse() / 2
								- spieler.getRadius()

						& spieler.getY() < getFeld()[i][i2].y

						& spieler.getX() > getFeld()[i][i2].x - getSpielfeldgroesse() / 2
						& spieler.getX() < getFeld()[i][i2].x + getSpielfeldgroesse() / 2

						|| getFeld()[i][i2].beinhaltet.equals("bombe")
						& spieler.getY() > getFeld()[i][i2].y - getSpielfeldgroesse() / 2
								- spieler.getRadius()

						& spieler.getY() < getFeld()[i][i2].y

						& spieler.getX() > getFeld()[i][i2].x - getSpielfeldgroesse() / 2
						& spieler.getX() < getFeld()[i][i2].x + getSpielfeldgroesse() / 2
						|| getFeld()[i][i2].beinhaltet.equals("mauer_destroyable")
						& spieler.getY() > getFeld()[i][i2].y - getSpielfeldgroesse() / 2
								- spieler.getRadius()

						& spieler.getY() < getFeld()[i][i2].y

						& spieler.getX() > getFeld()[i][i2].x - getSpielfeldgroesse() / 2
						& spieler.getX() < getFeld()[i][i2].x + getSpielfeldgroesse() / 2) {
					System.out.println("Unten nach oben");
					spieler.setY(getFeld()[i][i2].y - getSpielfeldgroesse() / 2
							- spieler.getRadius());

				}
				/*
				 * rechts nach links wird abgefragt:
				 * 
				 * 
				 * 1)ist spielermitte in x-Richtung kleiner als die mitte des
				 * abgefragten spielfeldes plus halbe spielfeldgroesse plus
				 * spielerradius
				 * 
				 * 2)liegt spielermitte wirklich rechts des abgefragten
				 * spielfeldes
				 * 
				 * 3)&4) einschraenkung der spielerposition durch die breite des
				 * spielfeldes in y-Richtungen
				 */
				if (getFeld()[i][i2].mauer

						& spieler.getX() < getFeld()[i][i2].x + getSpielfeldgroesse() / 2
								+ spieler.getRadius()

						& spieler.getX() > getFeld()[i][i2].x

						& spieler.getY() > getFeld()[i][i2].y - getSpielfeldgroesse() / 2
						& spieler.getY() < getFeld()[i][i2].y + getSpielfeldgroesse() / 2

						|| getFeld()[i][i2].beinhaltet.equals("bombe")
						& spieler.getX() < getFeld()[i][i2].x + getSpielfeldgroesse() / 2
								+ spieler.getRadius()

						& spieler.getX() > getFeld()[i][i2].x

						& spieler.getY() > getFeld()[i][i2].y - getSpielfeldgroesse() / 2
						& spieler.getY() < getFeld()[i][i2].y + getSpielfeldgroesse() / 2
						|| getFeld()[i][i2].beinhaltet.equals("mauer_destroyable")
						& spieler.getX() < getFeld()[i][i2].x + getSpielfeldgroesse() / 2
								+ spieler.getRadius()

						& spieler.getX() > getFeld()[i][i2].x

						& spieler.getY() > getFeld()[i][i2].y - getSpielfeldgroesse() / 2
						& spieler.getY() < getFeld()[i][i2].y + getSpielfeldgroesse() / 2) {
					System.out.println("Rechts nach links");
					spieler.setX(getFeld()[i][i2].x + getSpielfeldgroesse() / 2
							+ spieler.getRadius());

				}
				/*
				 * links nach rechts wird abgefragt:
				 * 
				 * 1)ist spielermitte in x-Richtung gr��er als die mitte des
				 * abgefragten spielfeldes plus halbe spielfeldgroesse plus
				 * spielerradius
				 * 
				 * 2)liegt spielermitte wirklich links des abgefragten
				 * spielfeldes
				 * 
				 * 3)&4) einschraenkung der spielerposition durch die breite des
				 * spielfeldes in y-Richtungen
				 */
				if (getFeld()[i][i2].mauer

						& spieler.getX() > getFeld()[i][i2].x - getSpielfeldgroesse() / 2
								- spieler.getRadius()

						& spieler.getX() < getFeld()[i][i2].x

						& spieler.getY() > getFeld()[i][i2].y - getSpielfeldgroesse() / 2
						& spieler.getY() < getFeld()[i][i2].y + getSpielfeldgroesse() / 2

						|| getFeld()[i][i2].beinhaltet.equals("bombe")
						& spieler.getX() > getFeld()[i][i2].x - getSpielfeldgroesse() / 2
								- spieler.getRadius()

						& spieler.getX() < getFeld()[i][i2].x

						& spieler.getY() > getFeld()[i][i2].y - getSpielfeldgroesse() / 2
						& spieler.getY() < getFeld()[i][i2].y + getSpielfeldgroesse() / 2
						|| getFeld()[i][i2].beinhaltet.equals("mauer_destroyable")
						& spieler.getX() > getFeld()[i][i2].x - getSpielfeldgroesse() / 2
								- spieler.getRadius()

						& spieler.getX() < getFeld()[i][i2].x

						& spieler.getY() > getFeld()[i][i2].y - getSpielfeldgroesse() / 2
						& spieler.getY() < getFeld()[i][i2].y + getSpielfeldgroesse() / 2) {
					System.out.println("Links nach rechts");
					spieler.setX(getFeld()[i][i2].x - getSpielfeldgroesse() / 2
							- spieler.getRadius());

				}

			}
		}

	}
/**
 * pruefe mit items
 */
	public static void item(Spieler sp) {
		Spieler spieler = sp;
		/*
		 * hier wird geprueft ob der Spieler ein Item ber�hrt. Ist dies der
		 * Fall, wird das Item entfernt, indem das Feld die belegung auf "false"
		 * und das feld mit "nothing"(also nichts) gesetzt wird
		 */
		for (int i = 0; i < getSpielfelder(); i++) {
			for (int i2 = 0; i2 < getSpielfelder(); i2++) {
				FeldCheck.check(spieler.getX(), spieler.getY());
				// check fuer die explosionsstaerke
				if (getBombe_x() == getFeld()[i][i2].x & getBombe_y() == getFeld()[i][i2].y
						& getFeld()[i][i2].beinhaltet.equals("feuer")) {

					spieler.setExplosions_staerke(spieler.getExplosions_staerke() + 1);
					// explosionsstaerke des spielers darf die anzahl
					// der spielfelder-2 nicht �berschreiten
					if (spieler.getExplosions_staerke() > getSpielfelder() - 2)
						spieler.setExplosions_staerke(getSpielfelder() - 2);

					getFeld()[i][i2].belegt = false;
					getFeld()[i][i2].beinhaltet = "nothing";
				}
				// check f�r die bombenanzahl des spielers
				if (getBombe_x() == getFeld()[i][i2].x & getBombe_y() == getFeld()[i][i2].y
						& getFeld()[i][i2].beinhaltet.equals("explosiv")) {

					spieler.setMaxbombenanzahl(spieler.getMaxbombenanzahl() + 1);
					// der spieler darf maximal 8 bomben legen k�nnen.
					// hier wird gecheckt, ob er mehr hat, wenn ja, wird
					// der zaehler auf 8 gesetzt
					if (spieler.getMaxbombenanzahl() > 8)
						spieler.setMaxbombenanzahl(8);
					getFeld()[i][i2].belegt = false;
					getFeld()[i][i2].beinhaltet = "nothing";
				}

			}
		}
	}
}
