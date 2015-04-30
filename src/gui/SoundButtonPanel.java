package gui;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.UIManager;

public class SoundButtonPanel extends JPanel {

	private BlinkListener bL = new BlinkListener();
	private BtnListener bl = new BtnListener();
	private Timer blinkTimer = new Timer(500, bL);
	private RightClick rcl = new RightClick();

	private Color colorBackground = new Color(255, 255, 255);
	private Color colorForeground = new Color(0, 0, 0);
	private Color colorActive;
	private Color colorGegen;
	private boolean istStandardColor = true;

	int btnAnzahly = 5;
	int btnAnzahlx = 8;
	private JMButton[][] btnOneSong = new JMButton[btnAnzahlx][btnAnzahly];
	private JMButton btnActive;
	private DialogSoundButton dialogSB;

	// private String[][] musicData;

	private JFXPanel myJFXPanel = new JFXPanel();
	private MediaPlayer sbPlayer;

	public SoundButtonPanel() {
		try {
			UIManager.setLookAndFeel(UIManager
					.getCrossPlatformLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		setLayout(new GridLayout(btnAnzahlx, btnAnzahly));
		// musicData = MusicFolderToPathList.getMusicData();
		for (int sp = 0; sp < btnAnzahlx; sp++) {
			for (int i = 0; i < btnAnzahly; i++) {
				btnOneSong[sp][i] = new JMButton(0, String.valueOf(btnAnzahly
						* sp + i), this);
				btnOneSong[sp][i].addActionListener(bl);
				btnOneSong[sp][i].addMouseListener(rcl);
				btnOneSong[sp][i].setBackground(colorBackground);
				btnOneSong[sp][i].setForeground(colorForeground);
				add(btnOneSong[sp][i]);
			}
		}
	}

	private class BlinkListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			changeBtnColor();
		}
	}

	private class RightClick implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			if (SwingUtilities.isRightMouseButton(e) || e.isControlDown()) {
				System.out.println("Rechtsklick: " + e.getSource());
				for (int sp = 0; sp < btnOneSong.length; sp++) {
					for (int i = 0; i < btnOneSong[sp].length; i++) {
						if (e.getSource() == btnOneSong[sp][i]) {
							dialogSB = new DialogSoundButton(btnOneSong[sp][i]);
						}
					}
				}
			}

		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

		}

	}

	private class BtnListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			if (sbPlayer == null) {

			} else {
				sbPlayer.stop();
				sbPlayer.dispose();
				sbPlayer = null;
			}
			// For-Schleife, um alle Button auf den Panel zu durchlaufen
			for (int sp = 0; sp < btnOneSong.length; sp++) {
				for (int i = 0; i < btnOneSong[sp].length; i++) {
					// Abfrage welcher Button gedrückt wurde
					if (e.getSource() == btnOneSong[sp][i]) {
						// Abfrage ob der alte Button gleich der neue ist
						if (btnActive == btnOneSong[sp][i]) {
							blinkTimer.stop();
							// Farbe wird auf Standard zurückgesetzt
							if (istStandardColor == false) {
								changeBtnColor();
							}
							btnActive = null;
							// Wenn ein neuer Button gedrückt wurde
						} else {
							blinkTimer.stop();
							if (btnActive != null) {
								if (istStandardColor == false) {
									changeBtnColor();
								}
							}
							if (btnOneSong[sp][i].getMusicFile() != null) {
								sbPlayer = new MediaPlayer(new Media(
										btnOneSong[sp][i].getPathASCII()));
								sbPlayer.play();
								colorActive = btnOneSong[sp][i].getBackground();
								colorGegen = btnOneSong[sp][i].getForeground();
								System.out.println(btnOneSong[sp][i]
										.getForeground());
								btnActive = btnOneSong[sp][i];
								changeBtnColor();
								blinkTimer.start();
							} else {
								System.out
										.println("Keine Musikdatei hinterlegt");
							}
						}
					}
				}
			}
		}
	}

	private void changeBtnColor() {
		if (istStandardColor == true) {
			btnActive.setBackground(colorGegen);
			btnActive.setForeground(colorActive);
			System.out.println("Buttonfarbe rot!");
			istStandardColor = false;
		} else {
			btnActive.setBackground(colorActive);
			btnActive.setForeground(colorGegen);
			System.out.println("Buttonfarbe grau!");
			istStandardColor = true;
		}
	}
}