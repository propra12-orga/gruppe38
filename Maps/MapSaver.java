package gruppe38.Maps;

import gruppe38.Main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
/**
 * Speichert Karten
 * 
 * @author Gruppe38
 * 
 */
public class MapSaver implements ActionListener {
	String name;
	JFrame frame;
	JPanel jpanel;
	Dimension screensize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
	/**
	 * @throws Speicherfehler
	 * @param s
	 *            Dateipfad in dem gespeichert werden soll
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
			// 5=leer!

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

				}
				fw.append(System.getProperty("line.separator")); // e.g.
				// "\n"
			}
		} catch (IOException e) {
			System.err.println("Konnte Datei nicht erstellen");
		} finally {
			if (fw != null)
				try {
					frame("Level gespeichert");
					fw.close();
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
