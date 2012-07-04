package gruppe38.Items;

import java.awt.Menu;

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
//		Menu menue = new Menu();
	}

}
