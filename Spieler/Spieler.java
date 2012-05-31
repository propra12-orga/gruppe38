package gruppe38.Spieler;

import gruppe38.Main;

public class Spieler {
	// x und y position und groesse/radius des Spielers
	private double x;
	private double y;
	private double radius = Main.spielfeldgroesse / 2.25;
	String picture;
	private int explosions_staerke = 1;
	private int maxbombenanzahl = 2;
	private int bombenanzahlcounter = 0;
	private int speed;

	public Spieler(double x_coor, double y_coor, String pic,
			int explosions_staerke2, int geschwindigkeit) {
		setX(x_coor);
		setY(y_coor);
		picture = pic;
		setExplosions_staerke(explosions_staerke2);
		setSpeed(geschwindigkeit);

	}

	public int getBombenanzahlcounter() {
		return bombenanzahlcounter;
	}

	public void setBombenanzahlcounter(int bombenanzahlcounter) {
		this.bombenanzahlcounter = bombenanzahlcounter;
	}

	public double getRadius() {
		return radius;
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public int getExplosions_staerke() {
		return explosions_staerke;
	}

	public void setExplosions_staerke(int explosions_staerke) {
		this.explosions_staerke = explosions_staerke;
	}

	public int getMaxbombenanzahl() {
		return maxbombenanzahl;
	}

	public void setMaxbombenanzahl(int bombenanzahl) {
		this.maxbombenanzahl = bombenanzahl;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}
}
