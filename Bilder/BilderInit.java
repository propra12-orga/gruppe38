package gruppe38.Bilder;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

/**
 * Initialisiert die Bilder -> Gleiche Groesse
 * 
 * @author Gruppe38
 * 
 * 
 */

public class BilderInit {
	public BufferedImage[] loadPics(String path, int pics) {

		BufferedImage[] anim = new BufferedImage[pics];
		BufferedImage source = null;

		URL pic_url = getClass().getClassLoader().getResource(path);

		try {
			source = ImageIO.read(pic_url);
		} catch (IOException e) {
			e.printStackTrace();
		}

		for (int x = 0; x < pics; x++) {
			anim[x] = source.getSubimage(x * source.getWidth() / pics, 0,
					source.getWidth() / pics, source.getHeight());
		}

		return anim;
	}

	// Bild_reference wird ben�tigt um bei allein bildern die gleiche
	public BufferedImage mauer;
	public BufferedImage bombe;
	public BufferedImage explosion;
	public BufferedImage bombe_extra;
	public BufferedImage bombe_energie;
	public BufferedImage mauer_destroyable;
	public BufferedImage armor;
	public BufferedImage cake;
	public BufferedImage explosiv;
	public BufferedImage feuer;
	public BufferedImage exit;
	public BufferedImage spawn;


	public void init() {
		mauer = loadPics("mauer.png", 1)[0];
		bombe = loadPics("pics/background.jpg", 1)[0];
		explosion = loadPics("pics/background.jpg", 1)[0];
		bombe_extra = loadPics("src/gruppe38/Bilder/bombe_extra.png", 1)[0];
		bombe_energie = loadPics("src/gruppe38/Bilder/bombe_energie.png", 1)[0];
		mauer_destroyable = loadPics("pics/background.jpg", 1)[0];
		armor = loadPics("pics/background.jpg", 1)[0];
		cake = loadPics("pics/background.jpg", 1)[0];
		explosiv = loadPics("pics/background.jpg", 1)[0];
		feuer = loadPics("pics/background.jpg", 1)[0];
		exit = loadPics("src/gruppe38/Bilder/exit.png", 1)[0];

	}
	// public Image bild_reference = Toolkit.getDefaultToolkit().getImage(
	// "src/gruppe38/Bilder/mauer.png");
	// public static Image bombe = Toolkit.getDefaultToolkit().getImage(
	// "src/gruppe38/Bilder/bombe_extra.png");
	// public Image feuer = Toolkit.getDefaultToolkit().getImage(
	// "src/gruppe38/Bilder/bombe_energie.png");
	// public Image explosiv = Toolkit.getDefaultToolkit().getImage(
	// "src/gruppe38/Bilder/bombe_extra.png");
	// public Image mauer = Toolkit.getDefaultToolkit().getImage(
	// "src/gruppe38/Bilder/mauer.png");
	// public Image mauer_destroyable = Toolkit.getDefaultToolkit().getImage(
	// "src/gruppe38/Bilder/mauer_destroyable1.png");
	// public Image armor = Toolkit.getDefaultToolkit().getImage(
	// "src/gruppe38/Bilder/mauer_destroyable1.png");
	// public Image cake = Toolkit.getDefaultToolkit().getImage(
	// "src/gruppe38/Bilder/mauer.png");
	// public Image explosion = Toolkit.getDefaultToolkit().getImage(
	// "src/gruppe38/Bilder/explosion2.png");
}
