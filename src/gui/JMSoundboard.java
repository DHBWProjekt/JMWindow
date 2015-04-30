package gui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JFrame;

public class JMSoundboard extends JFrame {
	SoundButtonPanel sbp = new SoundButtonPanel();
	ShuffleButtonPanel shbp = new ShuffleButtonPanel();
	private GridBagConstraints c = new GridBagConstraints();

	public JMSoundboard() {
		setLayout(new GridBagLayout());

		c.fill = GridBagConstraints.BOTH;
		c.gridy = 0; // Zeile 0
		c.gridx = 0; // Spalte 0
		c.weighty = 0.1; // Prozentual zugeordnete Höhe
		c.weightx = 0.1; // Prozentual zugeordnete Breite
		shbp.setPreferredSize(new Dimension(1, 1));
		add(shbp, c);

		c.gridy = 0; // Zeile 0
		c.gridx = 1; // Spalte 1
		c.weighty = 0.1; // Prozentual zugeordnete Höhe
		c.weightx = 0.6; // Prozentual zugeordnete Breite
		c.gridwidth = 2; // Geht über zwei Spalten
		sbp.setPreferredSize(new Dimension(1, 1));
		add(sbp, c);
		c.gridwidth = 1; // Weite wird zurück gesetzt

		// setJMenuBar(myMenu);
		setSize(900, 700);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		JMSoundboard jms = new JMSoundboard();
	}

}
