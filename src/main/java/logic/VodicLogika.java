package logic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import vodic.VodiciGui;
import vodic.VodiciPridanie;
import vodic.VodiciZmena;
import database.TableManager;

public class VodicLogika {

	private VodiciGui vg = null;
	private VodiciPridanie vp = null;
	private VodiciZmena vz = null;
	private JTable table = null;
	private String lastUsedString = null;
	private TableManager tableManager;
	final String query = "SELECT * FROM vodic";

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public VodicLogika() throws SQLException {
		tableManager = TableManager.getSingletonObject();
		vg = new VodiciGui();
		table = vg.getTable();
		tableManager.update(table, query);
		table.setRowSelectionAllowed(true);
		table.setColumnSelectionAllowed(false);
		registerButtonsVodici();
		vg.getComboBox().setModel(
				new DefaultComboBoxModel(tableManager.columnNames));
	}

	private void registerButtonsVodiciZmena(final VodiciZmena vz) {
		vz.getBtnPrida().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String query;

				query = "UPDATE vodic SET vodic_meno = '"
						+ vz.getTextField().getText()
						+ "', "
						+ "vodic_priezvisko = '"
						+ vz.getTextField_1().getText()
						+ "', "
						+ "rodne_cislo =  "
						+ vz.getTextField_2().getText()
						+ " "
						+ "WHERE id_vodic = "
						+ ((Number) table.getValueAt(vg.getTable()
								.getSelectedRow(), 0)).intValue();

				System.out.println("Queryyyyyy: " + query);
				try {
					tableManager.insert(table, query);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				query = "SELECT * FROM vodic";
				System.out.println("Queryaaa: " + query);
				try {
					tableManager.update(table, query);
				} catch (SQLException e) {
					e.printStackTrace();
				}

				JOptionPane.showMessageDialog(vz.getPanel(),
						"Zamestnanec bol pridany.", "",
						JOptionPane.INFORMATION_MESSAGE);
				vz.getFrame().dispose();

			}
		});
	}

	private void registerButtonsVodiciPridanie(final VodiciPridanie vp) {
		vp.getBtnPrida().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String query = "INSERT INTO vodic (vodic_meno, vodic_priezvisko, rodne_cislo) VALUES ('"
						+ vp.getTextField().getText()
						+ "', '"
						+ vp.getTextField_1().getText()
						+ "', "
						+ vp.getTextField_2().getText() + ")";
				System.out.println("Queryyyyyy: " + query);
				try {
					tableManager.insert(table, query);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				query = "SELECT * FROM vodic";
				System.out.println("Queryaaa: " + query);
				try {
					tableManager.update(table, query);
				} catch (SQLException e) {
					e.printStackTrace();
				}

				JOptionPane.showMessageDialog(vp.getPanel(),
						"Zamestnanec bol pridany.", "",
						JOptionPane.INFORMATION_MESSAGE);
				vp.getFrame().dispose();

			}
		});
	}

	private void registerButtonsVodici() {
		vg.getBtnPridanieZamestnanca().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vp = new VodiciPridanie();
				registerButtonsVodiciPridanie(vp);
			}
		});

		vg.getBtnZmenaDetailov().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vz = new VodiciZmena(table, tableManager, ((Number) table
						.getValueAt(table.getSelectedRow(), 0)).intValue());
				registerButtonsVodiciZmena(vz);
				table.clearSelection();
			}
		});

		vg.getBtnPrepustenieZamestnanca().addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String delQuery = "DELETE FROM vodic where id_vodic = "
								+ ((Number) table.getValueAt(
										table.getSelectedRow(), 0)).intValue();
						try {
							tableManager.updateItem(table, delQuery);
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
						table.clearSelection();

						lastUsedString = "SELECT * FROM vodic";
						try {
							tableManager.update(table, lastUsedString);
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
						table.setRowSelectionAllowed(true);
						table.setColumnSelectionAllowed(false);
						JOptionPane.showMessageDialog(vg.getFrame()
								.getContentPane(), "Zaznam bol vymazany.", "",
								JOptionPane.INFORMATION_MESSAGE);
					}
				});

		vg.getBtnVyh().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String qr1 = null;
				if (vg.getComboBox().getSelectedIndex() != -1) {
					qr1 = " WHERE " + vg.getComboBox().getSelectedItem()
							+ " = '" + vg.getTextField().getText() + "'";
					try {
						lastUsedString = query + qr1;
						System.out.println(lastUsedString);
						tableManager.update(table, lastUsedString);
					} catch (SQLException e11) {
						e11.printStackTrace();
					}

					vg.getComboBox().setSelectedIndex(-1);
					vg.getTextField().setText("");
					vg.getTextField().setVisible(false);
				}
			}
		});

		vg.getBtnNewButton_1().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*
				 * final String query1 =
				 * "SELECT id_vodic, vodic_meno, vodic_priezvisko, count(id_nehoda) AS pocet_nehod FROM vodic vod "
				 * + "LEFT JOIN vyprava vy ON vod.id_vodic = vy.id_vodici " +
				 * "LEFT JOIN nehoda n ON (vy.id_vozidla = n.id_vozidla AND vy.datum_vyprava = n.datum_nehody) "
				 * + "GROUP BY id_vodic " + "ORDER BY pocet_nehod "; try {
				 * lastUsedString = query1 + "DESC";
				 * System.out.println(lastUsedString);
				 * tableManager.update(table, lastUsedString); } catch
				 * (SQLException e11) { e11.printStackTrace(); }
				 */

				tableManager.vodicVypisPodlaPoctuNehod(table);

			}

		});

		vg.getBtnNewButton_2().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*
				 * final String query1 =
				 * "SELECT id_vodic, vodic_meno, vodic_priezvisko, COALESCE(sum(skoda), 0) AS sposobene_skody FROM vodic vod "
				 * + "LEFT JOIN vyprava vy ON vod.id_vodic = vy.id_vodici " +
				 * "LEFT JOIN nehoda n ON (vy.id_vozidla = n.id_vozidla AND vy.datum_vyprava = n.datum_nehody) "
				 * + "GROUP BY id_vodic " + "ORDER BY sposobene_skody "; try {
				 * lastUsedString = query1 + "DESC";
				 * System.out.println(lastUsedString);
				 * tableManager.update(table, lastUsedString);
				 * table.setRowSelectionAllowed(true);
				 * table.setColumnSelectionAllowed(false); } catch (SQLException
				 * e11) { e11.printStackTrace(); }
				 */
				tableManager.vodicVypisPodlaSposobenejSkody(table);
			}

		});

		vg.getBtnNewButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lastUsedString = "SELECT datum_vyprava, cislo_linka, vozidlo_cislo FROM vyprava v "
						+ "JOIN linka l ON v.id_linky = l.id_linka "
						+ "JOIN vozidlo b ON b.id_vozidlo = v.id_vozidla "
						+ "WHERE id_vodici = "
						+ ((Number) table.getValueAt(table.getSelectedRow(), 0))
								.intValue();
				try {
					tableManager.update(table, lastUsedString);
					table.setRowSelectionAllowed(true);
					table.setColumnSelectionAllowed(false);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});

		vg.getBtnObnovi().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lastUsedString = query;
				System.out.println(lastUsedString);
				try {
					tableManager.update(table, lastUsedString);
					table.setRowSelectionAllowed(true);
					table.setColumnSelectionAllowed(false);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}

			}
		});

		table.getTableHeader().addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int col = table.columnAtPoint(e.getPoint());
				String name = table.getColumnName(col);
				System.out.println("Column index selected " + col + " " + name);
				lastUsedString = lastUsedString + " ORDER BY " + name;
				try {
					System.out.println(lastUsedString);
					tableManager.update(table, lastUsedString);
					table.setRowSelectionAllowed(true);
					table.setColumnSelectionAllowed(false);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});

	}

}
