package linka;

import javax.swing.*;
import java.sql.SQLException;

public class LinkaGui {

	private JFrame frame;
	private JTable table;
	private JButton btnPridanieLinky;
	private JButton btnZmenaDetailov;
	private JButton btnZrusenieLinky;

	public LinkaGui() throws SQLException {
		initialize();
		frame.setVisible(true);
	}

	private void initialize() throws SQLException {
		frame = new JFrame();
		frame.setBounds(100, 100, 497, 356);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 461, 296);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 441, 172);
		panel.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);
		table.setRowSelectionAllowed(true);
		table.setColumnSelectionAllowed(false);
		table.setToolTipText("Pre usporiadaný výber kliknite na header.");

		btnPridanieLinky = new JButton("Pridanie linky");
		btnPridanieLinky.setBounds(10, 194, 180, 23);
		panel.add(btnPridanieLinky);

		btnZmenaDetailov = new JButton("Zmena detailov");
		btnZmenaDetailov.setBounds(10, 228, 180, 23);
		panel.add(btnZmenaDetailov);

		btnZrusenieLinky = new JButton("Zru\u0161enie linky");
		btnZrusenieLinky.setBounds(10, 262, 180, 23);
		panel.add(btnZrusenieLinky);
	}

	public JTable getTable() {
		return table;
	}

	public JButton getBtnZmenaDetailov() {
		return btnZmenaDetailov;
	}

	public JButton getBtnPridanieLinky() {
		return btnPridanieLinky;
	}

	public JButton getBtnZrusenieLinky() {
		return btnZrusenieLinky;
	}

	public JFrame getFrame() {
		return frame;
	}
}
