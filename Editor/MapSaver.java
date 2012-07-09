package gruppe38.Editor;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

/**
 * Speichert Karten
 * 
 * @author Gruppe38
 * 
 */
public class MapSaver {
	String name;

	/**
	 * @throws Speicherfehler
	 * @param s
	 *            Dateipfad in dem gespeichert werden soll
	 */
	public void save(String s) {
		Writer fw = null;
		name = s;

		try {
			// 0=unkaputtbare Mauer
			// 1=kaputtbare Mauer
			// 2=Spawn
			// 3=Bombenpickup
			// 4=Explosionspickup
			// 5=leer

			fw = new FileWriter(s + ".txt");
			for (int i = 0; i < Editor.getSpielfelder(); i++) {
				for (int i2 = 0; i2 < Editor.getSpielfelder(); i2++) {
					if (Editor.getFeld()[i][i2].beinhaltet
							.equals("mauer_destroyable")) {
						fw.write("0");
					}
					if (Editor.getFeld()[i][i2].beinhaltet.equals("mauer")) {
						fw.write("1");
					}
					if (Editor.getFeld()[i][i2].beinhaltet.equals("spawn")) {
						fw.write("2");
					}
					if (Editor.getFeld()[i][i2].beinhaltet.equals("feuer")) {
						fw.write("3");
					}
					if (Editor.getFeld()[i][i2].beinhaltet.equals("explosiv")) {
						fw.write("4");
					}
					if (Editor.getFeld()[i][i2].beinhaltet.equals("nothing")) {
						fw.write("5");
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
