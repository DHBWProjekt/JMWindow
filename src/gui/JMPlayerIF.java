package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import lib.MusicFolderToPathList;
import listener.JMPActionListener;
import data.MusicTable;

public class JMPlayerIF extends JInternalFrame {
	// SelectionListener wird erstellt, um in Tabelle angeklickte Titel zu
	// erkennen
	private Hauptfenster hf;
	private MusicTableSelectionListener tsl = new MusicTableSelectionListener();
	private JMPActionListener bal = new JMPActionListener(this);

	private String[] title2 = new String[] { "Pfad", "Pfad ASCII", "Dauer" };
	private String[][] data2 = MusicFolderToPathList.getMusicData(this);
	private DefaultTableModel pfadModel = new DefaultTableModel(data2, title2);

	// Tabelle wird erstellt und Daten werden übergeben
	private MusicTable table = new MusicTable(pfadModel);

	// Alle Steuerelemente werden erstellt
	private JPanel northPanel = new JPanel(new GridLayout(2, 1));
	private JTextField anzeige = new JTextField();
	private JPanel btnPanel = new JPanel(new FlowLayout());
	private JButton btnPlay = new JButton("Play");
	private JButton btnPause = new JButton("Pause");
	private JButton btnStopp = new JButton("Stopp");
	private JButton btnNext = new JButton("Next");
	private JButton btnBack = new JButton("Back");

	private JMenuBar myMenu = new JMenuBar();
	private JMenu myReiter1 = new JMenu("Datei");
	private JMenuItem openFile = new JMenuItem("Ordner öffnen");

	// Konstruktor für JMPlayer,
	public JMPlayerIF(Hauptfenster parent, String name, boolean resizable,
			boolean closable, boolean maximizable, boolean iconifiable) {
		super(name, resizable, closable, maximizable, iconifiable);
		hf = parent;
		// Fenster Titel wird gesetzt
		// Buttons bekommen den Action listener bal zugewiesen
		btnPlay.addActionListener(bal);
		btnPause.addActionListener(bal);
		btnStopp.addActionListener(bal);
		btnNext.addActionListener(bal);
		btnBack.addActionListener(bal);

		table.getSelectionModel().addListSelectionListener(tsl);

		btnPanel.add(btnPlay);
		btnPanel.add(btnPause);
		btnPanel.add(btnStopp);
		btnPanel.add(btnNext);
		btnPanel.add(btnBack);
		northPanel.add(anzeige);
		northPanel.add(btnPanel);
		// table bekommt den ListSelectionlistener zugewiesen

		openFile.addActionListener(bal);
		myReiter1.add(openFile);
		myMenu.add(myReiter1);
		setJMenuBar(myMenu);

		setLayout(new BorderLayout());
		add(northPanel, BorderLayout.NORTH);
		getContentPane().add(new JScrollPane(table), BorderLayout.CENTER);
		pack();
		setVisible(true);
	}

	public Object getMyFrame() {
		return this;
	}

	/*
	 * Listener der aktiviert wird, wenn eine Auswahl in der Tabelle über Maus
	 * oder Tastatur getroffen wird
	 */
	private class MusicTableSelectionListener implements ListSelectionListener {

		public void valueChanged(ListSelectionEvent e) {
			int selectedRow = table.getSelectedRow();
			String anzeigeText = "";
			// Schleifendurchlauf um alle Spalten der Tabelle auszulesen
			for (int i = 0; i < table.getColumnCount(); i++) {
				anzeigeText = anzeigeText
						+ String.valueOf(table.getValueAt(selectedRow, i) + " ");
			}
			anzeige.setText(anzeigeText);
			if (hf.getMasterPlayer() == null) {
			} else {
				hf.getMasterPlayer().stop();
				hf.getMasterPlayer().dispose();
				hf.setMasterPlayer(null);
			}
			hf.setMasterPlayer(new MediaPlayer(new Media(String.valueOf(table
					.getValueAt(selectedRow, 1)))));
			hf.getMasterPlayer().play();
			anzeige.setText(String.valueOf(hf.getMasterPlayer().getMedia()
					.getMetadata().get("title")));
		}
	}

	public void setData(String[][] tableData) {
		data2 = tableData;
	}

	public void changeTable() {
		pfadModel = new DefaultTableModel(data2, title2);
		table.getSelectionModel().removeListSelectionListener(tsl);
		table.setModel(pfadModel);
		table.getSelectionModel().addListSelectionListener(tsl);
	}

	public void playNextSong() {
		int zeilenZeiger = table.getSelectedRow();
		table.setRowSelectionInterval(zeilenZeiger + 1, zeilenZeiger + 1);
	}

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
}
