package gruppe38.Items;

import gruppe38.Init;
import gruppe38.Main;
import gruppe38.Sonstiges.StdDraw;
import gruppe38.Spieler.Spieler;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Atombomben Item
 * 
 * @author Tom Berwald
 * 
 */

public class AtomItem extends Item {

	/**
	 * 
	 * @param xWert
	 * @param yWert
	 */

	public AtomItem(int xWert, int yWert) {
		super(xWert, yWert);
	}

	@Override
	public String getBild() {
		return null;
	}

	public static String getName() {
		return "atom";
	}

	@Override
	public int getIndex() {
		// TODO Auto-generated method stub
		return 8;
	}

	/**
	 * <b>Eigenschaft<b> Das Spiel wird beendet und ein Frame wird geoeffnet
	 * 
	 * @param sp
	 *            Spieler, der das Item einsammelt
	 */
	public static void eigenschaft(Spieler sp) {
		// System.out.println("Alle Tod!");
		StdDraw.init();
		totFrame(sp.getName());
		Main.setMenu_start(true);
		Init.init();
	}

	/**
	 * Der oben besagte Frame
	 * 
	 * @param s
	 *            Der Spieler, der das Item eingesammelt hat
	 */

	private static void totFrame(String s) {
		JFrame frame_tester = new JFrame();
		frame_tester.setResizable(false);
		frame_tester.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame_tester.setSize(300, 300);
		frame_tester.setVisible(true);
		frame_tester.getWindowFocusListeners();

		JPanel jpanel = new JPanel();

		frame_tester.add(jpanel);

		JLabel label1 = new JLabel(s + " hat alle mit einer Atombombe getötet!");
		JLabel label2 = new JLabel("Es hat keiner Gewonnen.");
		jpanel.add(label1, BorderLayout.NORTH);
		jpanel.add(label2, BorderLayout.SOUTH);
		label1.setFont(new Font("Stencil Std", Font.PLAIN, 16));
		label1.setBackground(new Color(0, 153, 255));
		label2.setForeground(Color.red);
		label2.setFont(new Font("Stencil Std", Font.PLAIN, 16));
		frame_tester.setLocation(300, 300);
		frame_tester.pack();
	}

}
