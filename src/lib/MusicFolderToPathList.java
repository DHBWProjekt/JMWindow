package lib;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;

public abstract class MusicFolderToPathList {

	private static String[][] musicData;
	private static JFileChooser fc = new JFileChooser();
	private static File f;
	private static File[] fileArray;
	private static String path;

	public static String[][] getMusicData(JInternalFrame parent) {
		// Ordner öffnen
		// Dialog zum Oeffnen von Dateien anzeigen
		fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int auswahl = fc.showOpenDialog(parent);

		if (auswahl == JFileChooser.APPROVE_OPTION) {
			// Ausgabe der ausgewaehlten Datei
			f = new File(fc.getSelectedFile().getPath());
			fileArray = f.listFiles();
			int musicFileCounter = 0;
			for (int i = 0; i < fileArray.length; i++) {
				if (fileArray[i].getName().endsWith(".mp3") == true
						|| fileArray[i].getName().endsWith(".m4a") == true
						|| fileArray[i].getName().endsWith(".mp3") == true
						|| fileArray[i].getName().endsWith(".wav") == true) {
					musicFileCounter++;
				}
			}
			musicData = new String[musicFileCounter][2];
			musicFileCounter = 0;
			for (int i = 0; i < fileArray.length; i++) {
				if (fileArray[i].getName().endsWith(".mp3") == true
						|| fileArray[i].getName().endsWith(".m4a") == true
						|| fileArray[i].getName().endsWith(".mp3") == true
						|| fileArray[i].getName().endsWith(".wav") == true) {
					path = fileArray[i].toURI().toASCIIString();
					musicData[musicFileCounter][0] = fileArray[i].getName();
					musicData[musicFileCounter][1] = path;
					System.out.println("File: " + musicFileCounter);
					musicFileCounter++;
				}
			}
			System.out.println("Ordner öffnen");
		}
		return musicData;
	}
}
