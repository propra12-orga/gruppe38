/*
 * Das Spielfeld
 */

public class Spielfeld2 {
	public double size = 0;
	public boolean belegt = false;
	public double x;
	public double y;

	public Spielfeld2(double d, double e, double spielfeldgröße, boolean b) {
		x = d;
		y = e;
		size = spielfeldgröße;
		belegt = b;
	}

	public void show() {
		StdDraw.show();
	}

	public void clear() {
		StdDraw.clear();
	}

}
