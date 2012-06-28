package gruppe38.Menu;

import gruppe38.Main;



import java.awt.*;
import javax.swing.*;
import javax.swing.ImageIcon;



/**
 * @author Daniel Braune
 */


public class menu extends JFrame {
	
	public menu(){
		
		super("BOMBERMAN 2012");
		
		JPanel jpanel = new JPanel();
		add(jpanel);
		
		JLabel label1 = new JLabel("BOMBERMAN");
		jpanel.add(label1, BorderLayout.PAGE_START);
		label1.setFont(new Font("Stencil Std", Font.PLAIN, 71));
		label1.setBackground(new Color(0, 153, 255));
		
		JLabel label3 = new JLabel("by Patrick Hippler, Daniel Braune, Tom Berwald, Patrick Szewior, Basti Holthausen & Ilja Zelenov");
		jpanel.add(label3);
		label3.setFont(new Font("Quartz", Font.PLAIN, 15));
		label3.setBackground(new Color(0, 153, 255));
		
		//JLabel labelbild = new JLabel("");
		//labelbild.setIcon(new ImageIcon ("bombe.gif"));
		//labelbild.setToolTipText( "blub");
		//getContentPane().add(labelbild, BorderLayout.CENTER);
		
		JLabel labelleer = new JLabel("                                                  ");
		jpanel.add(labelleer, BorderLayout.CENTER);
		labelleer.setFont(new Font("Arial", Font.PLAIN, 40));
		
		JLabel labelmitte = new JLabel("ZIEL DES SPIELS:");
		jpanel.add(labelmitte, BorderLayout.CENTER);
		labelmitte.setFont(new Font("Arial", Font.PLAIN, 30));
		
		JLabel labelmitte1 = new JLabel("Ziel des Spiel ist es, den Ausgang diagonal gegenüber des Startpunktes");
		jpanel.add(labelmitte1, BorderLayout.CENTER);
		labelmitte1.setFont(new Font("Arial", Font.PLAIN, 15));
		
		JLabel labelmitte2 = new JLabel("zu erreichen, indem die zerstörbaren Mauern mithilfe von Bomben");
		jpanel.add(labelmitte2, BorderLayout.CENTER);
		labelmitte2.setFont(new Font("Arial", Font.PLAIN, 15));
		
		JLabel labelmitte3 = new JLabel("zerstört werden und sich somit ein weg zum Ausgang freigemacht wird.");
		jpanel.add(labelmitte3, BorderLayout.CENTER);
		labelmitte3.setFont(new Font("Arial", Font.PLAIN, 15));
		
		JLabel labelleer2 = new JLabel("                                                 ");
		jpanel.add(labelleer2, BorderLayout.CENTER);
		labelleer2.setFont(new Font("Arial", Font.PLAIN, 40));
		
		JLabel labelmitte4 = new JLabel("STEUERUNG");
		jpanel.add(labelmitte4, BorderLayout.CENTER);
		labelmitte4.setFont(new Font("Arial", Font.PLAIN, 30));
		
		JLabel labelmitte5 = new JLabel("PFEILTASTEN: Hoch, Runter, Rechts, Links  // SPACE: Bombe legen");
		jpanel.add(labelmitte5, BorderLayout.CENTER);
		labelmitte5.setFont(new Font("Arial", Font.PLAIN, 15));
		
		JLabel labelleer3 = new JLabel("                                                 ");
		jpanel.add(labelleer3, BorderLayout.CENTER);
		labelleer3.setFont(new Font("Arial", Font.PLAIN, 40));
		
		JPanel jpanel2 = new JPanel();
		add(jpanel2, BorderLayout.PAGE_END);
		
		JButton buttonstart = new JButton("Spiel starten");
		jpanel2.add(buttonstart);
		//start.addActionListener(std);
		//menu2.add(start);
		
		JButton buttonladen = new JButton("Level laden");
		jpanel2.add(buttonladen);
		
		JButton buttonspieler = new JButton("Spieler");
		jpanel2.add(buttonspieler);
		
		JButton buttonende = new JButton("Spiel beenden");
		jpanel2.add(buttonende);
		
		JLabel labelcopy = new JLabel("© 2012 by Gruppe38, HHU Düsseldorf");
		jpanel.add(labelcopy, BorderLayout.CENTER);
		labelcopy.setFont(new Font("Arial", Font.PLAIN, 15));
		
		setSize(600, 600);
		setLocation(300,300);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	public static void main(String[] args){
		menu starten = new menu();
	}
}

// Layout ist fertig soweit, ActionListener mach ich noch...

