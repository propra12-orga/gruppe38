package gruppe38;

public class Spieler {
	double x;
	double y;
	String picture;
	int explosions_st�rke = 1;
	int speed;

	public Spieler(double x_coor, double y_coor, String pic,
			int explosions_st�rke2, int geschwindigkeit) {
		x = x_coor;
		y = y_coor;
		picture = pic;
		explosions_st�rke = explosions_st�rke2;
		speed = geschwindigkeit;

	}
}
