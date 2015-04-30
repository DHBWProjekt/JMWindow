package gui;

import java.io.File;
import java.io.Serializable;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JPanel;

public class JMButton extends JButton implements Serializable {
	private JPanel jms;
	private double volume;
	private File musicFile;
	private File[] musicFileArray;
	private int buttonArt; // 0 entspricht SoundButton, 1 entspricht
							// ShuffleButton

	// String pfad;

	public JMButton(int buttonArt, String name, JPanel parent) {
		super(name);
		jms = parent;
		this.buttonArt = buttonArt;
	}

	public JMButton(int buttonArt, String name, JPanel parent, File musicFile) {
		super(name);
		jms = parent;
		this.musicFile = musicFile;
		this.buttonArt = buttonArt;
	}

	public File getMusicFile() {
		return musicFile;
	}

	public void setMusicFile(File musicFile) {
		this.musicFile = musicFile;
	}

	public void setMusicFileArray(File[] musicFileArray) {
		this.musicFileArray = musicFileArray;
	}

	public File[] getMusicFileArray() {
		return musicFileArray;
	}

	public String getPath() {
		return musicFile.getPath();
	}

	public String getPathASCII() {
		return musicFile.toURI().toASCIIString();
	}

	public String getShufflePath() {
		Random shuffle = new Random();
		int zufallsZahl = shuffle.nextInt(musicFileArray.length);
		System.out.println(zufallsZahl + ": "
				+ musicFileArray[zufallsZahl].toURI().toASCIIString());
		return musicFileArray[zufallsZahl].toURI().toASCIIString();
	}

	public int getButtonArt() {
		return buttonArt;
	}
}
