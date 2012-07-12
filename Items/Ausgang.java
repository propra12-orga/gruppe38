package gruppe38.Items;

/**
 * Der Ausgang
 * @author Tom Berwald
 */

import gruppe38.Init;
import gruppe38.Menu.Menu;
import gruppe38.Sonstiges.StdDraw;
import gruppe38.Spieler.Spieler;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Ausgang extends Item {

	static JFrame frame_tester;
	static JPanel jpanel;

	public Ausgang(int xWert, int yWert) {
		super(xWert, yWert);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getIndex() {
		return 9;
	}

	@Override
	public String getBild() {
		// TODO Auto-generated method stub
		return null;
	}

	public static void siegFrame(String s) {
		frame_tester = new JFrame();
		frame_tester.setResizable(false);
		frame_tester.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame_tester.setSize(300, 300);
		frame_tester.setVisible(true);
		frame_tester.getWindowFocusListeners();

		jpanel = new JPanel();

		frame_tester.add(jpanel);

		JLabel label1 = new JLabel(s + " hat Gewonnen!");
		jpanel.add(label1, BorderLayout.SOUTH);
		label1.setFont(new Font("Stencil Std", Font.PLAIN, 16));
		label1.setBackground(new Color(0, 153, 255));
		frame_tester.setLocation(300, 300);
		frame_tester.pack();
	}

	/**
	 * Beende das Spiel und gib spieler als Sieger
	 * 
	 * @param sp
	 *            Der Spieler, der den Ausgang erreicht
	 */

	public static void eigenschaft(Spieler sp) {
		System.out.println(sp.getName());
		siegFrame(sp.getName());
		StdDraw.init();
		Menu.Nachricht(sp.getName() + " ist Sieger.");
		Menu.main2.setMenu_start(true);
		Init.init();
	}

	public static String getName() {
		return "exit";
	}

}
