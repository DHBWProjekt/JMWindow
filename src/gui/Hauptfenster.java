package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javafx.embed.swing.JFXPanel;
import javafx.scene.media.MediaPlayer;

import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import listener.HFActionListener;

public class Hauptfenster extends JFrame {

	// JDesktopPane kann JInternalFrames aufnehmen (Fenster im Fenster)
	JDesktopPane deskPane = new JDesktopPane();

	// Componente für Menübar werden erzeugt
	private JMenuBar myMenu = new JMenuBar();
	private JMenu myReiter1 = new JMenu("Datei");
	private JMenuItem openNewJMPlayer = new JMenuItem("JMPlayer hinzufügen");

	// Aktionlistener für Buttons und MenuItems wird erzeugt
	private HFActionListener hfa = new HFActionListener(this);

	// Wird für MediaPlayer vorausgesetzt
	private JFXPanel myJFXPanel = new JFXPanel();
	private MediaPlayer masterPlayer;

	// Notfall StoppButton wird erzeugt
	private JButton btnStopp = new JButton("Stopp");

	// JPanel für Button wird erzeugt
	private JPanel btnPanel = new JPanel(new FlowLayout());

	// Konstruktor für Haupfenster:
	public Hauptfenster() {
		// Programm wird beendet beim Schließen über den Fenster-Schließ-Button
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// ActionListener wird auf MenüItem und Button gelegt
		openNewJMPlayer.addActionListener(hfa);
		btnStopp.addActionListener(hfa);

		// Menübar wird dem Fenster hinzugefügt
		myReiter1.add(openNewJMPlayer);
		myMenu.add(myReiter1);
		setJMenuBar(myMenu);

		// Button wird hinzugefügt
		btnPanel.add(btnStopp);

		// Layout vom Hauptfenster wird festgelegt und Componente hinzugefügt
		setLayout(new BorderLayout());
		add(btnPanel, BorderLayout.NORTH);
		getContentPane().add(deskPane, BorderLayout.CENTER);
		// Größe vom Hauptfenster wird festgelegt
		setSize(1000, 700);
		setVisible(true);
	}

	// Main-Methode wird beim Programmstart ausgeführt
	public static void main(String[] args) {
		Hauptfenster hf = new Hauptfenster();
	}

	// Aktionlistener benötigt für Abgleich das Objekt vom MenüItem, um
	// festellen zu können ob es ausgelöst wurde
	public JMenuItem getMIOpenNewJMPlayer() {
		return openNewJMPlayer;
	}

	public JButton getBtnStopp() {
		return btnStopp;
	}

	// Methode, um untergeordnete JMPlayerIF zu ermöglichen den MasterPlayer zu
	// setzen
	public void setMasterPlayer(MediaPlayer childPlayer) {
		masterPlayer = childPlayer;
	}

	// Methode um Zugriff auf den MasterPlayer zu haben
	public MediaPlayer getMasterPlayer() {
		return masterPlayer;
	}

	// Methode damit der HFActionListener neue JMPlayer auf deskPane legen kann
	public JDesktopPane getDeskPane() {
		return deskPane;
	}
}
