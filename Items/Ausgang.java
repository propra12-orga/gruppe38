package gruppe38.Items;

import gruppe38.Spieler.Spieler;

public class Ausgang extends Item{

	public Ausgang(int xWert, int yWert) {
		super(xWert, yWert);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getIndex() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getBild() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void eigenschaft(Spieler sp){
		//Beende das Spiel und gib sp als Sieger
	}

}
