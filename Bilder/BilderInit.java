package gruppe38.Bilder;

import java.awt.Image;
import java.awt.Toolkit;

/**
 * Initialisiert die Bilder -> Gleiche Groesse
 * 
 * @author Gruppe38
 *
 *
 */
public class BilderInit {
	// Bild_reference wird ben�tigt um bei allein bildern die gleiche bildgr��e
	// anzuzeigen. nehmen wir gr��ere bilder, sind unsere spielfelder verrutscht
	public Image bild_reference = Toolkit.getDefaultToolkit().getImage(
			"src/gruppe38/Bilder/mauer.png");
	public static Image bombe = Toolkit.getDefaultToolkit().getImage(
			"src/gruppe38/Bilder/bombe_extra.png");
	public Image feuer = Toolkit.getDefaultToolkit().getImage(
			"src/gruppe38/Bilder/bombe_energie.png");
	public Image explosiv = Toolkit.getDefaultToolkit().getImage(
			"src/gruppe38/Bilder/bombe_extra.png");
	public Image mauer = Toolkit.getDefaultToolkit().getImage(
			"src/gruppe38/Bilder/mauer.png");
	public Image mauer_destroyable = Toolkit.getDefaultToolkit().getImage(
			"src/gruppe38/Bilder/mauer_destroyable.png");
	public Image armor = Toolkit.getDefaultToolkit().getImage(
			"src/gruppe38/Bilder/mauer_destroyable.png");
	public Image cake = Toolkit.getDefaultToolkit().getImage(
			"src/gruppe38/Bilder/mauer.png");
	public Image explosion = Toolkit.getDefaultToolkit().getImage(
			"src/gruppe38/Bilder/explosion2.png");
}
