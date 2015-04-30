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

public class ShuffleButtonPanel extends JPanel {

	private BlinkListener bL = new BlinkListener();
	private Timer blinkTimer = new Timer(500, bL);
	private BtnListener bl = new BtnListener();
	private RightClick rcl = new RightClick();

	private boolean istStandardColor = true;
	private Color colorBackground = new Color(255, 255, 255);
	private Color colorForeground = new Color(0, 0, 0);
	private Color colorActive;
	private Color colorGegen;

	int btnAnzahly = 1;
	int btnAnzahlx = 4;
	private JMButton btnActive;
	private JMButton[][] btnShuffle = new JMButton[btnAnzahlx][btnAnzahly];
	private String[] shuffleBtnName = new String[] { "Punktgewinn",
			"Punktverlust", "Auszeit", "TA" };

	private DialogSoundButton dialogShB;

	private JFXPanel myJFXPanel = new JFXPanel();
	private MediaPlayer sbPlayer;

	public ShuffleButtonPanel() {
		try {
			UIManager.setLookAndFeel(UIManager
					.getCrossPlatformLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		setLayout(new GridLayout(btnAnzahlx, btnAnzahly));

		for (int sp = 0; sp < btnAnzahlx; sp++) {
			for (int i = 0; i < btnAnzahly; i++) {
				btnShuffle[sp][i] = new JMButton(1, shuffleBtnName[sp], this);
				btnShuffle[sp][i].setBackground(colorBackground);
				btnShuffle[sp][i].setForeground(colorForeground);
				btnShuffle[sp][i].addActionListener(bl);
				btnShuffle[sp][i].addMouseListener(rcl);
				add(btnShuffle[sp][i]);
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
				for (int sp = 0; sp < btnShuffle.length; sp++) {
					for (int i = 0; i < btnShuffle[sp].length; i++) {
						if (e.getSource() == btnShuffle[sp][i]) {
							dialogShB = new DialogSoundButton(btnShuffle[sp][i]);
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

			for (int sp = 0; sp < btnShuffle.length; sp++) {
				for (int i = 0; i < btnShuffle[sp].length; i++) {
					if (e.getSource() == btnShuffle[sp][i]) {
						if (btnActive == btnShuffle[sp][i]) {
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
							if (btnShuffle[sp][i].getMusicFileArray() != null) {
								sbPlayer = new MediaPlayer(new Media(
										btnShuffle[sp][i].getShufflePath()));
								sbPlayer.play();
								colorActive = btnShuffle[sp][i].getBackground();
								colorGegen = btnShuffle[sp][i].getForeground();
								btnActive = btnShuffle[sp][i];
								changeBtnColor();
								blinkTimer.start();
							} else {
								System.out
										.println("Es ist kein Musikpfad hinterlegt");
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