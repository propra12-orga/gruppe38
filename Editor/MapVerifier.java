package gruppe38.Editor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MapVerifier implements ActionListener {
	int i;
	int i2;
	JFrame frame_tester;
	JPanel jpanel;
	Dimension screensize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();

	public MapVerifier(int x, int y) {
		i = x;
		i2 = y;
	}

	public boolean verifier() {

		if (Editor.getFeld()[i - 1][i2].belegt
				& Editor.getFeld()[i + 1][i2].belegt
				& Editor.getFeld()[i][i2 + 1].belegt
				& Editor.getFeld()[i][i2 - 1].belegt) {

			frame("Fehler, speichern nicht möglich! Ein Spawnpunkt ist nicht spielbar. Bitte entfernen Sie dort anliegende Blöcke! Ausbessern?");

			return false;
		}
		return true;
	}

	public void frame(String s) {

		frame_tester = new JFrame();
		frame_tester.setResizable(false);
		frame_tester.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame_tester.setSize(300, 300);
		frame_tester.setVisible(true);
		frame_tester.getWindowFocusListeners();

		jpanel = new JPanel();

		frame_tester.add(jpanel);

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
		JButton buttonJa = new JButton("Ja");
		buttonJa.addActionListener(this);
		jpanel2.add(buttonJa);
		JButton buttonNein = new JButton("Nein");
		buttonNein.addActionListener(this);
		jpanel2.add(buttonNein);

		jpanel.setSize(100, 200);
		jpanel.setLocation(0, 0);

		jpanel.setVisible(true);
		frame_tester.pack();
		frame_tester.setLocation(screensize.height / 8 + 200,
				screensize.width / 8 + 300);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		System.out.println("keks");
		if (e.getActionCommand().equals("Ja")) {
			InitEditor.clearSpawn();
			frame_tester.dispose();
		}
		if (e.getActionCommand().equals("Nein")) {
			frame_tester.dispose();
		}

	}

}
