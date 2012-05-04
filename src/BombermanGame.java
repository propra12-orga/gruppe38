
public class BombermanGame {

	/**
	 * @param args
	 * 
	 * Unsere schöne main :)
	 */
	public static void main(String[] args) {
		Spielfeld field = new Spielfeld(10,10);
		Figur player = new Figur(0,100, field);
		
			while (true){
				field.clear();
				player.moveDown();
				player.show();
			}
		
	}

}
