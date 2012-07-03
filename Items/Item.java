package gruppe38.Items;

import gruppe38.Spieler.Spieler;

/**
 * Oberklasse fuer Items
 * @author Tom Berwald
 *
 */
public abstract class Item {
	private double x;
	private double y;
	private boolean existent;
	private String bild;
	private Spieler sp;
	private String name;
	
	public Item(int xWert, int yWert){
		setX(xWert);
		setY(yWert);
	}
		
	public abstract String getBild();
	
	public abstract String getName();

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

	public void eigenschaft(Spieler sp) {
		this.sp = sp;
	}

}
