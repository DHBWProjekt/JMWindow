package listener;

import gui.JMPlayerIF;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import lib.MusicFolderToPathList;

public class JMPActionListener implements ActionListener {

	private JMPlayerIF jmp;
	private String[][] musicData;

	public JMPActionListener(JMPlayerIF parent) {
		jmp = parent;
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == jmp.getBtnPlay()) {
			// Musik startet
			jmp.getMasterPlayer().play();
			System.out.println("Musik startet");
		} else if (e.getSource() == jmp.getBtnPause()) {
			// Musik pausiert
			jmp.getMasterPlayer().pause();
			System.out.println("Musik pausiert");
		} else if (e.getSource() == jmp.getBtnStopp()) {
			// Musik stoppt
			jmp.getMasterPlayer().stop();
			System.out.println("Musik stoppt");
		} else if (e.getSource() == jmp.getBtnNext()) {
			// Nächster Song
			jmp.playNextSong();
			System.out.println("Nächster Song");
		} else if (e.getSource() == jmp.getBtnBack()) {
			// Vorheriger Song
			jmp.playLastSong();
			System.out.println("Vorheriger Song");
		} else if (e.getSource() == jmp.getMIOpenFile()) {
			// Ordner öffnen
			musicData = MusicFolderToPathList.getMusicData(jmp);
			jmp.setData(musicData);
			jmp.changeTable();
			System.out.println("Ordner öffnen");
		}
	}
}
