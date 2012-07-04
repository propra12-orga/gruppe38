package gruppe38.Items;

import gruppe38.Init;
import gruppe38.Menu.Menu;
import gruppe38.Sonstiges.StdDraw;


/**
 * 
 * @author Tom Berwald
 *
 */

public class AtomItem extends Item{

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
	
	public void eigenschaft(){
		System.out.println("Alle Tod!");
		StdDraw.init();
		Menu.main2.setMenu_start(true);
		Init.init();
	}

}
