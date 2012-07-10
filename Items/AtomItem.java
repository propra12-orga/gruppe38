package gruppe38.Items;

import gruppe38.Init;
import gruppe38.Main;
import gruppe38.Menu.Menu;
import gruppe38.Sonstiges.StdDraw;
import gruppe38.Spieler.Spieler;

/**
 * 
 * @author Tom Berwald
 * 
 */

public class AtomItem extends Item {

	public AtomItem(int xWert, int yWert) {
		super(xWert, yWert);
	}

	@Override
	public String getBild() {
		return null;
	}

	public static String getName() {
		return "atom";
	}

	@Override
	public int getIndex() {
		// TODO Auto-generated method stub
		return 8;
	}

	public static void eigenschaft(Spieler sp) {
		System.out.println("Alle Tod!");
		StdDraw.init();
		Menu.Nachricht("Alle sind durch eine Atombombe gestorben!");
		Main.setMenu_start(true);
		Init.init();
	}

}
