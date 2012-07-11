package gruppe38.Editor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Laed Karten
 * 
 * @author Gruppe38
 * 
 */
public class MapLoader implements ActionListener {
	String name;
	String feld_einlesen;
	JFrame frame;
	JPanel jpanel;
	Dimension screensize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
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

			// 0=kaputtbare Mauer
			// 1=unkaputtbare Mauer
			// 2=Spawn
			// 3=Bombenpickup
			// 4=Explosionspickup
			// 5=leer

			reader = new FileReader(name);
			System.out.println(reader.toString());
			for (int i = 0; i < Editor.getSpielfelder(); i++) {
				for (int i2 = 0; i2 < Editor.getSpielfelder(); i2++) {
					warum = (char) reader.read();
					feld_einlesen = "" + warum;

					if (feld_einlesen.equals("0")) {
						System.out.print(feld_einlesen);
						Editor.getFeld()[i2][i].beinhaltet = "mauer_destroyable";
					}
					if (feld_einlesen.equals("1")) {
						System.out.print(feld_einlesen);
						Editor.getFeld()[i2][i].beinhaltet = "mauer";
					}
					if (feld_einlesen.equals("2")) {
						System.out.print(feld_einlesen);
						Editor.getFeld()[i2][i].beinhaltet = "spawn";
					}
					if (feld_einlesen.equals("3")) {
						System.out.print(feld_einlesen);
						Editor.getFeld()[i2][i].beinhaltet = "feuer";
					}
					if (feld_einlesen.equals("4")) {
						System.out.print(feld_einlesen);
						Editor.getFeld()[i2][i].beinhaltet = "explosion";
					}
					if (feld_einlesen.equals("5")) {
						System.out.print(feld_einlesen);
						Editor.getFeld()[i2][i].beinhaltet = "nothing";
					}
				}
				reader.read();
				System.out.println();
				// reader.append(System.getProperty("line.separator")); // e.g.
				// // "\n"
			}
		} catch (IOException e) {
			System.err.println("Konnte Datei nicht lesen");
		} finally {
			if (reader != null)
				try {
					frame("Map geladen");
					reader.close();
				} catch (IOException e) {
				}
		}
	}

	public void frame(String s) {

		frame = new JFrame();
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(300, 300);
		frame.setVisible(true);
		frame.getWindowFocusListeners();

		jpanel = new JPanel();

		frame.add(jpanel);

		JLabel label1 = new JLabel(s);
		jpanel.add(label1, BorderLayout.SOUTH);
		label1.setFont(new Font("Stencil Std", Font.PLAIN, 16));
		label1.setBackground(new Color(0, 153, 255));

		// JLabel labelbild = new JLabel("");
		// labelbild.setIcon(new ImageIcon ("bombe.gif"));
		// labelbild.setToolTipText( "blub");
		// getContentPane().add(labelbild, BorderLayout.CENTER);

		JPanel jpanel2 = new JPanel();
		jpanel.add(jpanel2, BorderLayout.WEST);
		//
		JButton buttonJa = new JButton("Schliessen");
		buttonJa.addActionListener(this);
		jpanel2.add(buttonJa);

		jpanel.setSize(100, 200);
		jpanel.setLocation(0, 0);

		jpanel.setVisible(true);
		frame.pack();
		frame.setLocation(screensize.height / 8 + 200,
				screensize.width / 8 + 300);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getActionCommand().equals("Schliessen"))
			frame.dispose();

	}
}
