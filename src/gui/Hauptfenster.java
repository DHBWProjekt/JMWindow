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

	JDesktopPane deskPane = new JDesktopPane();
	private JMenuBar myMenu = new JMenuBar();
	private JMenu myReiter1 = new JMenu("Datei");
	private JMenuItem openNewJMPlayer = new JMenuItem("JMPlayer hinzufügen");
	private HFActionListener hfa = new HFActionListener(this);

	private JFXPanel myJFXPanel = new JFXPanel();
	private MediaPlayer masterPlayer;

	private JButton btnPlay = new JButton("Play");
	private JButton btnPause = new JButton("Pause");
	private JButton btnStopp = new JButton("Stopp");

	private JPanel btnPanel = new JPanel(new FlowLayout());

	public Hauptfenster() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		openNewJMPlayer.addActionListener(hfa);
		btnPlay.addActionListener(hfa);
		btnPause.addActionListener(hfa);
		btnStopp.addActionListener(hfa);

		myReiter1.add(openNewJMPlayer);
		myMenu.add(myReiter1);
		setJMenuBar(myMenu);

		btnPanel.add(btnPlay);
		btnPanel.add(btnPause);
		btnPanel.add(btnStopp);

		setLayout(new BorderLayout());
		add(btnPanel, BorderLayout.NORTH);
		getContentPane().add(deskPane, BorderLayout.CENTER);
		setSize(1000, 700);
		setVisible(true);
	}

	public JMenuItem getMIOpenNewJMPlayer() {
		return openNewJMPlayer;
	}

	public void setOpenNewJMPlayer(JMenuItem openNewJMPlayer) {
		this.openNewJMPlayer = openNewJMPlayer;
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

	public void setMasterPlayer(MediaPlayer childPlayer) {
		masterPlayer = childPlayer;
	}

	public MediaPlayer getMasterPlayer() {
		return masterPlayer;
	}

	public static void main(String[] args) {
		Hauptfenster hf = new Hauptfenster();
	}

	public JDesktopPane getDeskPane() {
		return deskPane;
	}
}
