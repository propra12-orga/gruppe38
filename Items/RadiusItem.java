package gruppe38.Items;

import gruppe38.Spieler.Spieler;

public class RadiusItem extends Item {
	

	public RadiusItem(int xWert,int yWert) {
		super(xWert, yWert);
		
	}
	
	public String getBild() {
		return "src/gruppe38/Bilder/SB_Extra_Bomb.png";
	}

	public static void eigenschaft(Spieler sp) {
		sp.setExplosions_staerke(sp.getExplosions_staerke()+1);
	}

	public static String getName() {
		return "feuer";
	}

	@Override
	public int getIndex() {
		// TODO Auto-generated method stub
		return 4;
	}

}
