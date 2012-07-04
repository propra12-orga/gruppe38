package gruppe38.Spieler;

import gruppe38.Main;

/**
 * Eigenschaften eines Spielers
 * @author Gruppe38
 *
 */
public class Spieler {
	// x und y position und groesse/radius des Spielers
	private double x;
	private double y;
	private double radius = Main.getSpielfeldgroesse() / 2.25;
	String picture;
	private int explosions_staerke = 1;
	private int maxbombenanzahl = 2;
	private int bombenanzahlcounter = 0;
	private int speed;
	private String name;
	private int leben = 1;
	
	/**
	 * Ein Spieler
	 * @param x_coor
	 * @param y_coor
	 * @param pic Spielerbild
	 * @param explosions_staerke2
	 * @param geschwindigkeit
	 */

	public Spieler(double x_coor, double y_coor, String pic,
			int explosions_staerke2, int geschwindigkeit, String name_spieler) {
		setX(x_coor);
		setY(y_coor);
		picture = pic;
		setExplosions_staerke(explosions_staerke2);
		setSpeed(geschwindigkeit);
		setName(name_spieler);

	}
	
	public void setLeben(int i){
		leben=i;
	}
	
	public int getLeben(){
		return leben;
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
	
	public void setName(String name_Spieler){
		this.name = name_Spieler;
	}
 
	public String getName(){
		return name;
	}
	
//    @Override
//    public String getPath() {
//        return "black-bm-down.gif";
//    }
}
