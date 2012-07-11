package gruppe38.Tests;

/**
 * @author Tom Berwald
 */

import gruppe38.Init;
import gruppe38.Main;
import gruppe38.Items.Explosion;
import gruppe38.Menu.Menu;
import gruppe38.Sonstiges.StdDraw;
import gruppe38.Spieler.Spieler;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SpielerTest {

	double bombX;
	double bombY;
	double radius;
	double spRadius;
	double spielerX;
	double spielerY;
	Spieler spieler;
	JFrame frame_tester;
	JPanel jpanel;

	public SpielerTest(Explosion b, Spieler leger, Spieler sp) {
		bombX = b.getX();
		bombY = b.getY();
		radius = leger.getExplosions_staerke()
				* (Main.getSpielfeldgroesse() + spRadius) + .5
				* Main.getSpielfeldgroesse();
		spRadius = sp.getRadius();
		spielerX = sp.getX();
		spielerY = sp.getY();
		spieler = sp;
	}

	public void totFrame(String s) {
		frame_tester = new JFrame();
		frame_tester.setResizable(false);
		frame_tester.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame_tester.setSize(300, 300);
		frame_tester.setVisible(true);
		frame_tester.getWindowFocusListeners();

		jpanel = new JPanel();

		frame_tester.add(jpanel);

		JLabel label1 = new JLabel(s + " ist tot");
		jpanel.add(label1, BorderLayout.SOUTH);
		label1.setFont(new Font("Stencil Std", Font.PLAIN, 16));
		label1.setBackground(new Color(0, 153, 255));
		frame_tester.setLocation(300, 300);
		frame_tester.pack();
	}

	public void test() {
		Feldwiedergabe fc = FeldCheck.check(bombX, bombY);
		System.out.println("Bombe:" + fc.getX() + " " + fc.getY());
		Feldwiedergabe fw = FeldCheck.check(spielerX, spielerY);
		System.out.println("Spieler:" + fw.getX() + " " + fw.getY());

		if (!Main.getFeld()[fc.getX() + 1][fc.getY()].beinhaltet
				.equals("mauer")
				& !Main.getFeld()[fc.getX() + 1][fc.getY()].beinhaltet
						.equals("mauer_destroyable")) {

			if (bombX + radius >= spielerX
					& spielerX >= bombX
					& ((bombY + spRadius) >= spielerY & (bombY - spRadius) <= spielerY)) {
				spieler.setLeben(spieler.getLeben() - 1);

				System.out.println("X1 ist falsch");

				if (spieler.getLeben() <= 0) {
					System.out.println(spieler.getName() + " ist Tod");
					StdDraw.init();
					totFrame(spieler.getName());
					Menu.Nachricht(spieler.getName() + " ist Tod.");
					Main.setMenu_start(true);
					Init.init();
				}
			}

		}
		if (!Main.getFeld()[fc.getX() - 1][fc.getY()].beinhaltet
				.equals("mauer")
				& !Main.getFeld()[fc.getX() - 1][fc.getY()].beinhaltet
						.equals("mauer_destroyable")) {

			if (bombX - radius <= spielerX
					& spielerX <= bombX
					& ((bombY + spRadius) >= spielerY & (bombY - spRadius) <= spielerY)) {

				System.out.println("X2 ist falsch");

				spieler.setLeben(spieler.getLeben() - 1);
				if (spieler.getLeben() <= 0) {
					System.out.println(spieler.getName() + " ist Tod");
					StdDraw.init();
					totFrame(spieler.getName());
					Menu.Nachricht(spieler.getName() + " ist Tod.");
					Main.setMenu_start(true);
					Init.init();
				}
			}

		}
		if (!Main.getFeld()[fc.getX()][fc.getY() + 1].beinhaltet
				.equals("mauer")
				& !Main.getFeld()[fc.getX()][fc.getY() + 1].beinhaltet
						.equals("mauer_destroyable")) {

			if (bombY + radius >= spielerY
					& spielerY >= bombY
					& ((bombX + spRadius) >= spielerX & (bombX - spRadius) <= spielerX)) {
				spieler.setLeben(spieler.getLeben() - 1);

				System.out.println("Y1 ist falsch");

				if (spieler.getLeben() <= 0) {
					System.out.println(spieler.getName() + " ist Tod");
					StdDraw.init();
					totFrame(spieler.getName());
					Menu.Nachricht(spieler.getName() + " ist Tod.");
					Main.setMenu_start(true);
					Init.init();
				}
			}
		}
		if (!Main.getFeld()[fc.getX()][fc.getY() - 1].beinhaltet
				.equals("mauer")
				& !Main.getFeld()[fc.getX()][fc.getY() - 1].beinhaltet
						.equals("mauer_destroyable")) {

			if (bombY - radius <= spielerY
					& ((bombX + spRadius) >= spielerX & (bombX - spRadius) <= spielerX)
					& spielerY <= bombY) {

				System.out.println("Y2 ist falsch");

				spieler.setLeben(spieler.getLeben() - 1);
				if (spieler.getLeben() <= 0) {
					System.out.println(spieler.getName() + " ist Tod");
					StdDraw.init();
					totFrame(spieler.getName());
					Menu.Nachricht(spieler.getName() + " ist Tod.");
					Main.setMenu_start(true);
					Init.init();
				}
			}
		}
	}
}
