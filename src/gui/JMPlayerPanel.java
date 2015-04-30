package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javafx.scene.media.MediaPlayer;

import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import lib.MusicFolderToPathList;
import listener.JMPActionListener;
import listener.JMPInternalFrameListener;
import listener.JMPMusicTableSelectionListener;
import data.MusicTable;

public class JMPlayerPanel extends JPanel {
	// Haupfenster wird angelegt und im Konstruktor übergeben, damit der
	// JMPlayerIF sein Hauptfenster kennt
	private Hauptfenster hf;
	// SelectionListener für Tabelle wird erstellt,
	// Objekt vom Typ JMPlayer wird übergeben, für den Zugriff
	private JMPMusicTableSelectionListener tsl = new JMPMusicTableSelectionListener(
			null);
	// ActionListener für Buttons und MenuItems wird erstellt,
	// Objekt vom Typ JMPlayer wird übergeben, für den Zugriff
	private JMPActionListener bal = new JMPActionListener(null);

	private String[] musicDataTitle = new String[] { "Pfad", "Pfad ASCII" };
	// MusicData wird erstellt und Klassenmethode aufgerufen um Ordner
	// auszulesen
	private String[][] musicData = MusicFolderToPathList.getMusicData();
	// Tablemodel wird erstellt um es in ein JTabel anzeigen zu lassen
	private DefaultTableModel pfadModel = new DefaultTableModel(musicData,
			musicDataTitle);

	// Tabelle wird erstellt und Daten werden als Tablemodel übergeben
	private MusicTable table = new MusicTable(pfadModel);

	// Alle Steuerelemente werden erstellt
	private JPanel northPanel = new JPanel(new GridLayout(2, 1));
	private JTextField tfAnzeige = new JTextField();
	private JPanel btnPanel = new JPanel(new FlowLayout());
	private JButton btnPlay = new JButton("Play");
	private JButton btnPause = new JButton("Pause");
	private JButton btnStopp = new JButton("Stopp");
	private JButton btnNext = new JButton("Next");
	private JButton btnBack = new JButton("Back");

	// Steuerelemente für Menüleiste
	private JMenuBar myMenu = new JMenuBar();
	private JMenu myReiter1 = new JMenu("Datei");
	private JMenuItem openFile = new JMenuItem("Ordner öffnen");

	// Konstruktor für JMPlayer,
	public JMPlayerPanel(Hauptfenster parent, String name, boolean resizable,
			boolean closable, boolean maximizable, boolean iconifiable) {
		// Superklasse ist JInternalFrame und Standardwerte werden übergeben
		// Hauptfenster übergibt beim Aufruf von JMPLayerIF sich selber, um die
		// Verknüpfung herzustellen und in hf reinzuschreiben
		hf = parent;
		// Buttons bekommen den Action listener bal zugewiesen
		btnPlay.addActionListener(bal);
		btnPause.addActionListener(bal);
		btnStopp.addActionListener(bal);
		btnNext.addActionListener(bal);
		btnBack.addActionListener(bal);

		// table bekommt den ListSelectionlistener zugewiesen
		table.getSelectionModel().addListSelectionListener(tsl);

		// Steuerelemente werde zu den Panels hinzugefügt
		btnPanel.add(btnPlay);
		btnPanel.add(btnPause);
		btnPanel.add(btnStopp);
		btnPanel.add(btnNext);
		btnPanel.add(btnBack);
		northPanel.add(tfAnzeige);
		northPanel.add(btnPanel);

		// MenuItem bekommen den Action listener bal zugewiesen
		openFile.addActionListener(bal);
		myReiter1.add(openFile);
		myMenu.add(myReiter1);
		// setJMenuBar(myMenu);

		JMPInternalFrameListener ifl = new JMPInternalFrameListener(null);

		// Layout von Contentpane wird gestetzt
		setLayout(new BorderLayout());
		add(northPanel, BorderLayout.NORTH);
		add(new JScrollPane(table), BorderLayout.CENTER);
		// pack();
		setVisible(true);
	}

	// Methode um die ausgelesenen Daten eines Musikordners zu übergeben
	public void setData(String[][] tableData) {
		musicData = tableData;
	}

	// Methode um Tabellendaten zu ändern, wird benötigt von JMPActionListener
	public void changeTable() {
		pfadModel = new DefaultTableModel(musicData, musicDataTitle);
		table.getSelectionModel().removeListSelectionListener(tsl);
		table.setModel(pfadModel);
		table.getSelectionModel().addListSelectionListener(tsl);
	}

	// Methode um den nächsten Song auszuwählen, abspielen wird durch die
	// Änderung
	// ausgelöst
	public void playNextSong() {
		int zeilenZeiger = table.getSelectedRow();
		table.setRowSelectionInterval(zeilenZeiger + 1, zeilenZeiger + 1);
	}

	// Methode um den vorherigen Song auszuwählen, abspielen wird durch die
	// Änderung ausgelöst
	public void playLastSong() {
		int zeilenZeiger = table.getSelectedRow();
		table.setRowSelectionInterval(zeilenZeiger - 1, zeilenZeiger - 1);
	}

	public JButton getBtnBack() {
		return btnBack;
	}

	public JButton getBtnPlay() {
		return btnPlay;
	}

	public JButton getBtnPause() {
		return btnPause;
	}

	public JButton getBtnStopp() {
		return btnStopp;
	}

	public JButton getBtnNext() {
		return btnNext;
	}

	public JMenuItem getMIOpenFile() {
		return openFile;
	}

	public MediaPlayer getMasterPlayer() {
		return hf.getMasterPlayer();
	}

	// Es wird immer der MasterPlayer vom Hauptfenster verwendet, erstellte
	// Player von Unterfenster werden durchgereicht
	public void setMasterPlayer(MediaPlayer childPlayer) {
		hf.setMasterPlayer(childPlayer);
	}

	// Methode um Anzeige auf aktuellen Song zu setzen
	public void setTfAnzeige() {
		tfAnzeige.setText(String.valueOf(table.getValueAt(
				table.getSelectedRow(), 0)));
	}

	// Methode um den aktuellen Pfadstring aus markierter Spalte zu bekommen
	public String getPathSelectedSong() {
		return String.valueOf(table.getValueAt(table.getSelectedRow(), 1));
	}
}
