package gruppe38;

public class Spieler {
	double x;
	double y;
	double radius = Main.spielfeldgroesse / 2.25;
	String picture;
	int explosions_staerke = 1;
	int speed;

	public Spieler(double x_coor, double y_coor, String pic,
			int explosions_staerke2, int geschwindigkeit) {
		x = x_coor;
		y = y_coor;
		picture = pic;
		explosions_staerke = explosions_staerke2;
		speed = geschwindigkeit;

	}
}
