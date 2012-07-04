package gruppe38.Sonstiges;

import gruppe38.Main;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
/**
 * Speichert Karten
 * @author Gruppe38
 *
 */
public class MapSaver {
	String name;
	/**
	 * @throws Speicherfehler
	 * @param s Dateipfad in dem gespeichert werden soll
	 */
	public void save(String s) {
		Writer fw = null;
		name = s;

		try {
			// 0=kaputtbare Mauer
			// 1=unkaputtbare Mauer
			// 2=Spawn
			// 3=Bombenpickup
			// 4=Explosionspickup
			// 5=leer
			// 8=AtombombenItem
			// 9=Ausgang(Versteckt)

			fw = new FileWriter(s + ".txt");
			for (int i = 0; i < Main.getSpielfelder(); i++) {
				for (int i2 = 0; i2 < Main.getSpielfelder(); i2++) {
					if (Main.getFeld()[i][i2].beinhaltet
							.equals("mauer_destroyable")) {
						fw.write("0");
					}
					if (Main.getFeld()[i][i2].beinhaltet.equals("mauer")) {
						fw.write("1");
					}
					if (Main.getFeld()[i][i2].beinhaltet.equals("spawn")) {
						fw.write("2");
					}
					if (Main.getFeld()[i][i2].beinhaltet.equals("feuer")) {
						fw.write("3");
					}
					if (Main.getFeld()[i][i2].beinhaltet.equals("explosiv")) {
						fw.write("4");
					}
					if (Main.getFeld()[i][i2].beinhaltet.equals("nothing")) {
						fw.write("5");
					}
					if (Main.getFeld()[i][i2].beinhaltet.equals("atom")) {
						fw.write("8");
					}
					if (Main.getFeld()[i][i2].beinhaltet.equals("ausgang")) {
						fw.write("9");
					}

				}
				fw.append(System.getProperty("line.separator")); // e.g.
				// "\n"
			}
		} catch (IOException e) {
			System.err.println("Konnte Datei nicht erstellen");
		} finally {
			if (fw != null)
				try {

					fw.close();
				} catch (IOException e) {
				}
		}
	}
}
