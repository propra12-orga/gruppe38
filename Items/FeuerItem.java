package gruppe38.Items;

/**
 * Erhoeht die Maximale Anzahl der Bomben, die ein Spieler legen kann
 * @author Tom Berwald
 */

import gruppe38.Spieler.Spieler;

public class FeuerItem extends Item {

	public FeuerItem(int xWert, int yWert) {
		super(xWert, yWert);
	}

	public static String getName() {
		return "feuer";
	}

	@Override
	public String getBild() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getIndex() {
		// TODO Auto-generated method stub
		return 3;
	}

	/**
	 * Erhöht Maxbombenanahl um eins
	 * 
	 * @param spieler
	 *            Der Spieler, der das Item einsammelt
	 */
	public static void eigenschaft(Spieler spieler) {
		spieler.setMaxbombenanzahl(spieler.getMaxbombenanzahl() + 1);
	}

}
