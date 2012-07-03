package gruppe38.Items;

/**
 * Oberklasse fuer Items
 * @author Tom Berwald
 *
 */
public abstract class Item extends Thread {
	private double x;
	private double y;
	private boolean existent;
	private String bild;
	
	public Item(int xWert, int yWert){
		setX(xWert);
		setY(yWert);
	}
	
	public abstract void run();
	
	public abstract String getBild();

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
}
