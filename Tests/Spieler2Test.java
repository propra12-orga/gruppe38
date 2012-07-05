package gruppe38.Tests;

/**
 * @author Tom Berwald
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;

import gruppe38.Init;
import gruppe38.Main;
import gruppe38.Items.BombeSp2;
import gruppe38.Menu.Menu;
import gruppe38.Sonstiges.StdDraw;
import gruppe38.Spieler.Spieler;

public class Spieler2Test {

	double bombX;
	double bombY;
	int radius;
	double spRadius;
	double spielerX;
	double spielerY;
	Spieler spieler;
	JFrame frame_tester;
	JPanel jpanel;
	
	public Spieler2Test(BombeSp2 b, Spieler sp){
		bombX = b.getX();
		bombY = b.getY();
		radius = Main.getSp2().getExplosions_staerke();
		spRadius = sp.getRadius();
		spielerX = sp.getX();
		spielerY = sp.getY();
		spieler = sp;
	}
	public void totFrame(String s){
		frame_tester = new JFrame();
		frame_tester.setResizable(false);
		frame_tester.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame_tester.setSize(300, 300);
		frame_tester.setVisible(true);
		frame_tester.getWindowFocusListeners();

		jpanel = new JPanel();

		frame_tester.add(jpanel);

		JLabel label1 = new JLabel(s+" ist tot");
		jpanel.add(label1, BorderLayout.SOUTH);
		label1.setFont(new Font("Stencil Std", Font.PLAIN, 16));
		label1.setBackground(new Color(0, 153, 255));
		frame_tester.setLocation(300,300);
		frame_tester.pack();
	}
	
	public void test(){
		if (bombX+radius*(1/Main.getSpielfelder())>=spielerX & ((bombY+spRadius)>=spielerY & (bombY-spRadius)<=spielerY))
		{
			spieler.setLeben(spieler.getLeben()-1);
			if (spieler.getLeben()<=0){
			System.out.println(spieler.getName()+" ist Tod");
			StdDraw.init();
			totFrame(spieler.getName());
			Menu.Nachricht(spieler.getName()+" ist Tod.");
			Menu.main2.setMenu_start(true);
			Init.init();
			}
		}
		else if (bombX-radius*(1/Main.getSpielfelder())>=spielerX & ((bombY+spRadius)>=spielerY & (bombY-spRadius)<=spielerY))
		{
			spieler.setLeben(spieler.getLeben()-1);
			if (spieler.getLeben()<=0){
			System.out.println(spieler.getName()+" ist Tod");
			StdDraw.init();
			totFrame(spieler.getName());
			Menu.Nachricht(spieler.getName()+" ist Tod.");
			Menu.main2.setMenu_start(true);
			Init.init();
			}
		}
		else if (bombY+radius*(1/Main.getSpielfelder())<=spielerY & ((bombX+spRadius)>=spielerX & (bombX-spRadius)<=spielerX))
		{
			spieler.setLeben(spieler.getLeben()-1);
			if (spieler.getLeben()<=0){
			System.out.println(spieler.getName()+" ist Tod");
			StdDraw.init();
			totFrame(spieler.getName());
			Menu.Nachricht(spieler.getName()+" ist Tod.");
			Menu.main2.setMenu_start(true);
			Init.init();
			}
		}
		else if (bombY-radius*(1/Main.getSpielfelder())<= spielerY & ((bombX+spRadius)>=spielerX & (bombX-spRadius)<=spielerX))
		{
			spieler.setLeben(spieler.getLeben()-1);
			if (spieler.getLeben()<=0){
			System.out.println(spieler.getName()+" ist Tod");
			StdDraw.init();
			totFrame(spieler.getName());
			Menu.Nachricht(spieler.getName()+" ist Tod.");
			Menu.main2.setMenu_start(true);
			Init.init();
			}
		}
	}

}
