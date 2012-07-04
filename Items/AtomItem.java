package gruppe38.Items;

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


	public String getName() {
		return "atom";
	}


	@Override
	public int getIndex() {
		// TODO Auto-generated method stub
		return 8;
	}

}
