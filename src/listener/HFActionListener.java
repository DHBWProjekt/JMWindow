package listener;

import gui.Hauptfenster;
import gui.JMPlayerIF;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HFActionListener implements ActionListener {
	private Hauptfenster hf;

	public HFActionListener(Hauptfenster parent) {
		hf = parent;
	}

	public void actionPerformed(ActionEvent e) {
		// JInternalFrames werden unserem JDesktopPane hinzugefügt
		if (e.getSource() == hf.getBtnPlay()) {
			// Musik startet
			hf.getMasterPlayer().play();
			System.out.println("Musik startet");
		} else if (e.getSource() == hf.getBtnPause()) {
			// Musik pausiert
			hf.getMasterPlayer().pause();
			System.out.println("Musik pausiert");
		} else if (e.getSource() == hf.getBtnStopp()) {
			// Musik stoppt
			hf.getMasterPlayer().stop();
			System.out.println("Musik stoppt");
		} else if (e.getSource() == hf.getMIOpenNewJMPlayer()) {
			// Ordner öffnen
			hf.getDeskPane().add(
					new JMPlayerIF(hf, "JMPlayer", true, true, true, true));
			System.out.println("JMPlayer laden");
		}
	}
}