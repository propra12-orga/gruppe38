package gruppe38.Netzwerk;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class IP {
	
	public static int[] ip = new int[4];
	
	private static JFrame ipframe;
	private static JPanel jpanel;
	private static JPanel jpanel2;
	private static JTextField ip1 = new JTextField("", 3);
	private static JTextField ip2 = new JTextField("", 3);
	private static JTextField ip3 = new JTextField("", 3);
	private static JTextField ip4 = new JTextField("", 3);
	
	public static void popUp(){
		ipframe = new JFrame();
		ipframe.setResizable(false);
		ipframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		ipframe.setSize(300, 300);
		ipframe.setVisible(true);
		ipframe.getWindowFocusListeners();

		jpanel = new JPanel();
		jpanel2 = new JPanel();

		ipframe.add(jpanel);

		JLabel label1 = new JLabel("Bitte geben Sie die IP des Servers ein:");
		jpanel.add(label1, BorderLayout.NORTH);
		label1.setFont(new Font("Arial", Font.PLAIN, 20));
		label1.setBackground(new Color(0, 153, 255));
		
		jpanel2.add(ip1);
		jpanel2.add(ip2);
		jpanel2.add(ip3);
		jpanel2.add(ip4);
		jpanel.add(jpanel2, BorderLayout.CENTER);
		
		
		ipframe.setLocation(300, 300);
		ipframe.pack();
		
	}

}
