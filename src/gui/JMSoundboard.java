package gui;

import javax.swing.JFrame;
import javax.swing.JSplitPane;

public class JMSoundboard extends JFrame {

	private JMPlayerPanel test1 = new JMPlayerPanel(null, "Test", true, true,
			true, true);
	private JMPlayerPanel test2 = new JMPlayerPanel(null, "Test", true, true,
			true, true);

	public JMSoundboard() {

		JSplitPane splitpane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		splitpane.setRightComponent(test1);
		splitpane.setLeftComponent(test2);
		getContentPane().add(splitpane);
		setSize(500, 500);
		setVisible(true);

	}

	public static void main(String[] args) {
		JMSoundboard jms = new JMSoundboard();
	}

}
