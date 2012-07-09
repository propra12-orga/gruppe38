package gruppe38.Editor;

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
	char feld_einlesen;
	char warum;

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
			// 0=unkaputtbare Mauer
			// 1=kaputtbare Mauer
			// 2=Spawn
			// 3=Bombenpickup
			// 4=Explosionspickup
			// 5=leer

			reader = new FileReader(name);
			System.out.println(reader.toString());
			for (int i = 0; i < Editor.getSpielfelder(); i++) {
				for (int i2 = 0; i2 < Editor.getSpielfelder(); i2++) {
					feld_einlesen = (char) reader.read();

					if (feld_einlesen == '0') {
						System.out.print(feld_einlesen);
						Editor.getFeld()[i2][i].beinhaltet = "mauer_destroyable";
					}
					if (feld_einlesen == '1') {
						System.out.print(feld_einlesen);
						Editor.getFeld()[i2][i].beinhaltet = "mauer";
					}
					if (feld_einlesen == '2') {
						System.out.print(feld_einlesen);
						Editor.getFeld()[i2][i].beinhaltet = "spawn";
					}
					if (feld_einlesen == '3') {
						System.out.print(feld_einlesen);
						Editor.getFeld()[i2][i].beinhaltet = "feuer";
					}
					if (feld_einlesen == '4') {
						System.out.print(feld_einlesen);
						Editor.getFeld()[i2][i].beinhaltet = "explosion";
					}
					if (feld_einlesen == '5') {
						System.out.print(feld_einlesen);
						Editor.getFeld()[i2][i].beinhaltet = "nothing";
					}
				}
				reader.read();
				// reader.read();
				System.out.println();
				// reader.append(System.getProperty("line.separator")); // e.g.
				// // "\n"
			}
		} catch (IOException e) {
			System.err.println("Konnte Datei nicht lesen");
		} finally {
			if (reader != null)
				try {

					reader.close();
				} catch (IOException e) {
				}
		}
	}
}
