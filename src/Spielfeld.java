/*
 * Das Spielfeld
 */
public class Spielfeld {
	private int x;
	private int y;
	
	public Spielfeld(int xWert,int yWert){
		x = xWert;
		y = yWert;
		
		StdDraw.setCanvasSize(x*50, y*50);
		StdDraw.setXscale(0, x);
        StdDraw.setYscale(0, y);

	}
	
	public void show(){
		StdDraw.show();
	}
	
	public void clear(){
		StdDraw.clear();
	}
	
	public int getRandRechts(){
		return x;
	}
	
	public int getRandOben(){
		return y;
	}
    
}
