package logic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.JTable;

import linka.LinkaGui;
import linka.LinkaPridanie;
import linka.LinkaZmena;
import database.TableManager;

public class LinkaLogika {

	private LinkaGui lg = null;
	private LinkaPridanie lp = null;
	private LinkaZmena lz = null;
	private JTable table = null;
	private TableManager tableManager;
	final String query = "SELECT * FROM linka";
	private String lastUsedString = query;

	public LinkaLogika() throws SQLException {
		tableManager = TableManager.getSingletonObject();
		lg = new LinkaGui();
		table = lg.getTable();
		tableManager.update(table, query);
		registerButtonsLinka();
	}

	private void registerButtonsPridanieLinky(final LinkaPridanie lp) {
		lp.getBtnPrida().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String query = "INSERT INTO linka (cislo_linka, konecna1, konecna2) VALUES ( "
						+ lp.getTextField().getText()
						+ ", '"
						+ lp.getTextField_1().getText()
						+ "', '"
						+ lp.getTextField_2().getText() + "')";
				// System.out.println("Queryyyyyy: " + query);
				try {
					tableManager.insert(table, query);
				} catch (SQLException e) {
					e.printStackTrace();
				}

				query = "SELECT * FROM linka";
				// System.out.println("Queryaaa: " + query);
				try {
					tableManager.update(table, query);
				} catch (SQLException e) {
					e.printStackTrace();
				}

				JOptionPane.showMessageDialog(lp.getPanel(),
						"Linka bola pridana.", "",
						JOptionPane.INFORMATION_MESSAGE);
				lp.getFrame().dispose();

			}
		});
	}

	private void registerButtonsLinkaZmena(final LinkaZmena lz) {
		lz.getBtnPrida().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String query = "UPDATE linka SET cislo_linka = "
						+ lz.getTextField().getText() + ", konecna1 = '"
						+ lz.getTextField_1().getText() + "', konecna2 =  '"
						+ lz.getTextField_2().getText() + "' WHERE id_linka = ";
				// System.out.println("Queryyyyyy: " + query);
				try {
					tableManager.updateItem(
							table,
							query
									+ ((Number) table.getValueAt(
											table.getSelectedRow(), 0))
											.intValue());
				} catch (SQLException e) {
					e.printStackTrace();
				}

				query = "SELECT * FROM linka";
				// System.out.println("Queryaaa: " + query);
				try {
					tableManager.update(table, query);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				table.clearSelection();
				JOptionPane.showMessageDialog(lz.getPanel(),
						"Linka bola upravena.", "",
						JOptionPane.INFORMATION_MESSAGE);
				lz.getFrame().dispose();

			}
		});

	}

	private void registerButtonsLinka() {
		lg.getBtnPridanieLinky().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lp = new LinkaPridanie();
				registerButtonsPridanieLinky(lp);
			}
		});

		lg.getBtnZmenaDetailov().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lz = new LinkaZmena();
				registerButtonsLinkaZmena(lz);

			}
		});

		lg.getBtnZrusenieLinky().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String delQuery = "DELETE FROM linka where id_linka = "
						+ ((Number) table.getValueAt(table.getSelectedRow(), 0))
								.intValue();
				try {
					tableManager.updateItem(table, delQuery);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				table.clearSelection();
				lastUsedString = "SELECT * FROM linka";
				try {
					tableManager.update(table, lastUsedString);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				table.setRowSelectionAllowed(true);
				table.setColumnSelectionAllowed(false);
				JOptionPane.showMessageDialog(lg.getFrame().getContentPane(),
						"Zaznam bol vymazany.", "",
						JOptionPane.INFORMATION_MESSAGE);
			}
		});

		lg.getTable().getTableHeader().addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

				// System.out.println("Column index selected " + col + " " +
				// name);
				lastUsedString = lastUsedString
						+ " ORDER BY "
						+ table.getColumnName(table.columnAtPoint(e.getPoint()));
				try {
					// System.out.println(lastUsedString);
					tableManager.update(table, lastUsedString);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});

	}
}