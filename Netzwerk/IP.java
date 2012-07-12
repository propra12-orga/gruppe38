package gruppe38.Netzwerk;

import gruppe38.Main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Stellt die IP für den Client ein
 * 
 * @author Patrick Szewior
 * 
 */
public class IP {

	public static int[] ip = new int[4];
	private static JFrame ipframe;
	private static JPanel jpanel;
	private static JPanel jpanel2;
	private static JTextField ip1 = new JTextField("", 3);
	private static JTextField ip2 = new JTextField("", 3);
	private static JTextField ip3 = new JTextField("", 3);
	private static JTextField ip4 = new JTextField("", 3);

	static ActionListener action = new ActionListener() {
		public void actionPerformed(ActionEvent ev) {

			if (ev.getActionCommand().equals("Verbinden")) {
				try {
					System.out.println("Verbinden gedrückt");
					Main.setIP(1, Integer.parseInt(ip1.getText()));
					Main.setIP(2, Integer.parseInt(ip2.getText()));
					Main.setIP(3, Integer.parseInt(ip3.getText()));
					Main.setIP(4, Integer.parseInt(ip4.getText()));
					Main.is_client = true;
					Main.setNetzwerkStatus(true);
					Client.establishConnection();
					Client.connect();
					Client cli = new Client(true);
					cli.start();
					ipframe.dispose();
				} catch (Exception e) {
					System.out.println("Fehler beim Einlesen der IP");
				}
			}

		}
	};

	/**
	 * PopUp zur Abfrage der IP-Adresse
	 */
	public static void popUp() {
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
		jpanel.add(label1, BorderLayout.BEFORE_FIRST_LINE);
		label1.setFont(new Font("Arial", Font.PLAIN, 20));
		label1.setBackground(new Color(0, 153, 255));

		jpanel2.add(ip1);
		jpanel2.add(ip2);
		jpanel2.add(ip3);
		jpanel2.add(ip4);
		jpanel.add(jpanel2, BorderLayout.AFTER_LAST_LINE);

		JButton connect = new JButton("Verbinden");
		connect.addActionListener(action);
		jpanel.add(connect, BorderLayout.PAGE_END);

		ipframe.enableInputMethods(true);

		ipframe.toFront();
		ipframe.requestFocus();

		ipframe.setLocation(300, 300);
		ipframe.pack();

	}

}
