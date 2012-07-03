package gruppe38.Items;

/**
 * Oberklasse fuer Items
 * @author Tom Berwald
 *
 */
public abstract class Item {
	private double x;
	private double y;
	private boolean existent;
	
	public Item(int xWert, int yWert){
		setX(xWert);
		setY(yWert);
	}
	
	public abstract int getIndex();
	
//	public String getName();

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public boolean isExistent() {
		return existent;
	}

	public void setExistent(boolean existent) {
		this.existent = existent;
	}

	public abstract String getBild();


}
