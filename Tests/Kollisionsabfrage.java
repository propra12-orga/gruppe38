package gruppe38.Tests;

import gruppe38.Main;

public class Kollisionsabfrage extends Main {

	public static void mauer() {
		/*
		 * kollisionsabfrage mit den Mauern und Bomben
		 * 
		 * Hier wird jedes Feld einzeln geprüft, ob es eine Mauer oder Bombe ist
		 * und ob es in der Nähe des Spielers ist. Naeheres steht bei jeder
		 * Abfrage
		 */

		for (int i = 0; i < spielfelder; i++) {
			for (int i2 = 0; i2 < spielfelder; i2++) {
				/*
				 * oben nach unten wird abgefragt:
				 * 
				 * 
				 * 1)ist spielermitte in y-Richtung kleiner als die mitte des
				 * abgefragten spielfeldes plus halbe spielfeldgroesse plus
				 * spielerradius
				 * 
				 * 2)liegt spielermitte wirklich überhalb des abgefragten
				 * spielfeldes
				 * 
				 * 3)&4) einschraenkung der spielerposition durch die breite des
				 * spielfeldes in x-Richtungen
				 */
				if (getFeld()[i][i2].mauer
						& sp1.getY() < getFeld()[i][i2].y + spielfeldgroesse / 2
								+ sp1.getRadius()

						& sp1.getY() > getFeld()[i][i2].y

						& sp1.getX() > getFeld()[i][i2].x - spielfeldgroesse / 2
						& sp1.getX() < getFeld()[i][i2].x + spielfeldgroesse / 2

						|| getFeld()[i][i2].beinhaltet.equals("bombe")
						& sp1.getY() < getFeld()[i][i2].y + spielfeldgroesse / 2
								+ sp1.getRadius()

						& sp1.getY() > getFeld()[i][i2].y

						& sp1.getX() > getFeld()[i][i2].x - spielfeldgroesse / 2
						& sp1.getX() < getFeld()[i][i2].x + spielfeldgroesse / 2
						|| getFeld()[i][i2].beinhaltet.equals("mauer_destroyable")
						& sp1.getY() < getFeld()[i][i2].y + spielfeldgroesse / 2
								+ sp1.getRadius()

						& sp1.getY() > getFeld()[i][i2].y

						& sp1.getX() > getFeld()[i][i2].x - spielfeldgroesse / 2
						& sp1.getX() < getFeld()[i][i2].x + spielfeldgroesse / 2) {
					System.out.println("Oben nach unten");
					sp1.setY(getFeld()[i][i2].y + spielfeldgroesse / 2
							+ sp1.getRadius());
				}
				/*
				 * unten nach oben wird abgefragt:
				 * 
				 * 
				 * 1)ist spielermitte y-Richtung größer als die mitte des
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
						& sp1.getY() > getFeld()[i][i2].y - spielfeldgroesse / 2
								- sp1.getRadius()

						& sp1.getY() < getFeld()[i][i2].y

						& sp1.getX() > getFeld()[i][i2].x - spielfeldgroesse / 2
						& sp1.getX() < getFeld()[i][i2].x + spielfeldgroesse / 2

						|| getFeld()[i][i2].beinhaltet.equals("bombe")
						& sp1.getY() > getFeld()[i][i2].y - spielfeldgroesse / 2
								- sp1.getRadius()

						& sp1.getY() < getFeld()[i][i2].y

						& sp1.getX() > getFeld()[i][i2].x - spielfeldgroesse / 2
						& sp1.getX() < getFeld()[i][i2].x + spielfeldgroesse / 2
						|| getFeld()[i][i2].beinhaltet.equals("mauer_destroyable")
						& sp1.getY() > getFeld()[i][i2].y - spielfeldgroesse / 2
								- sp1.getRadius()

						& sp1.getY() < getFeld()[i][i2].y

						& sp1.getX() > getFeld()[i][i2].x - spielfeldgroesse / 2
						& sp1.getX() < getFeld()[i][i2].x + spielfeldgroesse / 2) {
					System.out.println("Unten nach oben");
					sp1.setY(getFeld()[i][i2].y - spielfeldgroesse / 2
							- sp1.getRadius());

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

						& sp1.getX() < getFeld()[i][i2].x + spielfeldgroesse / 2
								+ sp1.getRadius()

						& sp1.getX() > getFeld()[i][i2].x

						& sp1.getY() > getFeld()[i][i2].y - spielfeldgroesse / 2
						& sp1.getY() < getFeld()[i][i2].y + spielfeldgroesse / 2

						|| getFeld()[i][i2].beinhaltet.equals("bombe")
						& sp1.getX() < getFeld()[i][i2].x + spielfeldgroesse / 2
								+ sp1.getRadius()

						& sp1.getX() > getFeld()[i][i2].x

						& sp1.getY() > getFeld()[i][i2].y - spielfeldgroesse / 2
						& sp1.getY() < getFeld()[i][i2].y + spielfeldgroesse / 2
						|| getFeld()[i][i2].beinhaltet.equals("mauer_destroyable")
						& sp1.getX() < getFeld()[i][i2].x + spielfeldgroesse / 2
								+ sp1.getRadius()

						& sp1.getX() > getFeld()[i][i2].x

						& sp1.getY() > getFeld()[i][i2].y - spielfeldgroesse / 2
						& sp1.getY() < getFeld()[i][i2].y + spielfeldgroesse / 2) {
					System.out.println("Rechts nach links");
					sp1.setX(getFeld()[i][i2].x + spielfeldgroesse / 2
							+ sp1.getRadius());

				}
				/*
				 * links nach rechts wird abgefragt:
				 * 
				 * 1)ist spielermitte in x-Richtung größer als die mitte des
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

						& sp1.getX() > getFeld()[i][i2].x - spielfeldgroesse / 2
								- sp1.getRadius()

						& sp1.getX() < getFeld()[i][i2].x

						& sp1.getY() > getFeld()[i][i2].y - spielfeldgroesse / 2
						& sp1.getY() < getFeld()[i][i2].y + spielfeldgroesse / 2

						|| getFeld()[i][i2].beinhaltet.equals("bombe")
						& sp1.getX() > getFeld()[i][i2].x - spielfeldgroesse / 2
								- sp1.getRadius()

						& sp1.getX() < getFeld()[i][i2].x

						& sp1.getY() > getFeld()[i][i2].y - spielfeldgroesse / 2
						& sp1.getY() < getFeld()[i][i2].y + spielfeldgroesse / 2
						|| getFeld()[i][i2].beinhaltet.equals("mauer_destroyable")
						& sp1.getX() > getFeld()[i][i2].x - spielfeldgroesse / 2
								- sp1.getRadius()

						& sp1.getX() < getFeld()[i][i2].x

						& sp1.getY() > getFeld()[i][i2].y - spielfeldgroesse / 2
						& sp1.getY() < getFeld()[i][i2].y + spielfeldgroesse / 2) {
					System.out.println("Links nach rechts");
					sp1.setX(getFeld()[i][i2].x - spielfeldgroesse / 2
							- sp1.getRadius());

				}

			}
		}

	}

	public static void item() {

		/*
		 * hier wird geprueft ob der Spieler ein Item berührt. Ist dies der
		 * Fall, wird das Item entfernt, indem das Feld die belegung auf "false"
		 * und das feld mit "nothing"(also nichts) gesetzt wird
		 */
		for (int i = 0; i < spielfelder; i++) {
			for (int i2 = 0; i2 < spielfelder; i2++) {
				FeldCheck.check(sp1.getX(), sp1.getY());
				// check fuer die explosionsstaerke
				if (bombe_x == getFeld()[i][i2].x & bombe_y == getFeld()[i][i2].y
						& getFeld()[i][i2].beinhaltet.equals("feuer")) {

					sp1.setExplosions_staerke(sp1.getExplosions_staerke() + 1);
					// explosionsstaerke des spielers darf die anzahl
					// der spielfelder-2 nicht überschreiten
					if (sp1.getExplosions_staerke() > spielfelder - 2)
						sp1.setExplosions_staerke(spielfelder - 2);

					getFeld()[i][i2].belegt = false;
					getFeld()[i][i2].beinhaltet = "nothing";
				}
				// check für die bombenanzahl des spielers
				if (bombe_x == getFeld()[i][i2].x & bombe_y == getFeld()[i][i2].y
						& getFeld()[i][i2].beinhaltet.equals("explosiv")) {

					sp1.setMaxbombenanzahl(sp1.getMaxbombenanzahl() + 1);
					// der spieler darf maximal 8 bomben legen können.
					// hier wird gecheckt, ob er mehr hat, wenn ja, wird
					// der zaehler auf 8 gesetzt
					if (sp1.getMaxbombenanzahl() > 8)
						sp1.setMaxbombenanzahl(8);
					getFeld()[i][i2].belegt = false;
					getFeld()[i][i2].beinhaltet = "nothing";
				}

			}
		}
	}
}
