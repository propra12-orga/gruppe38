package gruppe38.Items;

/**
 * @author Tom Berwald
 */



import gruppe38.Init;
import gruppe38.Menu.Menu;
import gruppe38.Sonstiges.StdDraw;
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
		System.out.println(sp.getName());
		StdDraw.init();
		Menu.main2.setMenu_start(true);
		Init.init();
	}

	public static String getName() {
		return "exit";
	}

}
