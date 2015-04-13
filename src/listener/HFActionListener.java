package listener;

import gui.Hauptfenster;
import gui.JMPlayerIF;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HFActionListener implements ActionListener {
	// Verknüpfung zu Hauptfenster
	private Hauptfenster hf;

	// Konstruktor bekommt Objekt vom Hauptfenster, um zu wissen wer ihn
	// ausgelöst hat
	public HFActionListener(Hauptfenster parent) {
		hf = parent;
	}

	// Methode die ausgelöst wird wenn ein Steuerelement gedrückt wurde
	public void actionPerformed(ActionEvent e) {
		// JInternalFrames werden unserem JDesktopPane hinzugefügt
		// wenn es der StoppButton war wird der Player über eine Methode vom
		// Hauptfenster gestoppt
		if (e.getSource() == hf.getBtnStopp()) {
			// Musik stoppt
			hf.getMasterPlayer().stop();
			System.out.println("Musik stoppt");
			// wenn das MenuItem OpenNewJMPLayer ausgelöst wurde wird ein neues
			// JMPlayerIF ertstellt
		} else if (e.getSource() == hf.getMIOpenNewJMPlayer()) {
			// JMPLayer starten und ist resizeable, closeable,
			// maximizable, iconifiable
			hf.getDeskPane().add(
					new JMPlayerIF(hf, "JMPlayer", true, true, true, true));
			System.out.println("JMPlayer laden");
		}
	}
}