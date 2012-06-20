package gruppe38.Bilder;

import java.awt.Image;
import java.awt.Toolkit;

public class BilderInit {
	// Bild_reference wird benötigt um bei allein bildern die gleiche bildgröße
	// anzuzeigen. nehmen wir größere bilder, sind unsere spielfelder verrutscht
	public Image bild_reference = Toolkit.getDefaultToolkit().getImage(
			"src/gruppe38/Bilder/mauer.gif");
	public static Image bombe = Toolkit.getDefaultToolkit().getImage(
			"src/gruppe38/Bilder/SB_Extra_Bomb.png");
	public Image feuer = Toolkit.getDefaultToolkit().getImage(
			"src/gruppe38/Bilder/SB_Explosion_Expander.png");
	public Image explosiv = Toolkit.getDefaultToolkit().getImage(
			"src/gruppe38/Bilder/SB_Extra_Bomb.png");
	public Image mauer = Toolkit.getDefaultToolkit().getImage(
			"src/gruppe38/Bilder/mauer.png");
	public Image mauer_destroyable = Toolkit.getDefaultToolkit().getImage(
			"src/gruppe38/Bilder/mauer_destroyable.png");
	public Image armor = Toolkit.getDefaultToolkit().getImage(
			"src/gruppe38/Bilder/SB_Armor.png");
	public Image cake = Toolkit.getDefaultToolkit().getImage(
			"src/gruppe38/Bilder/SB_Cake.gif");
	public Image explosion = Toolkit.getDefaultToolkit().getImage(
			"src/gruppe38/Bilder/explosion2.png");
}
