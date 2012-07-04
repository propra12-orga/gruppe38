commit 1fddd24e2faf0b290b5d93318288cc7f12dbe109
Author: Ilja Zelenov <ilja.zelenov@uni-duesseldorf.de>
Date:   Wed Jul 4 16:22:26 2012 +0200

    Hauptmenü langsam, aber >wannabe tutorial< drin

diff --git a/Sonstiges/StdDraw.java b/Sonstiges/StdDraw.java
index 5423a29..e169a9a 100644
--- a/Sonstiges/StdDraw.java
+++ b/Sonstiges/StdDraw.java
@@ -267,7 +267,7 @@ public final class StdDraw
 		frame.requestFocusInWindow();
 		frame.setVisible(true);
 
-		// // Frame f�r das Laden einer Map
+		// // Frame fï¿½r das Laden einer Map
 		// frame2.setContentPane(draw);
 		// frame2.addKeyListener(std); // JLabel cannot get keyboard focus
 		// frame2.setResizable(false);
@@ -283,100 +283,72 @@ public final class StdDraw
 		
 	}
 public static void menu(){
-	System.out.println("keks");
-		
-		jpanel = new JPanel();
-		jpanel.setName("BOMBERMAN 2012");
-		
-		frame.add(jpanel);
-		
-		JLabel label1 = new JLabel("BOMBERMAN");
-		jpanel.add(label1, BorderLayout.PAGE_START);
-		label1.setFont(new Font("Stencil Std", Font.PLAIN, 71));
-		label1.setBackground(new Color(0, 153, 255));
-		
-		JLabel label3 = new JLabel("by Patrick Hippler, Daniel Braune, Tom Berwald, Patrick Szewior, Sebastian Holthausen & Ilja Zelenov");
-		jpanel.add(label3);
-		label3.setFont(new Font("Quartz", Font.PLAIN, 11));
-		label3.setBackground(new Color(0, 153, 255));
-		
-		//JLabel labelbild = new JLabel("");
-		//labelbild.setIcon(new ImageIcon ("bombe.gif"));
-		//labelbild.setToolTipText( "blub");
-		//getContentPane().add(labelbild, BorderLayout.CENTER);
-		
-		JLabel labelleer = new JLabel("                                                  ");
-		jpanel.add(labelleer, BorderLayout.CENTER);
-		labelleer.setFont(new Font("Arial", Font.PLAIN, 40));
-		
-		JLabel labelmitte = new JLabel("ZIEL DES SPIELS:");
-		jpanel.add(labelmitte, BorderLayout.CENTER);
-		labelmitte.setFont(new Font("Arial", Font.PLAIN, 30));
-		
-		JLabel labelmitte1 = new JLabel("Ziel des Spiel ist es, den Ausgang diagonal gegenüber des Startpunktes");
-		jpanel.add(labelmitte1, BorderLayout.CENTER);
-		labelmitte1.setFont(new Font("Arial", Font.PLAIN, 15));
-		
-		JLabel labelmitte2 = new JLabel("zu erreichen, indem die zerstörbaren Mauern mithilfe von Bomben");
-		jpanel.add(labelmitte2, BorderLayout.CENTER);
-		labelmitte2.setFont(new Font("Arial", Font.PLAIN, 15));
-		
-		JLabel labelmitte3 = new JLabel("zerstört werden und sich somit ein weg zum Ausgang freigemacht wird.");
-		jpanel.add(labelmitte3, BorderLayout.CENTER);
-		labelmitte3.setFont(new Font("Arial", Font.PLAIN, 15));
-		
-		JLabel labelleer2 = new JLabel("                                                 ");
-		jpanel.add(labelleer2, BorderLayout.CENTER);
-		labelleer2.setFont(new Font("Arial", Font.PLAIN, 40));
-		
-		JLabel labelmitte4 = new JLabel("STEUERUNG");
-		jpanel.add(labelmitte4, BorderLayout.CENTER);
-		labelmitte4.setFont(new Font("Arial", Font.PLAIN, 30));
-		
-		JLabel labelmitte5 = new JLabel("PFEILTASTEN: Hoch, Runter, Rechts, Links  // SPACE: Bombe legen");
-		jpanel.add(labelmitte5, BorderLayout.CENTER);
-		labelmitte5.setFont(new Font("Arial", Font.PLAIN, 15));
-		
-		JLabel labelleer3 = new JLabel("                                                 ");
-		jpanel.add(labelleer3, BorderLayout.CENTER);
-		labelleer3.setFont(new Font("Arial", Font.PLAIN, 40));
-		
-		JPanel jpanel2 = new JPanel();
-		jpanel.add(jpanel2, BorderLayout.PAGE_END);
-		
-		JButton buttonstart = new JButton("Spiel starten");
-		buttonstart.addActionListener(std);
-		jpanel2.add(buttonstart);
-		
-		JButton buttonsave = new JButton("Level speichern");
-		buttonsave.addActionListener(std);
-		jpanel2.add(buttonsave);
-		
-		JButton buttonladen = new JButton("Level laden");
-		buttonladen.addActionListener(std);
-		jpanel2.add(buttonladen);
-		
-		JButton buttonspieler = new JButton("Spieler");
-		buttonspieler.addActionListener(std);
-		jpanel2.add(buttonspieler);
-		
-		JButton buttonende = new JButton("Spiel beenden");
-		buttonende.addActionListener(std);
-		jpanel2.add(buttonende);
-		
-		JLabel labelcopy = new JLabel("(c) 2012 by Gruppe38, HHU Düsseldorf");
-		jpanel.add(labelcopy, BorderLayout.CENTER);
-		labelcopy.setFont(new Font("Arial", Font.PLAIN, 15));
-		
-		jpanel.setSize(600, 600);
+  	System.out.println("keks");
+  		
+  		jpanel = new JPanel();
+  		jpanel.setName("BOMBERMAN 2012");
+  		
+  		frame.add(jpanel);
+  		
+  		JLabel label1 = new JLabel("BOMBERMAN");
+  		jpanel.add(label1, BorderLayout.PAGE_START);
+  		label1.setFont(new Font("Stencil Std", Font.PLAIN, 71));
+  		label1.setBackground(new Color(0, 153, 255));
+  		
+  		JLabel label3 = new JLabel("by Patrick Hippler, Daniel Braune, Tom Berwald, Patrick Szewior, Sebastian Holthausen & Ilja Zelenov");
+  		jpanel.add(label3);
+  		label3.setFont(new Font("Quartz", Font.PLAIN, 11));
+  		label3.setBackground(new Color(0, 153, 255));
+  		
+  		//JLabel labelbild = new JLabel("");
+  		//labelbild.setIcon(new ImageIcon ("bombe.gif"));
+  		//labelbild.setToolTipText( "blub");
+  		//getContentPane().add(labelbild, BorderLayout.CENTER);
+  		
+//		JLabel labelleer = new JLabel("                                                  ");
+//		jpanel.add(labelleer, BorderLayout.CENTER);
+//		labelleer.setFont(new Font("Arial", Font.PLAIN, 40));
+
+  		JLabel labelmitte1 = new JLabel("<html><center><h1>ZIEL DES SPIELS</h1><p>Ziel des Spiel ist es, den Ausgang diagonal gegenüber des<br>Startpunktes zu erreichen, indem die zerstörbaren Mauern<br>mithilfe von Bomben zerstört werden und somit ein Weg<br>zum Ausgang freigemacht wird.<p/><br><h1>STEUERUNG</h1><p>&#8592; (Pfeil links):<img src='http://img849.imageshack.us/img849/8565/blackbmleft.gif'> nach links laufen<br>&#8594; (Pfeil rechts) <img src='http://img41.imageshack.us/img41/5863/blackbmright.gif'> nach rechts laufen<br>&#8593; (Pfeil oben): <img src='http://img837.imageshack.us/img837/8400/blackbmup.gif'> nach oben laufen<br>&#8595; (Pfeil unten) <img src='http://img535.imageshack.us/img535/8540/blackbmdown.gif'> nach unten laufen<br>Leertaste: <img width=50% height=50% src='http://img526.imageshack.us/img526/6987/boombe.png'> Bombe legen</p></center></html>");
+  		jpanel.add(labelmitte1, BorderLayout.CENTER);
+  		labelmitte1.setFont(new Font("Arial", Font.PLAIN, 15));
+  		
+  		JPanel jpanel2 = new JPanel();
+  		jpanel.add(jpanel2, BorderLayout.PAGE_END);
+  		
+  		JButton buttonstart = new JButton("Spiel starten");
+  		buttonstart.addActionListener(std);
+  		jpanel2.add(buttonstart);
+  		
+  		JButton buttonsave = new JButton("Level speichern");
+  		buttonsave.addActionListener(std);
+  		jpanel2.add(buttonsave);
+  		
+  		JButton buttonladen = new JButton("Level laden");
+  		buttonladen.addActionListener(std);
+  		jpanel2.add(buttonladen);
+  		
+  		JButton buttonspieler = new JButton("Spieler");
+  		buttonspieler.addActionListener(std);
+  		jpanel2.add(buttonspieler);
+  		
+  		JButton buttonende = new JButton("Spiel beenden");
+  		buttonende.addActionListener(std);
+  		jpanel2.add(buttonende);
+  		
+  		JLabel labelcopy = new JLabel("<html><center>&#169; 2012 by Gruppe38, HHU Düsseldorf</center></html>");
+  		jpanel.add(labelcopy, BorderLayout.CENTER);
+  		labelcopy.setFont(new Font("Arial", Font.PLAIN, 15));
+  		
+  		jpanel.setSize(600, 600);
 //		jpanel.setLocation(300,300);
-		
+  		
 //		((JFrame) jpanel).setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
-		jpanel.setVisible(true);
-		if(jpanel.isShowing()){
-			System.out.println("Daniel");
-		}
-	}
+  		jpanel.setVisible(true);
+  		if(jpanel.isShowing()){
+  			System.out.println("Daniel");
+  		}
+  	}
 
 	// create the menu bar (changed to private)
 	private static JMenuBar createMenuBar() {
@@ -1403,91 +1375,91 @@ public static void menu(){
 	@SuppressWarnings("deprecation")
 	public void actionPerformed(ActionEvent e) {
 
-		// wenn der button "Starten" aufgerufen wird, starte das spiel
-		if (e.getActionCommand().equals("Starten")) {
-			Main.setSpiel_start(true);
-			Init.init();
-		}
-		// wenn der button "Beenden" aufgerufen wird, beende das Programm
-		if (e.getActionCommand().equals("Beenden")) {
-			System.exit(0);
