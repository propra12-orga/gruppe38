package gruppe38.Sonstiges;

import gruppe38.Init;
import gruppe38.Main;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

/**
 * Laed Karten
 * 
 * @author Gruppe38
 * 
 */
public class MapLoader {
	String name;
	int feld_einlesen;

	/**
	 * @throws Ladefehler
	 * @param s
	 *            Dateipfad aus dem Geladen werden soll
	 */
	public void load(String s) {
		Reader reader = null;
		name = s;
		System.out.println(s);

		try {
			// 0=kaputtbare Mauer
			// 1=unkaputtbare Mauer
			// 2=Spawn
			// 3=Bombenpickup
			// 4=Explosionspickup
			// 5=leer

			reader = new FileReader(s);
			for (int i = 0; i < Main.getSpielfelder(); i++) {
				for (int i2 = 0; i2 < Main.getSpielfelder(); i2++) {
					feld_einlesen = reader.read();
					System.out.println(reader.read());

					if (feld_einlesen == 0) {
						Main.getFeld()[i][i2].beinhaltet = "mauer_destroyable";
					}
					if (feld_einlesen == 1) {
						Main.getFeld()[i][i2].beinhaltet = "mauer";
					}
					if (feld_einlesen == 2) {
						Main.getFeld()[i][i2].beinhaltet = "spawn";
					}
					if (feld_einlesen == 3) {
						Main.getFeld()[i][i2].beinhaltet = "feuer";
					}
					if (feld_einlesen == 4) {
						Main.getFeld()[i][i2].beinhaltet = "explosion";
					}
					if (feld_einlesen == 5) {
						Main.getFeld()[i][i2].beinhaltet = "nothing";
					}
				}
				// reader.append(System.getProperty("line.separator")); // e.g.
				// // "\n"
			}
		} catch (IOException e) {
			System.err.println("Konnte Datei nicht lesen");
		} finally {
			if (reader != null)
				try {
					Main.setSpiel_start(true);
					Init.init();

					reader.close();
				} catch (IOException e) {
				}
		}
	}
}
