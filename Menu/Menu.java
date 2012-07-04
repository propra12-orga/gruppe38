package gruppe38.Menu;

//import gruppe38.Init;
import gruppe38.Init;
import gruppe38.Main;
import gruppe38.Sonstiges.MapLoader;
import gruppe38.Sonstiges.MapSaver;
import gruppe38.Sonstiges.StdDraw;



import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;



/**
 * @author Daniel Braune
 */

public class Menu extends JFrame implements ActionListener {
	

	private static String text = "";
	
	/**
	 * 
	 */
	public static Main main2 = new Main();
	private static final long serialVersionUID = 1L;
	
	public static void Nachricht(String msg){
		text = msg;
	}
	public Menu(){
		
		super("BOMBERMAN 2012");
//		menu menu = new menu();
		JPanel jpanel = new JPanel();
		add(jpanel);
		
		JLabel label1 = new JLabel("BOMBERMAN");
		jpanel.add(label1, BorderLayout.PAGE_START);
		label1.setFont(new Font("Stencil Std", Font.PLAIN, 71));
		label1.setBackground(new Color(0, 153, 255));
		
		JLabel label2 = new JLabel (text);
		jpanel.add(label2);
		label2.setFont(new Font("Quartz", Font.PLAIN, 14));
		label2.setBackground(new Color(0, 153, 255));
		
		JLabel label3 = new JLabel("by Patrick Hippler, Daniel Braune, Tom Berwald, Patrick Szewior & Sebastian Holthausen");
		jpanel.add(label3);
		label3.setFont(new Font("Quartz", Font.PLAIN, 14));
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
		
		JLabel labelmitte1 = new JLabel("Ziel des Spiel ist es, den Ausgang diagonal gegen�ber des Startpunktes");
		jpanel.add(labelmitte1, BorderLayout.CENTER);
		labelmitte1.setFont(new Font("Arial", Font.PLAIN, 15));
		
		JLabel labelmitte2 = new JLabel("zu erreichen, indem die zerst�rbaren Mauern mithilfe von Bomben");
		jpanel.add(labelmitte2, BorderLayout.CENTER);
		labelmitte2.setFont(new Font("Arial", Font.PLAIN, 15));
		
		JLabel labelmitte3 = new JLabel("zerst�rt werden und sich somit ein weg zum Ausgang freigemacht wird.");
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
		buttonstart.addActionListener(this);
		jpanel2.add(buttonstart);
		
		JButton buttonsave = new JButton("Level speichern");
		buttonsave.addActionListener(this);
		jpanel2.add(buttonsave);
		
		JButton buttonladen = new JButton("Level laden");
		buttonladen.addActionListener(this);
		jpanel2.add(buttonladen);
		
		JButton buttonspieler = new JButton("Spieler");
		buttonspieler.addActionListener(this);
		jpanel2.add(buttonspieler);
		
		JButton buttonende = new JButton("Spiel beenden");
		buttonende.addActionListener(this);
		jpanel2.add(buttonende);
		
		JLabel labelcopy = new JLabel("� 2012 by Gruppe38, HHU D�sseldorf");
		jpanel.add(labelcopy, BorderLayout.CENTER);
		labelcopy.setFont(new Font("Arial", Font.PLAIN, 15));
		
		setSize(600, 600);
		setLocation(300,300);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
//	public static void main(String[] args){
//		menu starten = new menu();
//	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		System.out.println(e.getActionCommand());
		if(e.getActionCommand().equals("Spiel starten")){
			System.out.println("k2k");
			StdDraw.init();
			System.out.println("k3k");
			main2.setMenu_start(true);
			System.out.println("k4k");
//			Main.start();
			System.out.println("k5k");
			Init.init();
			System.out.println("k6k");
//			System.exit(0); //Menu-Feld wird geschlossen, nachdem das Spiel geoeffnet wurde
			
		}
		if(e.getActionCommand().equals("Level laden")){
			FileDialog chooser = new FileDialog(StdDraw.frame,
					"W�hle eine Datei aus", FileDialog.LOAD);
			chooser.setVisible(true);
			String filename = chooser.getFile();
			// if (filename != null) {
			// StdDraw.save(chooser.getDirectory() + File.separator
			// + chooser.getFile());
			// }
			MapLoader map = new MapLoader();
			map.load(filename);
		}
		if(e.getActionCommand().equals("Level speichern")){
			FileDialog chooser = new FileDialog(StdDraw.frame,
					"Level speichern als", FileDialog.SAVE);
			chooser.setVisible(true);
			String filename = chooser.getFile();
			// if (filename != null) {
			// StdDraw.save(chooser.getDirectory() + File.separator
			// + chooser.getFile());
			// }
			MapSaver map = new MapSaver();
			map.save(filename);
		}
		if(e.getActionCommand().equals("Spieler")){
			System.out.println("k4k");
		}
		if(e.getActionCommand().equals("Spiel beenden")){
			System.exit(0);
		}
	}
}

// noch in Arbeit...

