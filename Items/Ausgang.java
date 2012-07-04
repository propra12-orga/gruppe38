package gruppe38.Items;

/**
 * @author Tom Berwald
 */

import gruppe38.Spieler.Spieler;

public class Ausgang extends Item{

	public Ausgang(int xWert, int yWert) {
		super(xWert, yWert);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getIndex() {
		return 9;
	}

	@Override
	public String getBild() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public static void eigenschaft(Spieler sp){
		//Beende das Spiel und gib spieler als Sieger
		System.exit(0); //zum Testen
	}

	public static String getName() {
		return "exit";
	}

}
