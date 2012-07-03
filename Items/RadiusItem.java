package gruppe38.Items;

import gruppe38.Spieler.Spieler;

public class RadiusItem extends Item {
	

	public RadiusItem(int xWert,int yWert) {
		super(xWert, yWert);
		
	}

	@Override
	public String getBild() {
		return "src/gruppe38/Bilder/SB_Extra_Bomb.png";
	}

	@Override
	public void eigenschaft(Spieler sp) {
		sp.setExplosions_staerke(sp.getExplosions_staerke()+1);
	}

	@Override
	public String getName() {
		return "feuer";
	}

}
