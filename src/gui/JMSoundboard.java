package gui;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.UIManager;

public class JMSoundboard extends JFrame {
	SoundButtonPanel sbp = new SoundButtonPanel();

	public JMSoundboard() {
		try {
			UIManager.setLookAndFeel(UIManager
					.getCrossPlatformLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		// btnShuffle.setPreferredSize(new Dimension(130, 20));
		// myReiter1.add(myItem1);
		// myMenu.add(myReiter1);
		// shuffleBtnPanel.setPreferredSize(new Dimension(130, 100));
		// sPanel.setPreferredSize(new Dimension(100, 100));
		// sPanel.setDividerLocation(1.0);
		// setLayout(new BorderLayout());
		// sPanel.setRightComponent(gblPanel);
		// sPanel.setLeftComponent(shuffleBtnPanel);
		// sPanel.setEnabled(true);

		add(sbp, BorderLayout.CENTER);
		// setJMenuBar(myMenu);
		setSize(900, 700);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		JMSoundboard jms = new JMSoundboard();
	}

}
