/*
 * Die Spielfiguren
 */

public class Figur {
	private int x;
	private int y;
	private Spielfeld feld;
	
	
	public Figur(int xWert, int yWert, Spielfeld s){
		x = xWert;
		y = yWert;
		feld = s;
	}
	
	public void moveRight(){
		if (x<feld.getRandRechts()){
			x = x+5;
		}
	}
	
	public void moveDown(){
		if (y>0){
			y = y-5;
		}
	}
	
	public void moveLeft(){
		if (x>0){
			x = x-5;
		}
	}
	
	public void moveUp(){
		if (y<feld.getRandOben()){
			y = y+5;
		}
		
	}
	
	public void show(){
		StdDraw.picture(x, y, "src/earth.gif");
		StdDraw.show(10);
	}
}
