package gruppe38;

public class Spieler {
	double x;
	double y;
	String picture;
	int explosions_stärke = 1;
	int speed;

	public Spieler(double x_coor, double y_coor, String pic,
			int explosions_stärke2, int geschwindigkeit) {
		x = x_coor;
		y = y_coor;
		picture = pic;
		explosions_stärke = explosions_stärke2;
		speed = geschwindigkeit;

	}
}
