package gruppe38;

/*
 * Das Spielfeld
 */

public class Spielfeld2 {
	public double size = 0;
	public boolean belegt = false;
	public double x;
	public double y;
	public String beinhaltet;

	public Spielfeld2(double d, double e, double spielfeldgr��e, String s,
			boolean b) {
		x = d;
		y = e;
		size = spielfeldgr��e;
		belegt = b;
		beinhaltet = s;
	}

	public void show() {
		StdDraw.show();
	}

	public void clear() {
		StdDraw.clear();
	}

}
