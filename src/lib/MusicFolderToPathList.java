package lib;

import java.io.File;

import javafx.scene.media.Media;

import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;

//Abstracte Klasse um Musikdateien aus einem Ordner auszulesen
public abstract class MusicFolderToPathList {

	private static String[][] musicData; // String für musicTable
	// StandardDialog FileChooser wird erzeugt
	private static JFileChooser fc = new JFileChooser();
	private static File f;
	private static File[] fileArray;
	private static String path;

	// Klassenmethode wird erstellt und bekommt das Fenster übergeben von dem es
	// aufgerufen wurde
	public static String[][] getMusicData(JInternalFrame parent) {
		// festlegen, dass nur Ordner ausgewählt werden können
		fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

		// parent wird übergeben, damit User erst nach auswahl zum Hauptfenster
		// zurückkehren kann
		int auswahl = fc.showOpenDialog(parent);
		// Abfrage ob Auswahl getroffen wurde, bzw. abgebrochen wurde
		if (auswahl == JFileChooser.APPROVE_OPTION) {
			// DateiPfade werden in ein FileArray geladen
			f = new File(fc.getSelectedFile().getPath());
			fileArray = f.listFiles();
			// Schleife zur Festellung von Anzahl der Musikdateien im Ordner
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
			// Schleife um MusicData mit daten zu füllen
			for (int i = 0; i < fileArray.length; i++) {
				if (fileArray[i].getName().endsWith(".mp3") == true
						|| fileArray[i].getName().endsWith(".m4a") == true
						|| fileArray[i].getName().endsWith(".mp3") == true
						|| fileArray[i].getName().endsWith(".wav") == true) {
					path = fileArray[i].toURI().toASCIIString();
					// Name wird ausgelesen
					musicData[musicFileCounter][0] = fileArray[i].getName();
					// Pfad wird ausgelesen
					musicData[musicFileCounter][1] = path;
					System.out.println("File: " + musicFileCounter);
					Media temp = new Media(path);
					System.out.print(temp.getMetadata().get("album") + " ");
					System.out.print(temp.getMetadata().get("genre") + " ");
					System.out.println(temp.getMetadata().get("title"));
					musicFileCounter++;
				}
			}
			System.out.println("Ordner ausgelesen");
		}
		// Musikdaten werden zurückgegeben
		return musicData;
	}
}
