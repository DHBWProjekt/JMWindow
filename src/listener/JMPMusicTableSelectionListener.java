package listener;

import gui.JMPlayerIF;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class JMPMusicTableSelectionListener implements ListSelectionListener {

	// Verknüpfung zum Fenster, das den SelectionListener auslöst
	JMPlayerIF jmp;

	// Konsturktor bekommt das Objekt übergeben von dem es ausgelöst wurde
	public JMPMusicTableSelectionListener(JMPlayerIF parent) {
		jmp = parent;
	}

	// Methode die aufgerufen wird, wenn sich die Auswahl geändert hat
	public void valueChanged(ListSelectionEvent e) {

		// wird über Methode vom JMPlayerIF gesetzt
		jmp.setTfAnzeige();

		// If-Abfrage zur Überprüfung, ob es bereits einen MediaPlayer gibt
		if (jmp.getMasterPlayer() == null) { // MasterPlayer vom Hauptfenster
		} else {
			jmp.getMasterPlayer().stop();
			jmp.getMasterPlayer().dispose(); // freigabe zum abräumen für GC
			jmp.setMasterPlayer(null); // Sicherheitshalber
		}
		// MasterPlayer wird erstellt, mit neuer Media, mit Pfad aus musicTable
		jmp.setMasterPlayer(new MediaPlayer(
				new Media(jmp.getPathSelectedSong())));
		// Player wird gestartet
		jmp.getMasterPlayer().play();
	}
}
