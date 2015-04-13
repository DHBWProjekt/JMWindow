package data;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class MusicTable extends JTable {

	// private JMPlayer myPlayer; // Tabelle wird mit dem Player verknüpft

	public MusicTable(DefaultTableModel model) {
		super(model);
		// this.myPlayer = myPlayer;
	}

	// Methode um festzulegen welche Zellen bearbeitet werden können
	public boolean isCellEditable(int row, int col) {
		// if (col == 0 || col == 1 || col == 2) {
		// return true;
		// }
		return false;
	}
};
